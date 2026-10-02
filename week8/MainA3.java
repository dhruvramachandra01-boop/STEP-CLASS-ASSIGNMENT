
import java.util.*;

enum SeatCategory {
    REGULAR(150.00),
    PREMIUM(250.00),
    RECLINER(400.00);

    private final double price;

    SeatCategory(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class Seat {

    private String seatNumber;
    private SeatCategory category;

    public Seat(String seatNumber, SeatCategory category) {
        this.seatNumber = seatNumber;
        this.category = category;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public SeatCategory getCategory() {
        return category;
    }

    public double getPrice() {
        return category.getPrice();
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

class Show {

    private String showTime;
    private Map<String, Seat> allSeats = new HashMap<>();
    private Set<String> bookedSeatNumbers = new HashSet<>();

    public Show(String showTime) {
        this.showTime = showTime;
    }

    public void addSeat(Seat seat) {
        allSeats.put(seat.getSeatNumber(), seat);
    }

    public Seat getSeat(String seatNumber) {
        return allSeats.get(seatNumber);
    }

    public boolean isSeatAvailable(String seatNumber) {
        return allSeats.containsKey(seatNumber) && !bookedSeatNumbers.contains(seatNumber);
    }

    public boolean bookSeats(List<String> seatNumbers) {
        for (String sn : seatNumbers) {
            if (!isSeatAvailable(sn)) {
                System.out.println("Seat " + sn + " is already booked for this show.");
                return false;
            }
        }
        bookedSeatNumbers.addAll(seatNumbers);
        return true;
    }

    public void releaseSeats(List<String> seatNumbers) {
        bookedSeatNumbers.removeAll(seatNumbers);
    }
}

class Booking {

    private Customer customer;
    private Show show;
    private List<Seat> bookedSeats;
    private boolean isCancelled = false;

    private Booking(Customer customer, Show show, List<Seat> bookedSeats) {
        this.customer = customer;
        this.show = show;
        this.bookedSeats = bookedSeats;
    }

    public static Booking createBooking(Customer customer, Show show, List<Seat> seats) {
        if (seats.size() > 6) {
            System.out.println("Booking failed: Cannot book more than 6 seats at once.");
            return null;
        }

        List<String> seatNums = new ArrayList<>();
        for (Seat s : seats) {
            seatNums.add(s.getSeatNumber());
        }

        if (show.bookSeats(seatNums)) {
            Booking booking = new Booking(customer, show, seats);
            double total = booking.calculateTotal();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < seats.size(); i++) {
                sb.append(seats.get(i).getSeatNumber());
                if (i < seats.size() - 1) {
                    sb.append(", ");
                }
            }

            System.out.printf("Booking confirmed for %s: %s. Total: %.2f.\n",
                    customer.getName(), sb.toString(), total);
            return booking;
        }
        return null;
    }

    public double calculateTotal() {
        double total = 0;
        for (Seat s : bookedSeats) {
            total += s.getPrice();
        }
        return total;
    }

    public void cancel(boolean isBeforeShowStart) {
        if (!isBeforeShowStart) {
            System.out.println("Cannot cancel booking after show has started.");
            return;
        }
        if (isCancelled) {
            return;
        }

        List<String> seatNums = new ArrayList<>();
        for (Seat s : bookedSeats) {
            seatNums.add(s.getSeatNumber());
        }
        show.releaseSeats(seatNums);
        this.isCancelled = true;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bookedSeats.size(); i++) {
            sb.append(bookedSeats.get(i).getSeatNumber());
            if (i < bookedSeats.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.println(customer.getName() + "'s booking cancelled. Seats " + sb.toString() + " released.");
    }
}

public class MainA3 {

    public static void main(String[] args) {
        Show show7PM = new Show("7 PM");

        Seat a1 = new Seat("A1", SeatCategory.REGULAR);
        Seat a2 = new Seat("A2", SeatCategory.REGULAR);
        Seat f5 = new Seat("F5", SeatCategory.PREMIUM);
        Seat r1 = new Seat("R1", SeatCategory.RECLINER);

        show7PM.addSeat(a1);
        show7PM.addSeat(a2);
        show7PM.addSeat(f5);
        show7PM.addSeat(r1);

        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        // Asha books A1, A2, F5
        Booking bookingAsha = Booking.createBooking(asha, show7PM, Arrays.asList(a1, a2, f5));

        // Ravi attempts to book A2 (already booked)
        Booking bookingRavi1 = Booking.createBooking(ravi, show7PM, Arrays.asList(a2));

        // Ravi books R1
        Booking bookingRavi2 = Booking.createBooking(ravi, show7PM, Arrays.asList(r1));

        // Asha cancels booking
        if (bookingAsha != null) {
            bookingAsha.cancel(true);
        }

        // Neha books A2
        Booking bookingNeha = Booking.createBooking(neha, show7PM, Arrays.asList(a2));
    }
}
