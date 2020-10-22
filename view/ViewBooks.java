package view;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Admin;
import model.Book;
import model.Employee;
import model.Tipi;
import readRW.BookRW;




public class ViewBooks  {
	private  static ArrayList<Book> book;
        private static  BookRW brw;
        private Employee e;
	public ViewBooks(Employee e) {
            book=new ArrayList<Book>();
            brw=new BookRW();
            this.e = e;
		
	}

    public void shfaq(Stage st) {
	
        book=brw.readBok();
        //prw.test();
	TableView table=new TableView();
	VBox vb=new VBox();
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
        TableColumn snm = new TableColumn("Publisher");
        snm.setMinWidth(100);
        snm.setCellValueFactory(
                new PropertyValueFactory<>("pulisher"));
        TableColumn contact = new TableColumn("Published Year");
        contact.setMinWidth(100);
        contact.setCellValueFactory(
                new PropertyValueFactory<>("pubYear"));
        
        TableColumn adress = new TableColumn("Review");
        adress.setMinWidth(222);
        adress.setCellValueFactory(
                new PropertyValueFactory<>("review"));
      
        
        table.setItems(FXCollections.observableArrayList(
        			book
        		));
        table.getColumns().addAll(idm,title,nm,snm,contact,adress);
		Button back=new Button("Back");
		back.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent arg0) {
				
                            
                                new AdminInterface((Admin) e).show(st);
                            
				
			}
		});
        HBox hb =new HBox();
        Button add=new Button("Add Book");
        add.setOnAction(
        		new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						new AddBooks(e).show(st);
					}
        			
        		}
        		);
        Button review=new Button("Check Review");
        review.setOnAction(
        		new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
                                              try{
                                                  new ReviewBook((Book) table.getSelectionModel().getSelectedItem()).showReview();
                                              }catch(Exception e){
                                                  Alert al = new Alert(AlertType.ERROR,"Please select a book!");
                                                  al.show();
                                              }
					}
        		}
        		);
        Button del=new Button("Remove Book");
		del.setOnAction(
				e->{
					for(Object x:table.getSelectionModel().getSelectedItems()) {
						if(x instanceof Book)
                                                    brw.remove((Book) x);
						new PublisherView().shfaq(st);
					}
				}
				);

//		Button editQuantity=new Button("Buy Medicines");
//		editQuantity.setOnAction(e -> {
//			setQuantity((Medicine) table.getSelectionModel().getSelectedItem(),st);
//			
//		});
        hb.getChildren().addAll(add,del,back,review);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(table,hb);
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-background-color: black");
		Scene sc=new Scene(vb,830,350);
		sc.getStylesheets().add(getClass().getClassLoader().getResource("styleC.css").toExternalForm());		
		st.setScene(sc);
		st.setTitle("View Books");
        
		st.show();
	}
}
	
	