package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerService {
    Owner create(Owner owner);

    Owner read(Long id);

    Owner update(Owner owner);

    Owner delete(Long id);

    List<Owner> readAll();

    Optional<Owner> getByChatId(long chatId);
}
