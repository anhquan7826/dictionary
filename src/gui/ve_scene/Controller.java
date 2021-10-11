package gui.ve_scene;

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

public class Controller implements Initializable {
    @FXML
    private TextField search = new TextField();

    @FXML
    private ListView<String> searchResult = new ListView<>();

    @FXML
    private WebView viewWord = new WebView();
    private WebEngine engine;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = viewWord.getEngine();
    }

    @FXML
    public void resultOnEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (search.getText().isBlank()) {
                searchResult.getItems().clear();
            } else {
                searchResult.getItems().setAll(Operate.VEDictionary.searchWord(search.getText().trim()));
            }
        }
    }

    @FXML
    public void resultOnType() {
        if (search.getText().isBlank()) {
            searchResult.getItems().clear();
        } else {
            searchResult.getItems().setAll(Operate.VEDictionary.searchWord(search.getText()));
        }
    }

    @FXML
    public void showSelectedWord() {
        String key = searchResult.getSelectionModel().getSelectedItem();
        Word word = Operate.VEDictionary.getWord(key);
        engine.loadContent(word.getWord_explain(), "text/html");
    }
}