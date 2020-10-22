package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Employee;

public class Edit {
    private Employee emp;
    public Edit(Employee emp){
        this.emp=emp;
    }
    
    public void show(Stage st){
        
        BorderPane pane = new BorderPane();
        
        Button add = new Button("Add User");
        add.setOnAction(e->{
            new AddUser().showAdm(st);
        });
        add.setPrefSize(200, 50);
        
        Button pw = new Button("Edit Employee");
        pw.setOnAction(e->{
            new EditEmployee(emp).show(st);
        });
        pw.setPrefSize(200, 50);
        
        Button  eu = new Button("Edit User");
        eu.setOnAction(e->{
            new EditPerson(emp).show(st);
        });
        eu.setPrefSize(200, 50);
        Button back = new Button("Back");
        back.setPrefSize(200, 50);
        
        back.setOnAction(e->{
            new AdminInterface(emp).show(st);
        });
        
        
        VBox vb = new VBox(add,pw,eu,back);
        vb.setPadding(new Insets(10,10,10,10));
        //vb.setPadding(new Insets(15));
        vb.setSpacing(25);
        
        vb.setAlignment(Pos.CENTER);
        pane.setCenter(vb);
        
       pane.setStyle(
            "-fx-background-image: url('images/editInterface.jpg');"
               + "-fx-background-repeat: stretch;"
						);
        Scene scene = new Scene(pane,550,550);
        st.setScene(scene);
        st.setTitle("Edit");
        scene.getStylesheets().add(getClass().getClassLoader().getResource("buton.css").toExternalForm());		

        st.show();
    }
}
