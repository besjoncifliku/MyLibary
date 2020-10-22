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
import model.Publisher;

public class PublisherRW //implements Administration
{

    private ArrayList<Publisher> emp;

    public PublisherRW() {

        emp = new ArrayList<Publisher>();
        emp = readPublisher();

    }

    public ArrayList<Publisher> readPublisher() {
        Connection conn = null;
        String sql = "SELECT * FROM publisher";
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
                //public Publisher(int pUBID, String name, String phone, String contactPerson, String adress) {
                emp.add(new Publisher(rs.getInt("PUBID"), rs.getString("Name"), rs.getInt("Phone"), rs.getString("ContactPerson"), rs.getString("Adress"), rs.getString("username"), rs.getString("password")));
            }
            return emp;
        } catch (SQLException e) {
            Alert al=new Alert(AlertType.ERROR,e.getMessage());
            al.show();
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

    public ArrayList<Publisher> readPub() {
        return emp;
    }

    public void test() {
        for (int i = 0; i < emp.size(); i++) {
            System.out.println(emp.get(i));
        }
    }

    public void addPublisher(Publisher e) {

        Connection conn = null;
        String user_sql = "INSERT INTO user(username,password,usertype) VALUES (?,?,?)";
        String sql = "INSERT INTO publisher(PUBID,Name,Phone,ContactPerson,Adress,username,password) VALUES (?,?,?,?,?,?,?)";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            PreparedStatement user_pstmt = conn.prepareStatement(user_sql, Statement.RETURN_GENERATED_KEYS);
            user_pstmt.setString(1, e.getUsername());
            user_pstmt.setString(2, e.getPass());
            user_pstmt.setString(3, e.getTipi().toString());

            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, e.getPUBID());
            pstmt.setString(2, e.getName());
            pstmt.setInt(3, e.getPhone());
            pstmt.setString(4, e.getContactPerson());
            pstmt.setString(5, e.getAdress());
            pstmt.setString(6, e.getUsername());
            pstmt.setString(7, e.getPass());
            
            user_pstmt.executeUpdate();
            pstmt.executeUpdate();
            emp.add(e);
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException ex) {
            Alert al=new Alert(AlertType.ERROR,ex.getMessage());
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

    public void remove(Publisher e) {

        Connection conn = null;
        String user_sql = "delete from user where username LIKE \"" + e.getUsername() + "\"";

        String sql = "delete from publisher where PUBID=" + e.getPUBID();
        
        String pub_sql = "select ISBN from book where Publisher_PUBID="+e.getPUBID();
        
        BookRW brw = new BookRW();
        try {

            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            conn = DriverManager.getConnection(url, user, password);
                        conn.setAutoCommit(false);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(pub_sql);
            while(rs.next()){
                brw.deleteBook(rs.getInt("ISBN"));
            }
            stmt.executeUpdate(sql);
            stmt.executeUpdate(user_sql);

            emp.remove(e);

            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            Alert al=new Alert(AlertType.ERROR,ex.getMessage());
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

    public void deletePublisher(int pubid) {

        Publisher temp = findUser(pubid);
        if (temp != null) {
            remove(temp);
        } else {
            Alert al = new Alert(AlertType.ERROR, "User Not Found!");
            al.show();
        }
    }

    public Publisher findUser(int id) {
        for (Publisher x : emp) {
            if (id == x.getPUBID()) {
                return x;
            }
        }
        return null;
    }
    
    public ArrayList<Book> viewMyBooks(Publisher p){
        ArrayList<Book> book = new ArrayList<>();
        Connection conn = null;
        //String sql = "SELECT ISBN,Title,Publishing_Year,Author FROM book where publisher_PUBID="+p.getId();
        String sql = "SELECT * FROM book where publisher_PUBID="+p.getPUBID();
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
                book.add(new Book(rs.getInt("ISBN"), rs.getString("Title"), rs.getString("Author"),p.getName(), new BDate(rs.getString("PublishingYear")), rs.getString("Review"), rs.getInt("Publisher_PUBID")));
            }
            return book;
        } catch (SQLException e) {
            Alert al=new Alert(AlertType.ERROR,e.getMessage());
            al.show();
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
        return book;
    }
}
