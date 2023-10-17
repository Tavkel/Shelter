package zhy.votniye.Shelter.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.models.domain.Pet;

import java.io.IOException;
import java.util.List;

public interface PetService {
    Pet create(Pet pet);

    Pet read(Long id);

    Pet update(Pet pet);

    Pet delete(Long id);

    List<Pet> readAll();

    List<Pet> readAllPagination(int pageNumber);
    Pet getPetPhotoPreview(long id);

    void savePetPhoto(long id, MultipartFile file) throws IOException;
}
