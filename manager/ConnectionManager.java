package manager;

import enums.ConnectionCategory;
import enums.Gender;
import person.Connection;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ConnectionManager extends A_ConnectionManager {
    Scanner scanner = new Scanner(System.in);
    private Map<String, List<String>> sentMessages;
    private Map<String, List<String>> receivedMessages;

    public ConnectionManager(){
        sentMessages = new HashMap<>();
    }
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
    public void sendMessage(String phoneNumber, String message) {
        Connection connection = connections.get(phoneNumber);

        LocalDate date = LocalDate.now();
        message = date + ": " + message;

        if(!sentMessages.containsKey(phoneNumber)){
            sentMessages.put(phoneNumber, new ArrayList<>());
        }
        List<String> messages = sentMessages.get(phoneNumber);
        messages.add(message);
        sentMessages.put(phoneNumber, messages);

        sentMessages.values().forEach(System.out::println);
        // connection.received
    }

    @Override
    public void call(String phoneNumber) {
        getConnect(phoneNumber, connection->System.out.println(connections.get(phoneNumber).getName() + " " + connections.get(phoneNumber).getSurname() + " calling..."));
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










