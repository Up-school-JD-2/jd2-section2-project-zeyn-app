package person;

import enums.ConnectionCategory;
import idGenerator.IdGenerator;
public class Connection extends Person{
    private ConnectionCategory category;
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
