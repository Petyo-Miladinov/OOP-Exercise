import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleDealerTest {

    private VehicleDealer dealer;
    private Vehicle car1;
    private Vehicle car2;
    private Vehicle suv1;
    private Vehicle suv2;
    private Vehicle truck1;
    private Vehicle truck2;

    @BeforeEach
    void setUp() {
        dealer = new VehicleDealer();
        car1 = new Car(1, "Toyota", "Camry", 2015, "Black", 10000.0, 5, 4, EquipmentLevel.PREMIUM);
        car2 = new Car(2, "Toyota", "Corolla", 2018, "White", 12000.0, 4, 4, EquipmentLevel.BASIC);
        suv1 = new SUV(3, "Ford", "Explorer", 2020, "Red", 25000.0, true, 8.5);
        suv2 = new SUV(4, "Honda", "Pilot", 2021, "Gray", 30000.0, true, 9.0);
        truck1 = new Truck(5, "Ford", "F-150", 2019, "White", 35000.0, 5000, 50.0);
        truck2 = new Truck(6, "Chevrolet", "Silverado", 2020, "Blue", 40000.0, 6000, 60.0);
        dealer.addVehicle(car1);
        dealer.addVehicle(car2);
        dealer.addVehicle(suv1);
        dealer.addVehicle(suv2);
        dealer.addVehicle(truck1);
        dealer.addVehicle(truck2);
    }

    @Test
    void testAddVehicle() {
        Vehicle newVehicle = new Car(7, "Toyota", "Camry", 2020, "Blue", 15000.0, 5, 4, EquipmentLevel.BASIC);
        dealer.addVehicle(newVehicle);
        assertEquals(7, dealer.getAllVehicles().size());
        assertTrue(dealer.getAllVehicles().contains(newVehicle));
    }

    @Test
    void testAddVehicleDuplicateId() {
        Vehicle newVehicle = new Car(1, "Honda", "Civic", 2016, "Gray", 9000.0, 4, 4, EquipmentLevel.BASIC);
        assertThrows(IllegalArgumentException.class, () -> dealer.addVehicle(newVehicle));
        assertEquals(6, dealer.getAllVehicles().size());
    }

    @Test
    void testGetAllVehiclesByIdByMaker() {
        Map<String, Map<Integer, Vehicle>> allVehiclesByIdByMaker = dealer.getAllVehiclesByIdByMaker();
        assertEquals(3, allVehiclesByIdByMaker.get("Toyota").size());
        assertEquals(2, allVehiclesByIdByMaker.get("Ford").size());
        assertEquals(1, allVehiclesByIdByMaker.get("Honda").size());
    }

    @Test
    void testGetAllSUVWith4By4() {
        List<SUV> allSUVWith4By4 = dealer.getAllSUVWith4By4();
        assertEquals(2, allSUVWith4By4.size());
        assertTrue(allSUVWith4By4.contains(suv1));
        assertTrue(allSUVWith4By4.contains(suv2));
    }

    @Test
    void testGetAllCarsWithEquipmentLevel() {
        List<Car> allCarsWithPremiumEquipment = dealer.getAllCarsWithEquipmentLevel(EquipmentLevel.PREMIUM);
        List<Car> allCarsWithBasicEquipment = dealer.getAllCarsWithEquipmentLevel(EquipmentLevel.BASIC);
        assertEquals(1, allCarsWithPremiumEquipment.size());
        assertEquals(1, allCarsWithBasicEquipment.size());
    
        assertTrue(allCarsWithPremiumEquipment.contains(car1));
        assertTrue(allCarsWithBasicEquipment.contains(car2));
    }
    
    @Test
    void testGetTotalNumberOfModels() {
        int totalNumberOfCamrys = dealer.getTotalNumberOfModels("Toyota", "Camry");
        int totalNumberOfExplorers = dealer.getTotalNumberOfModels("Ford", "Explorer");
        int totalNumberOfF150s = dealer.getTotalNumberOfModels("Ford", "F-150");
    
        assertEquals(2, totalNumberOfCamrys);
        assertEquals(1, totalNumberOfExplorers);
        assertEquals(1, totalNumberOfF150s);
    }
    
    @Test
    void testGetAveragePriceByMaker() {
        Map<String, Double> averagePriceByMaker = dealer.getAveragePriceByMaker();
        assertEquals(3, averagePriceByMaker.size());
        assertEquals(10500.0, averagePriceByMaker.get("Toyota"));
        assertEquals(27500.0, averagePriceByMaker.get("Ford"));
        assertEquals(40000.0, averagePriceByMaker.get("Chevrolet"));
    }
    
    @Test
    void testGetAllVehiclesByYearRange() {
        List<Vehicle> allVehiclesFrom2015To2019 = dealer.getAllVehiclesByYearRange(2015, 2019);
        List<Vehicle> allVehiclesFrom2020To2022 = dealer.getAllVehiclesByYearRange(2020, 2022);
    
        assertEquals(4, allVehiclesFrom2015To2019.size());
        assertEquals(2, allVehiclesFrom2020To2022.size());
    
        assertTrue(allVehiclesFrom2015To2019.contains(car1));
        assertTrue(allVehiclesFrom2015To2019.contains(car2));
        assertTrue(allVehiclesFrom2015To2019.contains(suv1));
        assertTrue(allVehiclesFrom2015To2019.contains(truck1));
    
        assertTrue(allVehiclesFrom2020To2022.contains(suv2));
        assertTrue(allVehiclesFrom2020To2022.contains(truck2));
    }
}
