
import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.LogIn;

public class mainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage st) throws Exception {
        (new LogIn()).show(st);
        st.show();
        //st.getIcons().add(new Image("images/agency.png"));
        st.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setContentText("Are you sure you want to leave?");
                ButtonType bt1 = new ButtonType("OK", ButtonData.APPLY);
                ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().clear();
                alert.getButtonTypes().addAll(bt1, cancel);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == bt1) {
                } else {
                    we.consume();
                }
            }

        });
    }
}
