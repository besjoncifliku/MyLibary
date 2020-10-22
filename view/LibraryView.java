/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Library;
import readRW.LibraryRW;

/**
 *
 * @author besjo
 */
public class LibraryView {
    private Employee emp;
    private LibraryRW lrw;
    public LibraryView(Employee e){
        this.emp = e;
       
        lrw = new LibraryRW();

    }
    private ArrayList<Library> lib;

    public void shfaq(Stage st) {
        lib = new ArrayList<>();
        lib = lrw.readBooks();
        TableView table = new TableView();
        VBox vb = new VBox();
        table.setEditable(true);
        TableColumn idm = new TableColumn("ISBN");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("bookISBN"));
        TableColumn nm = new TableColumn("CopyID");
        nm.setMinWidth(50);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("copyId"));
       

        table.setItems(FXCollections.observableArrayList(
                lib
        ));
        table.getColumns().addAll(idm, nm);
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
                        if (x instanceof Library)
                        {  lrw.removeBook((Library) x);
                        new LibraryView(emp).shfaq(st);
                            
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
        //vb.setStyle("-fx-background-color: black");
        Scene sc = new Scene(vb, 250, 450);
        //.getStylesheets().add(getClass().getClassLoader().getResource("styleC.css").toExternalForm());
        st.setScene(sc);
        st.setTitle("MyLibray");

        st.show();
    }
}
