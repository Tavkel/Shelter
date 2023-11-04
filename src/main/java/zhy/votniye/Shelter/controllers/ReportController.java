package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.controllers.interfaces.IReportController;
import zhy.votniye.Shelter.models.domain.AdoptionProcessMonitor;
import zhy.votniye.Shelter.services.interfaces.tg.TgBotService;
import zhy.votniye.Shelter.utils.mappers.ReportMapper;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.services.interfaces.ReportService;
import java.util.Collection;
import java.util.List;

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
    public String createAdoptionProcessMonitor(@PathVariable long ownerId) {
        reportService.createMonitor(ownerId);
        return "OK";
    }

    @Override
    @PutMapping("/monitor")
    public AdoptionProcessMonitor updateAdoptionProcessMonitor(@RequestBody AdoptionProcessMonitor monitor) {
        return reportService.updateMonitor(monitor);
    }

    @Override
    @GetMapping("/monitor/active")
    public List<AdoptionProcessMonitor> getActiveMonitors() {
        return reportService.getActiveMonitors();
    }


    @GetMapping("/owner")
    @Override
    public Collection<ReportDTO> readAllReportsByOwner(@RequestParam long ownerId) {

        return reportService.readAllReportsByOwnerId(ownerId).stream().map(ReportMapper::fromReport).toList();
    }

    @PutMapping("/monitor/{ownerId}")
    @Override
    public AdoptionProcessMonitor extendMonitoringPeriod(@RequestParam int period, @PathVariable long ownerId) {
        if (period > 1) {
            return reportService.extendMonitoringPeriod(period, ownerId);
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

    @GetMapping("/monitor/{ownerId}/warn")
    public String warnOwner(@PathVariable long ownerId, @RequestParam String text) {
        if (tgBotService.sendWarning(ownerId, text)) {
            return "ok";
        } else {
            return "Failed to send message";
        }
    }
}
