package model;

import java.io.Serializable;

public interface Administration extends Serializable {

	public void changePass(String username,String s);
	public void changeUserName(String username,String newUsername);
	public void changeLevel(String username,Tipi t);
//	public void editUser(int pos, model.Employee e);
//	public Employee findUser(String username);
	
}
