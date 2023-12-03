package gasStation.repository.customRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public class GasStationEntityCustomRepositoryImpl implements GasStationEntityCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Double findMinValueByFuelType(String fuelType) {
        if (isValidFuelType(fuelType)) {
            String queryStr = "SELECT MIN(" + fuelType + ") FROM gas_stations";
            Query query = entityManager.createNativeQuery(queryStr);
            return (Double) query.getSingleResult();
        }
        return null;
    }

    @Override
    public Double findMaxValueByFuelType(String fuelType) {
        if (isValidFuelType(fuelType)) {
            String queryStr = "SELECT MAX(" + fuelType + ") FROM gas_stations";
            Query query = entityManager.createNativeQuery(queryStr);
            return (Double) query.getSingleResult();
        }
        return null;
    }

    @Override
    public Double findAvgValueByFuelType(String fuelType) {
        if (isValidFuelType(fuelType)) {
            String queryStr = "SELECT AVG(" + fuelType + ") FROM gas_stations";
            Query query = entityManager.createNativeQuery(queryStr);
            return (Double) query.getSingleResult();
        }
        return null;
    }


    private boolean isValidFuelType(String fuelType) {
        return Arrays.asList("diesel", "e5", "e10").contains(fuelType);
    }
}