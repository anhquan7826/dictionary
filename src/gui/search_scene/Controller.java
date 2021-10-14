package gui.search_scene;

import dictionary.Operate;
import dictionary.Operate.Favorite;
import dictionary.manager.word.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    // main stuff
    @FXML
    private WebView viewWord = new WebView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchResult.getItems().setAll(Operate.EVDictionary.getData().keySet());
    }

    private Word wordBeingDisplayed;

    private void showSelectedWord(String selectedItem) {
        if (selectedItem == null) {return;}
        if (dictChoice.getText().equals("Eng - Vi")) {
            wordBeingDisplayed = Operate.EVDictionary.getWord(selectedItem);
            if (Favorite.getData().containsKey(selectedItem)) {
                favCheckBox.setSelected(true);
            } else {
                favCheckBox.setSelected(false);
            }
        } else {
            wordBeingDisplayed = Operate.VEDictionary.getWord(selectedItem);
        }
        viewWord.getEngine().loadContent(wordBeingDisplayed.getWord_explain(), "text/html");
        historyStack.push(wordBeingDisplayed);
    }

    // mode choosing
    @FXML
    private MenuButton dictChoice = new MenuButton();
        @FXML
        private MenuItem evChoice = new MenuItem();
        @FXML
        private MenuItem veChoice = new MenuItem();

    @FXML
    public void chooseEV() {
        dictChoice.setText("Eng - Vi");
        favCheckBox.setDisable(false);
        search.clear();
        searchResult.getItems().setAll(Operate.EVDictionary.getData().keySet());
    }
    @FXML
    public void chooseVE() {
        dictChoice.setText("Vi - Eng");
        favCheckBox.setDisable(true);
        search.clear();
        searchResult.getItems().setAll(Operate.VEDictionary.getData().keySet());
    }

    // search field
    @FXML
    private TextField search = new TextField();

    @FXML
    private ListView<String> searchResult = new ListView<>();

    @FXML
    public void showResultList() {
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
    public void showResultItemOnSelect() {
        showSelectedWord(searchResult.getSelectionModel().getSelectedItem());
    }

    // favorite field
    @FXML
    private CheckBox favCheckBox = new CheckBox();

    @FXML
    private ListView<String> favList = new ListView<>();

    @FXML
    public void showFavoriteList() {
        favList.getItems().setAll(Operate.Favorite.getData().keySet());
    }

    @FXML
    public void favCheckBoxOnAction() {
        if (!favCheckBox.isDisabled()) {
            if (favCheckBox.isSelected()) {
                Favorite.addWord(wordBeingDisplayed);
                showFavoriteList();
            } else {
                Favorite.removeWord(wordBeingDisplayed.getWord_target());
                showFavoriteList();
            }
        }
    }

    @FXML
    public void showFavoriteItemOnSelect() {
        showSelectedWord(favList.getSelectionModel().getSelectedItem());
    }

    // history field
    @FXML
    private ListView<String> historyList = new ListView<>();

    @FXML
    private Button historyClearButton = new Button();

    private class Stack {
        private List<Word> stack;

        private Stack() {
            stack = new ArrayList<Word>();
        }
    
        private void push(Word value) {
            stack.add(0, value);
        }

        private void clear() {
            stack.clear();
        }

        private List<String> toStringList() {
            List<String> r = new ArrayList<>();
            for (Word i : stack) {
                r.add(i.getWord_target());
            }
            return r;
        }
    }

    private Stack historyStack = new Stack();

    @FXML
    public void showHistoryList() {
        if (historyStack == null) {return;}
        historyList.getItems().setAll(historyStack.toStringList());
    }

    @FXML
    public void clearHistory() {
        historyStack.clear();
        historyList.getItems().clear();
    }
}