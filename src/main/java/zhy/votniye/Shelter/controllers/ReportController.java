package zhy.votniye.Shelter.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.mapper.ReportMapper;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.services.interfaces.ReportService;

import java.util.Collection;

import static zhy.votniye.Shelter.mapper.ReportMapper.*;

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
        var report = toReport(reportDTO);

        return fromReport(reportService.create(report));
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
        return fromReport(reportService.read(reportId));
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
        var report = toReport(reportDTO);

        return fromReport(reportService.update(report));
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
        return fromReport(reportService.delete(reportId));
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
    @GetMapping("/owner")
    public ResponseEntity<Collection<ReportDTO>> readAllReportsByOwner(@RequestParam long ownerId) {

        var result = reportService.readAllReportsByOwner(ownerId).stream().map(ReportMapper::fromReport).toList();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
