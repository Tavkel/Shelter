package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.mapper.ReportMapper;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.models.Owner;
import zhy.votniye.Shelter.models.Report;

import java.util.Collection;

@RestController
@RequestMapping("/report")
public class ReportController {

    public final ReportService reportService;
    public final ReportMapper reportMapper;

    public ReportController(ReportService reportService, ReportMapper reportMapper){
        this.reportService=reportService;
        this.reportMapper=reportMapper;
    }

    @PostMapping
    public ReportDTO create(@RequestBody ReportDTO reportDTO){

        var report = reportMapper.toReport(reportDTO);

        return reportMapper.fromReport(reportService.create(report));
    }

    @GetMapping("/{id}")
    public ReportDTO read(@PathVariable Report reportId){

        return reportMapper.fromReport(reportService.read(reportId));
    }

    @PutMapping
    public ReportDTO update(@RequestBody ReportDTO reportDTO){

        var report = reportMapper.toReport(reportDTO);

        return reportMapper.fromReport(reportService.update(report));
    }

    @DeleteMapping("/{id}")
    public ReportDTO delete(@PathVariable long reportId){
        return reportMapper.fromReport(reportService.delete(reportId));
    }

    @GetMapping("/{id}")
    public OwnerDTO readAllReportsByOwner(@RequestParam long ownerId){
        return reportService.readAllReportsByOwner();
    }
}
