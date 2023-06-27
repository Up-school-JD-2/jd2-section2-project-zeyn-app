package person;

import enums.ConnectionCategory;
import idGenerator.IdGenerator;
import java.util.List;
import java.util.Map;

public class Connection extends Person{
    private ConnectionCategory category;
    public Map<String, List<String>> sentMessages;
    public Map<String, List<String>> receivedMessages;
    private IdGenerator idGenerator = new IdGenerator();

    public ConnectionCategory getCategory() {
        return category;
    }
    public Connection(String name, String surName, String phoneNumber, String emailAddress, String category, String gender) {
        super(name, surName, phoneNumber, emailAddress, gender);
        this.category = ConnectionCategory.valueOf(category.toUpperCase());
        this.id = idGenerator.generateID(this.category.name().toLowerCase());
    }
}
