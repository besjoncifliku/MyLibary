package view;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Book;
import model.Employee;
import model.Publisher;
import readRW.LibraryRW;
import readRW.PublisherRW;
import readRW.UserRW;

public class ManagerInterface {

    private Employee emp;
    private UserRW urw;
    private ArrayList<Publisher> pub;
    private PublisherRW prw;
    private LibraryRW lrw;

    public ManagerInterface(Employee emp) {
        this.emp = emp;
        urw = new UserRW();
        prw = new PublisherRW();
        lrw = new LibraryRW();
    }
    private ArrayList<Book> book;
    private TableView tableList;

    public void show(Stage st) {
        tableList = new TableView();
        book = new ArrayList<Book>();
        GridPane mainPane = new GridPane();
        mainPane.setHgap(15);
        mainPane.setVgap(15);
        mainPane.setPadding(new Insets(10, 10, 10, 10));
        tableList = new ListBooks().listBooks();
        Label mainT = new Label("MyLibrary");
        //txt.setEffect(new DropShadow());
        mainT.setStyle("-fx-font-family: \"Arial Black\";"
                + "-fx-font-size: 21.5px;"
        //+"-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
        );
        mainT.setTextFill(Color.WHITE);
        VBox box = new VBox(mainT,tableList,new Label(" "),new Label(" "),new Label(" "));
        box.setSpacing(40);
        box.setAlignment(Pos.CENTER);
        mainPane.addColumn(0, box);

        //BorderPane pane = new BorderPane();
        //pane = (BorderPane) new AddBooks(emp).bookAdd();

        Label publisher = new Label("Add new book to library:");
        //publisher.setStyle("text_id");

        pub = prw.readPub();
        ChoiceBox<Publisher> list = new ChoiceBox<Publisher>(
                FXCollections.observableArrayList(pub)
        );
        list.setValue(pub.get(0));

        TableView table = new TableView();
        table.setEditable(true);
        TableColumn us = new TableColumn("Title");
        us.setMinWidth(130);
        us.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        TableColumn nm = new TableColumn("Author");
        nm.setMinWidth(130);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("author"));
        table.setItems(FXCollections.observableArrayList(
                list.getValue()
        ));
        table.getColumns().addAll(us, nm);
        table.setMaxSize(260, 580);
        //default value

        list.setOnAction(e -> {
            if (list.getValue() != null) {
                book = prw.viewMyBooks(list.getValue());

                table.setItems(FXCollections.observableArrayList(
                        book
                ));
            }
        });
        book = prw.viewMyBooks(list.getValue());
        ObservableList<Book> books //
                = FXCollections.observableArrayList(book);

        ChangeListener<Publisher> changeListener = new ChangeListener<Publisher>() {

            @Override
            public void changed(ObservableValue<? extends Publisher> observable, Publisher oldValue, Publisher newValue) {
                book = prw.viewMyBooks(newValue);

                table.setItems(FXCollections.observableArrayList(
                        prw.viewMyBooks(newValue)
                ));

            }
        };
        // Selected Item Changed.
        list.getSelectionModel().selectedItemProperty().addListener(changeListener);
        list.setOnAction(e -> {
            if (list.getValue() != null) {
                book = prw.viewMyBooks(list.getValue());
            }
        });
         Image addM = new Image("images/signin.png");
        Button add = new Button("Add In Library", new ImageView(addM));
        //Button add = new Button("Add In Library");
        add.setOnAction(e -> {
            try {
                lrw.addBookInLibrary((Book) table.getSelectionModel().getSelectedItem(), ((Book) table.getSelectionModel().getSelectedItem()).getReview());
                
            //book.add((Book) table.getSelectionModel().getSelectedItem());
                //tableList = new ListBooks().listBooks();
                new ManagerInterface(emp).show(st);
            } catch (Exception ex) {
                Alert al = new Alert(AlertType.ERROR, "Please select a book to add in the library!");
                al.show();
            }

        });
        add.setPrefSize(200, 50);
        Image exit = new Image("images/exit.png");
        Button logout = new Button("Logout", new ImageView(exit));
        //Button logout = new Button("Logout");
        logout.setOnAction(e -> {
            try {
                new LogIn().show(st);
            } catch (Exception ex) {
                Logger.getLogger(ManagerInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        logout.setPrefSize(200, 50);
        VBox vb = new VBox(publisher, list, table, add, logout);
        vb.setSpacing(15);
        vb.setAlignment(Pos.CENTER);
        
        
        Image img1 = new Image("images/statistics.png");
        Button statistic = new Button("", new ImageView(img1));
        Image img2 = new Image("images/view.png");
        Button view = new Button("", new ImageView(img2));
        Image img3 = new Image("images/check.png");
        Button admB = new Button("", new ImageView(img3));
        Image bok = new Image("images/books.png");
        Button vbook = new Button("", new ImageView(bok));
        vbook.setOnAction(e->{
            new LibraryView(emp).shfaq(st);
        });
        statistic.setOnAction(e->{
            
        });
        
        view.setOnAction(e->{
            new Members(emp).shfaq(st);
        });
        
        admB.setOnAction(e->{
           new BorrowedBook(emp).show(st);
        });
        
        VBox menu = new VBox(vbook,statistic,view,admB);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(20);
        
        mainPane.addColumn(2, vb);
        mainPane.addColumn(3,menu);

        Scene scene = new Scene(mainPane, 750, 500);
        st.setScene(scene);
        st.setTitle("Edit");
        scene.getStylesheets().add(getClass().getClassLoader().getResource("buton.css").toExternalForm());
        mainPane.setStyle("-fx-background-color: coral");
        mainPane.getStylesheets().add(getClass().getClassLoader().getResource("buton.css").toExternalForm());
        
        st.show();
    }
}
