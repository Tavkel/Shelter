package zhy.votniye.Shelter.mapper;

import zhy.votniye.Shelter.models.AdoptionRequest;
import zhy.votniye.Shelter.models.Contact;
import zhy.votniye.Shelter.models.DTO.AdoptionRequestDTO;
import zhy.votniye.Shelter.models.DTO.ContactDTO;
import zhy.votniye.Shelter.models.DTO.OwnerDTO;
import zhy.votniye.Shelter.models.Owner;

public class AdoptionRequestMapper {
    public AdoptionRequest toAdoptionRequest(AdoptionRequestDTO adoptionRequestDTO) {
        if (adoptionRequestDTO == null) {
            return null;
        }

        AdoptionRequest adoptionRequest = new AdoptionRequest();


        adoptionRequest.setId(adoptionRequestDTO.getId());
        adoptionRequest.setOwnerID(adoptionRequestDTO.getOwnerId());
        adoptionRequest.setPetId(adoptionRequestDTO.getPetId());
        adoptionRequest.setPathToFile(adoptionRequestDTO.getPathToFilePhotoAdoptionRequest());
        adoptionRequest.setAdditionalInfo(adoptionRequestDTO.getAdditionalInfo());
        adoptionRequest.setStatus(adoptionRequestDTO.getStatus());


        return adoptionRequest;
    }

    public AdoptionRequestDTO fromAdoptionRequest(AdoptionRequest adoptionRequest) {
        if (adoptionRequest == null) {
            return null;
        }

        AdoptionRequestDTO adoptionRequestDTO = new AdoptionRequestDTO();

        adoptionRequestDTO.setId(adoptionRequest.getId());
        adoptionRequestDTO.setOwnerId(adoptionRequest.getOwnerID());
        adoptionRequestDTO.setPetId(adoptionRequest.getPetId());
        adoptionRequestDTO.setPathToFilePhotoAdoptionRequest(adoptionRequest.getPathToFile());
        adoptionRequestDTO.setAdditionalInfo(adoptionRequest.getAdditionalInfo());
        adoptionRequestDTO.setStatus(adoptionRequest.getStatus());

        return adoptionRequestDTO;
    }

}
