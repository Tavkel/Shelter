package zhy.votniye.Shelter.models;

import jakarta.persistence.*;

import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;
    private Float weight;
    private int age;

    private byte[] photo;
    @Column(name = "path_to_file")
    private String pathToFile;
    private String description;
    @Column(name = "special_needs")
    private String specialNeeds;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;


    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }


    public Pet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecialNeeds() {
        return specialNeeds;
    }

    public void setSpecialNeeds(String specialNeeds) {
        this.specialNeeds = specialNeeds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return age == pet.age && Objects.equals(name, pet.name) && Objects.equals(breed, pet.breed) && Objects.equals(weight, pet.weight) && Arrays.equals(photo, pet.photo) && Objects.equals(pathToFile, pet.pathToFile) && Objects.equals(description, pet.description) && Objects.equals(specialNeeds, pet.specialNeeds);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, breed, weight, age, pathToFile, description, specialNeeds);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }
}
