package zhy.votniye.Shelter.models.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "reports")
public class Report {

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
    @Column(name = "feeding_report")
    private String feedingReport;
    @Column(name = "general_report")
    private String generalReport;
    @Column(name = "behavior_report")
    private String behaviorReport;
    private LocalDateTime date;

    public Report() {
        this.date = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
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

    public String getFeedingReport() {
        return feedingReport;
    }

    public void setFeedingReport(String feedingReport) {
        this.feedingReport = feedingReport;
    }

    public String getGeneralReport() {
        return generalReport;
    }

    public void setGeneralReport(String generalReport) {
        this.generalReport = generalReport;
    }

    public String getBehaviorReport() {
        return behaviorReport;
    }

    public void setBehaviorReport(String behaviorReport) {
        this.behaviorReport = behaviorReport;
    }

    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return ownerID == report.ownerID && petId == report.petId
                && Arrays.equals(photo, report.photo)
                && Objects.equals(pathToFile, report.pathToFile)
                && Objects.equals(feedingReport, report.feedingReport)
                && Objects.equals(generalReport, report.generalReport)
                && Objects.equals(behaviorReport, report.behaviorReport)
                && Objects.equals(date, report.date);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(ownerID, petId, pathToFile, feedingReport, generalReport, behaviorReport, date);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}




