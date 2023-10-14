package zhy.votniye.Shelter.models.DTO;

import java.util.Arrays;
import java.util.Objects;

public class AdoptionRequestDTO {

    private Long id;
    private Long ownerId;
    private Long petId;
    private byte[] photoAdoptionRequestDTO;
    private String pathToFilePhotoAdoptionRequestDTO;
    private String additionalInfo;

//    private status;


    public AdoptionRequestDTO(Long idAdoptionRequestDTO, Long ownerIdDTO,
                              Long petIdDTO, byte[] photoAdoptionRequestDTO,
                              String pathToFilePhotoAdoptionRequestDTO, String additionalInfo) {
        this.id = idAdoptionRequestDTO;
        this.ownerId = ownerIdDTO;
        this.petId = petIdDTO;
        this.photoAdoptionRequestDTO = photoAdoptionRequestDTO;
        this.pathToFilePhotoAdoptionRequestDTO = pathToFilePhotoAdoptionRequestDTO;
        this.additionalInfo = additionalInfo;
    }

    public AdoptionRequestDTO(){

    }

    public Long getIdAdoptionRequestDTO() {
        return id;
    }

    public void setIdAdoptionRequestDTO(Long idAdoptionRequestDTO) {
        this.id = idAdoptionRequestDTO;
    }

    public Long getOwnerIdDTO() {
        return ownerId;
    }

    public void setOwnerIdDTO(Long ownerIdDTO) {
        this.ownerId = ownerIdDTO;
    }

    public Long getPetIdDTO() {
        return petId;
    }

    public void setPetIdDTO(Long petIdDTO) {
        this.petId = petIdDTO;
    }

    public byte[] getPhotoAdoptionRequestDTO() {
        return photoAdoptionRequestDTO;
    }

    public void setPhotoAdoptionRequestDTO(byte[] photoAdoptionRequestDTO) {
        this.photoAdoptionRequestDTO = photoAdoptionRequestDTO;
    }

    public String getPathToFilePhotoAdoptionRequestDTO() {
        return pathToFilePhotoAdoptionRequestDTO;
    }

    public void setPathToFilePhotoAdoptionRequestDTO(String pathToFilePhotoAdoptionRequestDTO) {
        this.pathToFilePhotoAdoptionRequestDTO = pathToFilePhotoAdoptionRequestDTO;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionRequestDTO that = (AdoptionRequestDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(ownerId, that.ownerId)
                && Objects.equals(petId, that.petId) && Arrays.equals(photoAdoptionRequestDTO,
                that.photoAdoptionRequestDTO) && Objects.equals(pathToFilePhotoAdoptionRequestDTO,
                that.pathToFilePhotoAdoptionRequestDTO) && Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, ownerId, petId, pathToFilePhotoAdoptionRequestDTO, additionalInfo);
        result = 31 * result + Arrays.hashCode(photoAdoptionRequestDTO);
        return result;
    }
}
