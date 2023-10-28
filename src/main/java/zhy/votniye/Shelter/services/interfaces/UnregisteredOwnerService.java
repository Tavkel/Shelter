package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.UnregisteredOwner;

import java.util.Optional;

public interface UnregisteredOwnerService {

    void create(UnregisteredOwner unregisteredOwner);

    Optional<UnregisteredOwner> read(long chatId);

    void update(UnregisteredOwner unregisteredOwner);

    void delete(long chatId);
}
