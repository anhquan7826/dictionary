package gui.googletranslate_scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dictionary.Operate;

public class Controller implements Initializable {

    @FXML
    TextField targetVE = new TextField();

    @FXML
    WebView explainVE = new WebView();

    @FXML
    TextField targetEV = new TextField();

    @FXML
    WebView explainEV = new WebView();

    public void translateVE(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)){
            String meaning = targetVE.getText();
            explainVE.getEngine().loadContent(Operate.Translate.translate("vi","en", meaning) , "text/html");
        }
    }

    public void translateEV(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)){
            String meaning = targetEV.getText();
            explainEV.getEngine().loadContent(Operate.Translate.translate("en","vi", meaning) , "text/html");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
