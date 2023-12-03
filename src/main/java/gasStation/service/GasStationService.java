package gasStation.service;

import gasStation.domain.dto.GasStationDTO;
import gasStation.repository.customRepository.GasStationEntityCustomRepositoryImpl;
import gasStation.repository.GasStationEntityRepository;
import gasStation.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static gasStation.constants.GasStationConst.*;

@Service
@RequiredArgsConstructor
public class GasStationService {

    /**
     * initializing dependencies with lombok @RequiredArgsConstructor
     */
    private final GasStationEntityCustomRepositoryImpl gasStationEntityCustomRepository;
    private final GasStationEntityRepository gasStationEntityRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    /**
     * Finds the maximum value for a given fuel type.
     *
     * @param fuelType The type of fuel to find the maximum value for.
     * @return A formatted string containing the maximum value for the specified fuel type,
     *         or an error response if not found.
     */
    public String findMaxValueByTheFuelType(String fuelType) {
        Double maxValueByFuelType = gasStationEntityCustomRepository.findMaxValueByFuelType(fuelType);
        if (maxValueByFuelType == null) {
            return INVALID_FUEL_RESPONSE;
        }
        return String.format(MAX_VALUE_RESPONSE_TEMPLATE, fuelType.toUpperCase(), maxValueByFuelType);
    }


    /**
     * Finds the minimum value for a given fuel type.
     *
     * @param fuelType The type of fuel to find the minimum value for.
     * @return A formatted string containing the minimum value for the specified fuel type,
     *         or an error response if not found.
     */
    public String findMinValueByTheFuelType(String fuelType) {
        Double maxValueByFuelType = gasStationEntityCustomRepository.findMinValueByFuelType(fuelType);
        if (maxValueByFuelType == null) {
            return INVALID_FUEL_RESPONSE;
        }
        return String.format(MIN_VALUE_RESPONSE_TEMPLATE, fuelType.toUpperCase(), maxValueByFuelType);
    }


    /**
     * Finds the average value for a given fuel type.
     *
     * @param fuelType The type of fuel to find the average value for.
     * @return A formatted string containing the average value for the specified fuel type,
     *         or an error response if not found.
     */
    public String findAVGValueByTheFuelType(String fuelType) {
        Double maxValueByFuelType = gasStationEntityCustomRepository.findAvgValueByFuelType(fuelType);
        if (maxValueByFuelType == null) {
            return INVALID_FUEL_RESPONSE;
        }
        return String.format(AVG_VALUE_RESPONSE_TEMPLATE, fuelType.toUpperCase(), maxValueByFuelType);
    }


    /**
     * Gets a list of gas stations by name.
     *
     * @param name The name of the gas station to search for.
     * @return A list of GasStationDTOs for the specified gas station name,
     *         or an empty list if no matching entities are found.
     */
    public List<GasStationDTO> getAllByName(String name) {
        return gasStationEntityRepository
                .findAllByName(name).stream().map(gasStationEntity -> {
                    GasStationDTO map = modelMapper.map(gasStationEntity, GasStationDTO.class);
                    if (validationUtil.isValid(map)) {
                        return map;
                    }
                    return null;
                }).collect(Collectors.toList());
    }

}
