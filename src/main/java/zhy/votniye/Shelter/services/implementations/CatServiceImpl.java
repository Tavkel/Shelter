package zhy.votniye.Shelter.services.implementations;

import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.models.domain.Cat;
import zhy.votniye.Shelter.repository.CatRepository;
import zhy.votniye.Shelter.services.interfaces.OwnerService;
import zhy.votniye.Shelter.utils.PhotoCompression;

@Service
public class CatServiceImpl extends PetServiceImpl<Cat> {
    public CatServiceImpl(CatRepository catRepository,
                          PhotoCompression photoCompression,
                          OwnerService ownerService) {
        super(catRepository, photoCompression, ownerService);
    }
}
