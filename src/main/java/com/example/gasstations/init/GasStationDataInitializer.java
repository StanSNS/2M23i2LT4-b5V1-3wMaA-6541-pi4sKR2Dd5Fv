package com.example.gasstations.init;

import com.example.gasstations.repository.GasStationEntityRepository;
import com.example.gasstations.domain.JSON.GasStationDataJSON;
import com.example.gasstations.domain.JSON.GasStationJSON;
import com.example.gasstations.domain.entity.GasStationEntity;
import com.example.gasstations.util.ValidationUtil;
import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import static com.example.gasstations.constants.GasStationConst.GAS_STATION_DATA_URL;

@Component
@RequiredArgsConstructor
public class GasStationDataInitializer {

    /**
     * initializing dependencies with lombok @RequiredArgsConstructor
     */
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final GasStationEntityRepository gasStationEntityRepository;
    private final ValidationUtil validationUtil;


    /**
     * Initializes gas station data by reading JSON from a specified URL, converting it into domain objects,
     * and saving valid gas stations to the database.

     * This method is annotated with {@code @PostConstruct}, ensuring it is executed after the bean is created.
     *
     * @throws IOException if there's an issue reading JSON from the URL.
     */
    @PostConstruct
    public void initializeGasStations() throws IOException {

        if (gasStationEntityRepository.count() == 0) {

            String jsonContent = readJsonFromUrl();
            GasStationDataJSON gasStationData = gson.fromJson(jsonContent, GasStationDataJSON.class);

            for (GasStationJSON station : gasStationData.getStations()) {
                if (validationUtil.isValid(station)) {
                    GasStationEntity map = modelMapper.map(station, GasStationEntity.class);

                    if (map != null && map.isOpen()) {
                        gasStationEntityRepository.save(map);
                    }
                }

            }

        }

    }

    /**
     * Reads JSON content from a specified URL and returns it as a string.
     *
     * @return JSON content as a string.
     * @throws IOException if there's an issue reading JSON from the URL.
     */
    private String readJsonFromUrl() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(GAS_STATION_DATA_URL).openStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }
}