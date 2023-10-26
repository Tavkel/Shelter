package zhy.votniye.Shelter.services.interfaces;

import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.models.domain.Pet;

import java.io.IOException;
import java.util.List;

public interface PetService<T extends Pet> {
    T create(T pet);

    T read(Long id);

    T update(T pet);

    T delete(Long id);

    List<T> readAll();

    List<T> readAllPagination(int pageNumber);

    void savePetPhoto(long id, MultipartFile file) throws IOException;
}