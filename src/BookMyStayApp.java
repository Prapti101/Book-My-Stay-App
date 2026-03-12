import java.util.*;

class AddOnService {
    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {

    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        reservationServices
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);

        System.out.println(service.name + " added to reservation " + reservationId);
    }

    public double calculateServiceCost(String reservationId) {

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null)
            return 0;

        double total = 0;

        for (AddOnService s : services) {
            total += s.cost;
        }

        return total;
    }

    public void displayServices(String reservationId) {

        List<AddOnService> services = reservationServices.get(reservationId);

        if (services == null) {
            System.out.println("No services selected.");
            return;
        }

        System.out.println("Services for Reservation " + reservationId + ":");

        for (AddOnService s : services) {
            System.out.println("- " + s.name + " : ₹" + s.cost);
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "RES101";

        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1200));
        manager.addService(reservationId, new AddOnService("Spa Access", 2000));

        System.out.println();

        manager.displayServices(reservationId);

        System.out.println();

        double total = manager.calculateServiceCost(reservationId);

        System.out.println("Total Add-On Cost: ₹" + total);
    }
}