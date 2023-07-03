package backupAndRestore;

import connection.Connection;
import domain.Constant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestoreConnection {
    String fileName = Constant.CONNECTION_FILE;
    private Map<String, Connection> restoredConnections = new HashMap<>();
    public Map<String, Connection> readDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringToMapElement(line);
            }
            return restoredConnections;
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return null;
    }

    // name='Selin', surname='Tekin', gender='F', phoneNumber='05245534178', emailAddress='slntkn@gmail.com'
    public void stringToMapElement(String line){
        String[] keyAndValue = line.split(":");
        String value = keyAndValue[1];
        String[] connectionFieldsWithTitle = value.split(", ");
        String name = connectionFieldsWithTitle[0];
        String surname = connectionFieldsWithTitle[1];
        String gender = connectionFieldsWithTitle[2];
        String phoneNumber = connectionFieldsWithTitle[3];
        String emailAddress = connectionFieldsWithTitle[4];
        String category = connectionFieldsWithTitle[5];

        name = name.split("=")[1];
        surname = surname.split("=")[1];
        gender = gender.split("=")[1];
        gender = String.valueOf(gender.toCharArray()[1]);
        phoneNumber = phoneNumber.split("=")[1];
        emailAddress = emailAddress.split("=")[1];
        category = category.split("=")[1];

        Connection connection = new Connection(name, surname, phoneNumber, emailAddress, category, gender);
        restoredConnections.put(phoneNumber, connection);
    }


}
