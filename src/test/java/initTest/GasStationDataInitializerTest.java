package initTest;

import com.google.gson.Gson;
import gasStation.domain.JSON.GasStationJSON;
import gasStation.domain.JSON.GasStationWrapperJSON;
import gasStation.domain.entity.GasStationEntity;
import gasStation.init.GasStationDataInitializer;
import gasStation.repository.GasStationEntityRepository;
import gasStation.util.ValidationUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GasStationDataInitializerTest {

    @Mock
    private Gson gson;

    @Mock
    private GasStationEntityRepository gasStationEntityRepository;

    @Mock
    private ValidationUtil validationUtil;

    @InjectMocks
    private GasStationDataInitializer initializer;


    @Test
    void initializeGasStations_InvalidData() throws IOException {
        when(gasStationEntityRepository.count()).thenReturn(0L);

        GasStationWrapperJSON gasStationData = new GasStationWrapperJSON();
        gasStationData.setStations(List.of(new GasStationJSON()));

        when(gson.fromJson(any(String.class), eq(GasStationWrapperJSON.class))).thenReturn(gasStationData);
        when(validationUtil.isValid(any(GasStationJSON.class))).thenReturn(false);

        initializer.initializeGasStations();

        verify(gasStationEntityRepository, never()).save(any(GasStationEntity.class));
    }

    @Test
    void initializeGasStations_DataAlreadyInitialized() throws IOException {
        when(gasStationEntityRepository.count()).thenReturn(1L);

        initializer.initializeGasStations();

        verify(gasStationEntityRepository, never()).save(any(GasStationEntity.class));
    }

}
