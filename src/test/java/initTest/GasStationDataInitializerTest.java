package initTest;

import com.example.gasstations.domain.JSON.GasStationDataJSON;
import com.example.gasstations.domain.JSON.GasStationJSON;
import com.example.gasstations.domain.entity.GasStationEntity;
import com.example.gasstations.init.GasStationDataInitializer;
import com.example.gasstations.repository.GasStationEntityRepository;
import com.example.gasstations.util.ValidationUtil;
import com.google.gson.Gson;
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

        GasStationDataJSON gasStationData = new GasStationDataJSON();
        gasStationData.setStations(List.of(new GasStationJSON()));

        when(gson.fromJson(any(String.class), eq(GasStationDataJSON.class))).thenReturn(gasStationData);
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
