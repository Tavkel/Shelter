package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.domain.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByOwnerId(long ownerId);
}
