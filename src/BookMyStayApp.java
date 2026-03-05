import java.util.HashMap;

abstract class Room {
    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    void display() {
        System.out.println(type + " - Price: $" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 100);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 180);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 300);
    }
}

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 0);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    HashMap<String, Integer> getAllRooms() {
        return inventory;
    }
}

class SearchService {

    RoomInventory inventory;
    Room[] rooms;

    SearchService(RoomInventory inventory, Room[] rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    void searchAvailableRooms() {
        for (Room room : rooms) {
            int available = inventory.getAvailability(room.type);
            if (available > 0) {
                room.display();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Hotel Booking System v4.0");
        System.out.println("---------------------------------------");

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        SearchService search = new SearchService(inventory, rooms);

        search.searchAvailableRooms();
    }
}