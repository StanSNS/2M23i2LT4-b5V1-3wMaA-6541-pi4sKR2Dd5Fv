package gasStation.controller;

import gasStation.domain.dto.GasStationDTO;
import gasStation.service.GasStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static gasStation.constants.GasStationConst.NO_ENTITY_BY_NAME_FOUND_TEMPLATE;

@Controller
@RequiredArgsConstructor
public class GasStationController {

    /**
     * initializing dependencies with lombok @RequiredArgsConstructor
     */
    private final GasStationService gasStationService;


    /**
     * Endpoint to get the maximum value for a given fuel type.
     *
     * @param fuelType The type of fuel to find the maximum value for.
     * @return ResponseEntity containing the maximum value or an error message.
     */
    @GetMapping("max")
    public ResponseEntity<String> getMax(@RequestParam String fuelType) {
        return new ResponseEntity<>(gasStationService.findMaxValueByTheFuelType(fuelType.toLowerCase()), HttpStatus.OK);
    }


    /**
     * Endpoint to get the minimum value for a given fuel type.
     *
     * @param fuelType The type of fuel to find the minimum value for.
     * @return ResponseEntity containing the minimum value or an error message.
     */
    @GetMapping("min")
    public ResponseEntity<String> getMin(@RequestParam String fuelType) {
        return new ResponseEntity<>(gasStationService.findMinValueByTheFuelType(fuelType.toLowerCase()), HttpStatus.OK);
    }


    /**
     * Endpoint to get the average value for a given fuel type.
     *
     * @param fuelType The type of fuel to find the average value for.
     * @return ResponseEntity containing the average value or an error message.
     */
    @GetMapping("avg")
    public ResponseEntity<String> getAvg(@RequestParam String fuelType) {
        return new ResponseEntity<>(gasStationService.findAVGValueByTheFuelType(fuelType.toLowerCase()), HttpStatus.OK);
    }


    /**
     * Endpoint to get all gas stations by name.
     *
     * @param name The name of the gas station to search for.
     * @return ResponseEntity containing a list of GasStationDTOs or an error message if not found.
     */
    @GetMapping("findByName")
    public ResponseEntity<?> getAllByName(@RequestParam String name) {
        List<GasStationDTO> allFoundByName = gasStationService.getAllByName(name);

        if (allFoundByName.isEmpty()) {
            return new ResponseEntity<>(String.format(NO_ENTITY_BY_NAME_FOUND_TEMPLATE, name), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(allFoundByName, HttpStatus.OK);
    }

}
