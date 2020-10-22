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
import model.Borrow;
import model.Member;
import model.Tipi;

public class MemberRW //implements Administration
{

    private ArrayList<Member> emp;

    public MemberRW() {

        emp = new ArrayList<Member>();
        emp = readMember();

    }

    public ArrayList<Member> readMember() {
        Connection conn = null;
        String sql = "{ Call readMembers() }";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            CallableStatement stmt = conn.prepareCall(sql);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                //public Member(int id,String name, String surname,int phone,String adress,BDate regDate) {
                // System.out.println("+");
                emp.add(new Member(rs.getInt("IDM"), rs.getString("Name"), rs.getString("Surname"), rs.getInt("Phone"), rs.getString("Adress"), new BDate(rs.getString("Reg")), rs.getString("username"), rs.getString("password")));
            }
            return emp;
        } catch (SQLException e) {
            Alert al = new Alert(AlertType.ERROR, e.getMessage());
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

    public ArrayList<Member> readMem() {
        return emp;
    }

    public void addMember(Member e) {

        Connection conn = null;
        String user_sql = "INSERT INTO user(username,password,usertype) VALUES (?,?,?)";

        String sql = "INSERT INTO member(IDM,Name,Surname,Phone,Adress,Reg) VALUES (?,?,?,?,?,?)";
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

            pstmt.setInt(1, e.getIdm());
            pstmt.setString(2, e.getName());
            pstmt.setString(3, e.getSurname());
            pstmt.setInt(4, e.getPhone());
            pstmt.setString(5, e.getAdress());
            pstmt.setString(6, e.getRegDate().toString());
            
            user_pstmt.executeUpdate();
            pstmt.executeUpdate();
            emp.add(e);
            conn.commit();
            conn.setAutoCommit(true);
            

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

    public void remove(Member e) {

        Connection conn = null;
        String user_sql = "delete from user where username LIKE \"" + e.getUsername() + "\"";
        String borrow_sql = "delete from borrow where Member_IDM="+e.getIdm();
        String sql = "delete from member where IDM=" + e.getIdm();
        
        try {

            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(borrow_sql);

            stmt.executeUpdate(sql);
            stmt.executeUpdate(user_sql);

            emp.remove(e);
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

    public void deleteMember(int idm) {

        Member temp = findUser(idm);
        if (temp != null) {
            remove(temp);
        } else {
            Alert al = new Alert(AlertType.ERROR, "User Not Found!");
            al.show();
        }
    }

    public Member findUser(int id) {
        for (Member x : emp) {
            if (id == x.getIdm()) {
                return x;
            }
        }
        return null;
    }
     public ArrayList<Borrow> viewBorrowBook(Member m) {
        ArrayList<Borrow> book = new ArrayList<>();
        Connection conn = null;
        //String sql = "SELECT ISBN,Title,Publishing_Year,Author FROM book where publisher_PUBID="+p.getId();
        String sql = "SELECT ISBN,Title,Author,PUBID,Name,PublishingYear,Review,Date_From,Date_To,Library_BookID FROM book,borrow,publisher,library where Member_IDM=" + m.getIdm() + " and ISBN=Book_ISBN and PUBID=Publisher_PUBID and Library_BookID=BookID";
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
                //book.add(new Borrow(new Book(rs.getInt("ISBN"), rs.getString("Title"), rs.getString("Author"), rs.getString("Name"), new BDate(rs.getString("PublishingYear")), rs.getString("Review"), rs.getInt("Publisher_PUBID")),new BDate(rs.getString("Date_From")),new BDate(rs.getString("Date_To"))));
                //public Borrow(String member, int idm, BDate borrowDate, BDate givenDate, String bookTitle, int ISBN, int copyID) {

                book.add(new Borrow(m.getName(), m.getIdm(), new BDate(rs.getString("Date_From")), new BDate(rs.getString("Date_To")), rs.getString("Title"), rs.getInt("ISBN"), rs.getInt("Library_BookID")));
            }
            return book;
        } catch (SQLException e) {
            Alert al = new Alert(AlertType.ERROR, e.getMessage());
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
