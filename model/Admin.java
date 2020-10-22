package model;


import model.Tipi;

public class Admin extends Employee{
	public Admin(String user, String pass, String name, String surname,
			BDate bdt,int phone,double salary) {
		super(user, pass, name, surname, bdt,phone,salary);
		setTipi(Tipi.admin);
	}

	public String toString() {
		return "Administrator";
	}
}
