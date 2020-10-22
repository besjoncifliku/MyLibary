package view;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Publisher;
import readRW.PublisherRW;

public class PublisherInterface {
    private Publisher emp;
    private PublisherRW prw;
    public PublisherInterface(Publisher emp){
        this.emp=emp;
    }
    
    public void show(Stage st){
        
        BorderPane pane = new BorderPane();
        
        Button view = new Button("View My Books");
        view.setPrefSize(200, 50);
        view.setOnAction(e->{
            prw = new PublisherRW();
            
            new ViewMyBooks(prw.viewMyBooks(emp),emp).show(st);
        
    });
        Button pw = new Button("Publish Book");
        pw.setOnAction(e->{
            new AddBooks(emp).show(st);
        });
        pw.setPrefSize(200, 50);
        
     
        Button back = new Button("Log Out");
        back.setPrefSize(200, 50);
        
        back.setOnAction(e->{
            try {
                new LogIn().show(st);
            } catch (Exception ex) {
                Logger.getLogger(PublisherInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        
        VBox vb = new VBox(view,pw,back);
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
