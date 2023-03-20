import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        VehicleDealer dealer = new VehicleDealer();
        Car car1 = new Car(1, "Toyota", "Camry", 2015, "Black", 10000.0, 5, 4, EquipmentLevel.PREMIUM);
        Car car2 = new Car(2, "Toyota", "Corolla", 2018, "White", 12000.0, 4, 4, EquipmentLevel.BASIC);
        SUV suv1 = new SUV(3, "Ford", "Explorer", 2020, "Red", 25000.0, true, 8.5);
        SUV suv2 = new SUV(4, "Honda", "Pilot", 2021, "Gray", 30000.0, true, 9.0);
        Truck truck1 = new Truck(5, "Ford", "F-150", 2019, "White", 35000.0, 5000, 50.0);
        Truck truck2 = new Truck(6, "Chevrolet", "Silverado", 2020, "Blue", 40000.0, 6000, 60.0);
        
        dealer.addVehicle(car1);
        dealer.addVehicle(car2);
        dealer.addVehicle(suv1);
        dealer.addVehicle(suv2);
        dealer.addVehicle(truck1);
        dealer.addVehicle(truck2);
        
        // Get all vehicles
        List<Vehicle> allVehicles = dealer.getAllVehicles();
        System.out.println("All vehicles:");
        for (Vehicle vehicle : allVehicles) {
            System.out.println(vehicle);
        }
        
        // Get total number of models by maker
        int numModels = dealer.getTotalNumberOfModels("Toyota");
        System.out.println("Number of Toyota models: " + numModels);
        
        // Get average price by maker
        Map<String, Double> averagePriceByMaker = dealer.getAveragePriceByMaker();
        System.out.println("Average price by maker:");
        for (Map.Entry<String, Double> entry : averagePriceByMaker.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        // Get all vehicles by year range
        List<Vehicle> vehiclesByYearRange = dealer.getAllVehiclesByYearRange(2015, 2020);
        System.out.println("All vehicles between 2015 and 2020:");
        for (Vehicle vehicle : vehiclesByYearRange) {
            System.out.println(vehicle);
        }
    }
}
