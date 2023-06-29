package application;

import enums.ApplicationCategory;
import idGenerator.IdGenerator;

public class Application{
    private String id; // operatingSystem and may be UUID
    private String name;
    private String version; // Supplier
    private double size;
    private ApplicationCategory category;
    private IdGenerator idGenerator;
    public Application(String name,  double size, ApplicationCategory category) {
        idGenerator = IdGenerator.getIdGenerator();
        this.name = name;
        this.size = size;
        this.category = category;
        id = idGenerator.generateID(category.name().toLowerCase());
        this.version = version;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public double getSize(){
        return size;
    }
    @Override
    public String toString() {
        return "application.Application{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", category=" + category +
                ", size=" + size +
                '}';
    }
}
