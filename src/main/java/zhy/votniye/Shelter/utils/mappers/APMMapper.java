package zhy.votniye.Shelter.utils.mappers;

import zhy.votniye.Shelter.models.DTO.AdoptionProcessMonitorDto;
import zhy.votniye.Shelter.models.domain.AdoptionProcessMonitor;
import zhy.votniye.Shelter.models.domain.Owner;

public class APMMapper {
    public static AdoptionProcessMonitor toApm(AdoptionProcessMonitorDto dto) {
        if (dto == null){
            throw new NullPointerException("Tried to map null to APM");
        }
        AdoptionProcessMonitor domain = new AdoptionProcessMonitor();

        domain.setId(dto.getId());
        domain.setStartDate(dto.getStartDate());
        domain.setEndDate(dto.getEndDate());
        domain.setLatestReport(dto.getLatestReport());
        domain.setActive(dto.isActive());
        if (dto.getOwnerId() > 0) {
            domain.setOwner(new Owner(dto.getOwnerId()));
        }
        return domain;
    }

    public static AdoptionProcessMonitorDto fromApm(AdoptionProcessMonitor domain) {
        if (domain == null) {
            throw new NullPointerException("Tried to map null to APMDto");
        }

        AdoptionProcessMonitorDto dto = new AdoptionProcessMonitorDto();

        dto.setId(domain.getId());
        dto.setStartDate(domain.getStartDate());
        dto.setEndDate(domain.getEndDate());
        dto.setLatestReport(domain.getLatestReport());
        dto.setActive(domain.isActive());
        dto.setOwnerId(domain.getOwner().getId());

        return dto;
    }
}
