package phone;

import application.Application;
import idGenerator.IdGenerator;

import java.util.Map;
import java.util.Objects;

public class Phone {
    private String brand;
    private String model;
    private String serialNumber; // create it using Supplier functions
    private double storageSpace;
    private double occupancySpace;
    private double emptySpace;
    private OperatingSystem operatingSystem;
    Map<String, Application> apps;
    public Phone(String brand, String model, double storageSpace, String operatingSystem) {
        IdGenerator idGenerator = IdGenerator.getIdGenerator();
        this.brand = brand;
        this.model = model;
        this.storageSpace = storageSpace * 1000;
        this.operatingSystem = OperatingSystem.valueOf(operatingSystem.toUpperCase());
        this.serialNumber = idGenerator.generateID("serialNumber");
        occupancySpace = 0.0;
        emptySpace = this.storageSpace;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public double getStorageSpace() {
        return storageSpace;
    }

    public void setOccupancySpace(double applicationSize) {
        occupancySpace += applicationSize;
        setEmptySpace(storageSpace - occupancySpace);
    }

    public double getOccupancySpace() {
        return occupancySpace;
    }

    private void setEmptySpace(double applicationSize) {
        emptySpace = applicationSize;
    }

    public double getEmptySpace() {
        return emptySpace;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", storage=" + storageSpace +
                ", occupancySpace=" + occupancySpace +
                ", operatingSystem=" + operatingSystem +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(phone.storageSpace, storageSpace) == 0 && Double.compare(phone.occupancySpace, occupancySpace) == 0 && Double.compare(phone.emptySpace, emptySpace) == 0 && Objects.equals(brand, phone.brand) && Objects.equals(model, phone.model) && Objects.equals(serialNumber, phone.serialNumber) && operatingSystem == phone.operatingSystem && Objects.equals(apps, phone.apps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, serialNumber, storageSpace, occupancySpace, emptySpace, operatingSystem, apps);
    }
}
