package zhy.votniye.Shelter.models.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
@Schema(title = "Report")
public class ReportDTO {

    private Long reportId;
    private Long ownerId;
    private Long petId;
    private byte[] reportPhoto;
    private String pathToFileReportPhoto;
    private String feedingReport;
    private String generalReport;
    private String behaviorReport;

    private LocalDateTime reportDate;

    public ReportDTO(Long reportId, Long ownerId, Long petId,
                     byte[] reportPhoto, String pathToFileReportPhoto, String feedingReport,
                     String generalReport, String behaviorReport, LocalDateTime reportDate) {
        this.reportId = reportId;
        this.ownerId = ownerId;
        this.petId = petId;
        this.reportPhoto = reportPhoto;
        this.pathToFileReportPhoto = pathToFileReportPhoto;
        this.feedingReport = feedingReport;
        this.generalReport = generalReport;
        this.behaviorReport = behaviorReport;
        this.reportDate = reportDate;
    }

    public ReportDTO(){

    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public byte[] getReportPhoto() {
        return reportPhoto;
    }

    public void setReportPhoto(byte[] reportPhoto) {
        this.reportPhoto = reportPhoto;
    }

    public String getPathToFileReportPhoto() {
        return pathToFileReportPhoto;
    }

    public void setPathToFileReportPhoto(String pathToFileReportPhoto) {
        this.pathToFileReportPhoto = pathToFileReportPhoto;
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

    public LocalDateTime getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDateTime reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDTO reportDTO = (ReportDTO) o;
        return Objects.equals(reportId, reportDTO.reportId) && Objects.equals(ownerId, reportDTO.ownerId)
                && Objects.equals(petId, reportDTO.petId) && Arrays.equals(reportPhoto, reportDTO.reportPhoto)
                && Objects.equals(pathToFileReportPhoto, reportDTO.pathToFileReportPhoto)
                && Objects.equals(feedingReport, reportDTO.feedingReport)
                && Objects.equals(generalReport, reportDTO.generalReport)
                && Objects.equals(behaviorReport, reportDTO.behaviorReport)
                && Objects.equals(reportDate, reportDTO.reportDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(reportId, ownerId, petId, pathToFileReportPhoto,
                feedingReport, generalReport, behaviorReport, reportDate);
        result = 31 * result + Arrays.hashCode(reportPhoto);
        return result;
    }
}
