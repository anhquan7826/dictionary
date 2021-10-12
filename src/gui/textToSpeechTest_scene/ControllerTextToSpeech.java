package gui.textToSpeechTest_scene;

import dictionary.Operate;
import dictionary.manager.word.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTextToSpeech implements Initializable {
    @FXML
    private  ListView English = new ListView();
    @FXML
    private  WebView VN = new WebView();
    private WebEngine engine;
    @FXML
    private  TextField search = new TextField();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void resultOnEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (search.getText().isBlank()) {
                English.getItems().clear();
            } else {
                English.getItems().setAll(Operate.EVDictionary.searchWord(search.getText().trim()));
            }
        }
    }

    public void resultOnType() {
        if (search.getText().isBlank()) {
            English.getItems().setAll(Operate.EVDictionary.getDataDictionary.keySet());
        } else {
            English.getItems().setAll(Operate.EVDictionary.searchWord(search.getText()));
        }
    }

    public void showSelectedWord() {
        String key = English.getSelectionModel().getSelectedItem();
        Word word = Operate.EVDictionary.getWord(key);
        engine.loadContent(word.getWord_explain(), "text/html");
    }



}
