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
import model.Admin;
import model.BDate;
import model.Employee;
import model.Manager;

public class UserRW //implements Administration
{

    private ArrayList<Employee> emp;

    public UserRW() {

        emp = new ArrayList<Employee>();
        emp = readUser();

    }

    public ArrayList<Employee> readUser() {
        Connection conn = null;
        String sql = "{ call readUsers() }";
        //String sql = "SELECT user.username,user.password,usertype,pin,birthdate,name,surname,phone,salary FROM employee,user WHERE employee.username LIKE user.username";
        //String sql= "SELECT * FROM employee";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);

            CallableStatement stmt = conn.prepareCall(sql);
            ResultSet rs = stmt.executeQuery();

            int cnt = 1;
            while (rs.next()) {
                System.out.println(cnt);
                cnt++;
                if (rs.getString("usertype").equals("admin")) {
                    emp.add(new Admin(rs.getString("username"), rs.getString("pin"), rs.getString("name"), rs.getString("surname"),
                            new BDate(rs.getString("birthdate")), rs.getInt("phone"), rs.getDouble("salary")));
                } else {
                    emp.add(new Manager(rs.getString("username"), rs.getString("pin"), rs.getString("name"), rs.getString("surname"),
                            new BDate(rs.getString("birthdate")), rs.getInt("phone"), rs.getDouble("salary")));
                }
           //shto member dhe publisher

	    // more processing here
                // ... 
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

    public ArrayList<Employee> readEmp() {
        return emp;
    }

    public void test() {
        for (int i = 0; i < emp.size(); i++) {
            System.out.println(emp.get(i));
        }
    }

    public Employee checkUser(String username, String password) {
        for (Employee x : emp) {
            if (username.equals(x.getUsername()) && x.getPass().equals(password)) {
                return x;
            }
        }
        return null;
    }

    public void addEmp(Employee e) {

        Connection conn = null;
        String sql = "INSERT INTO employee(username,pin,birthdate,phone,salary,name,surname) VALUES (?,?,?,?,?,?,?)";
        String sql2 = "INSERT INTO user(username,password,usertype) VALUES (?,?,?)";

        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement pstmt2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);

            pstmt2.setString(1, e.getUsername());
            pstmt2.setString(2, e.getPass());
            pstmt2.setString(3, e.getTipi().toString());

            pstmt2.executeUpdate();

            pstmt.setString(1, e.getUsername());
            pstmt.setString(2, e.getPass());
            pstmt.setString(3, e.getBdt().toString());
            pstmt.setInt(4, e.getPhone());
            pstmt.setDouble(5, e.getSalary());
            pstmt.setString(6, e.getName());
            pstmt.setString(7, e.getSurname());
            pstmt.executeUpdate();
            
            emp.add(e);
             Alert al = new Alert(Alert.AlertType.INFORMATION, "Employee was successfully added!");
                            al.show();
                            
                            conn.commit();
                            conn.setAutoCommit(true);

        } catch (SQLException ex) {
		Alert al = new Alert(Alert.AlertType.INFORMATION, ex.getMessage());
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

    public void addUser(Employee e) {
        Connection conn = null;
        String sql = "INSERT INTO user(username,password,usertype) VALUES (?,?,?)";
        try {
            // db parameters
            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            // create a connection to the database
            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, e.getUsername());
            pstmt.setString(2, e.getPass());
            pstmt.setString(3, e.getTipi().toString());

            pstmt.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
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

    public void remove(Employee e) {

        Connection conn = null;
//        String sql = "delete from employee where id=" + e.getId();       
//        String sql2 = "delete from user where username LIKE \"" + e.getUsername() + "\"";
           String sql = "{ call deleteUsers(?,?) }";
        
        try {

            String url = "jdbc:mysql://localhost:3306/mylibrary";
            String user = "root";
            String password = "Simple 0.6";

            conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);

            //Statement stmt = conn.createStatement();
            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, e.getId());
            stmt.setString(2, e.getUsername());

            stmt.executeUpdate();
            //removeUser(e);

            emp.remove(e);
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException ex) {
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


    public void changePass(String username, String s) {

        Employee temp = findUser(username);
        if (temp != null) {
            Connection conn = null;
            String sql = "update employee set pin=" + s + " where id=" + temp.getId() + " and username=" + temp.getUsername();
            String sql2 = "update user set pin=" + s + " where  username LIKE \"" + temp.getUsername() + "\"";

            try {

                String url = "jdbc:mysql://localhost:3306/mylibrary";
                String user = "root";
                String password = "Simple 0.6";

                conn = DriverManager.getConnection(url, user, password);
                conn.setAutoCommit(false);
                Statement stmt = conn.createStatement();
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql);

                temp.setPass(s);
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

            Alert al = new Alert(AlertType.ERROR, "Password changed successfully!");
            al.show();
        } else {
            Alert al = new Alert(AlertType.ERROR, "User Not Found!");
            al.show();
        }
    }

    public void changeUserName(String username, String s) {

        Employee temp = findUser(username);
        if (temp != null) {
            if (checkUsername(s)) {

                Connection conn = null;
                String sql = "update employee set username=\"" + s + "\" where id=" + temp.getId() + " and username LIKE \"" + temp.getUsername() + "\"";
                String sql2 = "update user set username=\"" + s + "\" where  username LIKE \"" + temp.getUsername() + "\"";
                try {

                    String url = "jdbc:mysql://localhost:3306/mylibrary";
                    String user = "root";
                    String password = "Simple 0.6";

                    conn = DriverManager.getConnection(url, user, password);
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate(sql);
                    stmt.executeUpdate(sql2);
                    temp.setUsername(s);
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

                Alert al = new Alert(AlertType.ERROR, "Username changed successfully!");
                al.show();
            }//iff i dyte
            else {
                Alert al = new Alert(AlertType.INFORMATION, "This username already exists!");
                al.show();
            }
        }//if i pare
        else {
            Alert al = new Alert(AlertType.ERROR, "User Not Found!");
            al.show();
        }

    }

    public void editUser(Employee old, int pos, model.Employee e) {
        if (pos < 0 || pos >= emp.size()) {
            Alert al = new Alert(AlertType.ERROR, "User Not Found!");
            al.show();
            return;
        } else {
            Employee temp = findUser(old.getUsername());
            if (temp != null) {

                Connection conn = null;
                String use = "update user set username = \"" + e.getUsername() + "\" ,password=\"" + e.getPass() + "\" where username LIKE \"" + temp.getUsername() + "\"";
                //String use="update user set username = \"aqif\" ,password=\""+e.getPass()+"\" where username LIKE \""+temp.getUsername()+"\"";
                //String sql="update employee set username = \"aqif\" ,password=\""+e.getPass()+"\" where username LIKE \""+temp.getUsername()+"\"";
                String sql = "update employee set username = \"" + e.getUsername() + "\" ,pin=\"" + e.getPass() + "\" where username LIKE \"" + temp.getUsername() + "\"";

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

    public void deleteUser(String username) {

        Employee temp = findUser(username);
        if (temp != null) {
            remove(temp);
        } else {
            Alert al = new Alert(AlertType.ERROR, "User Not Found!");
            al.show();
        }
    }

    public Employee findUser(String username) {
        for (Employee x : emp) {
            if (username.equals(x.getUsername())) {
                return x;
            }
        }
        return null;
    }

    public boolean checkUsername(String username) {
        for (Employee x : emp) {
            if (username.equals(x.getUsername())) {
                return false;
            }
        }
        return true;//username is unique == does not exist
    }

}
