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
import model.Admin;
import model.Employee;
import model.Member;
import model.Tipi;
import readRW.MemberRW;
import readRW.UserRW;



public class UserView  {
	private ArrayList<Employee> med;
        //arraylist me persona dhe i bashkon 
        private UserRW mrw;
        private Employee e;
	public UserView(Employee e) {
            med=new ArrayList<Employee>();
            mrw=new UserRW();
            this.e=e;
		
	}

    /**
     *
     * @param st
     */
    public void shfaq(Stage st) {
	
            med=mrw.readEmp();
        TableView table = new TableView();
		VBox vb=new VBox();
		table.setEditable(true);
        TableColumn idm = new TableColumn("Name");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn nm = new TableColumn("Surname");
        nm.setMinWidth(100);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("surname"));
        TableColumn snm = new TableColumn("Birthdate");
        snm.setMinWidth(100);
        snm.setCellValueFactory(
                new PropertyValueFactory<>("bdt"));
        TableColumn bdy = new TableColumn("Phone");
        bdy.setMinWidth(100);
        bdy.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        TableColumn phone = new TableColumn("Salary");
        phone.setMinWidth(100);
        phone.setCellValueFactory(
                new PropertyValueFactory<>("salary"));
        
        //ArrayList<Member> bosh=new ArrayList<Member>();
        //bosh=mrw.readMember();
        table.setItems(FXCollections.observableArrayList(
        			med
        		));
        table.getColumns().addAll(idm,nm,snm,bdy,phone);
		
        Button back=new Button("Back");
		
        back.setOnAction(new EventHandler<ActionEvent>(){	
            @Override
                public void handle(ActionEvent arg0) {
                    
                        new AdminInterface((Admin) e).show(st);
                    
				
			}
		});
        HBox hb =new HBox();
        Button add=new Button("Add User");
        add.setOnAction(
                new EventHandler<ActionEvent>() {			
                    public void handle(ActionEvent event) {
                        new AddUser().showMenu(st);
						//new AddMedicine().show(st);
						//new AddSupplyer().show(st);		
                    }   	
                }	
        );
        Employee emp=e;
        Button del=new Button("Remove User");
		
        del.setOnAction(
                e->{
                    for(Object x:table.getSelectionModel().getSelectedItems()) {
                        if(x instanceof Employee)
                            mrw.remove((Employee) x);
                        new UserView(emp).shfaq(st);
                    }
                }	
        );

//		Button editQuantity=new Button("Buy Medicines");
//		editQuantity.setOnAction(e -> {
//			setQuantity((Medicine) table.getSelectionModel().getSelectedItem(),st);
//			
//		});
        hb.getChildren().addAll(add,del,back);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(table,hb);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: black");
		Scene sc=new Scene(vb,860,350);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("styleC.css").toExternalForm());		
		st.setScene(sc);
		st.setTitle("Magazina");
        
		st.show();
	}
	
	
	
	/*
	 * Method used to change quantity!
	 * 
	 */
/*	
	private void setQuantity(Medicine med,Stage st)
	{
		try {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		
		Button set = new Button("Add Quantity");
		Button cancel = new Button("Cancel");
		try {
			
		//TextField quantity = new TextField(Double.toString(med.getQuantity()));
			Label info=new Label("Quantity="+Double.toString(med.getQuantity())+"cop");
			TextField quantity = new TextField();
			quantity.setPromptText(Double.toString(med.getQuantity()));
		// force the field to be numeric only
		quantity.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable, String oldValue, 
		        String newValue) {
		        if (!newValue.matches("\\d*")) {
		            quantity.setText(newValue.replaceAll("[^\\d]", ""));
		        }
		    }
		});
		HBox hb = new HBox(set, cancel);
		VBox vb = new VBox(info,quantity, hb);
		hb.setAlignment(Pos.CENTER);
		vb.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(vb);
		stage.setScene(scene);
		stage.show();
		
		set.setOnAction(e -> {
			try {
			MedicineRW mrw=new MedicineRW();
			mrw.setQuantity(med, med.getQuantity()+Integer.parseInt(quantity.getText()),med.getPriceSold());
			brw.spendMoney((Integer.parseInt(quantity.getText())*med.getPriceBuy()));
			brw.calcExpenseProd(med, Integer.parseInt(quantity.getText()));
			brw.fillProdBuy(med,(Integer.parseInt(quantity.getText())));
		
			stage.close();
			
			new Stock().show(st);
			}catch(Exception err) {
				Alert al=new Alert(AlertType.INFORMATION,"Write desired quantity in empty box");
				al.show();
			}
		});
		
		cancel.setOnAction(e -> {
			stage.close();
		});
		}
		catch (Exception err) {
			Alert al=new Alert(AlertType.CONFIRMATION,"Make sure you select one cell!");
			al.show();
		}
	}
		catch (Exception e){
			Alert al=new Alert(AlertType.INFORMATION,"Select something in the table?");
			al.show();
		}
			
		}
*/	

    
	
}
