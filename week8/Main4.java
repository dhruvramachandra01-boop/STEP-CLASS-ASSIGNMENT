
import java.util.*;

abstract class Room {

    private String roomNumber;

    public Room(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public abstract double calculatePrice(int days);
}

class StandardRoom extends Room {

    private static final double RATE = 100.0;

    public StandardRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * RATE;
    }
}

class DeluxeRoom extends Room {

    private static final double RATE = 180.0;

    public DeluxeRoom(String roomNumber) {
        super(roomNumber);
    }

    @Override
    public double calculatePrice(int days) {
        return days * RATE;
    }
}

class Reservation {

    private String reservationId;
    private Customer customer;
    private Room room;
    private int startDay;
    private int endDay;
    private boolean isCancelled = false;

    public Reservation(String id, Customer customer, Room room, int startDay, int endDay) {
        this.reservationId = id;
        this.customer = customer;
        this.room = room;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public Room getRoom() {
        return room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getStartDay() {
        return startDay;
    }

    public int getEndDay() {
        return endDay;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public boolean overlapsWith(int start, int end) {
        if (isCancelled) {
            return false;
        }
        return (start < endDay && end > startDay);
    }
}

class Customer {

    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class HotelBookingService {

    private List<Reservation> reservations = new ArrayList<>();

    public boolean isAvailable(Room room, int startDay, int endDay) {
        for (Reservation r : reservations) {
            if (r.getRoom().getRoomNumber().equals(room.getRoomNumber()) && r.overlapsWith(startDay, endDay)) {
                return false;
            }
        }
        return true;
    }

    public Reservation reserve(String resId, Customer customer, Room room, int startDay, int endDay, String dateStr) {
        if (!isAvailable(room, startDay, endDay)) {
            System.out.println(room.getRoomNumber() + " is not available from Jan " + startDay + " to Jan " + endDay + ".");
            return null;
        }
        int days = endDay - startDay;
        double price = room.calculatePrice(days);
        Reservation res = new Reservation(resId, customer, room, startDay, endDay);
        reservations.add(res);
        System.out.println("Reservation confirmed for " + customer.getName() + ", " + room.getRoomNumber() + " (" + dateStr + "). Price: $" + (int) price + ".");
        return res;
    }

    public void cancelReservation(Reservation res, String dateStr) {
        if (res != null) {
            res.cancel();
            System.out.println("Reservation for " + res.getCustomer().getName() + ", " + res.getRoom().getRoomNumber() + " (" + dateStr + ") cancelled successfully.");
        }
    }
}

public class Main4 {

    public static void main(String[] args) {
        HotelBookingService service = new HotelBookingService();

        Room room101 = new StandardRoom("Standard Room 101");
        Room room201 = new DeluxeRoom("Deluxe Room 201");

        Customer customerA = new Customer("Customer A");
        Customer customerB = new Customer("Customer B");
        Customer customerC = new Customer("Customer C");

        System.out.println("Standard Room 101 is available from Jan 1 to Jan 5.");
        Reservation rA = service.reserve("RES1", customerA, room101, 1, 5, "Jan 1-5");
        Reservation rB = service.reserve("RES2", customerB, room101, 3, 7, "Jan 3-7");
        service.cancelReservation(rA, "Jan 1-5");
        Reservation rC = service.reserve("RES3", customerC, room201, 10, 12, "Feb 10-12");
    }
}
