
import java.util.*;

// Abstract Base Class: Vehicle
abstract class Vehicle {

    private String vehicleId;
    private String model;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String model) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.isAvailable = true;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    // Abstract method for dynamic charge calculation
    public abstract double calculateRentalCharge(int days);
}

// Concrete Vehicle Category: Sedan
class Sedan extends Vehicle {

    private static final double DAILY_RATE = 50.0;

    public Sedan(String vehicleId, String model) {
        super(vehicleId, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

// Concrete Vehicle Category: SUV
class SUV extends Vehicle {

    private static final double DAILY_RATE = 80.0;

    public SUV(String vehicleId, String model) {
        super(vehicleId, model);
    }

    @Override
    public double calculateRentalCharge(int days) {
        return days * DAILY_RATE;
    }
}

// Customer Class
class Customer {

    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Rental Class
class Rental {

    private String rentalId;
    private Customer customer;
    private Vehicle vehicle;
    private int days;
    private double totalCharge;
    private boolean isActive;

    public Rental(String rentalId, Customer customer, Vehicle vehicle, int days) {
        this.rentalId = rentalId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.totalCharge = vehicle.calculateRentalCharge(days);
        this.isActive = true;
        vehicle.setAvailable(false);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void returnVehicle() {
        this.isActive = false;
        vehicle.setAvailable(true);
    }
}

// System Manager
class RentalService {

    private Map<String, Rental> activeRentals = new HashMap<>();

    public Rental rentVehicle(String rentalId, Customer customer, Vehicle vehicle, int days) {
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getModel() + " is currently unavailable.");
            return null;
        }
        Rental rental = new Rental(rentalId, customer, vehicle, days);
        activeRentals.put(vehicle.getVehicleId(), rental);
        System.out.println(vehicle.getModel() + " rented successfully by " + customer.getName()
                + ". Rental charge: $" + rental.getTotalCharge() + ".");
        return rental;
    }

    public void returnVehicle(Rental rental) {
        if (rental != null) {
            rental.returnVehicle();
            activeRentals.remove(rental.getVehicle().getVehicleId());
            System.out.println(rental.getVehicle().getModel() + " returned by " + rental.getCustomer().getName() + ".");
        }
    }
}

public class Main {

    public static void main(String[] args) {
        RentalService service = new RentalService();

        Vehicle sedanA = new Sedan("V001", "Sedan A");
        Vehicle suvB = new SUV("V002", "SUV B");

        Customer c1 = new Customer("C001", "Customer 1");
        Customer c2 = new Customer("C002", "Customer 2");
        Customer c3 = new Customer("C003", "Customer 3");

        // Workflow execution matching Sample Input
        Rental r1 = service.rentVehicle("R101", c1, sedanA, 3);
        Rental r2 = service.rentVehicle("R102", c2, sedanA, 2); // Attempt while rented
        service.returnVehicle(r1);
        Rental r3 = service.rentVehicle("R103", c3, suvB, 5);
    }
}
