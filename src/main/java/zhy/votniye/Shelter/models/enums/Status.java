package zhy.votniye.Shelter.models.enums;

public class Status {
    public enum OwnerStatus {
        AWAITING_CONFIRMATION,
        REGISTERED,
        ON_PROBATION_PERIOD
    }

    public enum PetStatus {
        AVAILABLE,
        ON_PROBATION,
        IN_FAMILY
    }

    public enum AdoptionRequestStatus {
        OPEN,
        TRIAL_PERIOD,
        FINISHED,
        CLOSED
    }
    public enum OwnerPreference{
        DOG,
        CAT
    }
}
