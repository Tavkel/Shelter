package zhy.votniye.Shelter.models.DTO;

import java.util.Arrays;
import java.util.Objects;

public class AdoptionRequestDTO {

    private Long id;
    private Long ownerId;
    private Long petId;
    private byte[] photoAdoptionRequest;
    private String pathToFilePhotoAdoptionRequest;
    private String additionalInfo;
    private String status;


//    private status;


    public AdoptionRequestDTO(Long idAdoptionRequestDTO, Long ownerIdDTO,
                              Long petIdDTO, byte[] photoAdoptionRequest,
                              String pathToFilePhotoAdoptionRequest, String additionalInfo, String status) {
        this.id = idAdoptionRequestDTO;
        this.ownerId = ownerIdDTO;
        this.petId = petIdDTO;
        this.photoAdoptionRequest = photoAdoptionRequest;
        this.pathToFilePhotoAdoptionRequest = pathToFilePhotoAdoptionRequest;
        this.additionalInfo = additionalInfo;
        this.status = status;
    }

    public AdoptionRequestDTO(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long idAdoptionRequestDTO) {
        this.id = idAdoptionRequestDTO;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerIdDTO) {
        this.ownerId = ownerIdDTO;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petIdDTO) {
        this.petId = petIdDTO;
    }

    public byte[] getPhotoAdoptionRequest() {
        return photoAdoptionRequest;
    }

    public void setPhotoAdoptionRequest(byte[] photoAdoptionRequest) {
        this.photoAdoptionRequest = photoAdoptionRequest;
    }

    public String getPathToFilePhotoAdoptionRequest() {
        return pathToFilePhotoAdoptionRequest;
    }

    public void setPathToFilePhotoAdoptionRequest(String pathToFilePhotoAdoptionRequest) {
        this.pathToFilePhotoAdoptionRequest = pathToFilePhotoAdoptionRequest;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionRequestDTO that = (AdoptionRequestDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(ownerId, that.ownerId)
                && Objects.equals(petId, that.petId) && Arrays.equals(photoAdoptionRequest,
                that.photoAdoptionRequest) && Objects.equals(pathToFilePhotoAdoptionRequest,
                that.pathToFilePhotoAdoptionRequest) && Objects.equals(additionalInfo, that.additionalInfo)
                && Objects.equals(status,that.status);

    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, ownerId, petId, pathToFilePhotoAdoptionRequest, additionalInfo, status);
        result = 31 * result + Arrays.hashCode(photoAdoptionRequest);
        return result;
    }
}
