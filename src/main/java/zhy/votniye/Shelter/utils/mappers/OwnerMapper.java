package zhy.votniye.Shelter.utils.mappers;

import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.domain.Owner;

public class OwnerMapper {
    public static Owner toOwner(OwnerDTO ownerDTO) {
        if (ownerDTO == null) {
            throw new NullPointerException("Tried to map null to Owner");
        }

        Owner owner = new Owner();

        owner.setId(ownerDTO.getOwnerId());
        owner.setFirstName(ownerDTO.getFirstName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setMiddleName(ownerDTO.getMiddleName());
        owner.setTelegramChatId(ownerDTO.getTelegramChatId());
        owner.setTelegramHandle(ownerDTO.getTelegramHandle());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());
        owner.setEmail(ownerDTO.getEmail());
        owner.setAddress(ownerDTO.getAddress());
        owner.setComment(ownerDTO.getComment());
        if(ownerDTO.getStatus() != null) {
            owner.setStatus(ownerDTO.getStatus());
        }
        return owner;
    }

    public static OwnerDTO fromOwner(Owner owner) {
        if (owner == null) {
            throw new NullPointerException("Tried to map null to OwnerDTO");
        }

        OwnerDTO ownerDTO = new OwnerDTO();

        ownerDTO.setOwnerId(owner.getId());
        ownerDTO.setFirstName(owner.getFirstName());
        ownerDTO.setLastName(owner.getLastName());
        ownerDTO.setMiddleName(owner.getMiddleName());
        ownerDTO.setTelegramChatId(owner.getTelegramChatId());
        ownerDTO.setTelegramHandle(owner.getTelegramHandle());
        ownerDTO.setPhoneNumber(owner.getPhoneNumber());
        ownerDTO.setEmail(owner.getEmail());
        ownerDTO.setAddress(owner.getAddress());
        ownerDTO.setComment(owner.getComment());
        ownerDTO.setStatus(owner.getStatus());

        return ownerDTO;
    }
}
