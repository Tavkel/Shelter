package zhy.votniye.Shelter.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.repository.ReportRepository;
import zhy.votniye.Shelter.services.interfaces.OwnerService;
import zhy.votniye.Shelter.services.interfaces.PetService;
import zhy.votniye.Shelter.utils.mappers.OwnerMapper;
import zhy.votniye.Shelter.utils.mappers.PetMapper;
import zhy.votniye.Shelter.utils.mappers.ReportMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    ReportRepository reportRepository;

    @Autowired
    OwnerService ownerService;

    @Autowired
    PetService petService;

    @LocalServerPort
    int port;

    byte[] photo = new byte[1];

    OwnerDTO o = new OwnerDTO(0L,"dsagf","asfd","saf");

    ReportDTO r = new ReportDTO(0L, 1L, 1L, "qqq","dsafg","sfasf",
            LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

    PetDTO p = new PetDTO(0L,"f", true,"3w",3F, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),photo,
            null,"dsgf","dsf",null);

    @Test
    void create__returnStatus200AndReport() {
        long ownerId;
        long petId;
        try {
            var realOwner = ownerService.create(OwnerMapper.toOwner(o));
            var realPet = petService.create(PetMapper.toPet(p));
            ownerId = realOwner.getId();
            petId = realPet.getId();
        } catch (Exception e){
            ownerId = 1L;
            petId = 1L;
        }
        var expectedReport = new ReportDTO(1L, ownerId, petId, "qqq","dsafg","sfasf",
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        ResponseEntity<ReportDTO> reportDTOResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/report", r, ReportDTO.class);
        assertEquals(HttpStatus.OK, reportDTOResponseEntity.getStatusCode());
        assertEquals(expectedReport, reportDTOResponseEntity.getBody());
    }

    @Test
    void read_reportInDB_returnStatus200() {
        long ownerId;
        long petId;
        try {
            var realOwner = ownerService.create(OwnerMapper.toOwner(o));
            var realPet = petService.create(PetMapper.toPet(p));
            ownerId = realOwner.getId();
            petId = realPet.getId();
        } catch (Exception e){
            ownerId = 1L;
            petId = 1L;
        }
        var realReport = new ReportDTO(1L, ownerId, petId, "qqq","dsafg","sfasf",
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        var res = reportRepository.save(ReportMapper.toReport(realReport));

        ResponseEntity<ReportDTO> reportDTOResponseEntity =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/report/" + res.getId(), ReportDTO.class);

        assertEquals(HttpStatus.OK, reportDTOResponseEntity.getStatusCode());
        assertEquals(realReport, reportDTOResponseEntity.getBody());

    }

    @Test
    void update_reportInDB_returnStatus200() {
        long ownerId;
        long petId;
        try {
            var realOwner = ownerService.create(OwnerMapper.toOwner(o));
            var realPet = petService.create(PetMapper.toPet(p));
            ownerId = realOwner.getId();
            petId = realPet.getId();
        } catch (Exception e) {
            ownerId = 1L;
            petId = 1L;
        }
        var realReport = new ReportDTO(1L, ownerId, petId, "qqq",
                "dsafg", "sfasf",
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        var original = reportRepository.save(ReportMapper.toReport(realReport));

        var updated = new ReportDTO(original.getId(), ownerId,petId,"qqe","dsafg","sfasf",
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        ResponseEntity<ReportDTO> update = restTemplate.exchange(
                "http://localhost:" + port + "/report",
                HttpMethod.PUT, new HttpEntity<>(updated), ReportDTO.class);

        assertEquals(HttpStatus.OK, update.getStatusCode());
        assertEquals(updated, update.getBody());

    }

    @Test
    void delete_reportInDB_returnStatus200() {
        long ownerId;
        long petId;
        try {
            var realOwner = ownerService.create(OwnerMapper.toOwner(o));
            var realPet = petService.create(PetMapper.toPet(p));
            ownerId = realOwner.getId();
            petId = realPet.getId();
        } catch (Exception e){
            ownerId = 1L;
            petId = 1L;
        }
        var realReport = new ReportDTO(1L, ownerId, petId, "qqq","dsafg","sfasf",
                LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        var res = reportRepository.save(ReportMapper.toReport(realReport));

        ResponseEntity<ReportDTO> delete = restTemplate.exchange(
                "http://localhost:" + port + "/report/" + res.getId(),
                HttpMethod.DELETE, null, ReportDTO.class
        );

        assertEquals(HttpStatus.OK, delete.getStatusCode());
        assertEquals(ReportMapper.fromReport(res), delete.getBody());

    }

    @Test
    void readAllReportsByOwner_returnStatus200(){

    }
}
