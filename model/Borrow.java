package model;

public class Borrow {

    private String member;
    private int idm;
    private BDate borrowDate;
    private BDate givenDate;
    private String bookTitle;
    private int ISBN;
    private int copyID;

    public Borrow(String member, int idm, BDate borrowDate, BDate givenDate, String bookTitle, int ISBN, int copyID) {
        this.member = member;
        this.idm = idm;
        this.borrowDate = borrowDate;
        this.givenDate = givenDate;
        this.bookTitle = bookTitle;
        this.ISBN = ISBN;
        this.copyID = copyID;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }

    public BDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(BDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public BDate getGivenDate() {
        return givenDate;
    }

    public void setGivenDate(BDate givenDate) {
        this.givenDate = givenDate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getCopyID() {
        return copyID;
    }

    public void setCopyID(int copyID) {
        this.copyID = copyID;
    }

    @Override
    public String toString() {
        return "Borrow{" + "borrowDate=" + borrowDate + ", givenDate=" + givenDate + ", bookTitle=" + bookTitle + ", ISBN=" + ISBN + ", copyID=" + copyID + '}';
    }

}
