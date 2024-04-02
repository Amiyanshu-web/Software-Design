import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

public class TestTravelPackage {

    @Test
    public void testAddPassenger() {
        TravelPackage travelPackage = new TravelPackage("Test Travel Package", 2);
        Passenger passenger1 = new Passenger("John", 1, PassengerType.STANDARD);
        Passenger passenger2 = new Passenger("Jane", 2, PassengerType.GOLD);

        assertTrue(travelPackage.addPassenger(passenger1));
        assertTrue(travelPackage.addPassenger(passenger2));

        // Adding another passenger should fail since the capacity is only 2
        Passenger passenger3 = new Passenger("Alice", 3, PassengerType.STANDARD);
        assertFalse(travelPackage.addPassenger(passenger3));
    }

    @Test
    public void testAddDestination() {
        TravelPackage travelPackage = new TravelPackage("Test Travel Package", 2);
        Destination destination1 = new Destination("Test Destination 1");
        travelPackage.addDestination(destination1);

        List<Destination> itinerary = travelPackage.getItinerary();
        assertEquals(1, itinerary.size());
        assertEquals("Test Destination 1", itinerary.get(0).getName());
    }

}

