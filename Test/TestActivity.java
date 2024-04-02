import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestActivity {

    @Test
    public void testConstructorAndGetters() {
        Destination destination = new Destination("Test Destination");
        Activity activity = new Activity("Test Activity", "Description of Test Activity", 50.0, 10, destination);

        assertEquals("Test Activity", activity.getName());
        assertEquals("Description of Test Activity", activity.getDescription());
        assertEquals(50.0, activity.getCost(), 0.01);
        assertEquals(10, activity.getCapacity());
        assertEquals(destination, activity.getDestination());
    }

}
