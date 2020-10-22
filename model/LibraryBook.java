package model;

public class LibraryBook {
    
    private int ISBN;
    private String name;
    private int count;

    public LibraryBook(int ISBN, String name) {
        this.ISBN = ISBN;
        this.name = name;
        this.count = 1;
    }
    
    public LibraryBook(int ISBN, String name,int cnt){
        this.ISBN = ISBN;
        this.name = name;
        this.count = cnt;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public void addCopy() {
		this.count++;
	}
	

    @Override
    public String toString(){
        return ISBN+" "+name+" "+count;
    }
    
    
}
