package zhy.votniye.Shelter.service.interfaces;

import zhy.votniye.Shelter.models.Owner;

import java.util.List;

public interface OwnerService {
    Owner create(Owner owner);

    Owner read(Long id);

    Owner update(Owner owner);

    Owner delete(Long id);

    List<Owner> readAll();
}
