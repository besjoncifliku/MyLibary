package model;

import java.io.Serializable;

public class Member extends Person{

        //To do: Member to access the app !!!!!!!!!!!! 
        private int idm;
	private String name;
	private String surname;
	private BDate regDate;
	private int phone;
	private String adress;
	
     
	public Member(int id,String name, String surname,int phone,String adress,BDate regDate,String username, String password) {
            
                super(username,password);
                this.idm=id;
		this.name = name;
		this.surname = surname;
		this.regDate = regDate;
		this.phone=phone;
		this.adress=adress;
                setTipi(Tipi.member);
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



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public BDate getRegDate() {
		return regDate;
	}



	public void setRegDate(BDate regDate) {
		this.regDate = regDate;
	}



	public int getPhone() {
		return phone;
	}



	public void setPhone(int phone) {
		this.phone = phone;
	}



	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		this.adress = adress;
	}

        public int getIdm(){
            return idm;
        }
        public void setIdm(int id){
            this.idm=id;
        }

        @Override
	public String toString(){
		return "Member "+idm+" "+name+" "+surname+" "+phone+" "+adress+" "+regDate;
	}
}
