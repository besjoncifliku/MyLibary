package view;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import model.Publisher;
import readRW.BookRW;

public class ViewMyBooks {

    private ArrayList<Book> book;
    private Publisher pub;
    private BookRW brw;
    public ViewMyBooks(ArrayList<Book> book,Publisher p) {
        this.book = book;
        pub = p;
        
    }

    public void show(Stage st) {
        TableView table = new TableView();
        VBox vb = new VBox();
        table.setEditable(true);
        TableColumn idm = new TableColumn("ISBN");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("ISBN"));
        TableColumn title = new TableColumn("Title");
        title.setMinWidth(150);
        title.setCellValueFactory(
                new PropertyValueFactory<>("title"));
        TableColumn nm = new TableColumn("Author");
        nm.setMinWidth(150);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("author"));
        
        TableColumn contact = new TableColumn("Published Year");
        contact.setMinWidth(100);
        contact.setCellValueFactory(
                new PropertyValueFactory<>("pubYear"));



        table.setItems(FXCollections.observableArrayList(
                book
        ));
        table.getColumns().addAll(idm, title, nm,contact);
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {

                
                    new PublisherInterface(pub).show(st);
               

            }
        });
        HBox hb = new HBox();
        Button add = new Button("Add Book");
        add.setOnAction(
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
                        //new AddMyBook(p).show(st);
                    }

                }
        );
        Button review = new Button("Check Review");
        review.setOnAction(
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        try {
                            new ReviewBook((Book) table.getSelectionModel().getSelectedItem()).showReview();
                        } catch (Exception e) {
                            Alert al = new Alert(Alert.AlertType.ERROR, "Please select a book!");
                            al.show();
                        }
                    }
                }
        );
        Button del = new Button("Delete MyBook");
        del.setOnAction(
                e -> {
                    for (Object x : table.getSelectionModel().getSelectedItems()) {
                        if (x instanceof Book) {
                            brw = new BookRW();
                            brw.remove((Book) x);
                        }
                        new PublisherView().shfaq(st);
                    }
                }
        );

        hb.getChildren().addAll(add, del, back, review);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(table, hb);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: black");
        Scene sc = new Scene(vb, 830, 350);
        sc.getStylesheets().add(getClass().getClassLoader().getResource("styleC.css").toExternalForm());
        st.setScene(sc);
        st.setTitle("View Books");

        st.show();
    }
}
