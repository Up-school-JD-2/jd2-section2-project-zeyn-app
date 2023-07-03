package main;

import connection.ConnectionManager;
import person.Connection;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();

        Connection connection1 = new Connection("Zeynep", "Özdemir", "5241524178", "zeynepozdemir@gmail.com", "FAMILY", "F");
       // System.out.println(connection1.getId());

        Connection connection2 = new Connection("Cemre", "Okçu", "5241524179", "cmrkc@gmail.com", "FRIEND", "f");
      //  System.out.println(connection2.getId());

        Connection connection3 = new Connection("Burcu", "Şık", "5241524188", "brcsk@gmail.com", "COLLEAGUE", "f");
       // System.out.println(connection3.getId());

        Connection connection4 = new Connection("Mehmet", "Aşar", "5241524198", "mhmtasr@gmail.com", "friend", "m");
       // System.out.println(connection4.getId());

        Connection connection5 = new Connection("Selim", "Tekin", "5241534178", "slmtkn@gmail.com", "FAMILY", "M");
        Connection connection6 = new Connection("Selin", "Tekin", "5245534178", "slntkn@gmail.com", "FAMILY", "F");


        connectionManager.add(connection1);
        connectionManager.add(connection2);
        connectionManager.add(connection3);
        connectionManager.add(connection4);
        connectionManager.add(connection5);
        connectionManager.add(connection6);


        PhoneManagementApplication phoneManagementApplication = new PhoneManagementApplication();
        phoneManagementApplication.start();
    }
}