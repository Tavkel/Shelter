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
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Cat cat;
    @ManyToOne
    @JoinColumn(name = "dog_id")
    private Dog dog;
    private Long fileSize;
    private String mediaType;
    private byte[] photo;
    @Column(name = "path_to_file")
    private String pathToFile;
    @Column(name = "feeding_report")
    private String feedingReport;
    @Column(name = "general_report")
    private String generalReport;
    @Column(name = "behavior_report")
    private String behaviorReport;
    private final LocalDateTime dateOfReport;
    @ManyToOne()
    @JoinColumn(name = "apm")
    private AdoptionProcessMonitor apm;

    public Report() {
        this.dateOfReport = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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

    public LocalDateTime getDateOfReport() {
        return dateOfReport;
    }

    public AdoptionProcessMonitor getApm() {
        return apm;
    }

    public void setApm(AdoptionProcessMonitor apm) {
        this.apm = apm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(owner, report.owner)
                && Objects.equals(fileSize, report.fileSize) && Objects.equals(mediaType, report.mediaType)
                && Arrays.equals(photo, report.photo) && Objects.equals(pathToFile, report.pathToFile)
                && Objects.equals(feedingReport, report.feedingReport)
                && Objects.equals(generalReport, report.generalReport)
                && Objects.equals(behaviorReport, report.behaviorReport)
                && Objects.equals(dateOfReport, report.dateOfReport);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(owner, fileSize, mediaType, pathToFile, feedingReport, generalReport,
                behaviorReport, dateOfReport);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}




