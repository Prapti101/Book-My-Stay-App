import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;
    String roomId;

    Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
    }

    public String toString() {
        return "Reservation ID: " + reservationId + ", Guest: " + guestName + ", Room Type: " + roomType + ", Room ID: " + roomId;
    }
}

class Inventory {
    private Map<String, Integer> rooms = new HashMap<>();

    Inventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 2);
        rooms.put("Suite", 1);
    }

    public void increaseRoom(String roomType) {
        rooms.put(roomType, rooms.get(roomType) + 1);
    }

    public void displayInventory() {
        System.out.println("Current Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + " : " + rooms.get(type));
        }
    }
}

class CancellationService {

    private Stack<String> rollbackStack = new Stack<>();

    public void cancelReservation(String reservationId, Map<String, Reservation> bookings, Inventory inventory) {

        if (!bookings.containsKey(reservationId)) {
            System.out.println("Cancellation failed: Reservation not found.");
            return;
        }

        Reservation reservation = bookings.get(reservationId);

        rollbackStack.push(reservation.roomId);

        inventory.increaseRoom(reservation.roomType);

        bookings.remove(reservationId);

        System.out.println("Reservation cancelled: " + reservationId);
        System.out.println("Released Room ID: " + rollbackStack.pop());
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        Inventory inventory = new Inventory();
        CancellationService cancellationService = new CancellationService();

        Map<String, Reservation> bookings = new HashMap<>();

        Reservation r1 = new Reservation("RES401", "Amit", "Deluxe", "D101");
        Reservation r2 = new Reservation("RES402", "Priya", "Suite", "S201");

        bookings.put(r1.reservationId, r1);
        bookings.put(r2.reservationId, r2);

        System.out.println("Confirmed Bookings:");
        for (Reservation r : bookings.values()) {
            System.out.println(r);
        }

        System.out.println();

        cancellationService.cancelReservation("RES401", bookings, inventory);

        System.out.println();

        inventory.displayInventory();
    }
}