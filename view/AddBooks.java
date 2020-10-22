package view;

import java.util.ArrayList;
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

public class AddBooks {

    private ArrayList<Publisher> pub;
    private PublisherRW prw;
    private BookRW brw;
    private Employee emp;
    private int type = 0;
    public AddBooks(Employee e) {
        emp = e;
        prw = new PublisherRW();
        brw = new BookRW();
        type = 0;
    }
    private Publisher p;
    public AddBooks(Publisher p){
        prw = new PublisherRW();
        brw = new BookRW();
        type = 1;
        this.p=p;
    }

    public void show(Stage st) {

        BorderPane pane = new BorderPane();

        Label isbn = new Label("Enter ISBN");
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
        Label title = new Label("Enter Title");
        //title.setStyle("text_id");
        TextField titleF = new TextField();
        titleF.setStyle(""
                + "-fx-background-radius: 22px");
        Label publisher = new Label("Select Publisher");
        //publisher.setStyle("text_id");

        pub = prw.readPub();
        ChoiceBox<Publisher> list = new ChoiceBox<Publisher>(
                FXCollections.observableArrayList(pub)
        );
       
        if(type==0)
        list.setValue(pub.get(0));
        
        if(type == 1){
            //list.setValue(p);
            list.setVisible(false);
        }

        Label pubYear = new Label("Select Publishing Year");
        DatePicker pYear = new DatePicker();
        pYear.setEditable(false);

        Label author = new Label("Author");
        //author.setStyle("text_id");
        TextField authorF = new TextField();
        authorF.setStyle(""
                + "-fx-background-radius: 22px");
        Label review = new Label("Write a review:");
        TextField reviewF = new TextField();
        //review.setStyle("text_id");

        reviewF.setStyle(""
                + "-fx-background-radius: 22px");
        Button add = new Button("Add to library");
        add.setPrefSize(140, 35);
        add.setOnAction((ActionEvent e) -> {
            //public Book(int iSBN, String title, String author, String pulisher, BDate pubYear, String review, int pubId) {

            //  try {
            if (review.getText().isEmpty()) {
                review.setText("");
            }
            if (!isbnF.getText().isEmpty() && !authorF.getText().isEmpty()
                    && !titleF.getText().isEmpty() && !pYear.getValue().toString().replace("-", "/").isEmpty()) {
                if(type==0)
                brw.addBook(new Book(Integer.parseInt(isbnF.getText().toString()), titleF.getText().toString(),
                        authorF.getText().toString(), list.getValue().getName(), (new BDate(0, pYear.getValue().toString().replace("-", "/"))), reviewF.getText().toString(), list.getValue().getPUBID()));
                if(type==1)
                 brw.addBook(new Book(Integer.parseInt(isbnF.getText().toString()), titleF.getText().toString(),
                        authorF.getText().toString(),p.getName(), (new BDate(0, pYear.getValue().toString().replace("-", "/"))), reviewF.getText().toString(), p.getPUBID()));
                
              
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
            //
            if(type==0)
            new ViewBooks(emp).shfaq(st);
            else if(type == 1){
                new PublisherInterface(p).show(st);
            }
        });
        VBox vb = new VBox(isbn, isbnF, title, titleF, publisher, list, pubYear, pYear, author, authorF, review, reviewF, add, cancel);
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        pane.setCenter(vb);
        pane.setLeft(new Label("    "));
        pane.setRight(new Label("   "));
        pane.setStyle("-fx-background-color: coral");
        Scene scene = new Scene(pane, 380, 550);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("buton.css").toExternalForm());
        st.setScene(scene);
        st.setTitle("Add Books");
        st.show();

    }
    
    public Pane bookAdd(){
       BorderPane pane = new BorderPane();

        Label isbn = new Label("Enter ISBN");
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
        Label title = new Label("Enter Title");
        //title.setStyle("text_id");
        TextField titleF = new TextField();
        titleF.setStyle(""
                + "-fx-background-radius: 22px");
        Label publisher = new Label("Select Publisher");
        //publisher.setStyle("text_id");

        pub = prw.readPub();
        ChoiceBox<Publisher> list = new ChoiceBox<Publisher>(
                FXCollections.observableArrayList(pub)
        );
        list.setValue(pub.get(0));

        Label pubYear = new Label("Select Publishing Year");
        DatePicker pYear = new DatePicker();
        pYear.setEditable(false);

        Label author = new Label("Author");
        //author.setStyle("text_id");
        TextField authorF = new TextField();
        authorF.setStyle(""
                + "-fx-background-radius: 22px");
        Label review = new Label("Write a review:");
        TextField reviewF = new TextField();
        //review.setStyle("text_id");

        reviewF.setStyle(""
                + "-fx-background-radius: 22px");
        Button add = new Button("Add to library");
        add.setPrefSize(140, 35);
        add.setOnAction((ActionEvent e) -> {
            //public Book(int iSBN, String title, String author, String pulisher, BDate pubYear, String review, int pubId) {

            //  try {
            if (review.getText().isEmpty()) {
                review.setText("");
            }
            if (!isbnF.getText().isEmpty() && !authorF.getText().isEmpty()
                    && !titleF.getText().isEmpty() && !pYear.getValue().toString().replace("-", "/").isEmpty()) {

                brw.addBook(new Book(Integer.parseInt(isbnF.getText().toString()), titleF.getText().toString(),
                        authorF.getText().toString(), list.getValue().getName(), (new BDate(0, pYear.getValue().toString().replace("-", "/"))), reviewF.getText().toString(), list.getValue().getPUBID()));
                Alert al = new Alert(Alert.AlertType.INFORMATION, "Book was successfully added!");
                al.show();
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
            //
          //  new ManagerInterface(emp).show();
        });
        VBox vb = new VBox(isbn, isbnF, title, titleF, publisher, list, pubYear, pYear, author, authorF, review, reviewF, add, cancel);
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        pane.setCenter(vb);
        pane.setLeft(new Label("    "));
        pane.setRight(new Label("   "));
        pane.setStyle("-fx-background-color: coral");
        pane.getStylesheets().add(getClass().getClassLoader().getResource("buton.css").toExternalForm());
        pane.setPrefSize(350, 500);
        return pane;

    }
}
