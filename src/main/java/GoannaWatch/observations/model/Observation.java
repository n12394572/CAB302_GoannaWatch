package GoannaWatch.observations.model;

import GoannaWatch.account.model.Account;

import java.time.LocalDate;
import java.util.InputMismatchException;

public class Observation {

    private int id;
    private Account observer;
    private String location;
    private String animalSeen;
    private LocalDate observedAt;

    public Observation(Account observer, String location, String animalSeen, LocalDate observedAt){
        setObserver(observer);
        setLocation(location);
        setAnimalSeen(animalSeen);
        setObservedAt(observedAt);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getObserver() {
        return observer;
    }

    public void setObserver(Account observer) {
        if (observer == null){
            throw new InputMismatchException("Observation must be linked to an account.");
        }
        this.observer = observer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.isBlank()){
            throw new InputMismatchException("Location cannot be blank.");
        }
        this.location = location;
    }

    public String getAnimalSeen() {
        return animalSeen;
    }

    public void setAnimalSeen(String animalSeen) {
        if (animalSeen == null || animalSeen.isBlank()){
            throw new InputMismatchException("Animal cannot be blank.");
        }
        this.animalSeen = animalSeen;
    }

    public LocalDate getObservedAt() {
        return observedAt;
    }

    public void setObservedAt(LocalDate observedAt) {
        if (observedAt == null){
            throw new InputMismatchException("Observation date cannot be blank");
        }
        this.observedAt = observedAt;
    }
}
