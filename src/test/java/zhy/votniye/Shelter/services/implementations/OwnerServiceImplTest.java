package zhy.votniye.Shelter.services.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zhy.votniye.Shelter.exceptions.OwnerAlreadyExistsException;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.models.enums.Status;
import zhy.votniye.Shelter.repository.OwnerRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerServiceImpl out;
    List<Owner> owners;
    Owner owner = new Owner(
            1L,
            "Челоек",
            "Человекович",
            "Человеков",
            Status.OwnerStatus.AWAITING_CONFIRMATION,
            null
    );

    Pet pet = new Pet(
            1L,
            "Вася",
            true,
            "Дворняга",
            13.75F,
            null,
            null,
            Status.PetStatus.AVAILABLE,
            "./src",
            "веселый",
            "здоровый",
            owner
    );


    @BeforeEach
    void setUp() {
        owners = List.of(owner);
    }

    @Test
    void create_addOwnerInRepository_returnedOwner() {
        when(ownerRepository.save(owner)).thenReturn(owner);
        Owner result = out.create(owner);
        assertEquals(owner, result);

    }

    @Test
    void create_OwnerAlreadyInBd_returnedException() {
        when(ownerRepository.findByFirstNameAndLastNameAndMiddleName(
                owner.getFirstName(),
                owner.getLastName(),
                owner.getMiddleName()

        )).thenReturn(Optional.of(owner));
        OwnerAlreadyExistsException exception = assertThrows(OwnerAlreadyExistsException.class,
                () -> out.create(owner));
        assertEquals("The database already has this owner", exception.getMessage());
    }

    @Test
    void read_getOwnerFromBd_returnedOwner() {
        when(ownerRepository.findById(owner.getId())).thenReturn(Optional.of(owner));
        Owner result = out.read(owner.getId());
        assertEquals(owner, result);
    }

    @Test
    void read_OwnerNotFound_returnedException() {
        when(ownerRepository.findById(owner.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(owner.getId()));
        assertEquals("Owner not found", exception.getMessage());
    }

    @Test
    void update_updateAndAddOwnerInBd_returnedOwner() {
        when(ownerRepository.findById(pet.getId())).thenReturn(Optional.of(owner));
        Owner result = out.update(owner);
        assertNotEquals(owner, result);
    }

    @Test
    void update_ownerNotFound_returnedException() {
        when(ownerRepository.findById(pet.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(owner.getId()));
        assertEquals("Owner not found", exception.getMessage());
    }

    @Test
    void delete_deleteOwnerFromBd_returnedOwner() {
        when(ownerRepository.findById(pet.getId())).thenReturn(Optional.of(owner));
        Owner result = out.delete(owner.getId());
        assertEquals(owner, result);
    }

    @Test
    void delete_ownerNotFound_returnedException() {
        when(ownerRepository.findById(pet.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(owner.getId()));
        assertEquals("Owner not found", exception.getMessage());

    }

    @Test
    void readAll__returnedAllOwner() {
        when(ownerRepository.findAll()).thenReturn(owners);
        List<Owner> result = out.readAll();
        assertEquals(List.of(owner), result);
    }
}