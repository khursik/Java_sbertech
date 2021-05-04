package ru.sbt.reflection;

import java.util.Objects;

public class Car {
    private final int carId;
    private final String brand;
    private final String modelName;
    private final int maxVelocity;
    private final int power;
    private final int ownerId;

    public Car(int carId, String brand, String modelName, int maxVelocity, int power, int ownerId) {
        this.carId = carId;
        this.brand = brand;
        this.modelName = modelName;
        this.maxVelocity = maxVelocity;
        this.power = power;
        this.ownerId = ownerId;
    }

    public int getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelName() {
        return modelName;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public int getPower() {
        return power;
    }

    public int getOwnerId() {
        return ownerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return getCarId() == car.getCarId() && getMaxVelocity() == car.getMaxVelocity() && getPower() == car.getPower() && getOwnerId() == car.getOwnerId() && getBrand().equals(car.getBrand()) && getModelName().equals(car.getModelName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarId(), getBrand(), getModelName(), getMaxVelocity(), getPower(), getOwnerId());
    }


}