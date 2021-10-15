package gui.search_scene;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dictionary.Operate;
import dictionary.file.Type;
import dictionary.manager.word.Word;
import gui.search_scene.children.edit.EditController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
    // main stuff
    @FXML
    private WebView viewWord = new WebView();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchResult.getItems().setAll(Operate.Dictionary.getData(Type.EV).keySet());
        favList.getItems().setAll(Operate.Favorite.getData(Type.EV).keySet());
    }

    private Word wordBeingDisplayed;

    public void showSelectedWord(String selectedItem) {
        if (selectedItem == null) {return;}
        wordBeingDisplayed = Operate.Dictionary.getWord(dictChoice.getText(), selectedItem);
        if (Operate.Favorite.getData(Type.convert(dictChoice.getText())).containsKey(selectedItem)) {
            favCheckBox.setSelected(true);
        } else {
            favCheckBox.setSelected(false);
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
        dictChoice.setText(Type.EV);
        search.clear();
        speakButton.setDisable(false);
        searchResult.getItems().setAll(Operate.Dictionary.getData(Type.EV).keySet());
        favList.getItems().setAll(Operate.Favorite.getData(Type.EVFav).keySet());
    }
    @FXML
    public void chooseVE() {
        dictChoice.setText(Type.VE);
        search.clear();
        speakButton.setDisable(true);
        searchResult.getItems().setAll(Operate.Dictionary.getData(Type.VE).keySet());
        favList.getItems().setAll(Operate.Favorite.getData(Type.VEFav).keySet());
    }

    // search field
    @FXML
    private TextField search = new TextField();

    @FXML
    private ListView<String> searchResult = new ListView<>();

    @FXML
    public void showResultList() {
        if (search.getText().isBlank()) {
            searchResult.getItems().setAll(Operate.Dictionary.getData(dictChoice.getText()).keySet());
        } else {
            searchResult.getItems().setAll(Operate.Dictionary.searchWord(dictChoice.getText(), search.getText()));
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
        favList.getItems().setAll(Operate.Favorite.getData(Type.convert(dictChoice.getText())).keySet());
    }

    @FXML
    public void favCheckBoxOnAction() {
        if (favCheckBox.isSelected()) {
            Operate.Favorite.addWord(Type.convert(dictChoice.getText()),
                                     wordBeingDisplayed.getWord_target(), 
                                     wordBeingDisplayed.getWord_explain());
            showFavoriteList();
        } else {
            Operate.Favorite.deleteWord(Type.convert(dictChoice.getText()), wordBeingDisplayed.getWord_target());
            showFavoriteList();
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
        wordBeingDisplayed = null;
        viewWord.getEngine().loadContent(null);
    }

    @FXML
    public void showHistoryItemOnSelect() {
        wordBeingDisplayed = historyStack.stack.get(historyList.getSelectionModel().getSelectedIndex());
        if (wordBeingDisplayed == null) {return;}
        viewWord.getEngine().loadContent(wordBeingDisplayed.getWord_explain(), "text/html");
    }

    // speak field
    @FXML
    private Button speakButton = new Button();

    @FXML
    public void speakOnPress() {
        if (wordBeingDisplayed != null) {
            if (dictChoice.getText().equals(Type.EV)) {
                Operate.TextToSpeech.Speak(wordBeingDisplayed.getWord_target());
            }
        }
    }

    // menubar
        // file
    @FXML
    private MenuItem toTranslate = new MenuItem();
    @FXML
    private MenuItem openEditBox = new MenuItem();
    @FXML
    private MenuItem deleteWord = new MenuItem();

            // open translate
    @FXML
    public void openTranslateScene(ActionEvent e) throws IOException {
        Stage primaryStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Parent next = FXMLLoader.load(getClass().getResource("../googletranslate_scene/scene.fxml"));
        Scene scene = new Scene(next);
        primaryStage.setScene(scene);
    }

            // open editor
    @FXML
    public void openEditBoxWindow(ActionEvent e) throws IOException {
        Stage editStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("children/edit/edit_scene.fxml"));
        Scene scene = new Scene(loader.load());
        EditController editController = loader.getController();
        editController.setWord(wordBeingDisplayed);
        editStage.setTitle("Chỉnh sửa");
        editStage.setScene(scene);
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.show();
        editStage.setOnCloseRequest(event -> {
            if (editController.saveConfirmationBox()) {
                Operate.Dictionary.editWord(dictChoice.getText(), 
                                            wordBeingDisplayed.getWord_target(), 
                                            wordBeingDisplayed.getWord_explain());
                editStage.close();
            }
            viewWord.getEngine().loadContent(wordBeingDisplayed.getWord_explain(), "text/html");
        });
    }
}