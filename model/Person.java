package model;

import model.Tipi;
import model.User;

public class Person extends User {

    public Person(String user, String pass) {
        super(user, pass);

    }

    @Override
    public void setTipi(Tipi tipi) {
        super.tipi = tipi;
    }

    public String toString() {
        return "Person ";
    }
}
