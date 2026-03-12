import java.util.*;

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

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("Booking History Report:");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    public void displaySummary(List<Reservation> reservations) {
        System.out.println("\nTotal Confirmed Bookings: " + reservations.size());
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        Reservation r1 = new Reservation("RES201", "Amit", "Deluxe");
        Reservation r2 = new Reservation("RES202", "Priya", "Suite");
        Reservation r3 = new Reservation("RES203", "Rahul", "Standard");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        List<Reservation> reservations = history.getHistory();

        reportService.displayAllBookings(reservations);
        reportService.displaySummary(reservations);
    }
}