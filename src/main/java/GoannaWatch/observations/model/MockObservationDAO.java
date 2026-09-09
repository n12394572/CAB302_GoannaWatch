package GoannaWatch.observations.model;

import GoannaWatch.account.model.Account;

import java.util.ArrayList;
import java.util.List;

public class MockObservationDAO implements IObservationDAO{

    private static final ArrayList<Observation> observations = new ArrayList<>();
    private static int autoIncrementedId = 0;

    @Override
    public void addObservation(Observation observation) {
        observation.setId(autoIncrementedId);
        autoIncrementedId++;
        observations.add(observation);
    }

    @Override
    public void updateObservation(Observation observation) {
        for (int i = 0; i < observations.size(); i++) {
            if (observations.get(i).getId() == observation.getId()) {
                observations.set(i, observation);
                break;
            }
        }
    }

    @Override
    public void deleteObservation(Observation observation) {
        observations.remove(observation);
    }

    @Override
    public Observation getObservation(int id) {
        for (Observation observation : observations) {
            if (observation.getId() == id) {
                return observation;
            }
        }
        return null;
    }

    @Override
    public List<Observation> getAllObservations() {
        return new ArrayList<>(observations);
    }

    @Override
    public List<Observation> getObservationsByAccount(Account account) {
        List<Observation> result = new ArrayList<>();
        for (Observation observation : observations) {
            if (observation.getObserver().getId() == account.getId()) {
                result.add(observation);
            }
        }
        return result;
    }
}
