import java.util.ArrayList;
import java.util.List;


class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;
    private Destination destination;

    public Activity(String name, String description, double cost, int capacity, Destination destination) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Destination getDestination() {
        return destination;
    }
}

class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addActivity(Activity activity) {
        if (!activities.contains(activity)) {
            activities.add(activity);
        }
    }

    public List<Activity> getActivities() {
        return activities;
    }
}

class Passenger {
    private String name;
    private int passengerNumber;
    private double balance;
    private PassengerType type;
    private List<Activity> activities;

    public Passenger(String name, int passengerNumber, PassengerType type) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.type = type;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public PassengerType getType() {
        return type;
    }

    public void signUp(Activity activity) {
        if (activity.getCapacity() > 0) {
            double activityCost = activity.getCost();
            if (type == PassengerType.GOLD) {
                activityCost *= 0.9;
            }
            if (type != PassengerType.PREMIUM && balance < activityCost) {
                return;
            }
            activities.add(activity);
            activity.setCapacity(activity.getCapacity() - 1);
            if (type != PassengerType.PREMIUM) {
                balance -= activityCost;
            }
        }
    }

    public List<Activity> getActivities() {
        return activities;
    }
}

enum PassengerType {
    STANDARD, GOLD, PREMIUM
}

class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Passenger> passengers;
    private List<Destination> itinerary;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.passengers = new ArrayList<>();
        this.itinerary = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
        }
    }

    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        for (Destination destination : itinerary) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("Activity: " + activity.getName() + ", Cost: " + activity.getCost() + ", Capacity: " + activity.getCapacity() + ", Description: " + activity.getDescription());
            }
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers Enrolled: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("Passenger Name: " + passenger.getName() + ", Passenger Number: " + passenger.getPassengerNumber());
        }
    }

    public void printPassengerDetails(Passenger passenger) {
        System.out.println("Passenger Name: " + passenger.getName());
        System.out.println("Passenger Number: " + passenger.getPassengerNumber());
        if (passenger.getType() != PassengerType.PREMIUM) {
            System.out.println("Balance: " + passenger.getBalance());
        }
        System.out.println("Activities Signed Up For:");
        for (Activity activity : passenger.getActivities()) {
            System.out.println("Destination: " + activity.getDestination().getName() + ", Activity: " + activity.getName() + ", Price Paid: " + activity.getCost());
        }
    }

    public void printAvailableActivities() {
        System.out.println("Activities with Spaces Available:");
        for (Destination destination : itinerary) {
            for (Activity activity : destination.getActivities()) {
                if (activity.getCapacity() > 0) {
                    System.out.println("Destination: " + destination.getName() + ", Activity: " + activity.getName() + ", Spaces Available: " + activity.getCapacity());
                }
            }
        }
    }

    // Getter for itinerary
    public List<Destination> getItinerary() {
        return itinerary;
    }

    // Getter for passengers
    public List<Passenger> getPassengers() {
        return passengers;
    }
}

public class Main {
    public static void main(String[] args) {
        // Creating destinations and activities
        Destination destination1 = new Destination("Destination 1");
        Activity activity1 = new Activity("Activity 1", "Description of Activity 1", 50.0, 10, destination1);
        destination1.addActivity(activity1);

        Destination destination2 = new Destination("Destination 2");
        Activity activity2 = new Activity("Activity 2", "Description of Activity 2", 100.0, 5, destination2);
        destination2.addActivity(activity2);

        // Creating a travel package and adding destinations
        TravelPackage travelPackage = new TravelPackage("Travel Package 1", 20);
        travelPackage.addDestination(destination1);
        travelPackage.addDestination(destination2);

        // Creating passengers
        Passenger passenger1 = new Passenger("Passenger 1", 1, PassengerType.STANDARD);
        passenger1.setBalance(200.0);
        Passenger passenger2 = new Passenger("Passenger 2", 2, PassengerType.GOLD);
        passenger2.setBalance(300.0);
        Passenger passenger3 = new Passenger("Passenger 3", 3, PassengerType.PREMIUM);

        // Adding passengers to the travel package
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        travelPackage.addPassenger(passenger3);

        // Sign up passengers for activities
        passenger1.signUp(activity1);
        passenger2.signUp(activity2);
        passenger3.signUp(activity1);

        // Printing itinerary, passenger list, passenger details, and available activities
        travelPackage.printItinerary();
        travelPackage.printPassengerList();
        for (Passenger passenger : travelPackage.getPassengers()) {
            travelPackage.printPassengerDetails(passenger);
        }
        travelPackage.printAvailableActivities();
    }
}