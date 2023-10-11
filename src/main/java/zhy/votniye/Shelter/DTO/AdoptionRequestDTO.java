package zhy.votniye.Shelter.DTO;

public class AdoptionRequestDTO {

    private Long idAdoptionRequestDTO;
    private Long ownerIdDTO;
    private Long petIdDTO;
    private byte[] photoAdoptionRequestDTO;
    private String pathToFilePhotoAdoptionRequestDTO;
    private String additionalInfo;

//    private status;


    public AdoptionRequestDTO(Long idAdoptionRequestDTO, Long ownerIdDTO,
                              Long petIdDTO, byte[] photoAdoptionRequestDTO,
                              String pathToFilePhotoAdoptionRequestDTO, String additionalInfo) {
        this.idAdoptionRequestDTO = idAdoptionRequestDTO;
        this.ownerIdDTO = ownerIdDTO;
        this.petIdDTO = petIdDTO;
        this.photoAdoptionRequestDTO = photoAdoptionRequestDTO;
        this.pathToFilePhotoAdoptionRequestDTO = pathToFilePhotoAdoptionRequestDTO;
        this.additionalInfo = additionalInfo;
    }

    public Long getIdAdoptionRequestDTO() {
        return idAdoptionRequestDTO;
    }

    public void setIdAdoptionRequestDTO(Long idAdoptionRequestDTO) {
        this.idAdoptionRequestDTO = idAdoptionRequestDTO;
    }

    public Long getOwnerIdDTO() {
        return ownerIdDTO;
    }

    public void setOwnerIdDTO(Long ownerIdDTO) {
        this.ownerIdDTO = ownerIdDTO;
    }

    public Long getPetIdDTO() {
        return petIdDTO;
    }

    public void setPetIdDTO(Long petIdDTO) {
        this.petIdDTO = petIdDTO;
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
}
