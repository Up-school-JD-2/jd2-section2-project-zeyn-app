package person;

import phone.Phone;

public class User extends Person{
    private Phone phone;

    public User(){
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }
}
