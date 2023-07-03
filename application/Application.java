package application;

import idGenerator.IdGenerator;

import java.util.Objects;

public class Application{
    private String id; // operatingSystem and may be UUID
    private String name;
    private String version; // Supplier
    private double size;
    private ApplicationCategory category;
    private IdGenerator idGenerator;
    public Application(String name,  double size, String version, ApplicationCategory category) {
        idGenerator = IdGenerator.getIdGenerator();
        this.name = name;
        this.size = size;
        this.category = category;
        id = idGenerator.generateID(category.name().toLowerCase());
        this.version = version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    public double getSize(){
        return size;
    }

    public ApplicationCategory getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", category=" + category +
                ", size=" + size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Double.compare(that.size, size) == 0 && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(version, that.version) && category == that.category && Objects.equals(idGenerator, that.idGenerator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, version, size, category, idGenerator);
    }
}
