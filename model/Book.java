package model;

public class Book {

	private int ISBN;
	private String title;
	private String author;
	private String pulisher;  
	private BDate pubYear;
	private String review;
	private int pubId;
	public Book(int iSBN, String title, String author, String pulisher, BDate pubYear, String review, int pubId) {
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.pulisher = pulisher;
		this.pubYear = pubYear;
		this.review = review;
		this.pubId = pubId;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int iSBN) {
		ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
 
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPulisher() {
		return pulisher;
	}
	public void setPulisher(String pulisher) {
		this.pulisher = pulisher;
	}
	public BDate getPubYear() {
		return pubYear;
	}
	public void setPubYear(BDate pubYear) {
		this.pubYear = pubYear;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public int getPubId() {
		return pubId;
	}
	public void setPubId(int pubId) {
		this.pubId = pubId;
	}
	@Override
	public String toString() {
		return ISBN + " "+ title+" " + author +" "+ pulisher;
	}
	
	
	
	
	
}
