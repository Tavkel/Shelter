package zhy.votniye.Shelter.models;
import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "adoption_requests")
public class AdoptionRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "owner_id")
    private long ownerID;
    @Column(name = "pet_id")
    private long petId;
    private byte[] photo;
    @Column(name = "path_to_file")
    private String pathToFile;
    @Column(name = "additional_info")
    private String additionalInfo;

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





    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AdoptionRequest that)) return false;
        return ownerID == that.ownerID && petId == that.petId
                && Arrays.equals(photo, that.photo)
                && Objects.equals(pathToFile, that.pathToFile)
                && Objects.equals(additionalInfo, that.additionalInfo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ownerID, petId, pathToFile, additionalInfo);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
