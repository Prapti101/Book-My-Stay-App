import java.util.HashMap;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void displayInventory() {
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Available: " + inventory.get(roomType));
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Book My Stay - Hotel Booking System v3.1");
        System.out.println("---------------------------------------");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println();
        System.out.println("Checking availability for Double Room: "
                + inventory.getAvailability("Double Room"));

        inventory.updateAvailability("Double Room", 4);

        System.out.println("Updated Inventory:");
        inventory.displayInventory();
    }
}