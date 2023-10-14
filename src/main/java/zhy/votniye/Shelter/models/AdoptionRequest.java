package zhy.votniye.Shelter.models;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Arrays;
import java.util.Objects;

//@Entity
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long ownerID;
    private long petId;
    private byte[] photo;
    private String pathToFile;
    private String additionalInfo;
    private String status;

    public AdoptionRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        this.ownerID = ownerID;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdoptionRequest that)) return false;
        return ownerID == that.ownerID && petId == that.petId
                && Arrays.equals(photo, that.photo)
                && Objects.equals(pathToFile, that.pathToFile)
                && Objects.equals(additionalInfo, that.additionalInfo)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ownerID, petId, pathToFile, additionalInfo, status);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
