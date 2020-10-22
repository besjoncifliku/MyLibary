package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewUser {

    public NewUser(){
        
    }
    public void show(Stage st){
        
        BorderPane mainPane = new BorderPane();
        
        //Image img1 = new Image("images/membership.png");
        Button member = new Button("I am new Member!");
        member.setPrefSize(200, 80);
        member.setOnAction(e->{
           // new AddMember().show(st);
        });
        
//        Image img2 = new Image("images/feather.png");
        Button publisher = new Button("I am publisher!");
        publisher.setPrefSize(200, 80);
        publisher.setOnAction(e->{
            new AddPublisher().show(st);
        });

        Scene scene = new Scene(mainPane, 600, 400);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("styleC.css").toExternalForm());
        //HBox hb =new HBox(member,publisher);
        VBox hb = new VBox(publisher,member);
        hb.setSpacing(20);
        hb.setAlignment(Pos.CENTER);
        mainPane.setCenter(hb);
        
        //Scene sc = new Scene(mainPane,550,550);
        st.setScene(scene);
        st.show();
        st.setTitle("Sign Up!");
        
    }
}
