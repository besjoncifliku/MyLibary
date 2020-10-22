package model;

public class Manager extends Employee {

	public Manager(String user, String pass, String name, String surname,
			BDate bdt,int phone,double salary) {
		super(user, pass, name, surname, bdt,phone,salary);
		setTipi(Tipi.manager);
	}
	
	public String toString() {
		return "Manager";
	}

}
