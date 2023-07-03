package backupAndRestore;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class RestoreApplication {

    public void readDataFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }
/*
    public static void main(String[] args) {
        DataReader dataReader = new DataReader();
        dataReader.readDataFromFile("data.txt");
    }*/
}

