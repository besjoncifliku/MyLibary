package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Employee;

public class AdminInterface {

    private Employee adm;

    public AdminInterface(Employee admin) {
        this.adm = admin;

    }

    public void show(Stage st) {

        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        Image backImg = new Image("images/back.png");
        Button back = new Button("", new ImageView(backImg));
        HBox hb = new HBox(back);
        hb.setAlignment(Pos.CENTER_LEFT);
        pane.setTop(hb);
        back.setOnAction(
                e -> {
                    try {
                        new LogIn().show(st);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
        );

        Image img1 = new Image("images/add.png");
        Button add = new Button("", new ImageView(img1));
        Image img2 = new Image("images/view.png");
        Button view = new Button("", new ImageView(img2));
        Image img3 = new Image("images/check.png");
        Button admB = new Button("", new ImageView(img3));
        Image img4 = new Image("images/edit.png");
        Button edit = new Button("", new ImageView(img4));
        //Button edit = new Button("Edit");
        HBox box1 = new HBox(add, view, admB, edit);
        box1.setSpacing(38);
        box1.setAlignment(Pos.CENTER);

        pane.setCenter(box1);

        admB.setOnAction(
                e -> {
                    //view books
                    new ViewBooks(adm).shfaq(st);
                }
        );
        admB.setPrefSize(90, 90);

        add.setOnAction(
                e -> {
                    new AddUser().showAdm(st);
                }
        );
        add.setPrefSize(90, 90);
        view.setOnAction(
                e -> {
                    new UserView(adm).shfaq(st);
                }
        );
        view.setPrefSize(90, 90);
        edit.setOnAction(
                e -> {
                    new Edit(adm).show(st);
                });
        edit.setPrefSize(90, 90);
        pane.setStyle(
                "-fx-background-image: url('images/admin.jpg');"
                + "-fx-background-repeat: stretch;"
        );
        Scene scene = new Scene(pane, 600, 400);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("style.css").toExternalForm());

        st.setScene(scene);
        //st.setResizable(false);
        st.show();

    }

}
