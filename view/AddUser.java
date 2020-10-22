package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Admin;
import model.BDate;
import model.Employee;
import model.Manager;
import readRW.UserRW;

public class AddUser {

    private UserRW urw;
    private int type = 0;
    private Employee emp;

    public AddUser() {
        urw = new UserRW();
    }

    public AddUser(Employee e) {
        urw = new UserRW();
        emp = e;
    }

    public void showLog(Stage st) {
        type = 1;
        show(st);
    }

    public void showAdm(Stage st) {
        type = 2;
        show(st);
    }

    public void showMenu(Stage st) {
        type = 3;
        show(st);
    }

    public void show(Stage st) {

        BorderPane pane = new BorderPane();
        Text phone_error = new Text("Phone has only 10 numbers");
        phone_error.setVisible(false);
        Text surname_error = new Text("Do not enter numbers");
        surname_error.setVisible(false);

        Label welcome = new Label("Register New User");
        //welcome.setEffect(new DropShadow());
        welcome.setStyle("-fx-font-family: \"Arial Black\";"
                + "   -fx-font-size: 25px;"
                + "-fx-text-fill: white;");

        Label username = new Label("Enter username");
        TextField usernameF = new TextField();
        //isbn.setStyle("text_id");
        usernameF.setStyle(""
                + "-fx-background-radius: 22px");

        Label pin = new Label("Enter Password");
        //title.setStyle("text_id");
        TextField pinF = new TextField();
        pinF.setStyle(""
                + "-fx-background-radius: 22px");
        Label name = new Label("Enter Name:");
        TextField nameF = new TextField();
        nameF.setStyle(""
                + "-fx-background-radius: 22px");

        //publisher.setStyle("text_id");
        Label surname = new Label("Enter Surname:");
        TextField surnameF = new TextField();
        surnameF.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (!surnameF.getText().matches("[a-zA-Z]+")) {
                    surname_error.setVisible(true);
                } else {
                    surname_error.setVisible(false);
                }
            }
        });
        surnameF.setStyle(""
                + "-fx-background-radius: 22px");

        Label pubYear = new Label("Select Birthdate");
        DatePicker pYear = new DatePicker();
        pYear.setEditable(false);

        Label phone = new Label("Enter Phone-number:");
        //author.setStyle("text_id");
        TextField phoneF = new TextField();
        phoneF.setStyle(""
                + "-fx-background-radius: 22px");
        Label salary = new Label("Enter Salary");
        TextField salaryF = new TextField();
        phoneF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    phoneF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        phone.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        if (!phoneF.getText().matches(
                                "([0-9]{10})")) {
                            phone_error.setVisible(true);
                        } else {
                            phone_error.setVisible(false);
                        }
                    }
                }
        );
        //review.setStyle("text_id");

        salaryF.setStyle(""
                + "-fx-background-radius: 22px");
        // force the field to be numeric only
        salaryF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    salaryF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        Label select = new Label("Select role:");
        ToggleGroup pos = new ToggleGroup();
        RadioButton admin = new RadioButton("ADMIN");
        RadioButton manager = new RadioButton("MANAGER");
        admin.setToggleGroup(pos);
        manager.setToggleGroup(pos);
        HBox bx3 = new HBox();
        bx3.getChildren().addAll(admin, manager);
        bx3.setSpacing(5);
        bx3.setAlignment(Pos.CENTER);

        Button add = new Button("Add Employee");
        add.setPrefSize(140, 35);
        add.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                //public Employee(String user, String pass, String name,
                //String surname, BDate bdt,int phone,double salary) {
                if (!surname_error.isVisible()) {
                    
                		try {
                        if (!usernameF.getText().isEmpty() && !pinF.getText().isEmpty()
                                && !nameF.getText().isEmpty() && !pYear.getValue().toString().replace("-", "/").isEmpty() && !surnameF.getText().isEmpty()
                               && !salaryF.getText().isEmpty() && !phoneF.getText().isEmpty() && (admin.isSelected()  || manager.isSelected())) {
                            if (admin.isSelected()) {
                                urw.addEmp(new Admin(usernameF.getText().toString(), pinF.getText().toString(),
                                        nameF.getText().toString(), surnameF.getText().toString(), (new BDate(0, pYear.getValue().toString().replace("-", "/"))), Integer.parseInt(phoneF.getText().toString()),Double.parseDouble(salaryF.getText().toString())));
                            }else {
                                  urw.addEmp(new Manager(usernameF.getText().toString(), pinF.getText().toString(),
                                        nameF.getText().toString(), surnameF.getText().toString(), (new BDate(0, pYear.getValue().toString().replace("-", "/"))), Integer.parseInt(phoneF.getText().toString()),Double.parseDouble(salaryF.getText().toString())));
                            }
                           
                        } else {
                            Alert al = new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!");
                            al.show();
                        }
                		
                }catch(Exception ex) {
                    Alert al = new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!");
                    al.show();
                }
                		}
            else {
                    Alert al = new Alert(Alert.AlertType.INFORMATION, surname_error.getText());
                    al.show();

                }
                }
            }
        
        );

        Button cancel = new Button("Cancel");
        cancel.setPrefSize(140, 35);
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    if (type == 2) {
                        new Edit(emp).show(st);
                        st.show();
                    }
                    if (type == 1) {
                        new LogIn().show(st);
                        st.show();

                    }
                    if (type == 3) {
                        new UserView(emp).shfaq(st);
                        st.show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        pane.setTop(welcome);
        BorderPane.setAlignment(welcome, Pos.CENTER);
        VBox vb = new VBox(username, usernameF, pin, pinF, name, nameF, surname, surnameF);
        VBox vb2 = new VBox(new Label(" "),salary,salaryF,pubYear, pYear, phone, phoneF, select, bx3, phone_error);
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        vb2.setSpacing(10);
        vb2.setAlignment(Pos.CENTER);
        
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(5,5,5,5));
       
        gp.addColumn(0,vb);
        gp.addColumn(1,vb2);
        
        pane.setCenter(gp);
        gp.setHgap(15); //horizontal gap in pixels => that's what you are asking for
        gp.setVgap(5);
        HBox hb = new HBox(add,cancel);
        hb.setSpacing(15);
        hb.setAlignment(Pos.CENTER);
        pane.setBottom(hb);
        BorderPane.setAlignment(hb, Pos.CENTER);
        pane.setStyle("-fx-background-color: coral");
        Scene scene = new Scene(pane, 320, 430);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("buton.css").toExternalForm());
        st.setScene(scene);
        st.setTitle("Add Employee");
        st.show();
    }
}

