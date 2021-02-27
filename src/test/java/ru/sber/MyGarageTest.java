package ru.sber;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class MyGarageTest {

    @Test
    void allCarsUniqueOwners() {
        Garage myGarage = new MyGarage();
        Owner owner1 = new Owner(1, "Ekaterina", "Khursik", 19);
        Owner owner2 = new Owner(2, "Alex", "Binkevich", 20);
        Car car1 = new Car(1, "Mercedes", "GLE", 370, 389, 1);
        Car car2 = new Car(2, "BMW", "X7", 372, 389, 2);
        Car car3 = new Car(3, "Volvo", "S-class", 373, 389, 2);
        Car car4 = new Car(4, "Lamborghini", "Gallardo", 374, 389, 1);
        myGarage.addNewCar(car1, owner1);
        myGarage.addNewCar(car2, owner2);
        myGarage.addNewCar(car3, owner2);
        myGarage.addNewCar(car4, owner1);
        assertEquals(new ArrayList<>(myGarage.allCarsUniqueOwners()), myGarage.allCarsUniqueOwners().stream().distinct().collect(Collectors.toList()));
    }

    @Test
    void topThreeCarsByMaxVelocity() {
        Garage myGarage = new MyGarage();
        Owner owner = new Owner(1, "Ekaterina", "Khursik", 19);
        Car car1 = new Car(1, "Mercedes-Benz", "GLE", 370, 389, 1);
        Car car2 = new Car(2, "BMW", "X7", 372, 389, 1);
        Car car3 = new Car(3, "Volvo", "S-Class", 373, 389, 1);
        Car car4 = new Car(4, "Lamborghini", "Gallardo", 374, 389, 1);
        myGarage.addNewCar(car1, owner);
        myGarage.addNewCar(car2, owner);
        myGarage.addNewCar(car3, owner);
        myGarage.addNewCar(car4, owner);
        Collection<Car> fastest = myGarage.topThreeCarsByMaxVelocity();
        for (Car car : fastest) {
            assertNotEquals(car1, car);
        }
    }

    @Test
    void allCarsOfBrand() {
        Garage myGarage = new MyGarage();
        Owner owner = new Owner(1, "Ekaterina", "Khursik", 19);
        Car car1 = new Car(1, "Mercedes-Benz", "Mercedes-Benz GLE", 370, 389, 1);
        Car car2 = new Car(2, "Mercedes-Benz", "Mercedes-Benz G-Class", 372, 415, 1);
        Car car3 = new Car(3, "Mercedes-Benz", "Mercedes-Benz S-Class", 373, 374, 1);
        Car car4 = new Car(4, "Lamborghini", "Gallardo", 374, 456, 1);
        myGarage.addNewCar(car1, owner);
        myGarage.addNewCar(car2, owner);
        myGarage.addNewCar(car3, owner);
        myGarage.addNewCar(car4, owner);
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        assertEquals(myGarage.allCarsOfBrand("Mercedes-Benz"), cars);
    }

    @Test
    void carsWithPowerMoreThan() {
        Garage myGarage = new MyGarage();
        Owner owner = new Owner(1, "Ekaterina", "Khursik", 19);
        Car car1 = new Car(1, "Mercedes-Benz", "Mercedes-Benz GLE", 370, 389, 1);
        Car car2 = new Car(2, "Mercedes-Benz", "Mercedes-Benz G-Class", 372, 415, 1);
        Car car3 = new Car(3, "Mercedes-Benz", "Mercedes-Benz S-Class", 373, 374, 1);
        Car car4 = new Car(4, "Lamborghini", "Gallardo", 374, 456, 1);
        myGarage.addNewCar(car1, owner);
        myGarage.addNewCar(car2, owner);
        myGarage.addNewCar(car3, owner);
        myGarage.addNewCar(car4, owner);
        Collection<Car> fastestCars = myGarage.carsWithPowerMoreThan(380);
        for (Car fastestCar : fastestCars) {
            assertNotEquals(fastestCar, car3);
        }
    }

    @Test
    void allCarsOfOwner() {
        Garage myGarage = new MyGarage();
        Owner owner1 = new Owner(1, "Ekaterina", "Khursik", 19);
        Owner owner2 = new Owner(2, "Alex", "Binkevich", 20);
        Car car1 = new Car(1, "Mercedes-Benz", "Mercedes-Benz GLE", 370, 389, 1);
        Car car2 = new Car(2, "Mercedes-Benz", "Mercedes-Benz G-Class", 372, 415, 1);
        Car car3 = new Car(3, "Mercedes-Benz", "Mercedes-Benz S-Class", 373, 374, 2);
        Car car4 = new Car(4, "Lamborghini", "Gallardo", 374, 456, 2);
        myGarage.addNewCar(car1, owner1);
        myGarage.addNewCar(car2, owner1);
        myGarage.addNewCar(car3, owner2);
        myGarage.addNewCar(car4, owner2);
        HashSet<Car> cars = new HashSet<>();
        cars.add(car3);
        cars.add(car4);
        assertEquals(myGarage.allCarsOfOwner(owner2), cars);
    }

    @Test
    void meanOwnersAgeOfCarBrand() {
        Garage myGarage = new MyGarage();
        Owner owner1 = new Owner(1, "Ekaterina", "Khursik", 20);
        Owner owner2 = new Owner(2, "Alex", "Binkevich", 40);
        Owner owner3 = new Owner(2, "Anna", "Volkova", 60);
        Car car1 = new Car(1, "Mercedes-Benz", "Mercedes-Benz GLE", 370, 389, 1);
        Car car2 = new Car(2, "Mercedes-Benz", "Mercedes-Benz G-Class", 372, 415, 2);
        Car car3 = new Car(3, "Mercedes-Benz", "Mercedes-Benz S-Class", 373, 374, 3);
        Car car4 = new Car(4, "Lamborghini", "Gallardo", 374, 456, 2);
        myGarage.addNewCar(car1, owner1);
        myGarage.addNewCar(car2, owner2);
        myGarage.addNewCar(car3, owner3);
        myGarage.addNewCar(car4, owner2);
        assertEquals(myGarage.meanOwnersAgeOfCarBrand("Mercedes-Benz"), 40);
    }

    @Test
    void meanCarNumberForEachOwner() {
        Garage myGarage = new MyGarage();
        Owner owner1 = new Owner(1, "Ekaterina", "Khursik", 20);
        Owner owner2 = new Owner(2, "Alex", "Binkevich", 40);
        Owner owner3 = new Owner(2, "Anna", "Volkova", 60);
        Owner owner4 = new Owner(2, "Alex", "Kurch", 80);
        Owner owner5 = new Owner(2, "Alexandra", "Vink", 40);
        Car car1 = new Car(1, "Mercedes-Benz", "Mercedes-Benz GLE", 370, 389, 1);
        Car car2 = new Car(2, "Mercedes-Benz", "Mercedes-Benz G-Class", 372, 415, 2);
        Car car3 = new Car(3, "Mercedes-Benz", "Mercedes-Benz S-Class", 373, 374, 3);
        Car car4 = new Car(4, "Lamborghini", "Gallardo", 374, 456, 2);
        myGarage.addNewCar(car1, owner1);
        myGarage.addNewCar(car2, owner1);
        myGarage.addNewCar(car3, owner1);
        myGarage.addNewCar(car4, owner1);
        myGarage.addNewCar(car1, owner2);
        myGarage.addNewCar(car2, owner2);
        myGarage.addNewCar(car3, owner2);

        myGarage.addNewCar(car1, owner3);
        myGarage.addNewCar(car2, owner3);
        myGarage.addNewCar(car3, owner4);
        assertEquals(myGarage.meanCarNumberForEachOwner(), 2);
    }

    @Test
    void removeCar() {
        Garage myGarage = new MyGarage();
        Owner owner1 = new Owner(1, "Ekaterina", "Khursik", 20);
        Car car1 = new Car(1, "Mercedes-Benz", "Mercedes-Benz GLE", 370, 389, 1);
        Car car2 = new Car(2, "Lamborghini", "Gallardo", 374, 456, 2);
        myGarage.addNewCar(car1, owner1);
        myGarage.addNewCar(car2, owner1);
        myGarage.removeCar(1);
        HashSet<Car> cars = new HashSet<>();
        cars.add(car2);
        assertEquals(myGarage.allCarsOfOwner(owner1), cars);
    }

    @Test
    void addNewCar() {
        Garage myGarage = new MyGarage();
        Owner owner1 = new Owner(1, "Ekaterina", "Khursik", 20);
        Car car1 = new Car(1, "Mercedes-Benz", "Mercedes-Benz GLE", 370, 389, 1);
        Car car2 = new Car(2, "Lamborghini", "Gallardo", 374, 456, 2);
        myGarage.addNewCar(car1, owner1);
        assertNotNull(myGarage);
    }
}