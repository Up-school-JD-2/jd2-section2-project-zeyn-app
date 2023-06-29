package idGenerator;

import enums.ConnectionCategory;
import enums.OperatingSystem;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;

public class IdGenerator {
    private static IdGenerator idGenerator;
    public static IdGenerator getIdGenerator() {
        if(idGenerator==null)
            idGenerator = new IdGenerator();
        return idGenerator;
    }
    Map<String, Supplier<String>> idSuppliers;

    private IdGenerator() {
        idSuppliers = new HashMap<>();
        initIdGenerator();
    }

    public String generateID(String supplierName) {
        Supplier<String> supplier = idSuppliers.get(supplierName);
        if (supplier != null) {
            return supplier.get();
        } else
            return "Supplier not found";
    }

    private void registerIDSupplier(String supplierName, Supplier<String> supplier) {
        idSuppliers.put(supplierName, supplier);
    }

    private void initIdGenerator() {
        registerIDSupplier("family", () -> ConnectionCategory.FAMILY + "-" + UUID.randomUUID());
        registerIDSupplier("friend", () -> ConnectionCategory.FRIEND + "-" + UUID.randomUUID());
        registerIDSupplier("colleague", () -> ConnectionCategory.COLLEAGUE + "-" + UUID.randomUUID());
        registerIDSupplier("ios", () -> OperatingSystem.IOS + "-" + UUID.randomUUID());
        registerIDSupplier("android", () -> OperatingSystem.ANDROID + "-" + UUID.randomUUID());
        registerIDSupplier("other", () -> OperatingSystem.OTHER + "-" + UUID.randomUUID());
        registerIDSupplier("serialNumber", () -> generateRandomCharacters() + "-" + UUID.randomUUID());
        registerIDSupplier("version", () -> generateRandomCharacters().substring(0, 3) + "-" + UUID.randomUUID());
        registerIDSupplier("owner", () -> "OWNER" + UUID.randomUUID().toString().substring(0, 7));
    }

    private String generateRandomCharacters() {
        Random random = new Random();
        String randomCharacters = "";
        for (int i = 0; i < 4; i++) {
            char c = (char)(random.nextInt(26) + 'A');
            randomCharacters = c + randomCharacters;
        }
        return randomCharacters;
    }
}
