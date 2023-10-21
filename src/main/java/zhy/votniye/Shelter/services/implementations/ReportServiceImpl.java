package zhy.votniye.Shelter.services.implementations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.domain.Report;
import zhy.votniye.Shelter.repository.ReportRepository;
import zhy.votniye.Shelter.services.interfaces.ReportService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;

    }

    /**
     * The method creates a report and save it in the database
     * The repository method is used for saving {@link ReportRepository#save(Object)}
     *
     * @param report the report being created
     * @return a saved report
     */
    @Override
    public Report create(Report report) {
        logger.debug("The create method was called with the data " + report);
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
        if (reportRepository.findById(report.getId()).isEmpty()) {
            throw new NoSuchElementException("Report not found");
        }
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
        return report.get();
    }

    /**
     * The method shows all the owners reports
     * * The repository method {@link ReportRepository#findByOwnerId(long)}
     * is used to search for owner reports
     *
     * @param ownerId
     * @return all owner reports
     */
    @Override
    public List<Report> readAllReportsByOwner(long ownerId) {
        logger.debug("The ReadAll method is called");
        return reportRepository.findByOwnerId(ownerId);
    }
}
