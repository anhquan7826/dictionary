package gui.main_scene;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    @FXML
    private Button search;

    @FXML
    private Button translate;

    @FXML
    private Button close;

    @FXML
    public void toSearch(ActionEvent e) throws IOException {
        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent next = FXMLLoader.load(getClass().getResource("../search_scene/scene.fxml"));
        Scene scene = new Scene(next);
        primaryStage.setScene(scene);
    }

    @FXML
    public void toTranslate(ActionEvent e) throws IOException {
        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent next = FXMLLoader.load(getClass().getResource("../googletranslate_scene/scene.fxml"));
        Scene scene = new Scene(next);
        primaryStage.setScene(scene);
    }

    @FXML
    public void quit(ActionEvent e) {
        
    }

    @FXML
    void initialize() {}

}