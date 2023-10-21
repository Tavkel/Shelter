package zhy.votniye.Shelter.exceptions;

public class PetAlreadyExistsException extends RuntimeException{
    public PetAlreadyExistsException(String message) {
        super(message);
    }
}
