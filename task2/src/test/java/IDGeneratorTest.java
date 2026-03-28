import org.junit.jupiter.api.Test;
import utilities.IDGenerator;
import factory.details.*;
import static org.junit.jupiter.api.Assertions.*;

public class IDGeneratorTest {

    @Test
    void testCountersAreIndependent() {
        String bodyId1 = IDGenerator.generateID(Body.class);
        String motorId1 = IDGenerator.generateID(Motor.class);
        String accessoryId1 = IDGenerator.generateID(Accessory.class);
        String carId1 = IDGenerator.generateID(Car.class);

        String bodyId2 = IDGenerator.generateID(Body.class);
        String motorId2 = IDGenerator.generateID(Motor.class);
        String accessoryId2 = IDGenerator.generateID(Accessory.class);
        String carId2 = IDGenerator.generateID(Car.class);

        assertEquals("BDY0", bodyId1);
        assertEquals("BDY1", bodyId2);

        assertEquals("MTR0", motorId1);
        assertEquals("MTR1", motorId2);

        assertEquals("ACS0", accessoryId1);
        assertEquals("ACS1", accessoryId2);

        assertEquals("CAR0", carId1);
        assertEquals("CAR1", carId2);
    }
}