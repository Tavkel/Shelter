package zhy.votniye.Shelter.utils.tgUtility;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;

import static zhy.votniye.Shelter.config.InfoStrings.BotButtonLabelProvider.*;

public enum TgButton {
    ABOUT_SHELTER_BUTTON(new InlineKeyboardButton(getAboutShelterButtonLabel())
            .callbackData("about")),
    LEAVE_CONTACT_BUTTON(new InlineKeyboardButton(getLeaveContactButtonLabel())
            .callbackData("leaveContact")),
    SUBMIT_REPORT_BUTTON(new InlineKeyboardButton(getSubmitReportButtonLabel())
            .callbackData("submitReport")),
    ABOUT_GENERAL_BUTTON(new InlineKeyboardButton(getGeneralInfoButtonLabel())
            .callbackData("aboutGeneral")),
    ABOUT_CONTACTS_BUTTON(new InlineKeyboardButton(getContactsButtonLabel())
            .callbackData("aboutContacts")),
    ABOUT_PERMIT_BUTTON(new InlineKeyboardButton(getEntryPermitRequestButtonLabel())
            .callbackData("aboutEntryPermit")),
    ABOUT_RULES_ON_TERRITORY(new InlineKeyboardButton(getRulesOnTerritoryButtonLabel())
            .callbackData("aboutRulesOnTerritory")),
    CALL_VOLUNTEER_BUTTON(new InlineKeyboardButton(getCallVolunteerButtonLabel())
            .callbackData("callVolunteer")),
    ABOUT_ADOPTION_BUTTON(new InlineKeyboardButton(getAboutAdoptionButtonLabel())
            .callbackData("aboutAdoption")),
    BACK_MAIN_BUTTON(new InlineKeyboardButton(getBackToMainButtonLabel())
            .callbackData("backToMain")),
    //todo change text placeholders,
    CAT_SHELTER_BUTTON(new InlineKeyboardButton(getCatShelterButtonLabel())
            .callbackData("chooseCat")),
    DOG_SHELTER_BUTTON(new InlineKeyboardButton(getDogShelterButtonLabel())
            .callbackData("chooseDog")),
    ABOUT_GETTING_FAMILIAR_WITH_A_PET_BUTTON(new InlineKeyboardButton(getAboutGettingFamiliarWithAPetButtonLabel())
            .callbackData("aboutGettingFamiliarWithAPet")),
    ABOUT_REQUIRED_DOCUMENTS_BUTTON(new InlineKeyboardButton(getAboutRequiredDocumentsButtonLabel())
            .callbackData("aboutRequiredDocuments")),
    ABOUT_PET_TRANSPORTATION_BUTTON(new InlineKeyboardButton(getAboutPetTransportationButtonLabel())
            .callbackData("aboutPetTransportation")),
    ABOUT_LIVING_SPACE_FOR_YOUNG_PET_BUTTON(new InlineKeyboardButton(getAboutLivingSpaceForYoungPetButtonLabel())
            .callbackData("aboutLivingSpaceForYoungPet")),
    ABOUT_LIVING_SPACE_FOR_PET_BUTTON(new InlineKeyboardButton(getAboutLivingSpaceForAdultPetButtonLabel())
            .callbackData("aboutLivingSpaceForPet")),
    ABOUT_LIVING_SPACE_FOR_DISABLED_PET_BUTTON(new InlineKeyboardButton(getAboutLivingSpaceForDisabledPetButtonLabel())
            .callbackData("aboutLivingSpaceForDisabledPet")),
    ABOUT_WHY_ADOPTION_REQUEST_MIGHT_BE_REFUSED_BUTTON(new InlineKeyboardButton(getAboutRefusalButtonLabel())
            .callbackData("aboutWhyAdoptionRequestMightBeRefused")),
    ABOUT_CYNOLOGIST_CONTACTS_BUTTON(new InlineKeyboardButton(getAboutCynologistContactsButtonLabel())
            .callbackData("aboutCynologistContacts")),
    ABOUT_CYNOLOGIST_ADVICES_BUTTON(new InlineKeyboardButton(getAboutCynologistAdviceButtonLabel())
            .callbackData("aboutCynologistAdvice"));
    private final InlineKeyboardButton button;

    TgButton(InlineKeyboardButton button) {
        this.button = button;
    }

    public InlineKeyboardButton getButton() {
        return this.button;
    }
    }
