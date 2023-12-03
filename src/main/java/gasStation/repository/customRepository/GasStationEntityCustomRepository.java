package gasStation.repository.customRepository;

public interface GasStationEntityCustomRepository {
    Double findMinValueByFuelType(String fuelType);
    Double findMaxValueByFuelType(String fuelType);
    Double findAvgValueByFuelType(String fuelType);
}