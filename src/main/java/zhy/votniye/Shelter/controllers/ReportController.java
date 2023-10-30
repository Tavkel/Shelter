package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.controllers.interfaces.IReportController;
import zhy.votniye.Shelter.utils.mappers.ReportMapper;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.services.interfaces.ReportService;
import java.util.Collection;

@RestController
@RequestMapping("/report")
public class ReportController implements IReportController {

    public final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    @Override
    public ReportDTO create(@RequestBody ReportDTO reportDTO) {
        var report = ReportMapper.toReport(reportDTO);

        return ReportMapper.fromReport(reportService.create(report));
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

    //todo implement!
    @GetMapping("/owner")
    @Override
    public Collection<ReportDTO> readAllReportsByOwner(@RequestParam long ownerId) {

        return reportService.readAllReportsByOwner(ownerId).stream().map(ReportMapper::fromReport).toList();
    }
}
