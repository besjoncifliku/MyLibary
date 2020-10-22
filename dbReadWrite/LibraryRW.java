package readRW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import model.Admin;
import model.BDate;
import model.Book;
import model.Library;
import model.LibraryBook;
import model.Manager;

public class LibraryRW {

    private static ArrayList<Library> lib;
    private static ArrayList<LibraryBook> book;

    public LibraryRW() {
        lib = new ArrayList<Library>();
        book = new ArrayList<LibraryBook>();
        //readLibrary();
        readBooksFromLibrary();
        readLibrary();
    }

    private ArrayList<Library> readLibrary() {

        Connection conn = null;
        String sql = "SELECT * FROM library";
        //String sql = "SELECT user.username,user.password,usertype,pin,birthdate,name,surname,phone,salary FROM employee,user WHERE employee.username LIKE user.username";
        //String sql= "SELECT * FROM employee";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            BookRW brw = new BookRW();
            //ArrayList<Book> books=brw.readBok();
            int j = 0;
            while (rs.next()) {
               // Book temp = brw.getBook(rs.getInt("BookID"));

                lib.add(new Library(rs.getInt("BookID"), rs.getString("Description"), rs.getInt("Book_ISBN")));
                /*for (int i = 0; i < lib.size(); i++) {
                    if (book.get(i).getISBN() == rs.getInt("Book_ISBN")) {
                        book.get(i).addCopy();
                    } else {
                        book.add(new LibraryBook(temp.getISBN(), temp.getTitle()));
                    }
                }*/
                // j++;
            }
            return lib;
        } catch (SQLException e) {
            Alert al = new Alert(Alert.AlertType.ERROR, e.getMessage());
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
        return lib;

    }

    public void readBooksFromLibrary() {
        Connection conn = null;
        String sql = "SELECT  ISBN,title,COUNT(ISBN) AS cnt FROM library,book WHERE library.Book_ISBN=book.ISBN Group By(ISBN)";
        //String sql = "SELECT user.username,user.password,usertype,pin,birthdate,name,surname,phone,salary FROM employee,user WHERE employee.username LIKE user.username";
        //String sql= "SELECT * FROM employee";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            BookRW brw = new BookRW();
            //ArrayList<Book> books=brw.readBok();

            while (rs.next()) {
                Book temp = brw.getBook(rs.getInt("ISBN"));

                book.add(new LibraryBook(temp.getISBN(), temp.getTitle(), rs.getInt("cnt")));

            }

        } catch (SQLException e) {
            Alert al = new Alert(Alert.AlertType.ERROR, e.getMessage());
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
    }

    public void addBookInLibrary(Book e,String descr) {
        Connection conn = null;
        String sql = "INSERT INTO library(Description,Book_ISBN)VALUES (?,?)";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            pstmt.setString(1, descr);
            pstmt.setInt(2, e.getISBN());
          

            pstmt.executeUpdate();
            Alert al = new Alert(Alert.AlertType.INFORMATION, "Book successfully added to the library!");
                al.show();
            //book.add(e);

        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR, ex.getMessage());
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

    public void removeBook(Library e){
           Connection conn = null;
           System.out.println(e.getCopyId());
        String borrow_sql = "DELETE FROM library WHERE BookID="+e.getBookISBN();
        try {

            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(borrow_sql);
            lib.remove(e);

        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR, ex.getMessage());
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
        public void removeBookPermanently(Book e){
           Connection conn = null;
        String borrow_sql = "DELETE FROM library WHERE Book_ISBN="+e.getISBN();
        try {

            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(borrow_sql);

        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR, ex.getMessage());
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
    public ArrayList<Library> readBooks() {
        return lib;
    }

    public ArrayList<LibraryBook> readLib() {
        return book;
    }

}
