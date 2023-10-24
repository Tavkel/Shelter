package zhy.votniye.Shelter.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.utils.mappers.ReportMapper;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.services.interfaces.ReportService;
import java.util.Collection;

@RestController
@RequestMapping("/report")
public class ReportController {

    public final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @Operation(summary = "create report", tags = "Reports")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "created report",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Report exists",
                    content = @Content(mediaType = MediaType.TEXT_EVENT_STREAM_VALUE)
            )
    })
    @PostMapping
    public ReportDTO create(@RequestBody ReportDTO reportDTO) {
        var report = ReportMapper.toReport(reportDTO);

        return ReportMapper.fromReport(reportService.create(report));
    }

    @Operation(summary = "find report", tags = "Reports")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "find report",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Report not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @GetMapping("/{reportId}")
    public ReportDTO read(@PathVariable long reportId) {
        return ReportMapper.fromReport(reportService.read(reportId));
    }

    @Operation(summary = "update report", tags = "Reports")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "updated report",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Report not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @PutMapping
    public ReportDTO update(@RequestBody ReportDTO reportDTO) {
        var report = ReportMapper.toReport(reportDTO);

        return ReportMapper.fromReport(reportService.update(report));
    }

    @Operation(summary = "delete report", tags = "Reports")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "deleted report",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ReportDTO.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Report not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    @DeleteMapping("/{reportId}")
    public ReportDTO delete(@PathVariable long reportId) {
        return ReportMapper.fromReport(reportService.delete(reportId));
    }

    @Operation(summary = "read all reports by owner", tags = "Reports")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "find all reports by owner",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ReportDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Owner not found",
                    content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE)
            )
    })
    //not implemented!
    @GetMapping("/owner")
    public Collection<ReportDTO> readAllReportsByOwner(@RequestParam long ownerId) {

        return reportService.readAllReportsByOwner(ownerId).stream().map(ReportMapper::fromReport).toList();
    }
}
