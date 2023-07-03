package main;

import application.Application;
import application.ApplicationManager;
import backupAndRestore.RestoreApplication;
import backupAndRestore.RestoreConnection;
import comparator.*;
import connection.ConnectionManager;
import exceptions.*;
import connection.Connection;
import person.User;
import phone.Phone;
import phone.PhoneManager;

import java.util.Comparator;
import java.util.Scanner;

public class PhoneManagementApplication {
    Scanner scanner = new Scanner(System.in);
    Phone phone;
    User owner;
    ConnectionManager connectionManager;
    ApplicationManager applicationManager;
    PhoneManager phoneManager;

    public PhoneManagementApplication() {
        connectionManager = new ConnectionManager();
        defineOwnerInfo();
        phone = definePhoneInfo();
        applicationManager = new ApplicationManager(phone);
        phoneManager = new PhoneManager();
    }

    public void start() {
        System.out.println("\nHoşgeldiniz " + owner.getName() + " " + owner.getSurname());
        //applicationManager.list();
        //applicationManager.getStorageInfo();
        firstScreen();
    }


    public void firstScreen() {
        System.out.println();
        firstScreen:
        while (true) {
            System.out.println("\n1. Uygulamalara Git\n2. Verileri Yedekle&Geri Yükle\n3. Uygulamayı Sonlandır");
            System.out.print("Seçiminiz: ");
            String choice = scanner.next();
            switch (choice) {
                case "1" -> {
                    applicationsScreen:
                    while (true) {
                        //applicationManager.list();
                        System.out.println("\n\t==============================================");
                        System.out.println("\t1. Kişilerim\n\t2. Mesajlarım\n\t3. Aramalarım\n\t4. Uygulamalarım\n\t5. Uygulama Ekle\n\t6. Uygulama Kaldır\n\t7. Geri");
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
                                                        System.out.println("\t\t" + connection);
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
                                            System.out.println("\n\t\t1. Kişilerimi İsimlerine Göre Sırala\n\t\t2. Kişilerimi Soyisimlerine Göre Sırala\n\t\t3. Kişilerimi Telefon Numaralarına Göre Sırala\n\t\t4. Kişilerimi Email Adreslerine Göre Sırala\n\t\t5. Kişilerimi Kategorilerine Göre Grupla\n\t\t6. Kişilerimi Cinsiyetlerine Göre Grupla\n\t\t7. Geri");
                                            while (true) {
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
                            case "4" -> {
                                applicationManager.list();
                                apps:
                                while (true) {
                                    System.out.println("\n\t1. Hafıza durumunu göster\n\t2. Görünümü Değiştir&Filtrele\n\t3. Geri");
                                    System.out.print("\tSeçiminiz: ");
                                    String appChoice = scanner.next();
                                    switch (appChoice) {
                                        case "1" -> applicationManager.getStorageInfo();
                                        case "2" -> {
                                            while (true) {
                                                Comparator<Application> comparator;
                                                System.out.println("\n\t\t1. Uygulamaları İsimlerine Göre Sırala\n\t\t2. Uygulamalarımı Boyutlarına Göre Sırala\n\t\t3. Uygulamalarımı Kategorilerine Göre Grupla\n\t\t4. Uygulamalarımı Kategorilerine Göre Filtrele\n\t\t5. Geri");
                                                System.out.print("\t\tKişiler Nasıl Sıralansın: ");
                                                String comparatorChoice = scanner.next();
                                                switch (comparatorChoice) {
                                                    case "1" -> {
                                                        comparator = new ApplicationNameComparator();
                                                        applicationManager.sort(applicationManager.getApps(), comparator);
                                                    }
                                                    case "2" -> {
                                                        comparator = new ApplicationSizeComparator();
                                                        applicationManager.sort(applicationManager.getApps(), comparator);
                                                    }
                                                    case "3" -> {
                                                        System.out.println();
                                                        applicationManager.groupByCategory().forEach((category, application) -> System.out.println("\t\t" + category.name() + ": " + application));
                                                    }
                                                    case "4" -> {
                                                        // filtreleme işlemleri

                                                        while (true) {
                                                            System.out.println("\n\t\t1. Oyunlarımı Getir\n\t\t2. Sosyal Medya Uygulamalarımı Getir\n\t\t3. Eğitici Uygulamalarımı Getir\n\t\t4. Yaşam/Stil Uygulamalarımı Getir\n\t\t5. Diğer/Default Uygulamalarımı Getir\n\t\t6. Geri");
                                                            System.out.print("\t\tHangi Uygulamalar filtrelensin: ");
                                                            String filed = scanner.next();
                                                            switch (filed) {
                                                                case "1" ->
                                                                        applicationManager.filterByCategory("game").forEach(application -> System.out.println("\t\tGame: " + application));
                                                                case "2" ->
                                                                        applicationManager.filterByCategory("social_media").forEach(application -> System.out.println("\t\tSocial Media" + application));
                                                                case "3" ->
                                                                        applicationManager.filterByCategory("education").forEach(application -> System.out.println("\t\tEducation" + application));
                                                                case "4" ->
                                                                        applicationManager.filterByCategory("life_style").forEach(application -> System.out.println("\t\tLife/Style" + application));
                                                                case "5" ->
                                                                        applicationManager.filterByCategory("other").forEach(application -> System.out.println("\t\tOther: " + application));

                                                                case "6" -> {
                                                                    continue applicationsScreen;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    case "5" -> {
                                                        break apps;
                                                    }
                                                }
                                            }
                                        }
                                        case "3" -> {
                                            break apps;
                                        }
                                    }
                                }

                            }
                            case "5" -> {
                                applicationManager.getPlayStore().forEach(application -> System.out.println("\t\t" + application));
                                System.out.print("\t\tEklemek istediğiniz uygulama numarasını giriniz: ");
                                int chosenApp = scanner.nextInt();
                                chosenApp--;
                                scanner.nextLine();
                                Application application = applicationManager.getPlayStore().get(chosenApp);
                                try {
                                    applicationManager.add(application);
                                    applicationManager.list();
                                } catch (NoEnoughEmptySpaceException e) {
                                    System.out.println("\t\tUygulama eklemek için telefonda yeterince yer yok.");
                                } catch (LoadingExistException e) {
                                    System.out.println("\t\tYüklemeye çalıştığınız uygulama telefonda mevcuttur.");
                                }
                            }
                            case "6" -> {
                                try {
                                    applicationManager.list();
                                    System.out.print("\t\tKaldırmak istediğiniz uygulama numarasını giriniz: ");
                                    int chosenApp = scanner.nextInt();
                                    scanner.nextLine();
                                    Application application = applicationManager.getApps().values().stream().toList().get(chosenApp);
                                    applicationManager.remove(application);
                                } catch (NotFoundApplicationException e) {
                                    System.out.println(e.getMessage());
                                }

                            }
                            case "7" -> {
                                continue firstScreen;
                            }
                        }
                    }
                }
                case "2" ->{
                    System.out.println("\n\t\t\tÖnemli bilgi: her ekleme işleminde veriler yedeklenmektedir. Bu yüzden yedekleme işlemi ayrıca seçenek olarak sunulmamıştır");
                    System.out.println("\t\t1. Kişilerimi Yedekle\n\t\t2. Uygulamalarımı Yedekle\n\t\t3. Geri");
                    System.out.print("\t\tSeçiminiz: ");
                    String restoreChoice = scanner.next();
                    switch (restoreChoice){
                        case "1" ->{
                            RestoreConnection restoreConnection = new RestoreConnection();
                            connectionManager.setConnections(restoreConnection.readDataFromFile());
                            connectionManager.getConnections().values().forEach(connection -> System.out.println("\t\t" + connection));
                        }
                        case "2" ->{
                            RestoreApplication restoreApplication = new RestoreApplication();
                            applicationManager.setApps(restoreApplication.readDataFromFile());
                            applicationManager.getApps().values().forEach(application -> System.out.println("\t\t" + application));
                        }
                        case "3" ->{
                            //continue firstScreen;
                        }
                    }
                }

                /*case "2" -> System.out.println("\t\tÜzerinde çalışılıyor");
                {
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

                }*/
                case "3" -> {
                    break firstScreen;
                }
            }
        }

    }

    public void defineOwnerInfo() {

        System.out.println("Kullanıcı Bilgilerinizi Girer Misiniz");
            System.out.print("İsim: ");
            String name = scanner.next();
            System.out.print("Soyisim: ");
            String surname = scanner.next();
            owner = new User(name, surname);
    }

    public Phone definePhoneInfo() {
        System.out.println("Telefon bilgilerinizi girebilir misiniz");
        System.out.print("Brand: ");
        String brand = scanner.next();
        System.out.print("Model: ");
        String model = scanner.next();
        System.out.print("Operating System(ios/android/other): ");
        String operatingSystem = scanner.next();
        System.out.print("Storage Space(GB): ");
        double storageSpace = scanner.nextDouble();

        return new Phone(brand, model, storageSpace, operatingSystem);
    }
}
