package phone;

import application.Application;
import idGenerator.IdGenerator;

import java.util.Map;

public class Phone {
    private String brand;
    private String model;
    private String serialNumber; // create it using Supplier functions
    private double storageSpace;
    private double occupancySpace;
    private double emptySpace;
    private OperatingSystem operatingSystem;
    Map<String, Application> apps;

    public Map<String, Application> getApps() {
        return apps;
    }

    public void setApps(Map<String, Application> apps) {
        this.apps = apps;
    }

    public Phone() {

    }

    public Phone(String brand, String model, double storageSpace, String operatingSystem) {
        IdGenerator idGenerator = IdGenerator.getIdGenerator();
        this.brand = brand;
        this.model = model;
        this.storageSpace = storageSpace;
        this.operatingSystem = OperatingSystem.valueOf(operatingSystem.toUpperCase());
        this.serialNumber = idGenerator.generateID("serialNumber");
        occupancySpace = 0;
        emptySpace = storageSpace;
    }
    public String getSerialNumber() {
        return serialNumber;
    }
    public double getStorageSpace() {
        return storageSpace;
    }

    public void setOccupancySpace(double applicationSize){
        occupancySpace += applicationSize;
        setEmptySpace(storageSpace-occupancySpace);
    }

    private void setEmptySpace(double applicationSize){
        occupancySpace += applicationSize;
    }

    public double getEmptySpace(){
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
}
