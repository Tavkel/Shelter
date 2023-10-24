package zhy.votniye.Shelter.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.utils.mappers.PetMapper;
import zhy.votniye.Shelter.models.DTO.ContactDTO;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.repository.PetRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
            "shulc");

    PetDTO p = new PetDTO(0L,"f", true,"3w",3F,
            LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES),photo,
            null,"dsgf","dsf",null);


    @Test
    void create__returnStatus200AndPet() {
        ResponseEntity<PetDTO> petResponseEntity =
                restTemplate.postForEntity(
                        "http://localhost:" + port + "/pet", p, PetDTO.class);
        assertEquals(HttpStatus.OK, petResponseEntity.getStatusCode());
        assertEquals(p, petResponseEntity.getBody());
    }

    @Test
    void read_petInDB_returnStatus200() {
        var res = petRepository.save(PetMapper.toPet(p));
        ResponseEntity<PetDTO> petResponseEntity =
                restTemplate.getForEntity(
                        "http://localhost:" + port + "/pet/" + res.getId(), PetDTO.class);

        assertEquals(HttpStatus.OK, petResponseEntity.getStatusCode());
        assertEquals(PetMapper.fromPet(res), petResponseEntity.getBody());

    }

    @Test
    void update_petInDB_returnStatus200AndPet() {
        var original = petRepository.save(PetMapper.toPet(p));

        var updated = new PetDTO(original.getId(),"y", true,"3w",3F, LocalDateTime.now(),photo,
                null,"dsgf","dsf",null);

        ResponseEntity<PetDTO> update = restTemplate.exchange(
                "http://localhost:" + port + "/pet",
                HttpMethod.PUT, new HttpEntity<>(updated), PetDTO.class);

        assertEquals(HttpStatus.OK, update.getStatusCode());
        assertEquals(updated, update.getBody());
    }

    @Test
    void delete_petInDB_returnStatus200() {

        var res = petRepository.save(PetMapper.toPet(p));



        ResponseEntity<PetDTO> delete = restTemplate.exchange(
                "http://localhost:" + port + "/pet/" + res.getId(),
                HttpMethod.DELETE, null, PetDTO.class
                );
        assertEquals(HttpStatus.OK, delete.getStatusCode());
        assertEquals(PetMapper.fromPet(res), delete.getBody());
    }

    @Test
    void readAll__returnStatus200AndStudentList() {
        var res = petRepository.save(PetMapper.toPet(p));

        ResponseEntity<List<PetDTO>> exchange = restTemplate.exchange(
                "http://localhost:" + port + "/pet" + res,
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        assertEquals(HttpStatus.OK, exchange.getStatusCode());
        assertEquals(List.of(PetMapper.fromPet(res)), exchange.getBody());
    }

    @Test
    void getPetPage_returnStatus200(){

    }

    @Test
    void uploadPetPhoto_returnStatus200() throws IOException {
        try (InputStream is = Files.newInputStream(Path.of( "./src/test/resources/test.jpg"));
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ) {
            bis.transferTo(baos);
            this.photo = baos.toByteArray();
        }
        MultipartFile file = new MockMultipartFile("filename.jpg",
                "filename.jpg", "jpg", photo);
        var res = petRepository.save(PetMapper.toPet(p));

        ClassLoader classLoader = getClass().getClassLoader();



        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<String, Object>();
        parameters.add("file", photo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> entity =
                new HttpEntity<MultiValueMap<String, Object>>(parameters, headers);


        ResponseEntity<String> exchange = restTemplate.exchange(
                "http://localhost:" + port + "/pet/" + res.getId() ,HttpMethod.POST,
                new HttpEntity<>(null),String.class);

        assertEquals(HttpStatus.OK,exchange.getStatusCode());






    }


}
