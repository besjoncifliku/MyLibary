package readRW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.BDate;
import model.Employee;
import model.Member;

import model.Person;
import model.Publisher;

public class PersonRW //implements Administration
{

    private ArrayList<Person> emp;
    private PublisherRW prw;
    private MemberRW mrw;

    public PersonRW() {

        emp = new ArrayList<Person>();
        emp = readPerson();

    }

    public ArrayList<Person> readPerson() {
        Connection conn = null;
        String sqlPublisher = "SELECT * FROM publisher,user WHERE publisher.username LIKE user.username";
        String sqlMember = "SELECT * FROM member,user WHERE member.username LIKE user.username";

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
            ResultSet rsPub = stmt.executeQuery(sqlPublisher);

            int cnt = 1;
            while (rsPub.next()) {
                //System.out.println(cnt);
                System.out.print(cnt);
                emp.add(new Publisher(rsPub.getInt("PUBID"), rsPub.getString("Name"), rsPub.getInt("Phone"), rsPub.getString("ContactPerson"), rsPub.getString("Adress"), rsPub.getString("username"), rsPub.getString("password")));
            }
            
                        ResultSet rsMem = stmt.executeQuery(sqlMember);

            while (rsMem.next()) {
                               System.out.print(cnt);

                emp.add(new Member(rsMem.getInt("IDM"), rsMem.getString("Name"), rsMem.getString("Surname"), rsMem.getInt("Phone"), rsMem.getString("Adress"), new BDate(rsMem.getString("Reg")), rsMem.getString("username"), rsMem.getString("password")));
                
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

    public ArrayList<Person> readUsers() {
        return emp;
    }

    public void test() {
        for (int i = 0; i < emp.size(); i++) {
            System.out.println(emp.get(i));
        }
    }

    public Person checkUser(String username, String password) {
        for (Person x : emp) {
            if (username.equals(x.getUsername()) && x.getPass().equals(password)) {
                return x;
            }
        }
        return null;
    }

    public Person findUser(String username) {
        for (Person x : emp) {
            if (username.equals(x.getUsername())) {
                return x;
            }
        }
        return null;
    }

    public boolean checkUsername(String username) {
        for (Person x : emp) {
            if (username.equals(x.getUsername())) {
                return false;
            }
        }
        return true;//username is unique == does not exist
    }

    public void addPublisher(Publisher p) {
        prw = new PublisherRW();
        prw.addPublisher(p);
        emp.add(p);
    }

    public void deletePublisher(Publisher p) {
        prw = new PublisherRW();
        prw.remove(p);
        emp.remove(p);
    }

    public void addMember(Member p) {
        mrw = new MemberRW();
        mrw.addMember(p);
        emp.add(p);
    }

    public void deletePublisher(Member p) {
        mrw = new MemberRW();
        mrw.remove(p);
        emp.remove(p);
    }
    public void editUser(Person old, int pos, model.Person e) {
        if (pos < 0 || pos >= emp.size()) {
            Alert al = new Alert(AlertType.ERROR, "User Not Found!");
            al.show();
            return;
        } else {
            Person temp = findUser(old.getUsername());
            if (temp != null) {

                Connection conn = null;
                String use = "update user set username = \"" + e.getUsername() + "\" ,password=\"" + e.getPass() + "\" where username LIKE \"" + temp.getUsername() + "\"";
                //String use="update user set username = \"aqif\" ,password=\""+e.getPass()+"\" where username LIKE \""+temp.getUsername()+"\"";
                //String sql="update employee set username = \"aqif\" ,password=\""+e.getPass()+"\" where username LIKE \""+temp.getUsername()+"\"";
                String sql = "update person set username = \"" + e.getUsername() + "\" ,pin=\"" + e.getPass() + "\" where username LIKE \"" + temp.getUsername() + "\"";

                try {

                    String url = "jdbc:mysql://localhost:3306/mylibrary";
                    String user = "root";
                    String password = "Simple 0.6";

                    conn = DriverManager.getConnection(url, user, password);
                    Statement stmt = conn.createStatement();

                    stmt.executeUpdate(use);

                    stmt.executeUpdate(sql);

                    emp.set(pos, e);

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
        }
    }
}
