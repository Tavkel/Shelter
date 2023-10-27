package zhy.votniye.Shelter.config;

import zhy.votniye.Shelter.models.enums.Status;

public class InfoStrings {
    public static String getGeneralInfo(Status.OwnerPreference ownerPreference) {
        return switch (ownerPreference) {
            case CAT -> CatShelterInfoProvider.getGeneralInfo();
            case DOG -> DogShelterInfoProvider.getGeneralInfo();
        };
    }

    public static String getContactsInfo(Status.OwnerPreference ownerPreference) {
        return switch (ownerPreference) {
            case CAT -> CatShelterInfoProvider.getContacts();
            case DOG -> DogShelterInfoProvider.getContactsInfo();
        };
    }

    public static String getEntryPermitInfo(Status.OwnerPreference ownerPreference) {
        return switch (ownerPreference) {
            case CAT -> CatShelterInfoProvider.getEntryPermitInfo();
            case DOG -> DogShelterInfoProvider.getEntryPermitInfo();
        };
    }

    public static String getRulesOnTerritoryInfo(Status.OwnerPreference ownerPreference) {
        return switch (ownerPreference) {
            case CAT -> CatShelterInfoProvider.getRulesOnTerritoryInfo();
            case DOG -> DogShelterInfoProvider.getRulesOnTerritoryInfo();
        };
    }

    public static String getInformationAboutTheAnimalDatingRule(Status.OwnerPreference ownerPreference) {
        return switch (ownerPreference) {
            case CAT -> CatShelterInfoProvider.getInformationAboutTheAnimalDatingRule();
            case DOG -> DogShelterInfoProvider.getInformationAboutTheAnimalDatingRule();
        };
    }


    public static class DogShelterInfoProvider {

        public static String getGeneralInfo() {
            return "General info on the DOG shelter. Address, working hours, etc";
        }

        public static String getContactsInfo() {
            return "DOG SHELTER Contacts, phones, tg, whatsapp, link to web";
        }

        public static String getEntryPermitInfo() {
            return "You need to request permit to enter DOG shelter's territory. " +
                    "You can do it by calling following number: +*(***)***-**-**";
        }

        public static String getRulesOnTerritoryInfo() {
            return "Keep quiet, no running, no smoking, etc, etc";
        }
        public static String getInformationAboutTheAnimalDatingRule(){
            return "";
        }
    }

    public static class CatShelterInfoProvider {

        public static String getGeneralInfo() {
            return "General info on the CAT shelter. Address, working hours, etc";
        }

        public static String getContacts() {
            return "CAT SHELTER Contacts, phones, tg, whatsapp, link to web";
        }

        public static String getEntryPermitInfo() {
            return "You need to request permit to enter CAT shelter's territory. " +
                    "You can do it by calling following number: +*(***)***-**-**";
        }

        public static String getRulesOnTerritoryInfo() {
            return "Keep quiet, no running, no smoking, etc, etc";
        }
        public static String getInformationAboutTheAnimalDatingRule(){
            return "";
        }
    }

    public static class BotButtonLabelProvider {
        public static String getCatShelterButtonLabel() {
            return "Cat shelter";
        }

        public static String getDogShelterButtonLabel() {
            return "Dog Shelter";
        }

        public static String getAboutShelterButtonLabel() {
            return "About shelter";
        }

        public static String getLeaveContactButtonLabel() {
            return "Leave contact";
        }

        public static String getSubmitReportButtonLabel() {
            return "Submit Report";
        }

        public static String getGeneralInfoButtonLabel() {
            return "General information";
        }

        public static String getContactsButtonLabel() {
            return "Contacts";
        }

        public static String getEntryPermitRequestButtonLabel() {
            return "Request permit to the territory";
        }

        public static String getRulesOnTerritoryButtonLabel() {
            return "Rules for visitors";
        }

        public static String getAboutAdoptionButtonLabel() {
            return "About adoption";
        }

        public static String getAboutGettingFamiliarWithAPetButtonLabel() {
            return "Meeting pet";
        }

        public static String getAboutRequiredDocumentsButtonLabel() {
            return "Documents";
        }

        public static String getAboutPetTransportationButtonLabel() {
            return "Transportation";
        }

        public static String getAboutLivingSpaceForYoungPetButtonLabel() {
            return "Accommodating youngling";
        }

        public static String getAboutLivingSpaceForAdultPetButtonLabel() {
            return "Accommodating adult";
        }

        public static String getAboutLivingSpaceForDisabledPetButtonLabel() {
            return "Accommodating disabled";
        }

        public static String getAboutRefusalButtonLabel() {
            return "Rejections";
        }

        public static String getCallVolunteerButtonLabel() {
            return "Call volunteer";
        }

        public static String getBackToMainButtonLabel() {
            return "<- Back <-";
        }
    }

    public static class BotMessageTextProvider {
        public static String getHelloMessageForNewUser() {
            return "Hello! This is SHELTER-NAME bot-assistant!\n" +
                    "Please choose one of our shelters";
        }

        public static String getHelloMessageForReturningUser() {
            return "Hello! This is SHELTER-NAME bot-assistant!\n" +
                    "Please tell what i can do for you, or if you don't want to interact with me " +
                    "I can call one of the leather bags.";
        }

        public static String getCallVolunteerMessage() {
            return "Called volunteer. Please await response, someone will reach out to you soon!";
        }

        public static String getAboutShelterMenuMessage() {
            return "What exactly I should tell you about our shelter?";
        }

        public static String getBackToMainMenuMessage() {
            return "Want something else?";
        }

        public static String getAboutAdoptionMenuMessage() {
            return "Please take time to familiarize yourself with following information:";
        }
    }
}
