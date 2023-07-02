package main;

import application.ApplicationManager;
import comparator.ConnectionEmailComparator;
import comparator.ConnectionNameComparator;
import comparator.ConnectionPhoneNumberComparator;
import comparator.ConnectionSurnameComparator;
import connection.ConnectionManager;
import exceptions.PhoneNotFoundException;
import person.Connection;
import person.User;
import phone.PhoneManager;
import phone.Phone;

import java.util.Comparator;
import java.util.Scanner;

public class PhoneManagementApplication {
    // Bu sınıf, kullanıcı arayüzü sağlamalı ve telefon yönetim işlemlerini gerçekleştirmelidir.
    // Kullanıcıdan girişleri almalı, telefon, uygulama ve kişi yönetimi için gerekli işlemleri yapmalıdır.
    // Ayrıca, depolama alanı kontrolü, veri yedekleme ve geri yükleme gibi işlemleri de gerçekleştirebilirsiniz.
    Scanner scanner = new Scanner(System.in);
    ApplicationManager applicationManager;
    ConnectionManager connectionManager;
    PhoneManager phoneManager;


    public PhoneManagementApplication() {
        applicationManager = new ApplicationManager();
        connectionManager = new ConnectionManager();
        phoneManager = new PhoneManager();
    }

    public void start() {
        // try{
            /*owner = defineOwnerInfo();
            System.out.println("Hoşgeldiniz " + owner.getName());
            defineCurrentPhoneInformation();*/
        firstScreen();
        // }
        /*catch (PhoneNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }*/
    }


