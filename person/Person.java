package person;

import connection.Gender;
import idGenerator.IdGenerator;

import java.util.Objects;

public abstract class Person {
    protected String id;
    protected String name;
    protected String surname;
    protected String phoneNumber;
    protected String emailAddress;
    protected Gender gender;

    public Person() {
        IdGenerator idGenerator = IdGenerator.getIdGenerator();
        id = idGenerator.generateID("family");
    }

    public String getId() {
        return id;
    }

    public Person(String name, String surname, String phoneNumber, String emailAddress, String gender) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.gender = Gender.valueOf(gender.toUpperCase());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurName(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Gender getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(phoneNumber, person.phoneNumber) && Objects.equals(emailAddress, person.emailAddress) && gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, phoneNumber, emailAddress, gender);
    }
}
