package zhy.votniye.Shelter.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.repository.PetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {
    private byte[] photo;
    @Mock
    PetRepository petRepository;
    @InjectMocks
    PetServiceImpl out;
    List<Pet> pets;

    @BeforeEach
    void setUp() {
        pets = new ArrayList<>(List.of(pet));
    }

    Owner owner = new Owner(
            1L,
            "Челоек",
            "Человекович",
            "Человеков",
            Status.OwnerStatus.AWAITING_CONFIRMATION,
            pets
    );

    Pet pet = new Pet(
            1L,
            "Вася",
            true,
            "Дворняга",
            13.75F,
            null,
            photo,
            Status.PetStatus.AVAILABLE,
            "./src",
            "веселый",
            "здоровый",
            owner
    );
    Pet petForUpdate = new Pet(
            1L,
            "Вася",
            true,
            "Дворняга",
            13.95F,
            null,
            photo,
            Status.PetStatus.AVAILABLE,
            "./src",
            "веселый",
            "здоровый",
            owner
    );

    @Test
    void create() {
        when(petRepository.save(pet)).thenReturn(pet);
        Pet result = out.create(pet);
        assertEquals(pet, result);

    }

    @Test
    void read() {
        when(petRepository.findById(pet.getId())).thenReturn(Optional.of(pet));
        Pet result = out.read(pet.getId());
        assertEquals(pet, result);
    }

    @Test
    void update() {
        when(petRepository.findById(pet.getId())).thenReturn(Optional.of(pet));
        Pet result = out.update(petForUpdate);
        assertNotEquals(pet, result);
    }

    @Test
    void delete() {
        when(petRepository.findById(pet.getId())).thenReturn(Optional.of(pet));
        Pet result = out.delete(pet.getId());
        assertEquals(null, result);
    }

    @Test
    void readAll() {
        when(petRepository.findAll()).thenReturn(pets);
        List<Pet> result = out.readAll();
        assertEquals(List.of(pet),result);
    }

    @Test
    void readAllPagination() {
        when(petRepository.findAll())

    }
}