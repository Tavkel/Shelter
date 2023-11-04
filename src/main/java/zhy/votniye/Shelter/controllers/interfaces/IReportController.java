package zhy.votniye.Shelter.controllers.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.models.DTO.AdoptionProcessMonitorDto;
import zhy.votniye.Shelter.models.DTO.ReportDTO;

import java.util.Collection;
import java.util.List;

public interface IReportController {
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
    @PostMapping("/{ownerId}")
    ReportDTO create(@RequestBody ReportDTO reportDTO, @PathVariable long ownerId);

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
    ReportDTO read(@PathVariable long reportId);

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
    ReportDTO update(@RequestBody ReportDTO reportDTO);

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
    ReportDTO delete(@PathVariable long reportId);

    @Operation(summary = "create monitor for owner", tags = "APM")
    @ApiResponse(responseCode = "200",
            description = "created monitor",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdoptionProcessMonitorDto.class)))
    @PostMapping("/monitor/{ownerId}")
    AdoptionProcessMonitorDto createAdoptionProcessMonitorDto(@PathVariable long ownerId);

    @Operation(summary = "edit monitor for owner", tags = "APM")
    @ApiResponse(responseCode = "200",
            description = "created monitor",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = AdoptionProcessMonitorDto.class)))
    @PutMapping("/monitor")
    AdoptionProcessMonitorDto updateAdoptionProcessMonitorDto(@RequestBody AdoptionProcessMonitorDto monitor);

    @Operation(summary = "get all active monitors", tags = "APM")
    @ApiResponse(
            responseCode = "200",
            description = "All active monitors",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = AdoptionProcessMonitorDto.class))))
    List<AdoptionProcessMonitorDto> getActiveMonitors();

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
    Collection<ReportDTO> readAllReportsByOwner(@RequestParam long ownerId);

    @Operation(summary = "Prolong monitoring period", tags = "APM")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    @PutMapping("/monitor/{ownerId}")
    AdoptionProcessMonitorDto extendMonitoringPeriod(@RequestParam int period, @PathVariable long ownerId);

    @Operation(summary = "Finalize owners monitor", tags = "APM")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    @PutMapping("/monitor/{ownerId}/finalize")
    String endTrialPeriod(@PathVariable long ownerId, @RequestParam boolean success);

    @Operation(summary = "Send warning to user", tags = "APM")
    @ApiResponse(responseCode = "200",
            content = @Content(mediaType = MediaType.TEXT_PLAIN_VALUE))
    @GetMapping("/monitor/{ownerId}/warn")
    String warnOwner(@PathVariable long ownerId, @RequestParam String text);
}
