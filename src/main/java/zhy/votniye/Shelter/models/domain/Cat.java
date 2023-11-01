package zhy.votniye.Shelter.models.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import zhy.votniye.Shelter.models.enums.Status;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cat extends Pet {
    public Cat(Long id, String name, Boolean isMale,
        String breed, Float weight,
        LocalDateTime dateOfBirth,
        byte[] photo, Status.PetStatus status,
        String pathToFile, String description,
        String specialNeeds, Owner owner){
        super(id, name, isMale, breed, weight, dateOfBirth, photo, status, pathToFile, description, specialNeeds, owner);
    }
    public Cat() {
        super();
    }
}
