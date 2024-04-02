import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestPassenger {

    @Test
    public void testSignUp() {
        Destination destination = new Destination("Test Destination");
        Activity activity = new Activity("Test Activity", "Description of Test Activity", 50.0, 10, destination);

        Passenger passenger = new Passenger("John", 1, PassengerType.STANDARD);
        passenger.setBalance(100.0);

        // Sign up should succeed since the balance is sufficient
        passenger.signUp(activity);
        assertEquals(1, passenger.getActivities().size());

        // Sign up should fail due to insufficient balance
        Activity expensiveActivity = new Activity("Expensive Activity", "Description of Expensive Activity", 200.0, 5, destination);
        passenger.signUp(expensiveActivity);
        assertEquals(1, passenger.getActivities().size()); // No change in activities
    }

}

