package readRW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.BDate;
import model.Borrow;
import model.Library;
import model.Member;

public class BorrowRW //implements Administration
{

    private ArrayList<Borrow> borrow;

    public BorrowRW() {

        borrow = new ArrayList<Borrow>();
        borrow = readBook();

    }

    public ArrayList<Borrow> readBook() {
        Connection conn = null;
        String sql = "{ call readBooks() }";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                //public Borrow(BDate borrowDate, BDate givenDate, String bookTitle, int ISBN, int copyID) {

                borrow.add(new Borrow(rs.getString("Name"), rs.getInt("Member_IDM"), new BDate(rs.getString("Date_From")), new BDate(rs.getString("Date_To")),
                        rs.getString("Title"), rs.getInt("ISBN"), rs.getInt("Library_BookID")));

            }
            return borrow;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        //return emp;
        return borrow;

    }

    public ArrayList<Borrow> readBorrow() {
        return borrow;
    }

    public void borrowBook(Library e,Member m,BDate from,BDate to) {
        LibraryRW lrw = new LibraryRW();
        ArrayList<Library> list = new ArrayList<Library>();
        list = lrw.readBooks();
        Connection conn = null;
        String borrow_sql = "DELETE FROM library WHERE Book_ISBN=" + e.getBookISBN() + " and copyId=" + e.getCopyId();
        String sql = "INSERT INTO borrow(Date_From,Date_To,Member_IDM,Library_BookID) VALUES (?,?,?,?)";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            conn = DriverManager.getConnection(url, user, password);

            if (list.contains(e)) {
                conn.setAutoCommit(false);
                PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, from.toString());
                pstmt.setString(2, to.toString());
                pstmt.setInt(3, m.getIdm());
                pstmt.setInt(4, e.getCopyId());
                
                pstmt.executeUpdate();
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(borrow_sql);
                //stmt.executeUpdate(sql);
                borrow.remove(e);
                conn.commit();
                conn.setAutoCommit(true);
            } else {
                Alert al = new Alert(AlertType.ERROR, "This book is already taken");
                al.show();
            }

        } catch (SQLException ex) {
            Alert al = new Alert(AlertType.ERROR, ex.getMessage());
            al.show();
            //Alert al=new Alert(AlertType.ERROR,"You enetered an ivalid input!");
            //al.show();
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void remove(Borrow book) {
        System.out.print("Punon");
        Connection conn = null;
        String borrow_sql = "delete from borrow where Library_BookID=" + book.getCopyID() + " and Member_IDM=" + book.getIdm();
        String sql = "INSERT INTO library(Description,Book_ISBN)VALUES (?,?)";
        String temp = "select Description from library where Book_ISBN="+book.getISBN();
                System.out.print("Punon");

        try {
        System.out.print("Punon");

            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";
        System.out.print("Punon");

            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            stmt.executeUpdate(borrow_sql);
                    System.out.print("Punon");

            ResultSet rs = stmt.executeQuery(temp);
                    System.out.print("Punon");
                    rs.next();
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,rs.getString("Description"));
            pstmt.setInt(2, book.getISBN());
            pstmt.executeUpdate();
            //stmt.executeQuery(sql);
            borrow.remove(book);
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException ex) {
            Alert al = new Alert(AlertType.ERROR, ex.getMessage());
            al.show();
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }


    public Borrow findBorrowBook(int id) {
        for (Borrow x : borrow) {
            if (id == x.getISBN()) {
                return x;
            }
        }
        return null;
    }

   
}
