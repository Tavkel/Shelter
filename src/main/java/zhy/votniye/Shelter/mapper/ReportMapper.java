package zhy.votniye.Shelter.mapper;

import zhy.votniye.Shelter.models.DTO.PetDTO;
import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.models.Pet;
import zhy.votniye.Shelter.models.Report;

public class ReportMapper {
    public Report toReport(ReportDTO reportDTO) {
        if (reportDTO == null) {
            return null;
        }

        Report report = new Report();


        report.setId(reportDTO.getReportId());
        report.setOwnerID(reportDTO.getOwnerId());
        report.setPetId(reportDTO.getPetId());
        report.setPathToFile(reportDTO.getPathToFileReportPhoto());
        report.setFeedingReport(reportDTO.getFeedingReport());
        report.setGeneralReport(reportDTO.getGeneralReport());
        report.setBehaviorReport(reportDTO.getBehaviorReport());
        report.setDate(reportDTO.getReportDate());

        return report;
    }

    public ReportDTO fromReport(Report report) {
        if (report == null) {
            return null;
        }

        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setReportId(report.getId());
        reportDTO.setOwnerId(report.getOwnerID());
        reportDTO.setPetId(report.getPetId());
        reportDTO.setPathToFileReportPhoto(report.getPathToFile());
        reportDTO.setFeedingReport(report.getFeedingReport());
        reportDTO.setGeneralReport(report.getGeneralReport());
        reportDTO.setBehaviorReport(report.getBehaviorReport());
        reportDTO.setReportDate(report.getDate());


        return reportDTO;
    }
}
