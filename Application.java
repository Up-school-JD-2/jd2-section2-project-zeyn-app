import enums.ApplicationCategory;

public class Application{
    // Bu sınıf, uygulamaların adı, sürümü ve boyutu gibi
    // bilgilerini içermelidir.
    private String id;
    private String name;
    private String version; // Supplier
    private double size;
    private ApplicationCategory category;

    public Application() {
    }

    public Application(String name, String version, double size, ApplicationCategory category) {
        this.name = name;
        this.version = version;
        this.size = size;
        this.category = category;
    }

    public double getSize(){
        return size;
    }

    @Override
    public String toString() {
        return "Application{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", category=" + category +
                ", size=" + size +
                '}';
    }
}
