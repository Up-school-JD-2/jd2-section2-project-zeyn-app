package main;

import backupAndRestore.RestoreApplication;
import backupAndRestore.RestoreConnection;
import connection.Connection;

public class Main {
    public static void main(String[] args) {
        RestoreApplication restoreApplication = new RestoreApplication();
        restoreApplication.readDataFromFile("Connection.txt");

        RestoreConnection restoreConnection = new RestoreConnection();
        //restoreConnection.readDataFromFile("Connection.txt").values().forEach(System.out::println);

        PhoneManagementApplication phoneManagementApplication = new PhoneManagementApplication();
        phoneManagementApplication.start();

    }
}