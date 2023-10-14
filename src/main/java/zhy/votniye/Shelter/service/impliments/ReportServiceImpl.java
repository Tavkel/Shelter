package zhy.votniye.Shelter.service.impliments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zhy.votniye.Shelter.exception.ReportAlreadyAddException;
import zhy.votniye.Shelter.models.Report;
import zhy.votniye.Shelter.models.Report;
import zhy.votniye.Shelter.repository.ReportRepository;
import zhy.votniye.Shelter.service.interfaces.ReportService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ReportServiceImpl implements ReportService {
    private final Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;

    }

    @Override
    public Report create(Report report) {
        logger.info("The create method was called with the data " + report);
        logger.info(report + " - added to the database");
        return reportRepository.save(report);
    }

    @Override
    public Report read(Long id) {
        logger.info("The read method was called with the data " + id);
        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
            throw new NoSuchElementException("Report not found");
        }
        logger.info("The read method returned the report from the database" + report.get());
        return report.get();
    }

    @Override
    public Report update(Report report) {
        logger.info("The update method was called with the data " + report);
        if (reportRepository.findById(report.getId()).isEmpty()) {
            throw new NoSuchElementException("Report not found");
        }
        logger.info("The update method returned the report from the database" + report);
        return reportRepository.save(report);
    }

    @Override
    public Report delete(Long id) {
        logger.info("The delete method was called with the data " + id);
        Optional<Report> report = reportRepository.findById(id);
        if (report.isEmpty()) {
            throw new NoSuchElementException("Report not found");
        }
        logger.info("The  method returned the report from the database" + report.get());
        return null;
    }

    @Override
    public List<Report> readAll() {
        logger.info("The ReadAll method is called");
        return reportRepository.findAll();
    }
}
