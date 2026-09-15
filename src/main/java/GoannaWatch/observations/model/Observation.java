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


    /**
     * Constructs a new Observation with the specified account, location, animal, and date.
     *
     * @param observer   The account recording the observation.
     * @param location   The location of the observation.
     * @param animalSeen The animal observed.
     * @param observedAt The date of the observation.
     */
    public Observation(Account observer, String location, String animalSeen, LocalDate observedAt) {
        setObserver(observer);
        setLocation(location);
        setAnimalSeen(animalSeen);
        setObservedAt(observedAt);
    }

    /**
     * Returns the ID of the observation.
     *
     * @return The ID of the observation.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the observation.
     *
     * @param id The ID to set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the account associated with the observation.
     *
     * @return The account associated with the observation.
     */
    public Account getObserver() {
        return observer;
    }

    /**
     * Sets the account that recorded the observation.
     *
     * @param observer The account to be set.
     */
    public void setObserver(Account observer) {
        if (observer == null) {
            throw new InputMismatchException("Observation must be linked to an account.");
        }
        this.observer = observer;
    }

    /**
     * Returns the location that the animal was observed.
     *
     * @return The location that the animal was observed.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location that the animal was observed.
     *
     * @param location The location to be set.
     */
    public void setLocation(String location) {
        if (location == null || location.isBlank()) {
            throw new InputMismatchException("Location cannot be blank.");
        }
        this.location = location;
    }

    /**
     * Returns the animal observed.
     *
     * @return The animal observed.
     */
    public String getAnimalSeen() {
        return animalSeen;
    }

    /**
     * Sets the animal observed.
     *
     * @param animalSeen The animal to be set.
     */
    public void setAnimalSeen(String animalSeen) {
        if (animalSeen == null || animalSeen.isBlank()) {
            throw new InputMismatchException("Animal cannot be blank.");
        }
        this.animalSeen = animalSeen;
    }

    /**
     * Returns the date of the observation.
     *
     * @return The date of the observation.
     */
    public LocalDate getObservedAt() {
        return observedAt;
    }

    /**
     * Sets the date the record was observed.
     *
     * @param observedAt The date to be set.
     */
    public void setObservedAt(LocalDate observedAt) {
        if (observedAt == null) {
            throw new InputMismatchException("Observation date cannot be blank");
        }
        this.observedAt = observedAt;
    }
}
