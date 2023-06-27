package idGenerator;

import enums.ConnectionCategory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

public class IdGenerator {
    Map<String, Supplier<String>> idSuppliers;
    public IdGenerator(){
        idSuppliers = new HashMap<>();
        initIdGenerator();
    }
    public String generateID(String supplierName){
        Supplier<String> supplier = idSuppliers.get(supplierName);
        if(supplier!=null){
            return supplier.get();
        }
        else
            return "Supplier not found";
    }
    private void registerIDSupplier(String supplierName, Supplier<String> supplier){
        idSuppliers.put(supplierName, supplier);
    }

    private void initIdGenerator(){
        registerIDSupplier("family", () -> ConnectionCategory.FAMILY + UUID.randomUUID().toString());
        registerIDSupplier("friend", ()-> ConnectionCategory.FRIEND + UUID.randomUUID().toString());
        registerIDSupplier("colleague", ()->ConnectionCategory.COLLEAGUE + UUID.randomUUID().toString());
        registerIDSupplier("other", ()->ConnectionCategory.OTHER + UUID.randomUUID().toString());
    }
}
