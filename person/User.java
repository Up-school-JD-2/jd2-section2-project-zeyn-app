package person;

import connection.Gender;
import idGenerator.IdGenerator;
public class User extends Person {
    public User(String name, String surname, String phoneNumber, String emailAddress, String gender) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.gender = Gender.valueOf(gender.toUpperCase().substring(0, 1));
        IdGenerator idGenerator = IdGenerator.getIdGenerator();
        id = idGenerator.generateID("owner");
    }
    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
