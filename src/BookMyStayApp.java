import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String toString() {
        return "Reservation ID: " + reservationId + ", Guest: " + guestName + ", Room: " + roomType;
    }
}

class BookingValidator {

    private static final List<String> validRoomTypes = Arrays.asList("Standard", "Deluxe", "Suite");

    public static void validateReservation(Reservation reservation) throws InvalidBookingException {

        if (reservation.guestName == null || reservation.guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!validRoomTypes.contains(reservation.roomType)) {
            throw new InvalidBookingException("Invalid room type selected: " + reservation.roomType);
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        List<Reservation> reservations = new ArrayList<>();

        Reservation r1 = new Reservation("RES301", "Amit", "Deluxe");
        Reservation r2 = new Reservation("RES302", "Priya", "Luxury");
        Reservation r3 = new Reservation("RES303", "", "Suite");

        List<Reservation> inputs = Arrays.asList(r1, r2, r3);

        for (Reservation r : inputs) {
            try {
                BookingValidator.validateReservation(r);
                reservations.add(r);
                System.out.println("Booking successful: " + r);
            } catch (InvalidBookingException e) {
                System.out.println("Booking failed: " + e.getMessage());
            }
        }

        System.out.println("\nValid Reservations Stored: " + reservations.size());
    }
}