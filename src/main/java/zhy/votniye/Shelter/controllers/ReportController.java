package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.models.domain.Report;
import zhy.votniye.Shelter.services.interfaces.ReportService;

import java.util.Collection;

import static zhy.votniye.Shelter.mapper.ReportMapper.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    public final ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService=reportService;
    }

    @PostMapping
    public ReportDTO create(@RequestBody ReportDTO reportDTO){
        var report = toReport(reportDTO);

        return fromReport(reportService.create(report));
    }

    @GetMapping("/{id}")
    public ReportDTO read(@PathVariable long reportId){
        return fromReport(reportService.read(reportId));
    }

    @PutMapping
    public ReportDTO update(@RequestBody ReportDTO reportDTO){
        var report = toReport(reportDTO);

        return fromReport(reportService.update(report));
    }

    @DeleteMapping("/{id}")
    public ReportDTO delete(@PathVariable long reportId){
        return fromReport(reportService.delete(reportId));
    }

    @GetMapping("/owner")
    public Collection<Report> readAllReportsByOwner(@RequestParam long ownerId){
        return reportService.readAllReportsByOwner(ownerId);
    }
}
