import manager.ConnectionManager;
import person.Connection;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        ConnectionManager connectionManager = new ConnectionManager();

        Connection connection1 = new Connection("Zeynep", "Özdemir", "5241524178", "zeynepozdemir@gmail.com", "FAMILY", "F");
        System.out.println(connection1.getId());

        Connection connection2 = new Connection("asd", "Özdemir", "5241524179", "zeynepozdemir@gmail.com", "FRIEND", "m");
        System.out.println(connection2.getId());

        Connection connection3 = new Connection("ljd", "Özdemir", "5241524188", "zeynepozdemir@gmail.com", "COLLEAGUE", "m");
        System.out.println(connection3.getId());

        Connection connection4 = new Connection("dsg", "Özdemir", "5241524198", "zeynepozdemir@gmail.com", "other", "o");
        System.out.println(connection4.getId());


        connectionManager.add(connection1);
        connectionManager.add(connection2);
        connectionManager.add(connection3);
        connectionManager.add(connection4);

        connectionManager.list();
        System.out.println("****************************************");


        connectionManager.updateOnlyEmailAddress(connection3);
        connectionManager.updateOnlyNameAndSurname(connection2);
        connectionManager.updateOnlyPhoneNumber(connection1);
        connectionManager.updateAll(connection4);

        System.out.println("\n\n****************************************");
        connectionManager.list();

        System.out.println("\n\n****************************************");
        System.out.println(connectionManager.filterByGender("M"));
        System.out.println(connectionManager.filterByNameLength(5));
    }
}
