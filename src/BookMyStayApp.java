import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class Inventory {
    private Map<String, Integer> rooms = new HashMap<>();

    Inventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 2);
        rooms.put("Suite", 1);
    }

    public synchronized boolean allocateRoom(String roomType, String guestName) {
        int count = rooms.getOrDefault(roomType, 0);

        if (count > 0) {
            rooms.put(roomType, count - 1);
            System.out.println("Room allocated to " + guestName + " (" + roomType + ")");
            return true;
        } else {
            System.out.println("Booking failed for " + guestName + " (" + roomType + ") - No rooms available");
            return false;
        }
    }

    public void displayInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String type : rooms.keySet()) {
            System.out.println(type + " : " + rooms.get(type));
        }
    }
}

class BookingProcessor extends Thread {

    private Queue<BookingRequest> queue;
    private Inventory inventory;

    BookingProcessor(Queue<BookingRequest> queue, Inventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {

            BookingRequest request;

            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                request = queue.poll();
            }

            if (request != null) {
                inventory.allocateRoom(request.roomType, request.guestName);
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) throws InterruptedException {

        Queue<BookingRequest> bookingQueue = new LinkedList<>();
        Inventory inventory = new Inventory();

        bookingQueue.add(new BookingRequest("Amit", "Deluxe"));
        bookingQueue.add(new BookingRequest("Priya", "Deluxe"));
        bookingQueue.add(new BookingRequest("Rahul", "Suite"));
        bookingQueue.add(new BookingRequest("Sneha", "Suite"));
        bookingQueue.add(new BookingRequest("Arjun", "Standard"));

        BookingProcessor t1 = new BookingProcessor(bookingQueue, inventory);
        BookingProcessor t2 = new BookingProcessor(bookingQueue, inventory);
        BookingProcessor t3 = new BookingProcessor(bookingQueue, inventory);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        inventory.displayInventory();
    }
}