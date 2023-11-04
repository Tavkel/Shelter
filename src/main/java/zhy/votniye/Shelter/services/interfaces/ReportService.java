package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.AdoptionProcessMonitor;
import zhy.votniye.Shelter.models.domain.Report;

import java.util.List;

public interface ReportService {
    Report createReport(Report report);

    Report read(Long id);

    Report update(Report report);

    Report delete(Long id);

    List<Report> readAllReportsByOwnerId(long ownerId);
    AdoptionProcessMonitor createMonitor(long ownerId);

    AdoptionProcessMonitor updateMonitor(AdoptionProcessMonitor monitor);

    List<AdoptionProcessMonitor> getActiveMonitors();
}
