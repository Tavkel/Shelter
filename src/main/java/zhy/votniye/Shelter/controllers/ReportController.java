package zhy.votniye.Shelter.controllers;

import org.springframework.web.bind.annotation.*;
import zhy.votniye.Shelter.models.Owner;

import java.util.Collection;

@RestController
@RequestMapping("/report")
public class ReportController {

    public final ReportService reportService;

    private final int maxFileSizeInKb = 300;

    public ReportController(ReportService reportService){
        this.reportService=reportService;
    }

    @PostMapping
    public Report create(@RequestBody Report report){
        return reportService.create(report);
    }

    @GetMapping("/{id}")
    public Report read(@PathVariable Report report){
        return reportService.read(id);
    }

    @PutMapping
    public Report update(@RequestBody Report report){
        return reportService.update(report);
    }

    @DeleteMapping("/{id}")
    public Report delete(@PathVariable long id){
        return reportService.delete(id);
    }

    @GetMapping
    public Collection<Owner> readAllReportsByOwner(){
        return reportService.readAllReportsByOwner();
    }
}
