package person;

import enums.Gender;
import idGenerator.IdGenerator;
import phone.Phone;

public class User extends Person{
    private Phone phone;
    private IdGenerator idGenerator;
    /*
     protected String id;
    protected String name;
    protected String surname;
    protected String phoneNumber;
    protected String emailAddress;
    protected Gender gender;
     */

    public User(String name, String surname, String phoneNumber, String emailAddress, String gender){
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.gender = Gender.valueOf(gender.toUpperCase().substring(0,1));
        idGenerator = IdGenerator.getIdGenerator();
        id = idGenerator.generateID("owner");
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
