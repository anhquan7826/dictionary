import dictionary.Operate;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Operate.initOperation();
        Parent root = FXMLLoader.load(getClass().getResource("gui/main_scene/scene.fxml"));
        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent arg0) {
                try {
                    Operate.EVDictionary.updateData();
                    Operate.VEDictionary.updateData();
                    Operate.Favorite.updateData();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
