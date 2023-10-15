package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.Owner;
import zhy.votniye.Shelter.models.Report;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Owner> findByOwnerId(long ownerId);
}
