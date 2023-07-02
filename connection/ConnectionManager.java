package connection;

import logging.CallRecords;
import person.Connection;

import java.time.LocalDate;
import java.util.*;

public class ConnectionManager extends A_ConnectionManager {
    Scanner scanner = new Scanner(System.in);
    private Map<String, List<String>> sentMessages;
    private Map<String, List<String>> receivedMessages;

    public ConnectionManager() {
        sentMessages = new HashMap<>();
        init();
    }

    @Override
    public void updateOnlyNameAndSurname(Connection connection) {
        System.out.print("\t\tName: ");
        String name = scanner.next();

        System.out.print("\t\tSurname: ");
        String surName = scanner.next();

        update(connection.getPhoneNumber(), updatedConnection -> {
            updatedConnection.setName(name);
            updatedConnection.setSurName(surName);
        });
    }

    @Override
    public void updateOnlyPhoneNumber(Connection connection) {
        String oldPhoneNumber = connection.getPhoneNumber();
        System.out.print("\t\tPhone number: ");
        String phoneNumber = scanner.next();

        update(connection.getPhoneNumber(), connection1 ->
                connection1.setPhoneNumber(phoneNumber)
        ); // exception için bırakıldı

        connections.remove(oldPhoneNumber);
        connections.put(phoneNumber, connection);
    }

    @Override
    public void updateOnlyEmailAddress(Connection connection) {
        System.out.print("\t\tEmail address: ");
        String emailAddress = scanner.next();

        update(connection.getPhoneNumber(), connection1 ->
                connection1.setEmailAddress(emailAddress));
    }

    @Override
    public void updateAll(Connection connection) {
        updateOnlyNameAndSurname(connection);
        updateOnlyEmailAddress(connection);
        updateOnlyPhoneNumber(connection);
    }

    @Override
    public List<Connection> filterByCategory(String specifiedCategory) {
        return filterConnection(connection -> connection.getCategory().name().equalsIgnoreCase(specifiedCategory));
    }

    @Override
    public List<Connection> filterByGender(String specifiedGender) {
        return filterConnection(connection -> connection.getGender().name().equalsIgnoreCase(specifiedGender));
    }// getMyFamily,

    @Override
    public void sendMessage(String phoneNumber, String message) {
        // Connection connection = connections.get(phoneNumber);

        LocalDate date = LocalDate.now();
        message = date + ": " + message + "\n";

        if (!sentMessages.containsKey(phoneNumber)) {
            sentMessages.put(phoneNumber, new ArrayList<>());
        }
        List<String> messages = sentMessages.get(phoneNumber);
        messages.add(message);
        sentMessages.put(phoneNumber, messages);
    }

    public void getSentMessages(String phoneNumber) {
        sentMessages.get(phoneNumber).forEach(message -> System.out.print("\t\t" + message));
    }

    @Override
    public void call(String phoneNumber) {
        getConnect(phoneNumber, connection -> System.out.println("\t\t" + connections.get(phoneNumber).getName() + " " + connections.get(phoneNumber).getSurname() + " calling..."));
        CallRecords.logKaydet(phoneNumber);
    }

    private void init() {
        Connection connection1 = new Connection("Zeynep", "Özdemir", "5241524178", "zeynepozdemir@gmail.com", "FAMILY", "F");
        Connection connection2 = new Connection("Cemre", "Okçu", "5241524179", "cmrkc@gmail.com", "FRIEND", "f");
        Connection connection3 = new Connection("Burcu", "Şık", "5241524188", "brcsk@gmail.com", "COLLEAGUE", "f");
        Connection connection4 = new Connection("Mehmet", "Aşar", "5241524198", "mhmtasr@gmail.com", "friend", "m");
        Connection connection5 = new Connection("Selim", "Tekin", "5241534178", "slmtkn@gmail.com", "FAMILY", "M");
        Connection connection6 = new Connection("Selin", "Tekin", "5245534178", "slntkn@gmail.com", "FAMILY", "F");

        add(connection1);
        add(connection2);
        add(connection3);
        add(connection4);
        add(connection5);
        add(connection6);
    }

    public void create() {
        System.out.println("\n\t\t****** Yeni Kişi Ekle ******");
        System.out.print("\t\tİsim: ");
        String name = scanner.next();
        System.out.print("\t\tSoyisim: ");
        String surname = scanner.next();
        System.out.print("\t\tTelefon numarası: ");
        String phoneNumber = scanner.next();
        System.out.print("\t\tEmail adresi: ");
        String emailAddress = scanner.next();
        System.out.print("\t\tKategori(family, friend, colleague): ");
        String category = scanner.next();
        System.out.print("\t\tGender(F/M): ");
        String gender = scanner.next();

        Connection connection = new Connection(name, surname, phoneNumber, emailAddress, category, gender);

        if (connections.keySet().contains(connection.getPhoneNumber())) {
            System.out.print("\n\t\tGirdiğiniz telefon numarasına ait kullanıcı bilgileri bulunmaktadır. Değişiklikler kaydedilsin mi? (E/H): ");
            String choice = scanner.next();
            if (choice.startsWith("E") | choice.startsWith("e"))
                add(connection);
        } else
            add(connection);
    }
}









