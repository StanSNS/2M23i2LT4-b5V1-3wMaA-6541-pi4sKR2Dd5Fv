package utilTest;

import com.example.gasstations.domain.JSON.GasStationJSON;
import com.example.gasstations.util.ValidationUtil;
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