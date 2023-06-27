package manager;

import exceptions.EmailAddressInvalidException;
import exceptions.PhoneNumberInvalidException;
import person.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConnectionManager implements Manager<Connection> {

    Map<String, Connection> connections;
    Scanner scanner = new Scanner(System.in);


    public ConnectionManager() {
        connections = new HashMap<>();
    }


    @Override
    public Connection add(Connection connection) {
        return connections.put(connection.getPhoneNumber(), connection);
    }

    @Override
    public boolean remove(Connection connection) {
        return connections.keySet().remove(connection);
    }

    @Override
    public void update(String phoneNumber, Consumer<Connection> connectionConsumer) {
        Connection connection = connections.get(phoneNumber);
        connectionConsumer.accept(connection);
    }

    public void list() {
        connections.values().stream().forEach(System.out::println);
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

        update(connection.getPhoneNumber(), connection1 -> {
            connection1.setPhoneNumber(phoneNumber);
        });
    }

    public void updateOnlyEmailAddress(Connection connection) {
        System.out.print("Email address: ");
        String emailAddress = scanner.next();

        update(connection.getPhoneNumber(), connection1 -> {
            connection1.setEmailAddress(emailAddress);
        });
    }

    public void updateAll(Connection connection) {
        updateOnlyNameAndSurname(connection);
        updateOnlyEmailAddress(connection);
        updateOnlyPhoneNumber(connection);
    }

    private List<Connection> filterConnection(Predicate<Connection> filteredCondition) {
        return connections.values().stream().filter(filteredCondition).toList();
    }

    /*private Map<String, Connection> filterMap(Predicate<Connection> connectionPredicate){

        return connections.values().stream().collect(Collectors.groupingBy(Connection::getPhoneNumber, connections.values().stream().filter(connectionPredicate)));
    }*/

    public List<Connection> filterByGender(String specifiedGender) {
        Predicate<Connection> connectionPredicate = connection -> connection.getGender().name().equalsIgnoreCase(specifiedGender);
        return filterConnection(connectionPredicate);
    }

    public List<Connection> filterByNameLength(int length){
        Predicate<Connection> mapPredicate = connections_ -> connections_.getName().length()>=length;
        return filterConnection(mapPredicate);
        }
    }
/*
    public void createConnection(String name, String surname, String phoneNumber, String emailAddress, String category) throws PhoneNumberInvalidException, EmailAddressInvalidException{
        if(!isValidPhoneNumber(phoneNumber))
            throw new PhoneNumberInvalidException("Phone number must be 11 digits, starts with 0 and contain only numbers!");
        if(!isValidEmailAddress(emailAddress))throw new EmailAddressInvalidException("Email must contain @ and ends with \".com\"");

        Connection connection = new Connection(name, surname, phoneNumber, emailAddress, category);
        connections.put(connection.getPhoneNumber(), connection);
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 11 && phoneNumber.startsWith("0")) {
            for (Character character : phoneNumber.toCharArray()) {
                if (!Character.isDigit(character)) return false;
            }
        }
        return true;
    }

    private boolean isValidEmailAddress(String email) {
        return (email.contains("@") && email.endsWith(".com")) ? true : false;
    }

 */

