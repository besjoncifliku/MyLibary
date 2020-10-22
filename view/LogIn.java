package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Employee;
import model.Member;
import model.Person;
import model.Publisher;
import readRW.PersonRW;
import readRW.UserRW;

public class LogIn {

    private static Button login;
    private static Button signup;
    private static Button cancel;

    private Employee emp;
    private Person pers;

    public void show(Stage primaryStage) throws Exception {

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10, 10, 10, 10));
        pane.setHgap(5);
        pane.setVgap(5);

        GridPane mainPane = new GridPane();
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        mainPane.setHgap(5);
        mainPane.setVgap(5);

        Label txt = new Label("WELCOME");
        txt.setEffect(new DropShadow());
        txt.setStyle("-fx-font-family: \"Arial Black\";"
                + "-fx-font-size: 21.5px;"
        //+"-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
        );
        txt.setTextFill(Color.WHITE);

        //Create TextFields
        TextField userNameProva = new TextField();
        userNameProva.setPromptText("  | Username");

        userNameProva.setStyle("-fx-background-radius: 12;"
                + "-fx-background-color: white;");
        Image im_user = new Image("images/user.png");
        HBox login_box = new HBox(new ImageView(im_user), userNameProva);
        mainPane.addColumn(0, login_box);
        login_box.setPadding(new Insets(2));
        login_box.setStyle(
                "-fx-background-radius: 8;"
        //	+ "-fx-background-color: #3399ff;"
        );
        login_box.setSpacing(5);

        PasswordField pwProva = new PasswordField();
        pwProva.setPromptText("  | Password");
        pwProva.setStyle("-fx-background-radius: 12;"
                + "-fx-background-color: white;"
        );

        Image im_pass = new Image("images/lock.png");
        HBox pw_box = new HBox(new ImageView(im_pass), pwProva);
        pw_box.setPrefWidth(65);
        pw_box.setPrefHeight(20);
        mainPane.addColumn(0, pw_box);
        pw_box.setPadding(new Insets(2));
        pw_box.setSpacing(5);
        pw_box.setStyle(
                "-fx-background-radius: 8;"
        //	+ "-fx-background-color: #3399ff;"
        );

        //Create buttons
        //Button login=new Button("LogIn");
        try {
            Image img = new Image("images/signin.png");
            login = new Button("Login", new ImageView(img));
        } catch (Exception ex) {
            System.out.println("Images does not exist!");
        }
        //Button signup=new Button("SignUp");
        try {
            Image img = new Image("images/signup.png");
            signup = new Button("SignUp", new ImageView(img));
        } catch (Exception ex) {
            System.out.println("Images does not exist!");
        }
        //Button cancel=new Button("Exit");
        try {
            Image img = new Image("images/exit.png");
            cancel = new Button("Exit", new ImageView(img));
        } catch (Exception ex) {
            System.out.println("Images does not exist!");
        }
        login.setStyle(
                "-fx-text-fill: white;"
                + "-fx-border-color:white;"
                + "-fx-border-width: 3 3 3 3;"
                + "-fx-padding: 5px 15px;"
                + " -fx-background-color: black"
        );
        login.setOnMouseEntered(e -> {
            login.setStyle(
                    "-fx-border-color:white;"
                    + "-fx-border-width: 3 3 3 3;"
                    + "-fx-text-fill: black;"
                    + "-fx-padding: 5px 15px;"
                    + " -fx-background-color: white"
            );
        });

        login.setOnMouseExited(e -> {

            login.setStyle(
                    "-fx-text-fill: white;"
                    + "-fx-border-color:white;"
                    + "-fx-border-width: 3 3 3 3;"
                    + "-fx-padding: 5px 15px;"
                    + " -fx-background-color: black"
            );
        });

        signup.setStyle(
                "-fx-text-fill: white;"
                + "-fx-border-color:white;"
                + "-fx-border-width: 3 3 3 3;"
                + "-fx-padding: 5px 9px;"
                + "-fx-background-color: black;"
        );
        signup.setOnMouseEntered(e -> {
            signup.setStyle(
                    "-fx-border-color:white;"
                    + "-fx-border-width: 3 3 3 3;"
                    + "-fx-text-fill: black;"
                    + "-fx-padding: 5px 15px;"
                    + " -fx-background-color: white"
            );
        });

        signup.setOnMouseExited(e -> {

            signup.setStyle(
                    "-fx-text-fill: white;"
                    + "-fx-border-color:white;"
                    + "-fx-border-width: 3 3 3 3;"
                    + "-fx-padding: 5px 15px;"
                    + " -fx-background-color: black"
            );
        });

        cancel.setStyle(
                "-fx-text-fill: white;"
                + "-fx-border-color:white;"
                + "-fx-border-width: 3 3 3 3;"
                + "-fx-padding: 5px 9px;"
                + "-fx-background-color: black;"
        );

        cancel.setOnMouseEntered(e -> {
            cancel.setStyle(
                    "-fx-border-color:white;"
                    + "-fx-border-width: 3 3 3 3;"
                    + "-fx-text-fill: black;"
                    + "-fx-padding: 5px 15px;"
                    + " -fx-background-color: white"
            );
        });

        cancel.setOnMouseExited(e -> {

            cancel.setStyle(
                    "-fx-text-fill: white;"
                    + "-fx-border-color:white;"
                    + "-fx-border-width: 3 3 3 3;"
                    + "-fx-padding: 5px 15px;"
                    + " -fx-background-color: black"
            );
        });

        if (userNameProva.getText().isEmpty() && pwProva.getText().isEmpty()) {
            login.setDisable(true);
        } else {
            login.setDisable(false);
        }

        userNameProva.setOnKeyPressed(
                new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        if (!userNameProva.getText().isEmpty()) {
                            login.setDisable(false);
                        }
                    }

                }
        );
        userNameProva.setPrefSize(175, 27);
        pwProva.setPrefSize(175, 29);

        pwProva.setOnKeyPressed(
                new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent event) {

                        if (!pwProva.getText().isEmpty()) {
                            login.setDisable(false);
                        }
                    }

                }
        );

        HBox box = new HBox();
        box.setPadding(new Insets(10, 10, 10, 10));
        box.getChildren().add(txt);
        box.setAlignment(Pos.CENTER);

        pane.add(login_box, 0, 0);
        pane.add(pw_box, 0, 1);

        //Array of HBox to keep it simple
        HBox[] hb = {
            new HBox(txt),
            new HBox(login, signup),
            new HBox(cancel)

        };
        VBox layout = new VBox(hb);
        layout.getChildren().toArray();
        for (int i = 0; i < layout.getChildren().size(); i++) {
            hb[i].setPadding(new Insets(5, 5, 5, 5));
            hb[i].setMargin(login, new Insets(0, 10, 0, 0));
            hb[i].setAlignment(Pos.CENTER);
        }

        //MainPane I used to keep all other panes.
        //More flexible to modify
        mainPane.addColumn(2, hb[0], pane, hb[1], hb[2]);
        mainPane.setHgap(5);
        mainPane.setAlignment(Pos.CENTER);
        mainPane.setStyle(
                "-fx-background-image: url('images/logIn.jpg');"
                + "-fx-background-repeat: stretch;"
        );

        //Handling Events
        login.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        String userN = userNameProva.getText();
                        String pass = pwProva.getText();
                        UserRW urw = new UserRW();
                        PersonRW prw = new PersonRW();
                        //urw.test();
                        emp = urw.checkUser(userN, pass);
                        pers = prw.checkUser(userN, pass);
                        if (!urw.checkUsername(userN) || !prw.checkUsername(userN)) {
                            if (emp != null) {
                                //LOGIN
                                if (emp.getTipi().equals(model.Tipi.admin)) {
                                    System.out.println("Hello " + emp.getName());
                                    new AdminInterface(urw.findUser(userN)).show(primaryStage);
                                } else if (emp.getTipi().equals(model.Tipi.manager)) {
                                    System.out.println("Hello " + emp.getName());
                                    new ManagerInterface(emp).show(primaryStage);

                                    //System.out.println("Hello "+emp.getName());
                                    //new ManagerView(urw.findUser(userN)).show(primaryStage);
                                }// else if(emp.getTipi().equals(model.Tipi.)){
                                //new PharmaView(urw.findUser(userN)).show(primaryStage);
                                //}

                                primaryStage.show();
                            } else {
                                
                                if (pers != null) {
                                    //LOGIN
                                    if (pers.getTipi().equals(model.Tipi.pub)) {
                                        System.out.println("Hello Publisher");
                                        new PublisherInterface((Publisher) prw.findUser(userN)).show(primaryStage);
                                    } else if (pers.getTipi().equals(model.Tipi.member)) {
                                        System.out.println("Hello Member "+prw.findUser(userN).getUsername());
                                        new MemberInterface((Member) prw.findUser(userN)).show(primaryStage);

                                    }

                                    primaryStage.show();
                                } else {
                                    Alert al = new Alert(AlertType.ERROR, "Password is incorrect!");
                                    al.show();
                                    //Show ALERT password not correct

                                }
                            }
                        } else {
                            Alert al = new Alert(AlertType.ERROR, "Username does not exist!");
                            al.show();
                            //show ALERT user not correct
                        }
                    }
                }
        );

        signup.setOnAction(
                new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        primaryStage.close();
                        try {
                            new NewUser().show(primaryStage);
                            //new AddUser(emp).showLog(primaryStage);
                            //new ViewBooks(emp).shfaq(primaryStage);
                            //new PublisherView().shfaq(primaryStage);
                            //new Register().showLog(primaryStage);;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        primaryStage.show();

                    }

                }
        );

        login.setPrefSize(100, 30);
        signup.setPrefSize(100, 30);
        cancel.setPrefSize(100, 30);

        pwProva.setOnKeyPressed(new EventHandler<KeyEvent>() {

            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    login.fire();
                }
            }
        });
        cancel.setOnAction(e -> {
            //Close the window
            primaryStage.close();
        });

        //Create the scene and put the mainPane
        Scene scene = new Scene(mainPane, 550, 550);
        primaryStage.setTitle("Log In");
        //scene.getStylesheets().add(getClass().getClassLoader().getResource("buton.css").toExternalForm());		
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
