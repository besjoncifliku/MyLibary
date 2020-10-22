package model;

import java.util.ArrayList;

public class BorrowBook {
    
    private Book book;
    private BDate gd;
    private BDate td;

    public BorrowBook(Book book, BDate gd, BDate td) {
        this.book = book;
        this.gd = gd;
        this.td = td;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BDate getGd() {
        return gd;
    }

    public void setGd(BDate gd) {
        this.gd = gd;
    }

    public BDate getTd() {
        return td;
    }

    public void setTd(BDate td) {
        this.td = td;
    }

    @Override
    public String toString() {
        return "BorrowBook{" + "book=" + book + ", gd=" + gd + ", td=" + td + '}';
    }
    
    
    
}
