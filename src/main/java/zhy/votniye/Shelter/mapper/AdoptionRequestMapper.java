package zhy.votniye.Shelter.mapper;

import zhy.votniye.Shelter.models.domain.AdoptionRequest;
import zhy.votniye.Shelter.models.DTO.AdoptionRequestDTO;

public class AdoptionRequestMapper {
    public static AdoptionRequest toAdoptionRequest(AdoptionRequestDTO adoptionRequestDTO) {
        if (adoptionRequestDTO == null) {
            throw new NullPointerException("Tried to map null to AdoptionRequest");
        }

        AdoptionRequest adoptionRequest = new AdoptionRequest();

        adoptionRequest.setId(adoptionRequestDTO.getId());
        adoptionRequest.setOwnerID(adoptionRequestDTO.getOwnerId());
        adoptionRequest.setPetId(adoptionRequestDTO.getPetId());
        adoptionRequest.setPathToFile(adoptionRequestDTO.getPathToFilePhotoAdoptionRequest());
        adoptionRequest.setAdditionalInfo(adoptionRequestDTO.getAdditionalInfo());

        return adoptionRequest;
    }

    public static AdoptionRequestDTO fromAdoptionRequest(AdoptionRequest adoptionRequest) {
        if (adoptionRequest == null) {
            throw new NullPointerException("Tried to map null to AdoptionRequestDTO");
        }

        AdoptionRequestDTO adoptionRequestDTO = new AdoptionRequestDTO();

        adoptionRequestDTO.setId(adoptionRequest.getId());
        adoptionRequestDTO.setOwnerId(adoptionRequest.getOwnerID());
        adoptionRequestDTO.setPetId(adoptionRequest.getPetId());
        adoptionRequestDTO.setPathToFilePhotoAdoptionRequest(adoptionRequest.getPathToFile());
        adoptionRequestDTO.setAdditionalInfo(adoptionRequest.getAdditionalInfo());

        return adoptionRequestDTO;
    }
}
