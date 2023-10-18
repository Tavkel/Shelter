package zhy.votniye.Shelter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import zhy.votniye.Shelter.models.DTO.ContactDTO;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.Status;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.repository.PetRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PetControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    PetRepository petRepository;

    @LocalServerPort
    int port;

    @AfterEach
    void afterEach() {
        petRepository.deleteAll();
    }
    byte[] photo = new byte[1];
    ContactDTO c = new ContactDTO(2L,4257457435745L,
            "ohohohho","aaaaaa","kuku");
    OwnerDTO o = new OwnerDTO(0L,"ivan","ivanovich",
            "shulc",c, Status.OwnerStatus.REGISTERED);
//    PetDTO petDTO = new PetDTO(1L,"lupa","pupa",20F,10,
//            photo, "./src","123","good",null);
    PetDTO p = new PetDTO(0L,"f","3w",3F, LocalDateTime.now(),photo,
            "dgf","dsgf","dsf",null);


    @Test
    void create__returnStatus200AndPet() {

        ResponseEntity<PetDTO> petResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/pet", p, PetDTO.class);
        assertEquals(HttpStatus.OK, petResponseEntity.getStatusCode());
        assertEquals(p, petResponseEntity.getBody());


    }
//
//    @Test
//    void read_petInDB_returnStatus200() {
//        petRepository.save(petDTO);
//        ResponseEntity<String> petResponseEntity =
//                restTemplate.getForEntity(
//                        "http://localhost:" + port + "/pet" + petDTO.getPetId(), String.class);
//
//        assertEquals(HttpStatus.OK, petResponseEntity.getStatusCode());
//
//    }
//
//    @Test
//    void update_petInDB_returnStatus200AndPet() {
//        Pet thisPet = petRepository.save(petDTO);
//        ResponseEntity<PetDTO> update = restTemplate.exchange(
//                "http://localhost:" + port + "/pet",
//                HttpMethod.PUT, new HttpEntity<>(thisPet), PetDTO.class);
//
//        assertEquals(HttpStatus.OK, update.getStatusCode());
//        assertEquals(thisPet, update.getBody());
//    }

//    @Test
//    void delete_petInDB_returnStatus404() {
//        ResponseEntity<String> delete = restTemplate.exchange(
//                "http://localhost:" + port + "/pet" + petDTO.getPetId(),
//                HttpMethod.DELETE, null, new ParameterizedTypeReference<>() {
//                });
//        assertEquals(HttpStatus.NOT_FOUND, delete.getStatusCode());
//    }



}
