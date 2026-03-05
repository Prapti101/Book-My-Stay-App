import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation r) {
        queue.add(r);
    }

    void displayRequests() {
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class BookMyStayApp{

    public static void main(String[] args) {

        System.out.println("Book My Stay - Hotel Booking System v5.0");
        System.out.println("---------------------------------------");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        System.out.println("Booking Requests in Queue (FIFO Order):");
        bookingQueue.displayRequests();
    }
}