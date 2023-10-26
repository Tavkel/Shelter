package zhy.votniye.Shelter.services.implementations;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zhy.votniye.Shelter.exceptions.PetAlreadyExistsException;
import zhy.votniye.Shelter.models.domain.Cat;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.repository.CatRepository;
import zhy.votniye.Shelter.repository.PetRepository;
import zhy.votniye.Shelter.utils.PhotoCompression;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class CatServiceImpl extends PetServiceImpl<Cat> {
    public CatServiceImpl(CatRepository catRepository, PhotoCompression photoCompression) {
        super(catRepository, photoCompression);
    }
}
