package connection;

import exceptions.ConnectionCategoryInvalidException;
import exceptions.ConnectionGenderInvalidException;
import exceptions.EmailAddressInvalidException;
import exceptions.PhoneNumberInvalidException;

import java.time.LocalDate;
import java.util.*;

public class ConnectionManager extends A_ConnectionManager {
    Scanner scanner = new Scanner(System.in);
    private Map<String, List<String>> sentMessages;

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
        return filter(connection -> connection.getCategory().name().equalsIgnoreCase(specifiedCategory));
    }

    @Override
    public List<Connection> filterByGender(String specifiedGender) {
        return filter(connection -> connection.getGender().name().equalsIgnoreCase(specifiedGender));
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
        if (sentMessages.containsKey(phoneNumber))
            sentMessages.get(phoneNumber).forEach(message -> System.out.print("\t\t" + message));
        else
            System.out.println("\t\tHenüz bir mesaj gönderilmemiş.");
    }

    @Override
    public void call(String phoneNumber) {
        getConnect(phoneNumber, connection -> System.out.println("\t\t" + connections.get(phoneNumber).getName() + " " + connections.get(phoneNumber).getSurname() + " calling..."));
    }

    private void init() {
        Connection connection1 = new Connection("Zeynep", "Özdemir", "05241524178", "zeynepozdemir@gmail.com", "FAMILY", "F");
        Connection connection2 = new Connection("Cemre", "Okçu", "05241524179", "cmrkc@gmail.com", "FRIEND", "f");
        Connection connection3 = new Connection("Burcu", "Şık", "05241524188", "brcsk@gmail.com", "COLLEAGUE", "f");
        Connection connection4 = new Connection("Mehmet", "Aşar", "05241524198", "mhmtasr@gmail.com", "friend", "m");
        Connection connection5 = new Connection("Selim", "Tekin", "05241534178", "slmtkn@gmail.com", "FAMILY", "M");
        Connection connection6 = new Connection("Selin", "Tekin", "05245534178", "slntkn@gmail.com", "FAMILY", "F");

        add(connection1);
        add(connection2);
        add(connection3);
        add(connection4);
        add(connection5);
        add(connection6);
    }

    public void create() {
        try {
            System.out.println("\n\t\t****** Yeni Kişi Ekle ******");
            System.out.print("\t\tİsim: ");
            String name = scanner.next();
            System.out.print("\t\tSoyisim: ");
            String surname = scanner.next();
            System.out.print("\t\tTelefon numarası: ");
            String phoneNumber = scanner.next();
            if (!isValidPhoneNumber(phoneNumber))
                throw new PhoneNumberInvalidException("\t\t\tTelefon numarası 0(sıfır) ile başlamalıdır ve 11 karakter olmalıdr.");
            System.out.print("\t\tEmail adresi: ");
            String emailAddress = scanner.next();
            if (!isValidEmail(emailAddress))
                throw new EmailAddressInvalidException("\t\t\tEmail adresi bir tane '@' içermelidir ve '.com'  ile bitmelidir");
            System.out.print("\t\tKategori(family, friend, colleague): ");
            String category = scanner.next();
            if(!isValidCategory(category)) throw new ConnectionCategoryInvalidException("\t\t\tKategori ismini doğru yazdığınızdan emin olunuz.");
            System.out.print("\t\tGender(F/M): ");
            String gender = scanner.next();
            if(!isValidGender(gender)) throw new ConnectionGenderInvalidException("\t\t\tKategori ismini doğru yazdığınızdan emin olunuz.");

            Connection connection = new Connection(name, surname, phoneNumber, emailAddress, category, gender);

            if (connections.containsKey(connection.getPhoneNumber())) {
                System.out.print("\n\t\tGirdiğiniz telefon numarasına ait kullanıcı bilgileri bulunmaktadır. Değişiklikler kaydedilsin mi? (E/H): ");
                String choice = scanner.next();
                if (choice.startsWith("E") | choice.startsWith("e"))
                    add(connection);
            } else
                add(connection);
        } catch (EmailAddressInvalidException e) {
            System.out.println(e.getMessage());
        } catch (PhoneNumberInvalidException e) {
            System.out.println(e.getMessage());
        }
        catch (ConnectionCategoryInvalidException e) {
            System.out.println(e.getMessage());
        }
        catch (ConnectionGenderInvalidException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isValidEmail(String email) {
        return (email.contains("@") && email.endsWith(".com"));
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        return (phoneNumber.startsWith("0") && phoneNumber.length() == 11);
    }
    public boolean isValidGender(String gender){
        return (gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("F"));
    }
    public boolean isValidCategory(String category){
        return (category.equalsIgnoreCase("family") || category.equalsIgnoreCase("friend") || category.equalsIgnoreCase("COLLEAGUE"));
    }
}










