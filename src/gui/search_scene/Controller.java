package gui.search_scene;

import dictionary.Operate;
import dictionary.manager.word.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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

    @FXML
    private MenuButton dictChoice = new MenuButton();
        @FXML
        private MenuItem evChoice = new MenuItem();
        @FXML
        private MenuItem veChoice = new MenuItem();

/*     @FXML
    private ChoiceBox<String> dictChoiceBox = new ChoiceBox<String>();
    ObservableList<String> dictChoice = FXCollections.observableArrayList("Eng - Vi", "Vi - Eng"); */

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchResult.getItems().setAll(Operate.EVDictionary.getData().keySet());

/*         dictChoiceBox.setItems(dictChoice);
        dictChoiceBox.setValue(dictChoice.get(0)); */
    }


/*     public void listAllWordSwitcher() {
        if (dictChoice.getText().equals("Eng - Vi")) {
            search.clear();
            searchResult.getItems().setAll(Operate.EVDictionary.getData().keySet());
        } else {
            search.clear();
            searchResult.getItems().setAll(Operate.VEDictionary.getData().keySet());
        }
    } */
    @FXML
    public void chooseEV() {
        dictChoice.setText("Eng - Vi");
        search.clear();
        searchResult.getItems().setAll(Operate.EVDictionary.getData().keySet());
    }
    @FXML
    public void chooseVE() {
        dictChoice.setText("Vi - Eng");
        search.clear();
        searchResult.getItems().setAll(Operate.VEDictionary.getData().keySet());
    }

    @FXML
    public void resultOnType() {
        if (dictChoice.getText().equals("Eng - Vi")) {
            if (search.getText().isBlank()) {
                searchResult.getItems().setAll(Operate.EVDictionary.getData().keySet());
            } else {
                searchResult.getItems().setAll(Operate.EVDictionary.searchWord(search.getText()));
            }
        } else {
            if (search.getText().isBlank()) {
                searchResult.getItems().setAll(Operate.VEDictionary.getData().keySet());
            } else {
                searchResult.getItems().setAll(Operate.VEDictionary.searchWord(search.getText()));
            }
        }
    }

    @FXML
    public void showSelectedWord() {
        String key = searchResult.getSelectionModel().getSelectedItem();
        Word word;
        if (dictChoice.getText().equals("Eng - Vi")) {
            word = Operate.EVDictionary.getWord(key);
        } else {
            word = Operate.VEDictionary.getWord(key);
        }
        viewWord.getEngine().loadContent(word.getWord_explain(), "text/html");
    }
}