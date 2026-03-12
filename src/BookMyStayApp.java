import java.io.*;
import java.util.*;

class Reservation implements Serializable {
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

class Inventory implements Serializable {
    Map<String, Integer> rooms = new HashMap<>();

    Inventory() {
        rooms.put("Standard", 2);
        rooms.put("Deluxe", 2);
        rooms.put("Suite", 1);
    }

    public void displayInventory() {
        System.out.println("Inventory State:");
        for (String type : rooms.keySet()) {
            System.out.println(type + " : " + rooms.get(type));
        }
    }
}

class SystemState implements Serializable {
    List<Reservation> reservations;
    Inventory inventory;

    SystemState(List<Reservation> reservations, Inventory inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
    }
}

class PersistenceService {

    public static void saveState(SystemState state, String fileName) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(state);
            out.close();
            System.out.println("System state saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving system state.");
        }
    }

    public static SystemState loadState(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            SystemState state = (SystemState) in.readObject();
            in.close();
            System.out.println("System state restored successfully.");
            return state;
        } catch (Exception e) {
            System.out.println("No previous state found. Starting fresh.");
            return null;
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        String fileName = "system_state.dat";

        SystemState recoveredState = PersistenceService.loadState(fileName);

        List<Reservation> reservations;
        Inventory inventory;

        if (recoveredState != null) {
            reservations = recoveredState.reservations;
            inventory = recoveredState.inventory;
        } else {
            reservations = new ArrayList<>();
            inventory = new Inventory();
        }

        reservations.add(new Reservation("RES501", "Amit", "Deluxe"));
        reservations.add(new Reservation("RES502", "Priya", "Suite"));

        System.out.println("\nCurrent Reservations:");
        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println();
        inventory.displayInventory();

        SystemState state = new SystemState(reservations, inventory);

        PersistenceService.saveState(state, fileName);
    }
}