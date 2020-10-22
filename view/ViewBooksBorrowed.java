package view;

import java.util.ArrayList;
import static javafx.beans.binding.Bindings.add;
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
import model.Borrow;
import model.Member;
import readRW.BorrowRW;

public class ViewBooksBorrowed {

    private ArrayList<Borrow> book;
    private Member mem;
    private BorrowRW brw;
    public ViewBooksBorrowed(ArrayList<Borrow> book,Member m) {
        this.book = book;
        mem = m;
    }

    public void show(Stage st) {
        TableView table = new TableView();
        VBox vb = new VBox();
        table.setEditable(true);
        TableColumn idm = new TableColumn("BookTitle");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("bookTitle"));
        TableColumn title = new TableColumn("Date Borrowed");
        title.setMinWidth(150);
        title.setCellValueFactory(
                new PropertyValueFactory<>("borrowDate"));
        TableColumn nm = new TableColumn("Should be handed before:");
        nm.setMinWidth(150);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("givenDate"));
        
        table.setItems(FXCollections.observableArrayList(
                book
        ));
        table.getColumns().addAll(idm, title, nm);
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {

                
                    new MemberInterface(mem).show(st);
               

            }
        });
        
        Button del = new Button("Return Books");
        del.setOnAction(
                e -> {
                    for (Object x : table.getSelectionModel().getSelectedItems()) {
                        if (x instanceof Borrow) {
                            System.out.print(x);
                            book.remove((Borrow) x);

                            brw = new BorrowRW();
                            brw.remove((Borrow) x);
                        }
                        new ViewBooksBorrowed(book,mem).show(st);
                    }
                }
            );
        HBox hb = new HBox();
        hb.getChildren().addAll(del,back);
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
