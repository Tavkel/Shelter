package zhy.votniye.Shelter.controllers;

import org.junit.jupiter.api.AfterEach;
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
import zhy.votniye.Shelter.models.DTO.ContactDTO;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.repository.OwnerRepository;
import zhy.votniye.Shelter.utils.mappers.OwnerMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OwnerControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    OwnerRepository ownerRepository;

    @LocalServerPort
    int port;

    @AfterEach
    void afterEach() {
        ownerRepository.deleteAll();
    }

    byte[] photo = new byte[1];
    ContactDTO c = new ContactDTO(2L, 4257457435745L,
            "ohohohho", "aaaaaa", "kuku");
    OwnerDTO o = new OwnerDTO(0L, "ivan", "ivanovich",
            "shulc");


    PetDTO p = new PetDTO(0L, "f", true, "3w", 3F, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), photo,
            null, "dsgf", "dsf", null);

    @Test
    void create__returnStatus200AndOwner() {
        ResponseEntity<OwnerDTO> ownerDTOResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/owner", o, OwnerDTO.class);
        assertEquals(HttpStatus.OK, ownerDTOResponseEntity.getStatusCode());
        assertEquals(o, ownerDTOResponseEntity.getBody());
    }

    @Test
    void read__returnStatus200AndOwner() {

        var res = ownerRepository.save(OwnerMapper.toOwner(o));

        ResponseEntity<OwnerDTO> ownerDTOResponseEntity =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/owner/" + res.getId(), OwnerDTO.class);
        assertEquals(HttpStatus.OK, ownerDTOResponseEntity.getStatusCode());
        assertEquals(OwnerMapper.fromOwner(res), ownerDTOResponseEntity.getBody());
    }

    @Test
    void update_ownerInDB_returnStatus200AndOwner() {
        var original = ownerRepository.save(OwnerMapper.toOwner(o));

        var updated = new OwnerDTO(original.getId(), "ivanus", "ivanovich",
                "shulc");

        ResponseEntity<OwnerDTO> update = restTemplate.exchange(
                "http://localhost:" + port + "/owner",
                HttpMethod.PUT, new HttpEntity<>(updated), OwnerDTO.class);

        assertEquals(HttpStatus.OK, update.getStatusCode());
        assertEquals(updated, update.getBody());
    }

    @Test
    void delete_ownerInDB_returnStatus200() {

        var res = ownerRepository.save(OwnerMapper.toOwner(o));


        ResponseEntity<OwnerDTO> delete = restTemplate.exchange(
                "http://localhost:" + port + "/owner/" + res.getId(),
                HttpMethod.DELETE, null, OwnerDTO.class
        );
        assertEquals(HttpStatus.OK, delete.getStatusCode());
        assertEquals(OwnerMapper.fromOwner(res), delete.getBody());
    }


}

