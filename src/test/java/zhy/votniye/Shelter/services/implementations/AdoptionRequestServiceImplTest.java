package zhy.votniye.Shelter.services.implementations;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zhy.votniye.Shelter.models.domain.AdoptionRequest;
import zhy.votniye.Shelter.repository.AdoptionRequestRepository;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AdoptionRequestServiceImplTest {
    @Mock
    AdoptionRequestRepository adoptionRequestRepository;
    @InjectMocks
    AdoptionRequestServiceImpl out;
    AdoptionRequest adoptionRequest = new AdoptionRequest();

}