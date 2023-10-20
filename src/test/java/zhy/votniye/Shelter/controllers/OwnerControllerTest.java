package zhy.votniye.Shelter.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import zhy.votniye.Shelter.models.DTO.ContactDTO;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.Status;
import zhy.votniye.Shelter.repository.OwnerRepository;
import zhy.votniye.Shelter.repository.PetRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    ContactDTO c = new ContactDTO(2L,4257457435745L,
            "ohohohho","aaaaaa","kuku");
    OwnerDTO o = new OwnerDTO(1L,"ivan","ivanovich",
            "shulc",c, Status.OwnerStatus.AWAITING_CONFIRMATION);

    PetDTO p = new PetDTO(0L,"f","3w",3F, LocalDateTime.now(),photo,
            null,"dsgf","dsf",null);

    @Test
    void create__returnStatus200AndOwner() {
        ResponseEntity<OwnerDTO> ownerDTOResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/owner", o, OwnerDTO.class);
        assertEquals(HttpStatus.OK, ownerDTOResponseEntity.getStatusCode());
        assertEquals(o, ownerDTOResponseEntity.getBody());
    }
}

