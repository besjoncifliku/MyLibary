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
import model.Employee;
import model.LibraryBook;
import model.Member;
import readRW.LibraryRW;

public class ListBooks {

    private static ArrayList<LibraryBook> book;
    private static LibraryRW lrw;
    private Employee emp;

    public ListBooks() {
        book = new ArrayList<LibraryBook>();
        lrw = new LibraryRW();

    }
    public ListBooks(Employee e){
        this.emp = e;
        book = new ArrayList<LibraryBook>();
        lrw = new LibraryRW();

    }

    public void shfaq(Stage st) {

        book = lrw.readLib();
        TableView table = new TableView();
        VBox vb = new VBox();
        table.setEditable(true);
        TableColumn idm = new TableColumn("ISBN");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("ISBN"));
        TableColumn nm = new TableColumn("Title");
        nm.setMinWidth(200);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn snm = new TableColumn("Quantity");
        snm.setMinWidth(100);
        snm.setCellValueFactory(
                new PropertyValueFactory<>("count"));

        table.setItems(FXCollections.observableArrayList(
                book
        ));
        table.getColumns().addAll(idm, nm, snm);
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
		new ManagerInterface(emp).show(st);

            }
        });
        HBox hb = new HBox();

        Button del = new Button("Remove Book");
        del.setOnAction(
                e -> {
                    for (Object x : table.getSelectionModel().getSelectedItems()) {
                        //if (x instanceof Library) //mrw.remove((Member) x);
                        {
                            
                            //new Members(emp).shfaq(st);
                        }
                    }
                }
        );

        hb.getChildren().addAll(del, back);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(table, hb);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: black");
        Scene sc = new Scene(vb, 400, 350);
        sc.getStylesheets().add(getClass().getClassLoader().getResource("styleC.css").toExternalForm());
        st.setScene(sc);
        st.setTitle("MyLibray");

        st.show();
    }
    
    public TableView listBooks(){
        book = lrw.readLib();
        TableView table = new TableView();
        VBox vb = new VBox();
        table.setEditable(true);
        TableColumn idm = new TableColumn("ISBN");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("ISBN"));
        TableColumn nm = new TableColumn("Title");
        nm.setMinWidth(200);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn snm = new TableColumn("Quantity");
        snm.setMinWidth(100);
        snm.setCellValueFactory(
                new PropertyValueFactory<>("count"));

        table.setItems(FXCollections.observableArrayList(
                book
        ));
        table.getColumns().addAll(idm, nm, snm);
        
        table.setPrefSize(350, 650);
        return table;
        
        
    }
    

}
