package phone;

import application.ApplicationManager;
import exceptions.PhoneNotFoundException;
import interfaces.I_Manager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class PhoneManager implements I_Manager<Phone> {
    Map<String, Phone> phones = new HashMap<>();
    private Phone currentPhone;
    ApplicationManager applicationManager = new ApplicationManager();
    Scanner scanner = new Scanner(System.in);

    public PhoneManager() {
        defaultPhones();
    }

    public void setCurrentPhone(Phone currentPhone) throws PhoneNotFoundException {
        if (phones.isEmpty()) throw new PhoneNotFoundException("Sistemde herhangi bir teledfon kaydı bulunmamaktadır.");
        this.currentPhone = currentPhone;
    }

    public void chooseDefaultPhone(int index) throws PhoneNotFoundException {
        if (phones.isEmpty()) throw new PhoneNotFoundException("Sistemde herhangi bir telefon kaydı bulunmamaktadır.");
        else if (index <= 0 || index > phones.size()) {
            throw new PhoneNotFoundException("Lütfen belirtilen aralıkta bir değer giriniz.");
        }

        Phone phone = phones.values().stream().toList().get(index);
        setCurrentPhone(phone);
    }

    public Phone getCurrentPhone() {
        return currentPhone;
    }

    public Map<String, Phone> getPhones() {
        return phones;
    }

    @Override
    public Phone add(Phone phone) {
        return phones.put(phone.getSerialNumber(), phone);
    }

    @Override
    public Phone remove(Phone phone) {
        return phones.remove(phone.getSerialNumber());
    }

    @Override
    public void update(String item, Consumer<Phone> t) {
        // eklenecek!!!
    }

    public void list() {
        phones.values().forEach(System.out::println);
    }

    private void defaultPhones() {
        Phone firstPhone = new Phone("Huawei", "Mate 20 Lite", 128, "ANDROID");
        Phone secondPhone = new Phone("Iphone", "14 Pro Max", 256, "IOS");
        add(firstPhone);
        add(secondPhone);
    }

    private void defaultApps() {
    }

    public void create() {
        // Phone(String brand, String model, double storageSpace, OperatingSystem operatingSystem, User owner)
        System.out.print("Brand: ");
        String brand = scanner.next();
        System.out.print("Model: ");
        String model = scanner.next();
        System.out.print("Operating System(ios/android/other): ");
        String operatingSystem = scanner.next();
        System.out.print("Storage Space: ");
        double storageSpace = scanner.nextDouble();

        Phone phone = new Phone(brand, model, storageSpace, operatingSystem);
        add(phone);
    }
}
