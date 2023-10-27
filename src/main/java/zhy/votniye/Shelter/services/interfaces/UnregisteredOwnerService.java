package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.UnregisteredOwner;

public interface UnregisteredOwnerService {

    void create(UnregisteredOwner unregisteredOwner);

    void read(long chatId);

    void update(UnregisteredOwner unregisteredOwner);

    void delete(long chatId);
}
