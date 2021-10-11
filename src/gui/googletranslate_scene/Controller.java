package gui.googletranslate_scene;

import dictionary.Operate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField target = new TextField();

    @FXML
    WebView explain = new WebView();

    public void translateEV(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)){
            String meaning = target.getText();
            WebEngine engine = explain.getEngine();
            explain.getEngine().loadContent(Operate.Translate.translate("vi","en", meaning) , "text/html");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
