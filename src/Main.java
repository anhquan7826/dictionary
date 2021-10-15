import java.util.Optional;

import dictionary.Operate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Operate.initOperation();
        Parent root = FXMLLoader.load(getClass().getResource("gui/search_scene/main_scene.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Bạn chắc chắn muốn thoát chứ?");
            ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Không", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                try {
                    Operate.updateData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                event.consume();
            }
        });
    }
}
