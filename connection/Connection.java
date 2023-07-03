package connection;

import idGenerator.IdGenerator;
import person.Person;

public class Connection extends Person {
    private ConnectionCategory category;
    private IdGenerator idGenerator = IdGenerator.getIdGenerator();

    public ConnectionCategory getCategory() {
        return category;
    }

    public Connection(String name, String surName, String phoneNumber, String emailAddress, String category, String gender) {
        super(name, surName, phoneNumber, emailAddress, gender);
        this.category = ConnectionCategory.valueOf(category.toUpperCase());
        this.id = idGenerator.generateID(this.category.name().toLowerCase());
    }

    @Override
    public String toString() {
        return super.toString() + ", category=" + category;
    }
}