package readRW;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.BDate;
import model.Book;

public class BookRW //implements Administration
{

    private ArrayList<Book> emp;

    public BookRW() {

        emp = new ArrayList<Book>();
        emp = readBook();

    }

    public ArrayList<Book> readBook() {
        Connection conn = null;
        String sql = "SELECT * FROM book,publisher where Publisher_PUBID=PUBID";
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
                //public Book(int iSBN, String title, String author, String pulisher, BDate pubYear, String review, int pubId) {

                emp.add(new Book(rs.getInt("ISBN"), rs.getString("Title"), rs.getString("Author"), rs.getString("Name"), new BDate(rs.getString("PublishingYear")), rs.getString("Review"), rs.getInt("Publisher_PUBID")));

            }
            return emp;
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
        return emp;

    }

    public ArrayList<Book> readBok() {
        return emp;
    }

    public Book getBook(int ISBN) {
        for (int i = 0; i < emp.size(); i++) {
            if (emp.get(i).getISBN() == ISBN) {
                return emp.get(i);
            }
        }
        return null;
    }

    public void addBook(Book e) {

        Connection conn = null;
        String sql = "INSERT INTO book(ISBN,Title,Author,PublishingYear,Review,Publisher_PUBID) VALUES (?,?,?,?,?,?)";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, e.getISBN());
            pstmt.setString(2, e.getTitle());
            pstmt.setString(3, e.getAuthor());
            pstmt.setString(4, e.getPubYear().toString());
            pstmt.setString(5, e.getReview());
            pstmt.setInt(6, e.getPubId());

            pstmt.executeUpdate();
            emp.add(e);
            Alert al = new Alert(Alert.AlertType.INFORMATION, "Book was successfully added!");
            al.show();
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

    public void remove(Book e) {
        LibraryRW lrw = new LibraryRW();
        Connection conn = null;
        String sql = "delete from book where ISBN=" + e.getISBN();
        try {

            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            lrw.removeBookPermanently(e);
            stmt.executeUpdate(sql);
            emp.remove(e);

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

    public void deleteBook(int isbn) {

        Book temp = findBook(isbn);
        if (temp != null) {
            remove(temp);
        } else {
            Alert al = new Alert(AlertType.ERROR, "User Not Found!");
            al.show();
        }
    }

    public Book findBook(int id) {
        for (Book x : emp) {
            if (id == x.getISBN()) {
                return x;
            }
        }
        return null;
    }
}
