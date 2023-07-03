package backupAndRestore;


import application.Application;
import application.ApplicationCategory;
import domain.Constant;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestoreApplication {
    private String fileName = Constant.APPLICATION_FILE;
    private Map<String, Application> restoredApplications = new HashMap<>();

    public Map<String, Application> readDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringToMapElement(line);
            }
            return restoredApplications;
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return null;
    }

    public void stringToMapElement(String line){
        String[] keyAndValue = line.split(":");
        String key = keyAndValue[0];
        String value = keyAndValue[1];

        String[] connectionFieldsWithTitle = value.split(", ");
        String name = connectionFieldsWithTitle[0];
        String version = connectionFieldsWithTitle[1];
        String category = connectionFieldsWithTitle[2];
        String size = connectionFieldsWithTitle[3];

        name = name.split("=")[1];
        version = version.split("=")[1];
        category = category.split("=")[1];
        size = size.split("=")[1];
        size = size.substring(1, size.length()-1);
        double doubleSize = Double.parseDouble(size);

        Application application = new Application(name, doubleSize, version, ApplicationCategory.valueOf(category));
        restoredApplications.put(key, application);
    }

}

