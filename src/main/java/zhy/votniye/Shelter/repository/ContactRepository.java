package zhy.votniye.Shelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhy.votniye.Shelter.models.Contact;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByPhoneAndTelegramChatId(int phone, long chatId);
}
