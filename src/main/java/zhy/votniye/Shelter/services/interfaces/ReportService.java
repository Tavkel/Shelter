package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.Report;

import java.util.List;

public interface ReportService {
    Report create(Report report);

    Report read(Long id);

    Report update(Report report);

    Report delete(Long id);

    List<Report> readAllReportsByOwner(long ownerId);
}
