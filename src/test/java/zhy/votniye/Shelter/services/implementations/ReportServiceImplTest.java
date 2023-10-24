package zhy.votniye.Shelter.services.implementations;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zhy.votniye.Shelter.models.domain.Report;
import zhy.votniye.Shelter.repository.ReportRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReportServiceImplTest {
    @Mock
    ReportRepository reportRepository;
    @InjectMocks
    ReportServiceImpl out;
    Report report = new Report();
    List<Report> reports = List.of(report);

    @Test
    void create_addReportInRepository_returnedReport() {
        when(reportRepository.save(report)).thenReturn(report);
        Report result = out.create(report);
        assertEquals(report, result);

    }


    @Test
    void read_getReportFromBd_returnedReport() {
        when(reportRepository.findById(report.getId())).thenReturn(Optional.of(report));
        Report result = out.read(report.getId());
        assertEquals(report, result);
    }

    @Test
    void read_reportNotFound_returnedException() {
        when(reportRepository.findById(report.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(report.getId()));
        assertEquals("Report not found", exception.getMessage());
    }

    @Test
    void update_updateAndAddReportInBd_returnedReport() {
        when(reportRepository.findById(report.getId())).thenReturn(Optional.of(report));
        Report result = out.update(report);
        assertNotEquals(report, result);
    }

    @Test
    void update_reportNotFound_returnedException() {
        when(reportRepository.findById(report.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(report.getId()));
        assertEquals("Report not found", exception.getMessage());

    }

    @Test
    void delete_deleteReportFromBd_returnedReport() {
        when(reportRepository.findById(report.getId())).thenReturn(Optional.of(report));
        Report result = out.delete(report.getId());
        assertEquals(report, result);
    }

    @Test
    void delete_reportNotFound_returnedException() {
        when(reportRepository.findById(report.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(report.getId()));
        assertEquals("Report not found", exception.getMessage());

    }

    @Test
    void readAllReportsByOwner() {
        when(reportRepository.findByOwnerId(1L)).thenReturn(reports);
        List<Report> result = out.readAllReportsByOwner(1L);
        assertEquals(List.of(report), result);
    }
}
