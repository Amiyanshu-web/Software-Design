import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestDestination {

    @Test
    public void testAddActivity() {
        Destination destination = new Destination("Test Destination");
        Activity activity1 = new Activity("Test Activity 1", "Description of Test Activity 1", 50.0, 10, destination);
        Activity activity2 = new Activity("Test Activity 2", "Description of Test Activity 2", 100.0, 5, destination);

        destination.addActivity(activity1);
        destination.addActivity(activity2);

        assertEquals(2, destination.getActivities().size());
    }

}

