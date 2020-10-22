package model;
public class Library {

	private int bookISBN;
	private String descr;
	private int copyId;
	
	public Library(int isbn,String descr,int copyid) {
		this.bookISBN=isbn;
		this.copyId=copyid;
		this.descr=descr;
	}

	public int getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(int bookISBN) {
		this.bookISBN = bookISBN;
	}

	public int getCopyId() {
		return copyId;
	}

	
}
