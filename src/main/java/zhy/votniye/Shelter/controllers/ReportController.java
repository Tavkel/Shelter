package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.controllers.interfaces.IReportController;
import zhy.votniye.Shelter.models.DTO.AdoptionProcessMonitorDto;
import zhy.votniye.Shelter.services.interfaces.tg.TgBotService;
import zhy.votniye.Shelter.utils.mappers.APMMapper;
import zhy.votniye.Shelter.utils.mappers.ReportMapper;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.services.interfaces.ReportService;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/report")
public class ReportController implements IReportController {

    private final ReportService reportService;

    private final TgBotService tgBotService;

    public ReportController(ReportService reportService, TgBotService tgBotService) {
        this.reportService = reportService;
        this.tgBotService = tgBotService;
    }

    @PostMapping("/{ownerId}")
    @Override
    public ReportDTO create(@RequestBody ReportDTO reportDTO, @PathVariable long ownerId) {
        var report = ReportMapper.toReport(reportDTO);

        return ReportMapper.fromReport(reportService.createReport(report));
    }

    @GetMapping("/{reportId}")
    @Override
    public ReportDTO read(@PathVariable long reportId) {
        return ReportMapper.fromReport(reportService.read(reportId));
    }

    @PutMapping
    @Override
    public ReportDTO update(@RequestBody ReportDTO reportDTO) {
        var report = ReportMapper.toReport(reportDTO);

        return ReportMapper.fromReport(reportService.update(report));
    }

    @DeleteMapping("/{reportId}")
    @Override
    public ReportDTO delete(@PathVariable long reportId) {
        return ReportMapper.fromReport(reportService.delete(reportId));
    }

    @PostMapping("/monitor/{ownerId}")
    @Override
    public AdoptionProcessMonitorDto createAdoptionProcessMonitorDto(@PathVariable long ownerId) {
        return APMMapper.fromApm(reportService.createMonitor(ownerId));
    }

    @Override
    @PutMapping("/monitor")
    public AdoptionProcessMonitorDto updateAdoptionProcessMonitorDto(@RequestBody AdoptionProcessMonitorDto monitor) {
        return APMMapper.fromApm(reportService.updateMonitor(APMMapper.toApm(monitor)));
    }

    @Override
    @GetMapping("/monitor/active")
    public List<AdoptionProcessMonitorDto> getActiveMonitors() {
        return reportService.getActiveMonitors().stream().map(APMMapper::fromApm).collect(Collectors.toList());
    }
  
    @GetMapping("/owner")
    @Override
    public Collection<ReportDTO> readAllReportsByOwner(@RequestParam long ownerId) {

        return reportService.readAllReportsByOwnerId(ownerId).stream().map(ReportMapper::fromReport).toList();
    }

    @PutMapping("/monitor/{ownerId}")
    @Override
    public AdoptionProcessMonitorDto extendMonitoringPeriod(@RequestParam int period, @PathVariable long ownerId) {
        if (period > 1) {
            return APMMapper.fromApm(reportService.extendMonitoringPeriod(period, ownerId));
        } else {
            throw new IllegalArgumentException("Period must be 1 or less.");
        }
    }

    @PutMapping("/monitor/{ownerId}/finalize")
    @Override
    public String endTrialPeriod(@PathVariable long ownerId, @RequestParam boolean success) {
        reportService.endTrialPeriod(ownerId, success);
        return "ok";
    }

    @Override
    @GetMapping("/monitor/{ownerId}/warn")
    public String warnOwner(@PathVariable long ownerId, @RequestParam String text) {
        if (tgBotService.sendWarning(ownerId, text)) {
            return "ok";
        } else {
            return "Failed to send message";
        }
    }
}
