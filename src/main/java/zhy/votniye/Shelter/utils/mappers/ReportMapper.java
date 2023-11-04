package zhy.votniye.Shelter.utils.mappers;

import zhy.votniye.Shelter.models.DTO.ReportDTO;
import zhy.votniye.Shelter.models.domain.Owner;
import zhy.votniye.Shelter.models.domain.Pet;
import zhy.votniye.Shelter.models.domain.Report;

public class ReportMapper<T extends Pet> {
    public static Report toReport(ReportDTO reportDTO) {
        if (reportDTO == null) {
            throw new NullPointerException("Tried to map null to Report");
        }

        Report report = new Report();
        Owner owner = new Owner(reportDTO.getOwnerId());

        report.setId(reportDTO.getReportId());
        report.setOwner(owner);
        report.setFeedingReport(reportDTO.getFeedingReport());
        report.setGeneralReport(reportDTO.getGeneralReport());
        report.setBehaviorReport(reportDTO.getBehaviorReport());

        return report;
    }

    public static ReportDTO fromReport(Report report) {
        if (report == null) {
            throw new NullPointerException("Tried to map null to ReportDTO");
        }

        ReportDTO reportDTO = new ReportDTO();

        reportDTO.setReportId(report.getId());
        reportDTO.setOwnerId(report.getOwner().getId());
        reportDTO.setMediaType(report.getMediaType());
        reportDTO.setReportPhoto(reportDTO.getReportPhoto());
        reportDTO.setFeedingReport(report.getFeedingReport());
        reportDTO.setGeneralReport(report.getGeneralReport());
        reportDTO.setBehaviorReport(report.getBehaviorReport());
        reportDTO.setReportDate(report.getDateOfReport());

        return reportDTO;
    }
}
