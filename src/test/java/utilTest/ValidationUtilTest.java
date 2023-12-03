package utilTest;

import gasStation.domain.JSON.GasStationJSON;
import gasStation.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidationUtilTest {

    private ValidationUtil validationUtil;

    @BeforeEach
    public void setUp() {
        validationUtil = new ValidationUtil();
    }


    @Test
    public void testIsValidWithInvalidEntity() {
        GasStationJSON gasStationJSON = new GasStationJSON();

        boolean isValid = validationUtil.isValid(gasStationJSON);

        assertFalse(isValid, "Entity should be invalid");
    }
}