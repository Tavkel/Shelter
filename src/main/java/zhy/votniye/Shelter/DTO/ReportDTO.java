package zhy.votniye.Shelter.DTO;

import java.util.Date;

public class ReportDTO {

    private Long reportIdDTO;
    private Long ownerIdDTO;
    private Long petIdDTO;
    private byte[] reportPhoto;
    private String pathToFileReportPhoto;
    private String feedingReport;
    private String generalReport;
    private String behaviorReport;
    private Date reportDate;

    public ReportDTO(Long reportIdDTO, Long ownerIdDTO, Long petIdDTO,
                     byte[] reportPhoto, String pathToFileReportPhoto, String feedingReport,
                     String generalReport, String behaviorReport, Date reportDate) {
        this.reportIdDTO = reportIdDTO;
        this.ownerIdDTO = ownerIdDTO;
        this.petIdDTO = petIdDTO;
        this.reportPhoto = reportPhoto;
        this.pathToFileReportPhoto = pathToFileReportPhoto;
        this.feedingReport = feedingReport;
        this.generalReport = generalReport;
        this.behaviorReport = behaviorReport;
        this.reportDate = reportDate;
    }

    public Long getReportIdDTO() {
        return reportIdDTO;
    }

    public void setReportIdDTO(Long reportIdDTO) {
        this.reportIdDTO = reportIdDTO;
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

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}
