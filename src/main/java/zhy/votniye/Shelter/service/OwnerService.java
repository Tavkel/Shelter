package zhy.votniye.Shelter.service;

import zhy.votniye.Shelter.models.Owner;

import java.util.List;

public interface OwnerService {
    Owner create(Owner owner);

    Owner read(Long id);

    Owner update(Long id);

    Owner delete(Long id);

    List<Owner> readAll();
}
