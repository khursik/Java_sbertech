package ru.sber;


import java.util.*;
import java.util.stream.Collectors;

class MyGarage implements Garage {
    final HashMap<Owner, HashSet<Car>> carsByOwner;
    final TreeSet<Car> carsByMaxVelocity;
    final HashMap<String, List<Car>> carsOfBrand;
    final TreeSet<Car> carsByPower;
    final HashMap<Integer, Owner> ownerByCar;

    MyGarage() {
        this.carsByOwner = new HashMap<>();
        this.carsByMaxVelocity = new TreeSet<>((car1, car2)->{return Integer.compare(car2.getMaxVelocity(), car1.getMaxVelocity());});
        this.carsOfBrand = new HashMap<>();
        this.carsByPower = new TreeSet<>(Comparator.comparing(Car::getPower));
        this.ownerByCar = new HashMap<>();
    }

    public Collection<Owner> allCarsUniqueOwners() {
        return carsByOwner.keySet();
    }

    public Collection<Car> topThreeCarsByMaxVelocity() {
        return carsByMaxVelocity.stream().limit(3).collect(Collectors.toSet());
    }

    public Collection<Car> allCarsOfBrand(String brand) {
        return carsOfBrand.get(brand);
    }

    public Collection<Car> carsWithPowerMoreThan(int power) {
        for (Car car : carsByPower) {
            if (car.getPower() > power) {
                return carsByPower.tailSet(car);
            }
        }
        return new TreeSet<Car>();
    }

    public Collection<Car> allCarsOfOwner(Owner owner) {
        return carsByOwner.get(owner);
    }

    public int meanOwnersAgeOfCarBrand(String brand) {
        //реализовано с точки зрения машин, а не покупателя
        //т.е. если у нескольких машин один собственник, то собственник считается несколько раз
        int sum = 0;
        List<Car> cars = carsOfBrand.get(brand);
        for (Car car : cars) {
            sum += ownerByCar.get(car.getCarId()).getAge();
        }
        return sum / cars.size();
    }

    public int meanCarNumberForEachOwner() {
        int sum = 0;
        for (Owner owner : carsByOwner.keySet()) {
            sum += carsByOwner.get(owner).size();
        }
        return sum / carsByOwner.size();
    }

    public Car removeCar(int carId) {
        Owner owner = ownerByCar.get(carId);
        Car car = null;
        for (Car carInList : carsByOwner.get(owner)) {
            if (carInList.getCarId() == carId) {
                car = carInList;
                break;
            }
        }
        carsByOwner.get(owner).remove(car);
        if (carsByOwner.get(owner).size() == 0) {
            carsByOwner.remove(owner);
        }
        ownerByCar.remove(carId);
        carsByMaxVelocity.remove(car);
        assert car != null;
        carsOfBrand.get(car.getBrand()).remove(car);
        if (carsOfBrand.get(car.getBrand()).size() == 0) {
            carsOfBrand.remove(car.getBrand());
        }
        carsByPower.remove(car);
        return car;
    }

    public void addNewCar(Car car, Owner owner) {
        if (carsByOwner.containsKey(owner)) {
            carsByOwner.get(owner).add(car);
        } else {
            HashSet<Car> cars = new HashSet<>();
            cars.add(car);
            carsByOwner.put(owner, cars);
        }

        carsByMaxVelocity.add(car);

        if (carsOfBrand.containsKey(car.getBrand())) {
            carsOfBrand.get(car.getBrand()).add(car);
        } else {
            List<Car> cars = new ArrayList<>();
            cars.add(car);
            carsOfBrand.put(car.getBrand(), cars);
        }

        carsByPower.add(car);

        ownerByCar.put(car.getCarId(), owner);
    }
}