    public void firstScreen() {
        System.out.println("************************");
        firstScreen:
        while (true) {
            System.out.println("\n1. Uygulamalara Git\n2. Cihaz Değiştir");
            System.out.print("Seçiminiz: ");
            String choice = scanner.next();
            switch (choice) {
                case "1" -> {
                    applicationsScreen:
                    while (true) {
                        //applicationManager.list();
                        System.out.println("\n\t==============================================");
                        System.out.println("\t1. Kişilerim\n\t2. Mesajlarım\n\t3. Aramalarım\n\t4. Uygulama Ekle\n\t5. Uygulama Kaldır\n\t6. Görünümü Değiştir\n\t7. Filtrele\n\t8. Geri");
                        System.out.println("\t==============================================");
                        System.out.print("\tSeçiminiz: ");
                        choice = scanner.next();
                        switch (choice) {
                            case "1" -> {
                                connections:
                                while (true) {
                                    connectionManager.list();
                                    System.out.println("\n\t\t1. Yeni Kişi Ekle\n\t\t2. Detaylara Git\n\t\t3. Görünümü Değiştir\n\t\t4. Filtrele\n\t\t5. Geri");
                                    System.out.print("\t\tSeçiminiz: ");
                                    choice = scanner.next();
                                    switch (choice) {
                                        case "1" -> connectionManager.create();
                                        case "2" -> {
                                            //while (true) {
                                            System.out.print("\t\tTelefon numarası giriniz: ");
                                            String phoneNumber = scanner.next();
                                            Connection connection = connectionManager.getConnections().get(phoneNumber);
                                            if (connection == null) {
                                                System.out.println("\t\t~~Lütfen geçerli bir numara giriniz~~"); // exception ile nasıl handle edilecek?
                                                continue;
                                            }
                                            System.out.println("\t\t" + connection);
                                            while (true) {
                                                System.out.println("\t\t1. Ara\n\t\t2. Mesaj At\n\t\t3. Gönderilen Mesajlar\n\t\t4. Sil\n\t\t5. Düzenle\n\t\t6. Geri");
                                                System.out.print("\n\t\tSeçiminiz: ");
                                                choice = scanner.next();
                                                switch (choice) {
                                                    case "1" -> connectionManager.call(phoneNumber);
                                                    case "2" -> {
                                                        System.out.print("\t\tMesajınızı giriniz: ");
                                                        scanner.nextLine();
                                                        String message = scanner.nextLine();
                                                        connectionManager.sendMessage(connection.getPhoneNumber(), message);
                                                    }
                                                    case "3" -> {
                                                        System.out.println("\t\t==============================================");
                                                        System.out.println("\t\tSizden " + connection.getName() + " " + connection.getSurname() + " isimli kullanıcıya gönderilen mesajlar");
                                                        connectionManager.getSentMessages(phoneNumber);
                                                        System.out.println("\t\t==============================================\n");
                                                    }
                                                    case "4" -> {
                                                        System.out.print("\tSilmek istediğinizden emin misiniz?(E/H): ");
                                                        String answer = scanner.next();
                                                        if (answer.startsWith("e") || answer.startsWith("E")) {
                                                            connectionManager.remove(connection);
                                                            continue connections;
                                                        }
                                                    }
                                                    case "5" -> {
                                                        // update işlemleri
                                                        System.out.println("\t\tGüncelleme İşlemleri");
                                                        System.out.println("\t\t1. İsim Soyisim Bilgilerini Güncelle\n\t\t2. Email Adres Bilgilerini Güncelle\n\t\t3. Telefon Bilgilerini Güncelle\n\t\t4. Tüm Kullanıcı Bilgilerini Güncelle");
                                                        System.out.print("\t\tGüncellemek istediğiniz alanları seçiniz: ");
                                                        String answer = scanner.next();
                                                        System.out.println("\n\t\t==============================================");
                                                        switch (answer) {
                                                            case "1" ->
                                                                    connectionManager.updateOnlyNameAndSurname(connection);

                                                            case "2" ->
                                                                    connectionManager.updateOnlyEmailAddress(connection);

                                                            case "3" -> {
                                                                connectionManager.updateOnlyPhoneNumber(connection);
                                                                continue connections;
                                                            }

                                                            case "4" -> {
                                                                connectionManager.updateAll(connection);
                                                                continue connections;
                                                            }

                                                        }
                                                        System.out.println("\t" + connection);
                                                        System.out.println("\t\t==============================================\n");
                                                    }
                                                    case "6" -> {
                                                        continue connections;
                                                    }
                                                }

                                            }
                                        }
                                        case "3" -> {
                                            System.out.println("\n\t\t==============================================");
                                            while (true) {
                                                System.out.println("\n\t\t1. Kişilerimi İsimlerine Göre Sırala\n\t\t2. Kişilerimi Soyisimlerine Göre Sırala\n\t\t3. Kişilerimi Telefon Numaralarına Göre Sırala\n\t\t4. Kişilerimi Email Adreslerine Göre Sırala\n\t\t5. Kişilerimi Kategorilerine Göre Grupla\n\t\t6. Kişilerimi Cinsiyetlerine Göre Grupla\n\t\t7. Geri");
                                                System.out.print("\t\tKişiler Nasıl Sıralansın: ");
                                                Comparator<Connection> comparator;
                                                String filed = scanner.next();
                                                switch (filed) {
                                                    case "1" -> {
                                                        comparator = new ConnectionNameComparator();
                                                        System.out.println();
                                                        connectionManager.sort(connectionManager.getConnections(), comparator);
                                                        System.out.println();
                                                    }
                                                    case "2" -> {
                                                        comparator = new ConnectionSurnameComparator();
                                                        System.out.println();
                                                        connectionManager.sort(connectionManager.getConnections(), comparator);
                                                        System.out.println();
                                                    }
                                                    case "3" -> {
                                                        comparator = new ConnectionPhoneNumberComparator();
                                                        System.out.println();
                                                        connectionManager.sort(connectionManager.getConnections(), comparator);
                                                        System.out.println();
                                                    }
                                                    case "4" -> {
                                                        comparator = new ConnectionEmailComparator();
                                                        System.out.println();
                                                        connectionManager.sort(connectionManager.getConnections(), comparator);
                                                        System.out.println();
                                                    }
                                                    case "5" -> {
                                                        System.out.println("\n");
                                                        connectionManager.groupByCategory().forEach((category, connection) -> System.out.println("\t\t" + category.name() + ": " + connection));
                                                    }
                                                    case "6" -> {
                                                        System.out.println("\n");
                                                        connectionManager.groupByGender().forEach((category, connection) -> System.out.println("\t\t" + category.name() + ": " + connection));
                                                    }

                                                    case "7" -> {
                                                        continue connections;
                                                    }
                                                }
                                            }
                                        }
                                        case "4" -> {
                                            // filtreleme işlemleri
                                            while (true) {
                                                System.out.println("\n\t\t1. Aile Bireylerini Getir\n\t\t2. Arkadaşlarını Getir\n\t\t3. İş Arkadaşlarını Getir\n\t\t4. Kadınları Getir\n\t\t5. Erkekleri Getir\n\t\t6. Geri");
                                                System.out.print("\t\tKimler filtrelensin: ");
                                                String filed = scanner.next();
                                                switch (filed) {
                                                    case "1" ->
                                                            connectionManager.filterByCategory("family").forEach(connection -> System.out.println("\t\t" + connection));
                                                    case "2" ->
                                                            connectionManager.filterByCategory("friend").forEach(connection -> System.out.println("\t\t" + connection));
                                                    case "3" ->
                                                            connectionManager.filterByCategory("colleague").forEach(connection -> System.out.println("\t\t" + connection));
                                                    case "4" ->
                                                            connectionManager.filterByGender("f").forEach(connection -> System.out.println("\t\t" + connection));
                                                    case "5" ->
                                                            connectionManager.filterByGender("m").forEach(connection -> System.out.println("\t\t" + connection));

                                                    case "6" -> {
                                                        continue connections;
                                                    }
                                                }
                                            }
                                        }
                                        case "5" -> {
                                            continue applicationsScreen;
                                        }
                                    }
                                }
                            }
                            case "2" -> {
                                messages:
                                while (true) {
                                    System.out.println("\n\t\t1. Yeni Mesaj Oluştur\n\t\t2. Gönderilen Mesajlar\n\t\t3. Geri");
                                    System.out.print("\t\tSeçiminiz: ");
                                    choice = scanner.next();
                                    switch (choice) {
                                        case "1" -> {
                                            System.out.print("\t\tTelefon Numarası Giriniz: ");
                                            String phoneNumber = scanner.next();
                                            System.out.print("\t\tMesajınızı Giriniz: ");
                                            String message = scanner.next();
                                            connectionManager.sendMessage(phoneNumber, message);
                                        }
                                        case "2" -> {
                                            System.out.print("\t\tTelefon Numarası Giriniz: ");
                                            String phoneNumber = scanner.next();
                                            connectionManager.getSentMessages(phoneNumber);
                                        }
                                        case "3" -> {
                                            break messages;
                                        }
                                    }
                                }
                            }
                            case "3" -> {
                                calls:
                                while (true) {
                                    System.out.println("\n\t\t1. Arama Yap\n\t\t2. Geri");
                                    System.out.print("\t\tSeçiminiz: ");
                                    choice = scanner.next();
                                    switch (choice) {
                                        case "1" -> {
                                            System.out.print("\t\tTelefon Numarası Giriniz: ");
                                            String phoneNumber = scanner.next();
                                            connectionManager.call(phoneNumber);
                                        }
                                        case "2" -> {
                                            break calls;
                                        }
                                    }
                                }
                            }
                            case "8" -> {
                                continue firstScreen;
                            }
                        }
                    }
                }
                case "2" -> {
                    System.out.println("1. Cihaz Seç\n2. Cihaz Ekle\n3. Kaldır");
                    System.out.print("Seçiminiz: ");
                    choice = scanner.next();
                    phoneManager.list();
                    switch (choice) {
                        case "1" -> {
                            phoneManager.list();
                            System.out.print("Seçmek istediğiniz cihaz numarasını giriniz: ");
                            int index = scanner.nextInt();
                            Phone phone = phoneManager.getPhones().values().stream().toList().get(index - 1);
                            System.out.println(phone);
                            //User.getOwner().setPhone(phone);
                            // go to first switch
                        }
                        case "2" -> {
                            phoneManager.create();
                            phoneManager.list();
                            // go to first switch
                        }
                        case "3" -> {
                            System.out.print("Seçmek istediğiniz cihaz numarasını giriniz: ");
                            int index = scanner.nextInt();
                            Phone phone = phoneManager.getPhones().values().stream().toList().get(index - 1);
                            phoneManager.remove(phone);
                            phoneManager.list();
                            // go to this switch
                        }
                    }
                }
            }
        }

    }

    public void defineCurrentPhoneInformation() throws PhoneNotFoundException {
        // Phone(String brand, String model, double storageSpace, OperatingSystem operatingSystem)
        System.out.println("Telefon bilgilerini giriniz");
        System.out.print("Marka: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Depolama Alanı: ");
        double storageSpace = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("İşletim sistemi(android/ios/other): ");
        String operatingSystem = scanner.nextLine();

        Phone phone = new Phone(brand, model, storageSpace, operatingSystem);
        phoneManager.setCurrentPhone(phone);
    }

    public User defineOwnerInfo() {
        System.out.print("Adınız: ");
        String name = scanner.nextLine();
        System.out.print("Soyadınız: ");
        String surname = scanner.nextLine();
        System.out.print("Telefon Numaranız: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Email Adresiniz: ");
        String emailAddress = scanner.nextLine();
        System.out.print("Cinsiyet(F/M): ");
        String gender = scanner.nextLine();

        return new User(name, surname, phoneNumber, emailAddress, gender);
    }
}
