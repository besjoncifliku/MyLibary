package view;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.BDate;
import model.Book;
import model.Employee;
import model.Publisher;
import readRW.BookRW;
import readRW.PublisherRW;

public class AddPublisher {

    private ArrayList<Publisher> pub;
    private PublisherRW prw;
   // private Employee emp;
    private int type = 0;
    public AddPublisher() {
    
        prw = new PublisherRW();
        
        type = 0;
    }
    private Publisher p;
    
    public void show(Stage st) {

        BorderPane pane = new BorderPane();

        Label isbn = new Label("Enter ID");
        TextField isbnF = new TextField();
        //isbn.setStyle("text_id");
        isbnF.setStyle(""
                + "-fx-background-radius: 22px");

        // force the field to be numeric only
        isbnF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    isbnF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        
        Label phone = new Label("Enter Phone");
        TextField phoneF = new TextField();
        //isbn.setStyle("text_id");
        phoneF.setStyle(""
                + "-fx-background-radius: 22px");
         phoneF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    phoneF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // force the field to be numeric only
        isbnF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                    String newValue) {
                if (!newValue.matches("\\d*")) {
                    isbnF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        Label title = new Label("Enter Name");
        //title.setStyle("text_id");
        TextField titleF = new TextField();
        titleF.setStyle(""
                + "-fx-background-radius: 22px");
       Label user = new Label("Enter Username");
        //title.setStyle("text_id");
        TextField userF = new TextField();
        userF.setStyle(""
                + "-fx-background-radius: 22px");
        Label pass = new Label("Enter Password");
        //title.setStyle("text_id");
        TextField passF = new TextField();
        passF.setStyle(""
                + "-fx-background-radius: 22px");

        Label author = new Label("Contact Person");
        //author.setStyle("text_id");
        TextField authorF = new TextField();
        authorF.setStyle(""
                + "-fx-background-radius: 22px");
        Label review = new Label("Adress");
        TextField reviewF = new TextField();
        //review.setStyle("text_id");
        
        reviewF.setStyle(""
                + "-fx-background-radius: 22px");
        Button add = new Button("Sign Up");
        add.setPrefSize(140, 35);
        add.setOnAction((ActionEvent e) -> {
            //public Book(int iSBN, String title, String author, String pulisher, BDate pubYear, String review, int pubId) {

            //  try {
            if (review.getText().isEmpty()) {
                review.setText("");
            }
            if (!isbnF.getText().isEmpty() && !authorF.getText().isEmpty()
                    && !titleF.getText().isEmpty() && !reviewF.getText().isEmpty() 
                    && !userF.getText().isEmpty()&& !passF.getText().isEmpty()) {
                prw.addPublisher(new Publisher(Integer.parseInt(isbnF.getText().toString()),titleF.getText().toString(),Integer.parseInt(phoneF.getText().toString()),author.getText().toString(),reviewF.getText().toString(),userF.getText().toString(),passF.getText().toString()));
              
            } else {
                Alert al = new Alert(Alert.AlertType.INFORMATION, "Please fill all fields!");
                
                al.show();
            }

            /**
             * } catch (Exception ex) { Alert al = new
             * Alert(Alert.AlertType.INFORMATION, ex+" An error occured while
             * trying to add a new book!"); al.show();
            }
             */
        });

        Button cancel = new Button("Cancel");
        cancel.setPrefSize(140, 35);
        cancel.setOnAction(e -> {
            try {
                //
                new LogIn().show(st);
            } catch (Exception ex) {
                Logger.getLogger(AddPublisher.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        });
        VBox vb = new VBox(isbn, isbnF, title, titleF, user, userF, pass, passF,phone,phoneF, author, authorF, review, reviewF, add, cancel);
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        pane.setCenter(vb);
        pane.setLeft(new Label("    "));
        pane.setRight(new Label("   "));
        pane.setStyle("-fx-background-color: coral");
        Scene scene = new Scene(pane, 380, 550);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("buton.css").toExternalForm());
        st.setScene(scene);
        st.setTitle("New Publisher");
        st.show();

    }
}
