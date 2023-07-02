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
/*
        connectionManager.list();

        connectionManager.remove(connection4);

        System.out.println("****************************************");
        connectionManager.list();

        connectionManager.updateOnlyEmailAddress(connection3);
        connectionManager.updateOnlyNameAndSurname(connection2);
        connectionManager.updateOnlyPhoneNumber(connection1);
        connectionManager.updateAll(connection4);

       System.out.println("\n\n****************************************");
        connectionManager.list();

        System.out.println("\n\n****************************************");
        System.out.println(connectionManager.filterByGender("M"));
        System.out.println(connectionManager.filterByCategory("family"));

        System.out.println("\n\n****************************************");
        Map<ConnectionCategory, Long> groupCategory = connectionManager.groupByCategory();
        groupCategory.forEach(((category, countOfConnections) -> System.out.println(category + ": " + countOfConnections) ));

        System.out.println("\n\n****************************************");
        Map<Gender, Long> groupGender = connectionManager.groupByGender();
        groupGender.forEach(((gender, countOfConnections) -> System.out.println(gender + ": " + countOfConnections) ));

        System.out.println("\n\n****************************************");
        connectionManager.call(connection1.getPhoneNumber());

        System.out.println("\n\n****************************************");*/
        /*connectionManager.sendMessage("5241534178", "Merhaba, nasılsın?");
        connectionManager.sendMessage("5241534178", "Merhaba, ben de iyiyim");
        connectionManager.sendMessage("5241534178", "Merhaba, yine ben");
        connectionManager.sendMessage("5241534178", "Merhaba, yine mi ben?");*/

        PhoneManagementApplication phoneManagementApplication = new PhoneManagementApplication();
        phoneManagementApplication.start();
    }
}