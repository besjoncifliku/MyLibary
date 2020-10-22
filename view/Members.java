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
import model.Member;
import readRW.MemberRW;



public class Members  {
	private  static ArrayList<Member> med;
        private static  MemberRW mrw;
        private Employee emp;
	public Members(Employee emp) {
            med=new ArrayList<Member>();
            mrw=new MemberRW();
            this.emp=emp;
		
	}

    /**
     *
     * @param st
     */
    public void shfaq(Stage st) {
	
            med=mrw.readMem();
		TableView table=new TableView();
		VBox vb=new VBox();
		table.setEditable(true);
        TableColumn idm = new TableColumn("Member ID");
        idm.setMinWidth(100);
        idm.setCellValueFactory(
                new PropertyValueFactory<>("idm"));
        TableColumn nm = new TableColumn("Name");
        nm.setMinWidth(100);
        nm.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        TableColumn snm = new TableColumn("Surname");
        snm.setMinWidth(100);
        snm.setCellValueFactory(
                new PropertyValueFactory<>("surname"));
        TableColumn bdy = new TableColumn("Registration Date");
        bdy.setMinWidth(100);
        bdy.setCellValueFactory(
                new PropertyValueFactory<>("regDate"));
        TableColumn phone = new TableColumn("Phone");
        phone.setMinWidth(100);
        phone.setCellValueFactory(
                new PropertyValueFactory<>("phone"));
        
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
				new ManagerInterface(emp).show(st);
				
			}
		});
        HBox hb =new HBox();
        Button add=new Button("Add Member");
        add.setOnAction(
        		new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
                                            new Members(emp).shfaq(st);
					}
        			
        		}
        		);
        Button del=new Button("Remove Member");
		del.setOnAction(
				e->{
					for(Object x:table.getSelectionModel().getSelectedItems()) {
						if(x instanceof Member)
							mrw.remove((Member) x);
						new Members(emp).shfaq(st);
					}
				}
				);

        hb.getChildren().addAll(add,del,back);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(table,hb);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: black");
		Scene sc=new Scene(vb,860,350);
		//sc.getStylesheets().add(getClass().getClassLoader().getResource("styleC.css").toExternalForm());		
		st.setScene(sc);
		st.setTitle("Members View");
        
		st.show();
	}
	
}
