package view;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Employee; 
import model.Manager;
import model.Tipi;
import model.User;
import readRW.UserRW;


public class EditEmployee {
	private User currentUser;
	private int type=0;//tipi fillimisht eshte 0=admin
	public EditEmployee(Employee currentUser) {
		this.currentUser = currentUser;
	}
		

	
	public void show(Stage st) {
		UserRW rw = new UserRW();

		ObservableList<User> userList = FXCollections.observableArrayList(rw.readEmp());

		TableView userTable = new TableView();
		userTable.setEditable(true);
		TableColumn<Employee, String> uNameC = new TableColumn("Username");
		uNameC.setCellValueFactory(new PropertyValueFactory<>("username"));
		uNameC.setMinWidth(100);
		uNameC.setCellFactory(TextFieldTableCell.<Employee>forTableColumn());
		
		uNameC.setOnEditCommit(
            new EventHandler<CellEditEvent<Employee, String>>() {
                @Override
                public void handle(CellEditEvent<Employee, String> t) {
                if(rw.checkUsername(t.getNewValue())) { 	 
               
                    Employee oldData = (Employee) userTable.getSelectionModel().getSelectedItem();               	 
	
                    ((Employee) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setUsername(t.getNewValue());
                    
                	
                    Employee newData = (Employee) userTable.getSelectionModel().getSelectedItem();
           
                    UserRW rw=new UserRW();

                   //rw.changeUserName(newData.getUsername(), t.getNewValue());
               
                   rw.editUser(oldData,userTable.getSelectionModel().getSelectedIndex(),newData);
                   }else {
                	   
                		Alert al=new Alert(AlertType.INFORMATION,"This username already exists!\n"+""
                				+ "Could not complete saving! Try again...");
            			al.show();
                   }
                }
            }
        );
		
		TableColumn<Employee, String> passC = new TableColumn("Password");
		passC.setCellValueFactory(new PropertyValueFactory<>("pass"));
		passC.setMinWidth(100);
		passC.setCellFactory(TextFieldTableCell.<Employee>forTableColumn());
		
		passC.setOnEditCommit(
            new EventHandler<CellEditEvent<Employee, String>>() {
                @Override
                public void handle(CellEditEvent<Employee, String> t) {
                    Employee oldData = (Employee) userTable.getSelectionModel().getSelectedItem();               	 
                	((Employee) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setPass(t.getNewValue());
                   Employee newData = (Employee) userTable.getSelectionModel().getSelectedItem();                   
                   UserRW rw=new UserRW();
                   //rw.changePass(newData.getUsername(), t.getNewValue());
                   rw.editUser(oldData,userTable.getSelectionModel().getSelectedIndex(), newData);
                }
            }
        );

		
		
	        TableColumn nm = new TableColumn("Name");
	        nm.setMinWidth(100);
	        nm.setCellValueFactory(
	                new PropertyValueFactory<>("name"));
	        TableColumn snm = new TableColumn("Surname");
	        snm.setMinWidth(100);
	        snm.setCellValueFactory(
	                new PropertyValueFactory<>("surname"));
	        TableColumn bdy = new TableColumn("Birthday");
	        bdy.setMinWidth(100);
	        bdy.setCellValueFactory(
	                new PropertyValueFactory<>("bdt"));

	        ArrayList<User> bosh=new ArrayList<User>();
	        for(Employee x:rw.readEmp()) {
	        	bosh.add(x);
	        }
		userTable.setItems(FXCollections.observableArrayList(bosh));
		userTable.getColumns().addAll(uNameC, passC,nm,snm,bdy);
		
		Label lb=new Label("Select username or password and type new value(Press enter to save it!)");
		lb.setStyle(
				"   -fx-font-size: 12px;" + 
				"   -fx-font-family: \"Arial Black\";" + 
				"   -fx-fill: #818181;" 
				);
		
		HBox box1=new HBox(lb);
		box1.setAlignment(Pos.CENTER);
		
		Button cancel = new Button("Cancel");
		
		cancel.setOnAction(e -> {
			new Edit((Employee) currentUser).show(st);
		});
		
		VBox vb = new VBox();
		vb.getChildren().addAll(box1,new Label(""),userTable,new Label(""), cancel);
		vb.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vb,500,500);
		st.setScene(scene);
		st.setTitle("Edit Users");
		
		
	}

}
