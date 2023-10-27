package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.domain.Contact;

import java.util.Optional;
@Deprecated
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByPhoneAndTelegramChatId(long phone, long chatId);
}
