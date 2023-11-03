package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.exceptions.MultiplePetsOnProbationNotAllowed;
import zhy.votniye.Shelter.models.domain.AdoptionProcessMonitor;
import zhy.votniye.Shelter.models.domain.Report;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.repository.AdoptionProcessMonitorRepository;
import zhy.votniye.Shelter.repository.ReportRepository;
import zhy.votniye.Shelter.services.interfaces.OwnerService;
import zhy.votniye.Shelter.services.interfaces.ReportService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final ReportRepository reportRepository;
    private final AdoptionProcessMonitorRepository monitorRepository;
    private final OwnerService ownerService;


    public ReportServiceImpl(ReportRepository reportRepository,
                             AdoptionProcessMonitorRepository monitorRepository,
                             OwnerService ownerService) {
        this.reportRepository = reportRepository;
        this.monitorRepository = monitorRepository;
        this.ownerService = ownerService;
    }

    /**
     * The method creates a report and save it in the database
     * The repository method is used for saving {@link ReportRepository#save(Object)}
     *
     * @param report the report being created
     * @return a saved report
     */
    @Override
    public Report createReport(Report report, long ownerId) {
        logger.debug("The create method was called with the data " + report);
        var owner = ownerService.read(ownerId);
        var apm = monitorRepository.findByOwnerTelegramChatId(owner.getTelegramChatId()).orElseThrow(() -> new NoSuchElementException("Report monitor not found"));
        apm.setLatestReport(report.getDateOfReport());
        report.setApm(apm);
        monitorRepository.save(apm);
        return reportRepository.save(report);
    }

    /**
     * Search for a report by ID in the database.
     * The repository method is used {@link ReportRepository#findById(Object)}
     *
     * @param id cannot be null
     * @return the founds report
     * @throws NoSuchElementException Report not found
     */
    @Override
    public Report read(Long id) {
        logger.debug("The read method was called with the data " + id);
        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
            throw new NoSuchElementException("Report not found");
        }
        return report.get();
    }

    /**
     * The method recreates the report by searching for an identifier in the database
     * To find a report, use the repository method {@link ReportRepository#findById(Object)}
     * To recreate a report, use the repository method {@link ReportRepository#save(Object)}
     *
     * @param report rewritable report
     * @return the founds report
     * @throws NoSuchElementException Report not found
     */
    @Override
    public Report update(Report report) {
        logger.debug("The update method was called with the data " + report);
        var existingReport = reportRepository.findById(report.getId()).orElseThrow(() -> new NoSuchElementException("Report not found"));
        report.setMediaType(existingReport.getMediaType());
        report.setFileSize(existingReport.getFileSize());
        report.setPathToFile(existingReport.getPathToFile());
        report.setPhoto(existingReport.getPhoto());
        return reportRepository.save(report);
    }

    /**
     * The method searches for the report ID in the database and deletes it.
     * To find a report, use the repository method {@link ReportRepository#findById(Object)}
     * To delete information, use the repository method {@link ReportRepository#delete(Object)}
     *
     * @param id - cannot be null
     * @return delete report
     * @throws NoSuchElementException Report not found
     */
    @Override
    public Report delete(Long id) {
        logger.debug("The delete method was called with the data " + id);
        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
            throw new NoSuchElementException("Report not found");
        }
        reportRepository.delete(report.get());
        return report.get();
    }

    /**
     * The method shows all the owners reports
     * * The repository method {@link AdoptionProcessMonitorRepository#findByOwnerTelegramChatId(long)}
     * is used to search for owner reports
     *
     * @param ownerId
     * @return all owner reports
     */
    @Override
    public List<Report> readAllReportsByOwnerId(long ownerId) {
        logger.debug("The ReadAll method is called");
        var owner = ownerService.read(ownerId);
        var monitor = monitorRepository.findByOwnerTelegramChatId(owner.getTelegramChatId())
                .orElseThrow(() -> new NoSuchElementException("Owner with provided chat id has no reports"));
        return monitor.getReports();
    }

    /**
     * The method search ownerId to check owner status,
     * then create new monitor, save in to base, set status and update owner.
     *
     * @param ownerId
     * @return monitor
     */
    @Override
    public AdoptionProcessMonitor createMonitor(long ownerId) {
        var owner = ownerService.read(ownerId);
        if (owner.getStatus() == Status.OwnerStatus.ON_PROBATION_PERIOD) {
            throw new MultiplePetsOnProbationNotAllowed("Owner already has a pet on probation period");
        }
        var monitor = new AdoptionProcessMonitor();
        monitor.setStartDate(LocalDate.now());
        monitor.setEndDate(LocalDate.now().plusDays(30));
        monitor.setActive(true);
        monitor.setOwner(owner);
        monitorRepository.save(monitor);
        owner.setStatus(Status.OwnerStatus.ON_PROBATION_PERIOD);
        ownerService.update(owner);
        return monitor;
    }

    /**
     * The method find monitor by id, update him and save in to base.
     *
     * @param monitor
     * @return update monitor
     */
    @Override
    public AdoptionProcessMonitor updateMonitor(AdoptionProcessMonitor monitor) {
        if (monitorRepository.findById(monitor.getId()).isPresent()) {
            return monitorRepository.save(monitor);
        } else {
            throw new NoSuchElementException("Monitor not found");
        }
    }

    /**
     * The method get all active monitors in repository.
     *
     * @return all active Monitors
     */
    @Override
    public List<AdoptionProcessMonitor> getActiveMonitors() {
        return monitorRepository.findAllActive();
    }

    /**
     * This method get all active monitors and catch they end date,
     * if end date equals tomorrow date, send to volunteer then probation period is over soon.
     */
    @Override
    @Scheduled(cron = "0 21 * * * *")
    public void endDateTomorrow(){
        var activeMonitors = monitorRepository.findAllActive();
        var today = LocalDate.now();

        for(var monitor : activeMonitors){
            var endDate = monitor.getEndDate();

            if(today.plusDays(1L).equals(endDate)){
                //ДЛЯ НИНЫ(ТУТ ТИПО УВЕДОМЛЕНИЕ ВОЛОНТЕРА О СКОРОМ ЗАВЕРШЕНИИ ПРОБНОГО ПЕРИОДА)
            }
        }

    }

}
