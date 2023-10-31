package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import zhy.votniye.Shelter.models.domain.AdoptionProcessMonitor;

import java.util.List;
import java.util.Optional;

public interface AdoptionProcessMonitorRepository extends JpaRepository<AdoptionProcessMonitor, Long> {
    Optional<AdoptionProcessMonitor> findByOwnerTelegramChatId(long chatId);
    @Query(value = "select m from AdoptionProcessMonitor m where m.isActive = true")
    List<AdoptionProcessMonitor> findAllActive();
}
