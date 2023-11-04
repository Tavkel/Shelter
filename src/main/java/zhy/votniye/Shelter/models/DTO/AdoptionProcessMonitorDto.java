package zhy.votniye.Shelter.models.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class AdoptionProcessMonitorDto {
    private Long id;
    private Long ownerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime latestReport;
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getLatestReport() {
        return latestReport;
    }

    public void setLatestReport(LocalDateTime latestReport) {
        this.latestReport = latestReport;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdoptionProcessMonitorDto that = (AdoptionProcessMonitorDto) o;
        return isActive == that.isActive && Objects.equals(ownerId, that.ownerId) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate) && Objects.equals(latestReport, that.latestReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerId, startDate, endDate, latestReport, isActive);
    }

    @Override
    public String toString() {
        return "AdoptionProcessMonitorDto{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", latestReport=" + latestReport +
                ", isActive=" + isActive +
                '}';
    }
}
