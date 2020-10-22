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
import model.Publisher;
import readRW.PublisherRW;



public class PublisherView  {
	private  static ArrayList<Publisher> pub;
        private static  PublisherRW prw;
	public PublisherView() {
            pub=new ArrayList<Publisher>();
            prw=new PublisherRW();
		
	}

    public void shfaq(Stage st) {
	
        pub=prw.readPub();
        //prw.test();
	TableView table = new TableView();
        VBox vb = new VBox();
        table.setEditable(true);
        TableColumn idm = new TableColumn("ID");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("PUBID"));
        TableColumn nm = new TableColumn("Name");
        nm.setMinWidth(200);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn snm = new TableColumn("Phone");
        snm.setMinWidth(100);
        snm.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        TableColumn contact = new TableColumn("Contact Person");
        contact.setMinWidth(100);
        contact.setCellValueFactory(
                new PropertyValueFactory<>("contactPerson"));

        TableColumn adress = new TableColumn("Adress");
        adress.setMinWidth(150);
        adress.setCellValueFactory(
                new PropertyValueFactory<>("adress"));

        table.setItems(FXCollections.observableArrayList(
                pub
        ));
        table.getColumns().addAll(idm, nm, snm, contact, adress);
        Button back = new Button("Back");
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
				//new ManagerView().show(st);

            }
        });
        HBox hb = new HBox();
        Button add = new Button("Add Publisher");
        add.setOnAction(
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent event) {
						//new AddMedicine().show(st);
                        //new AddSupplyer().show(st);
                    }

                }
        );
        Button del = new Button("Remove Publisher");
        del.setOnAction(
                e -> {
                    for (Object x : table.getSelectionModel().getSelectedItems()) {
                        if (x instanceof Publisher) {
                            prw.remove((Publisher) x);
                        }
                        new PublisherView().shfaq(st);
                    }
                }
        );

//		Button editQuantity=new Button("Buy Medicines");
//		editQuantity.setOnAction(e -> {
//			setQuantity((Medicine) table.getSelectionModel().getSelectedItem(),st);
//			
//		});
        hb.getChildren().addAll(add, del, back);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(table, hb);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: black");
        Scene sc = new Scene(vb, 650, 350);
        sc.getStylesheets().add(getClass().getClassLoader().getResource("styleC.css").toExternalForm());
        st.setScene(sc);
        st.setTitle("View Publishers");

        st.show();
	}
	
}
