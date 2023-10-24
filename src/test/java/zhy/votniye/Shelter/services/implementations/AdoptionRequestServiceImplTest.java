package zhy.votniye.Shelter.services.implementations;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zhy.votniye.Shelter.exception.OwnerAlreadyExistsException;
import zhy.votniye.Shelter.models.domain.AdoptionRequest;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.repository.AdoptionRequestRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdoptionRequestServiceImplTest {
    @Mock
    AdoptionRequestRepository adoptionRequestRepository;
    @InjectMocks
    AdoptionRequestServiceImpl out;
    AdoptionRequest adoptionRequest = new AdoptionRequest();
    List<AdoptionRequest> adoptionRequests = List.of(adoptionRequest);
    @Test
    void create_addAdoptionRequestInRepository_returnedAdoptionRequest() {
        when(adoptionRequestRepository.save(adoptionRequest)).thenReturn(adoptionRequest);
        AdoptionRequest result = out.create(adoptionRequest);
        assertEquals(adoptionRequest, result);

    }


    @Test
    void read_getAdoptionRequestFromBd_returnedAdoptionRequest() {
        when(adoptionRequestRepository.findById(adoptionRequest.getId())).thenReturn(Optional.of(adoptionRequest));
        AdoptionRequest result = out.read(adoptionRequest.getId());
        assertEquals(adoptionRequest, result);
    }

    @Test
    void read_AdoptionRequestNotFound_returnedException() {
        when(adoptionRequestRepository.findById(adoptionRequest.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(adoptionRequest.getId()));
        assertEquals("AdoptionRequest not found", exception.getMessage());
    }

    @Test
    void update_updateAndAddAdoptionRequestInBd_returnedAdoptionRequest() {
        when(adoptionRequestRepository.findById(adoptionRequest.getId())).thenReturn(Optional.of(adoptionRequest));
        AdoptionRequest result = out.update(adoptionRequest);
        assertNotEquals(adoptionRequest, result);
    }

    @Test
    void update_adoptionRequestNotFound_returnedException() {
        when(adoptionRequestRepository.findById(adoptionRequest.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(adoptionRequest.getId()));
        assertEquals("AdoptionRequest not found", exception.getMessage());

    }

    @Test
    void delete_deleteAdoptionRequestFromBd_returnedAdoptionRequest() {
        when(adoptionRequestRepository.findById(adoptionRequest.getId())).thenReturn(Optional.of(adoptionRequest));
        AdoptionRequest result = out.delete(adoptionRequest.getId());
        assertEquals(adoptionRequest, result);
    }

    @Test
    void delete_adoptionRequestNotFound_returnedException() {
        when(adoptionRequestRepository.findById(adoptionRequest.getId())).thenReturn(Optional.empty());
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                () -> out.read(adoptionRequest.getId()));
        assertEquals("AdoptionRequest not found", exception.getMessage());

    }

    @Test
    void readAll__returnedAllAdoptionRequest() {
        when(adoptionRequestRepository.findAll()).thenReturn(adoptionRequests);
        List<AdoptionRequest> result = out.readAll();
        assertEquals(List.of(adoptionRequest), result);
    }
}
