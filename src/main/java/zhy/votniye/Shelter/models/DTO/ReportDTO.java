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
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private String mediaType;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private byte[] reportPhoto;
    private String feedingReport;
    private String generalReport;
    private String behaviorReport;
    private LocalDateTime reportDate;

    public ReportDTO(Long reportId, Long ownerId,
                     Long petId, String feedingReport,
                     String generalReport, String behaviorReport,
                     LocalDateTime reportDate) {
        this.reportId = reportId;
        this.ownerId = ownerId;
        this.petId = petId;
        this.feedingReport = feedingReport;
        this.generalReport = generalReport;
        this.behaviorReport = behaviorReport;
        this.reportDate = reportDate;
    }

    public ReportDTO() {
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
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
        return
                Objects.equals(feedingReport, reportDTO.feedingReport)
                && Objects.equals(generalReport, reportDTO.generalReport)
                && Objects.equals(behaviorReport, reportDTO.behaviorReport)
                && Objects.equals(reportDate, reportDTO.reportDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedingReport, generalReport, behaviorReport, reportDate);
    }

    @Override
    public String toString() {
        return "ReportDTO{" +
                ", feedingReport='" + feedingReport + '\'' +
                ", generalReport='" + generalReport + '\'' +
                ", behaviorReport='" + behaviorReport + '\'' +
                ", reportDate=" + reportDate +
                '}';
    }
}
