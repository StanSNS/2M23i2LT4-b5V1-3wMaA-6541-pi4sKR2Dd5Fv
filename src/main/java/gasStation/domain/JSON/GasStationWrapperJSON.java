package gasStation.domain.JSON;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GasStationWrapperJSON {

    private List<GasStationJSON> stations;

}