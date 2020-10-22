package model;
import model.Tipi;



public abstract class User{

	private int id;
	private String username;
	private String password;
	private static int nr_user=1;
	protected Tipi tipi;

	public User(String username, String pass) {
		
		this.id = nr_user++;
		this.username = username;
		this.password = pass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return password;
	}
	public void setPass(String pass) {
		this.password = pass;
	}
	public int getId() {
		return id;
	}
	public abstract void setTipi(Tipi t);
		
	public Tipi getTipi() {
		return tipi;
	}
//	@Override
//	public String toString() {
//		return "User: " + username + " "+ password;
//	}
	
	
	
}
