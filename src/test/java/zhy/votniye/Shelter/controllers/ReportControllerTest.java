package zhy.votniye.Shelter.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.repository.PetRepository;
import zhy.votniye.Shelter.repository.ReportRepository;
import zhy.votniye.Shelter.utils.mappers.PetMapper;
import zhy.votniye.Shelter.utils.mappers.ReportMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ReportRepository reportRepository;

    @LocalServerPort
    int port;

    @AfterEach
    void afterEach() {
        reportRepository.deleteAll();
    }

    byte[] photo = new byte[1];

    OwnerDTO o = new OwnerDTO(0L,"dsagf","asfd","saf");

    ReportDTO r = new ReportDTO(0L,"qqq","dsafg","sfasf",
            LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

    PetDTO p = new PetDTO(0L,"f", true,"3w",3F, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),photo,
            null,"dsgf","dsf",null);

    @Test
    void create__returnStatus200AndReport() {
        ResponseEntity<ReportDTO> reportDTOResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/report", r, ReportDTO.class);
        assertEquals(HttpStatus.OK, reportDTOResponseEntity.getStatusCode());
        assertEquals(r, reportDTOResponseEntity.getBody());
    }

    @Test
    void read_reportInDB_returnStatus200() {
        var res = reportRepository.save(ReportMapper.toReport(r));
        ResponseEntity<ReportDTO> reportDTOResponseEntity =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/report/" + res.getId(), ReportDTO.class);

        assertEquals(HttpStatus.OK, reportDTOResponseEntity.getStatusCode());
        assertEquals(ReportMapper.fromReport(res), reportDTOResponseEntity.getBody());

    }

}
