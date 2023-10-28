package zhy.votniye.Shelter.services.interfaces;

import zhy.votniye.Shelter.models.domain.UnregisteredOwner;

public interface UnregisteredOwnerService {

     UnregisteredOwner create(UnregisteredOwner unregisteredOwner);

     UnregisteredOwner read(long chatId);

     UnregisteredOwner update(UnregisteredOwner unregisteredOwner);

     UnregisteredOwner delete(long chatId);
}
