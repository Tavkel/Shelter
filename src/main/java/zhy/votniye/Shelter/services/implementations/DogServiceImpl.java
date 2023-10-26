package zhy.votniye.Shelter.services.implementations;

import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.domain.Dog;
import zhy.votniye.Shelter.repository.DogRepository;
import zhy.votniye.Shelter.utils.PhotoCompression;

@Service("DogServiceImpl")
public class DogServiceImpl extends PetServiceImpl<Dog> {
    public DogServiceImpl(DogRepository dogRepository, PhotoCompression photoCompression) {
        super(dogRepository, photoCompression);
    }
}
