package zhy.votniye.Shelter.service.interfaces;

import zhy.votniye.Shelter.models.Report;

import java.util.List;

public interface ReportService {
    Report create(Report report);

    Report read(Long id);

    Report update(Long id);

    Report delete(Long id);

    List<Report> readALl();
}
