package phone;

import idGenerator.IdGenerator;
import person.User;
import enums.OperatingSystem;

public class Phone {
    private String brand;
    private String model;
    private String serialNumber; // create it using Supplier functions
    private double storageSpace;
    private double occupancySpace;
    private double emptySpace;
    private OperatingSystem operatingSystem;
    private User owner;
    private IdGenerator idGenerator = IdGenerator.getIdGenerator();

    public Phone() {
        occupancySpace = 0;
        emptySpace = storageSpace;
    }

    public Phone(String brand, String model, double storageSpace, OperatingSystem operatingSystem, User owner) {
        this.brand = brand;
        this.model = model;
        this.storageSpace = storageSpace;
        this.operatingSystem = operatingSystem;
        this.owner = owner;
        owner.setPhone(this);
        this.serialNumber = idGenerator.generateID("serialNumber");
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
        return "Phone.Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", storage=" + storageSpace +
                ", occupancySpace=" + occupancySpace +
                ", operatingSystem=" + operatingSystem +
                '}';
    }
}
