package controller;

import gasStation.controller.GasStationController;
import gasStation.domain.dto.GasStationDTO;
import gasStation.service.GasStationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static gasStation.constants.GasStationConst.NO_ENTITY_BY_NAME_FOUND_TEMPLATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GasStationControllerTest {

    @Mock
    private GasStationService gasStationService;

    @InjectMocks
    private GasStationController gasStationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMax() {
        when(gasStationService.findMaxValueByTheFuelType(anyString())).thenReturn("10");

        ResponseEntity<String> responseEntity = gasStationController.getMax("gasoline");

        assertEquals("10", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(gasStationService, times(1)).findMaxValueByTheFuelType("gasoline");
    }

    @Test
    void testGetMin() {
        when(gasStationService.findMinValueByTheFuelType(anyString())).thenReturn("5");

        ResponseEntity<String> responseEntity = gasStationController.getMin("diesel");

        assertEquals("5", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(gasStationService, times(1)).findMinValueByTheFuelType("diesel");
    }

    @Test
    void testGetAvg() {
        when(gasStationService.findAVGValueByTheFuelType(anyString())).thenReturn("8");

        ResponseEntity<String> responseEntity = gasStationController.getAvg("petrol");

        assertEquals("8", responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(gasStationService, times(1)).findAVGValueByTheFuelType("petrol");
    }

    @Test
    void testGetAllByName() {
        String stationName = "Station A";
        List<GasStationDTO> gasStations = Collections.singletonList(new GasStationDTO());

        when(gasStationService.getAllByName(stationName)).thenReturn(gasStations);

        ResponseEntity<?> responseEntity = gasStationController.getAllByName(stationName);

        assertEquals(gasStations, responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(gasStationService, times(1)).getAllByName(stationName);
    }

    @Test
    void testGetAllByNameNotFound() {
        String stationName = "Nonexistent Station";
        List<GasStationDTO> emptyList = Collections.emptyList();

        when(gasStationService.getAllByName(stationName)).thenReturn(emptyList);

        ResponseEntity<?> responseEntity = gasStationController.getAllByName(stationName);

        assertEquals(String.format(NO_ENTITY_BY_NAME_FOUND_TEMPLATE, stationName), responseEntity.getBody());
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        verify(gasStationService, times(1)).getAllByName(stationName);
    }
}
