package model;

import java.io.Serializable;

public class Publisher extends Person{

	private int PUBID;
	private String name;
	private int phone;
	private String contactPerson;
	private String adress;
	public Publisher(int pUBID, String name, int phone, String contactPerson, String adress,String username,String password) {
                super(username,password);
		PUBID = pUBID;
		this.name = name;
		this.phone = phone;
		this.contactPerson = contactPerson;
		this.adress = adress;
                setTipi(Tipi.pub);

	}
	public int getPUBID() {
		return PUBID;
	}
	public void setPUBID(int pUBID) {
		PUBID = pUBID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	@Override
	public String toString() {
		return "Publisher " + name;
	}
	
	
}
