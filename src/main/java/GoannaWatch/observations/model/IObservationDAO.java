package GoannaWatch.observations.model;

import GoannaWatch.account.model.Account;

import java.util.List;


/**
 * Interface for the Observation Data Access Object that handles
 * the CRUD operations for the Observation class with the database.
 */
public interface IObservationDAO {

    /**
     * Adds a new observation to the database.
     *
     * @param observation The observation to add.
     */
    public void addObservation(Observation observation);

    /**
     * Updates an existing observation in the database.
     *
     * @param observation The observation to update.
     */
    public void updateObservation(Observation observation);

    /**
     * Deletes an observation from the database.
     *
     * @param observation The observation to delete.
     */
    public void deleteObservation(Observation observation);

    /**
     * Retrieves an observation from the database.
     *
     * @param id The id of the observation to retrieve.
     * @return The observation with the given id, or null if not found.
     */
    public Observation getObservation(int id);

    /**
     * Retrieves all GoanaWatch.observations from the database.
     *
     * @return A list of all GoanaWatch.observations in the database.
     */
    public List<Observation> getAllObservations();

    /**
     * Retrieves all GoanaWatch.observations from the database authored by the account.
     * @param account The author of the GoanaWatch.observations in the database.
     * @return A list of all GoanaWatch.observations in the database authored by the account.
     */
    public List<Observation> getObservationsByAccount(Account account);
}
