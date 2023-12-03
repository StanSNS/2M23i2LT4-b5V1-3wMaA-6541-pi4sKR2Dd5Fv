package service;

import gasStation.domain.dto.GasStationDTO;
import gasStation.domain.entity.GasStationEntity;
import gasStation.repository.customRepository.GasStationEntityCustomRepositoryImpl;
import gasStation.repository.GasStationEntityRepository;
import gasStation.service.GasStationService;
import gasStation.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static gasStation.constants.GasStationConst.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GasStationServiceTest {

    @Mock
    private GasStationEntityCustomRepositoryImpl gasStationEntityCustomRepository;

    @Mock
    private GasStationEntityRepository gasStationEntityRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private GasStationService gasStationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllByName() {
        String name = "ExampleName";

        GasStationEntity gasStationEntity = new GasStationEntity();
        gasStationEntity.setName(name);

        GasStationDTO gasStationDTO = new GasStationDTO();
        gasStationDTO.setName(name);

        when(gasStationEntityRepository.findAllByName(name)).thenReturn(Arrays.asList(gasStationEntity));
        when(modelMapper.map(gasStationEntity, GasStationDTO.class)).thenReturn(gasStationDTO);
        when(validationUtil.isValid(gasStationDTO)).thenReturn(true);

        List<GasStationDTO> result = gasStationService.getAllByName(name);

        assertNotNull(result);
        assertEquals(1, result.size());

        GasStationDTO resultDTO = result.get(0);
        assertEquals(name, resultDTO.getName());

        verify(gasStationEntityRepository, times(1)).findAllByName(name);
        verify(modelMapper, times(1)).map(gasStationEntity, GasStationDTO.class);
        verify(validationUtil, times(1)).isValid(gasStationDTO);
    }

    @Test
    void testFindMaxValueByTheFuelType() {
        when(gasStationEntityCustomRepository.findMaxValueByFuelType(anyString())).thenReturn(20.0);

        String result = gasStationService.findMaxValueByTheFuelType("gasoline");

        assertEquals(String.format(MAX_VALUE_RESPONSE_TEMPLATE, "GASOLINE", 20.0), result);
        verify(gasStationEntityCustomRepository, times(1)).findMaxValueByFuelType("gasoline");
    }

    @Test
    void testFindMinValueByTheFuelType() {
        when(gasStationEntityCustomRepository.findMinValueByFuelType(anyString())).thenReturn(5.0);

        String result = gasStationService.findMinValueByTheFuelType("diesel");

        assertEquals(String.format(MIN_VALUE_RESPONSE_TEMPLATE, "DIESEL", 5.0), result);
        verify(gasStationEntityCustomRepository, times(1)).findMinValueByFuelType("diesel");
    }

    @Test
    void testFindAVGValueByTheFuelType() {
        when(gasStationEntityCustomRepository.findAvgValueByFuelType(anyString())).thenReturn(15.0);

        String result = gasStationService.findAVGValueByTheFuelType("petrol");

        assertEquals(String.format(AVG_VALUE_RESPONSE_TEMPLATE, "PETROL", 15.0), result);
        verify(gasStationEntityCustomRepository, times(1)).findAvgValueByFuelType("petrol");
    }



    @Test
    void testGetAllByNameEmptyList() {
        when(gasStationEntityRepository.findAllByName(anyString())).thenReturn(Collections.emptyList());

        List<GasStationDTO> result = gasStationService.getAllByName("Nonexistent Station");

        assertEquals(0, result.size());
        verify(gasStationEntityRepository, times(1)).findAllByName("Nonexistent Station");
        verify(modelMapper, never()).map(any(), eq(GasStationDTO.class));
        verify(validationUtil, never()).isValid(any());
    }
}
