package manager;

import enums.ConnectionCategory;
import enums.Gender;
import person.Connection;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConnectionManager extends A_ConnectionManager {
    Scanner scanner = new Scanner(System.in);


    public void list() {
        connections.values().forEach(System.out::println);
    }

    public void updateOnlyNameAndSurname(Connection connection) {
        System.out.print("Name: ");
        String name = scanner.next();

        System.out.print("Surname: ");
        String surName = scanner.next();

        update(connection.getPhoneNumber(), updatedConnection -> {
            updatedConnection.setName(name);
            updatedConnection.setSurName(surName);
        });
    }

    public void updateOnlyPhoneNumber(Connection connection) {
        System.out.print("Phone number: ");
        String phoneNumber = scanner.next();

        update(connection.getPhoneNumber(), connection1 ->
                connection1.setPhoneNumber(phoneNumber)
        );
    }

    public void updateOnlyEmailAddress(Connection connection) {
        System.out.print("Email address: ");
        String emailAddress = scanner.next();

        update(connection.getPhoneNumber(), connection1 ->
                connection1.setEmailAddress(emailAddress));
    }

    public void updateAll(Connection connection) {
        updateOnlyNameAndSurname(connection);
        updateOnlyEmailAddress(connection);
        updateOnlyPhoneNumber(connection);
    }


    /*private Map<String, Connection> filterMap(Predicate<Connection> connectionPredicate){

        return connections.values().stream().collect(Collectors.groupingBy(Connection::getPhoneNumber, connections.values().stream().filter(connectionPredicate)));
    }*/

    public List<Connection> filterByGender(String specifiedGender) {
        return filterConnection(connection -> connection.getGender().name().equalsIgnoreCase(specifiedGender));
    }

    @Override
    public List<Connection> filterByCategory(String specifiedCategory) {
        return filterConnection(connection -> connection.getCategory().name().equalsIgnoreCase(specifiedCategory));
    }

    @Override
    public List<Connection> sortByName(int length) {
        return null;
    }

    public void sendMessage(String phoneNumber) {

    }

    @Override
    public void call(String phoneNumber) {

    }

    @Override
    public Map<ConnectionCategory, Long> groupByCategory() {
        return connections.values().stream().collect(Collectors.groupingBy(Connection::getCategory, Collectors.counting()));
    }

    @Override
    public Map<Gender, Long> groupByGender() {
        return connections.values().stream().collect(Collectors.groupingBy(Connection::getGender, Collectors.counting()));
    }
}










