package zhy.votniye.Shelter.services.interfaces.tg;

import com.pengrad.telegrambot.model.Message;

public interface TgCallbackService {
    void callVolunteer(Message message);

    void about(Message message);

    void aboutGeneral(Message message);

    void aboutContacts(Message message);

    void aboutEntryPermit(Message message);

    void aboutRulesOnTerritory(Message message);

    void backToMain(Message message);

    void leaveContact(Message message);

    void submitReport(Message message);

    void aboutAdoption(Message message);

    void aboutGettingFamiliarWithAPet(Message message);

    void aboutRequiredDocuments(Message message);

    void aboutPetTransportation(Message message);

    void aboutLivingSpaceForYoungPet(Message message);

    void aboutLivingSpaceForPet(Message message);

    void aboutLivingSpaceForDisabledPet(Message message);

    void aboutWhyAdoptionRequestMightBeRefused(Message message);

    void chooseCat(Message message);

    void chooseDog(Message message);
}
