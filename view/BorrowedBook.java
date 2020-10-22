package view;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Borrow;
import model.Employee;
import model.Member;
import readRW.BorrowRW;

public class BorrowedBook {

    private ArrayList<Borrow> book;
    private Employee emp;
    private BorrowRW brw;
    public BorrowedBook(Employee m) {
        book = new ArrayList<>();
        emp = m;
        brw = new BorrowRW();
    }

    public void show(Stage st) {
        book = brw.readBorrow();
        TableView table = new TableView();
        VBox vb = new VBox();
        table.setEditable(true);
        TableColumn idm = new TableColumn("Member Name");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("member"));
        TableColumn id = new TableColumn("Member ID");
        id.setMinWidth(100);
        id.setCellValueFactory(
                new PropertyValueFactory<>("idm"));
        
        TableColumn title = new TableColumn("Date Borrowed");
        title.setMinWidth(150);
        title.setCellValueFactory(
                new PropertyValueFactory<>("borrowDate"));
        TableColumn nm = new TableColumn("Should be handed before:");
        nm.setMinWidth(150);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("givenDate"));
        
        TableColumn isbn = new TableColumn("ISBN");
        isbn.setMinWidth(150);
        isbn.setCellValueFactory(
                new PropertyValueFactory<>("ISBN"));
        
        TableColumn bok = new TableColumn("bookTitle");
        bok.setMinWidth(150);
        bok.setCellValueFactory(
                new PropertyValueFactory<>("bookTitle"));
         TableColumn cid = new TableColumn("Copy Id");
        cid.setMinWidth(150);
        cid.setCellValueFactory(
                new PropertyValueFactory<>("copyID"));
        
        table.setItems(FXCollections.observableArrayList(
                book
        ));
        table.getColumns().addAll(idm,id, title, nm,isbn,bok,cid);
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {

                
                    new ManagerInterface(emp).show(st);
               

            }
        });
       
        HBox hb = new HBox();
        hb.getChildren().addAll(back);
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
