package gui.search_scene;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dictionary.Operate;
import dictionary.file.Type;
import dictionary.history.History;
import dictionary.manager.word.Word;
import gui.search_scene.children.add.AddController;
import gui.search_scene.children.edit.EditController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
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
        historyList.getItems().setAll(History.getAllHistory(Type.EV));
    }
    @FXML
    public void chooseVE() {
        dictChoice.setText(Type.VE);
        search.clear();
        speakButton.setDisable(true);
        searchResult.getItems().setAll(Operate.Dictionary.getData(Type.VE).keySet());
        favList.getItems().setAll(Operate.Favorite.getData(Type.VEFav).keySet());
        historyList.getItems().setAll(History.getAllHistory(Type.VE));
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
        History.addHistory(dictChoice.getText(), wordBeingDisplayed);
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
    public void showHistoryList() {
        historyList.getItems().setAll(History.getAllHistory(dictChoice.getText()));
    }

    @FXML
    public void clearHistory() {
        historyList.getItems().clear();
        History.clearHistory(dictChoice.getText());
        wordBeingDisplayed = null;
        viewWord.getEngine().loadContent(null);
    }

    @FXML
    public void showHistoryItemOnSelect() {
        showSelectedWord(historyList.getSelectionModel().getSelectedItem());
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
            // open translate
    @FXML
    public void openTranslateScene(ActionEvent e) throws IOException {
        Stage tranStage = new Stage();
//      FXMLLoader loader = new FXMLLoader();
//      loader.setLocation(getClass().getResource("../googletranslate_scene/GGTScene.fxml"));
//      Scene scene = new Scene(loader.load());
//      tranStage.setScene(scene);
        Parent root = FXMLLoader.load(getClass().getResource("../googletranslate_scene/GGTScene.fxml"));
        tranStage.setTitle("Translate by API");
        tranStage.setScene(new Scene (root));
        tranStage.initModality(Modality.APPLICATION_MODAL);
        tranStage.show();
    }

    // menu bar
        // Help
            // Ve tu dien nay
    @FXML
    public void openAboutUs(ActionEvent e) throws IOException {
        Stage tranStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../about_scene/AboutUs.fxml"));
        Scene scene = new Scene(loader.load());
        tranStage.setScene(scene);
        tranStage.setTitle("About Us");
        tranStage.initModality(Modality.APPLICATION_MODAL);
        tranStage.show();
    }

        //edit
            // open editor
    @FXML
    public void openEditBoxWindow(ActionEvent e) throws IOException {
        Stage editStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("children/edit/edit_scene.fxml"));
        Scene scene = new Scene(loader.load());
        EditController editController = loader.getController();
        editController.setMode(dictChoice.getText());
        editController.setWord(wordBeingDisplayed);
        editStage.setTitle("Chỉnh sửa");
        editStage.setScene(scene);
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.show();
        editStage.setOnCloseRequest(event -> {
            if (editController.saveConfirmationBox()) {
                editStage.close();
            }
        });
    }

            // open add
    @FXML
    public void openAddWindow(ActionEvent e) throws IOException {
        Stage editStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("children/add/add_scene.fxml"));
        Scene scene = new Scene(loader.load());
        AddController addController = loader.getController();
        addController.setMode(dictChoice.getText());
        editStage.setTitle("Thêm từ mới");
        editStage.setScene(scene);
        editStage.initModality(Modality.APPLICATION_MODAL);
        editStage.show();
        editStage.setOnCloseRequest(event -> {
            if (addController.saveConfirmationBox()) {
                editStage.close();
            }
        });
    }

            // delete word confirm
    @FXML
    public void deleteWordConfirmationBox() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Bạn có muốn xóa từ \"" + wordBeingDisplayed.getWord_target() + "\" không?");
        ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Không", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            History.removeHistory(dictChoice.getText(), wordBeingDisplayed);
            Operate.Dictionary.deleteWord(dictChoice.getText(), wordBeingDisplayed.getWord_target());
            wordBeingDisplayed = null;
            viewWord.getEngine().load("");
            search.clear();
            searchResult.getItems().setAll(Operate.Dictionary.getData(dictChoice.getText()).keySet());
            favList.getItems().setAll(Operate.Favorite.getData(Type.convert(dictChoice.getText())).keySet());
            historyList.getItems().setAll(History.getAllHistory(dictChoice.getText()));
        }
    }
}