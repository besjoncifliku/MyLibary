package model;


import model.Tipi;
import model.User;

public class Employee extends User {

	private String name;
	private String surname;
	private BDate bdt;
	private int phone;
	private String email;
	private double salary;
        private boolean priviledge=false;//if it is 1 it is an employeer if it is 0 it is a simple user
	
	   public Employee(String user, String pass, String name,
            String surname, BDate bdt, int phone, double salary) {
		super(user, pass);
		this.name = name;
		this.surname = surname;
		this.bdt = bdt;
		this.phone=phone;
		this.salary=salary;
                this.priviledge=false;
	}

        public boolean getPriviledge(){
            return priviledge;
        }
        
        public void setPriviledge(boolean priviledge){
            this.priviledge=priviledge;
        }
        
        
	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public BDate getBdt() {
		return bdt;
	}
	

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}


	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public void setTipi(Tipi tipi) {
		super.tipi=tipi;
	}
        @Override
	public String toString(){
		return "Employee "+name+" "+surname+" "+phone+" "+email+" "+salary;
	}
}
