package gui.search_scene.children.edit;

import java.util.Optional;

import dictionary.manager.word.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class EditController {
    private Word wordBeingEdited;

    @FXML
    private Label wordIndicator = new Label();

    @FXML
    private HTMLEditor editArea = new HTMLEditor();

    public Word getWord() {
        return wordBeingEdited;
    }

    public void setWord(Word word) {
        this.wordBeingEdited = word;
        wordIndicator.setText(wordIndicator.getText() + wordBeingEdited.getWord_target());
        editArea.setHtmlText(wordBeingEdited.getWord_explain());
    }

    public boolean saveConfirmationBox() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Bạn có muốn lưu không?");
        ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Không", ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
        Optional<ButtonType> result = alert.showAndWait();
        return (result.get() == buttonTypeYes);
    }

    public void saveButtonPressed(ActionEvent e) {
        Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // TODO: handle saving method here
        wordBeingEdited.setWord_explain(editArea.getHtmlText());
        currentStage.close();
    }

    public static void main(String[] args) {
        
    }
}
