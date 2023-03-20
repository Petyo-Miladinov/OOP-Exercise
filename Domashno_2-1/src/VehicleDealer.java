import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VehicleDealer {
    private List<Vehicle> vehicles; // generic type
    private double turnover;

    public VehicleDealer() {
        this.vehicles = new ArrayList<>();
        this.turnover = 0;
    }

    public void addVehicle(Vehicle vehicle) {
        for (Vehicle v : vehicles) {
            if (v.getId() == vehicle.getId()) {
                throw new IllegalArgumentException("Vehicle with this ID already exists!");
            }
        }
        vehicles.add(vehicle);
    }

    public Map<String, Map<Integer, Vehicle>> getAllVehiclesByIdByMaker() {
        Map<String, Map<Integer, Vehicle>> vehiclesByMaker = new HashMap<>();

        for (Vehicle v : vehicles) {
            String maker = v.getMaker();
            int id = v.getId();

            if (!vehiclesByMaker.containsKey(maker)) {
                vehiclesByMaker.put(maker, new HashMap<>());
            }

            vehiclesByMaker.get(maker).put(id, v);
        }

        return vehiclesByMaker;
    }

    public List<SUV> getAllSUVWith4By4() {
        List<SUV> suvsWith4By4 = new ArrayList<>();

        for (Vehicle v : vehicles) {
            if (v instanceof SUV) {
                SUV suv = (SUV) v;
                if (suv.has4by4()) {
                    suvsWith4By4.add(suv);
                }
            }
        }

        return suvsWith4By4;
    }

    public List<Car> getAllCarsWithEquipmentLevel(EquipmentLevel equipmentLevel) {
        List<Car> carsWithEquipmentLevel = new ArrayList<>();

        for (Vehicle v : vehicles) {
            if (v instanceof Car) {
                Car car = (Car) v;
                if (car.getEquipmentLevel() == equipmentLevel) {
                    carsWithEquipmentLevel.add(car);
                }
            }
        }

        return carsWithEquipmentLevel;
    }

    public Vehicle getCheapestVehicle(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            throw new IllegalArgumentException("List is empty!");
        }

        Vehicle cheapestVehicle = vehicles.get(0);

        for (int i = 1; i < vehicles.size(); i++) {
            Vehicle v = vehicles.get(i);
            if (v.getPrice() < cheapestVehicle.getPrice()) {
                cheapestVehicle = v;
            }
        }

        return cheapestVehicle;
    }

    public int getTotalNumberOfModels(String maker) {
        int count = 0;

        for (Vehicle v : vehicles) {
            if (v.getMaker().equals(maker)) {
                count++;
            }
        }

        return count;
    }

    /*
    public Map<String, Integer> getTotalNumberOfModelsByMaker() {
        Map<String, Integer> modelsCountByMaker = new HashMap<>();
        Set<String> uniqueModels = new HashSet<>();
        for (Vehicle vehicle : vehicles.values()) {
            String maker = vehicle.getMaker();
            String model = vehicle.getModel();
            if (!uniqueModels.contains(maker + model)) {
                uniqueModels.add(maker + model);
                modelsCountByMaker.merge(maker, 1, Integer::sum);
            }
        }
        return modelsCountByMaker;
    }
    */

    public void sellVehicle(int vehicleId) {
        Vehicle vehicleToSell = null;

        for (Vehicle v : vehicles) {
            if (v.getId() == vehicleId) {
                vehicleToSell = v;
                break;
            }
        }

        if (vehicleToSell == null) {
            throw new IllegalArgumentException("Vehicle with this ID does not exist!");
        }

        vehicles.remove(vehicleToSell);
        turnover += vehicleToSell.getPrice();
    }

    public double getTurnover() {
        return turnover;
    }

    public List<Vehicle> getAllVehicles() {
        return Collections.unmodifiableList(vehicles);
    }

    // With maker and model
    public int getTotalNumberOfModels(String maker, String model) {
        return (int) vehicles.stream()
                .filter(vehicle -> vehicle.getMaker().equals(maker) && vehicle.getModel().equals(model))
                .count();
    }

    public Map<String, Double> getAveragePriceByMaker() {
        return vehicles.stream()
                .collect(Collectors.groupingBy(Vehicle::getMaker, Collectors.averagingDouble(Vehicle::getPrice)));
    }

    public List<Vehicle> getAllVehiclesByYearRange(int startYear, int endYear) {
        return vehicles.stream()
                .filter(vehicle -> vehicle.getYear() >= startYear && vehicle.getYear() <= endYear)
                .collect(Collectors.toList());
    }
}
