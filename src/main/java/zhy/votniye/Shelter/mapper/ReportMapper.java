package zhy.votniye.Shelter.mapper;

import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.models.domain.Report;

public class ReportMapper {
    public static Report toReport(ReportDTO reportDTO) {
        if (reportDTO == null) {
            return null;
        }

        Report report = new Report();


        report.setId(reportDTO.getReportId());
        report.setOwnerId(reportDTO.getOwnerId());
        report.setPetId(reportDTO.getPetId());
        report.setPathToFile(reportDTO.getPathToFileReportPhoto());
        report.setFeedingReport(reportDTO.getFeedingReport());
        report.setGeneralReport(reportDTO.getGeneralReport());
        report.setBehaviorReport(reportDTO.getBehaviorReport());

        return report;
    }

    public static ReportDTO fromReport(Report report) {
        if (report == null) {
            return null;
        }

        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setReportId(report.getId());
        reportDTO.setOwnerId(report.getOwnerId());
        reportDTO.setPetId(report.getPetId());
        reportDTO.setPathToFileReportPhoto(report.getPathToFile());
        reportDTO.setFeedingReport(report.getFeedingReport());
        reportDTO.setGeneralReport(report.getGeneralReport());
        reportDTO.setBehaviorReport(report.getBehaviorReport());
        reportDTO.setReportDate(report.getDateOfReport());

        return reportDTO;
    }
}
