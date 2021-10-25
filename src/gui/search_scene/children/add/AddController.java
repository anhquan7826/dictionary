package gui.search_scene.children.add;

import java.util.Optional;

import dictionary.Operate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

public class AddController {
    ListView<String> mainListView;

    public void setListView(ListView<String> lView) {
        mainListView = lView;
    }

    private String mode;

    public void setMode(String mode) {
        this.mode = mode;
    }

    @FXML
    private TextField word_target = new TextField();

    @FXML
    private HTMLEditor word_explain = new HTMLEditor();

    @FXML
    public void saveButtonPressed(ActionEvent e) {
        if (word_target.getText().isBlank()) {
            Alert emptyStringAlert = new Alert(Alert.AlertType.ERROR);
            emptyStringAlert.setTitle("Lỗi!");
            emptyStringAlert.setHeaderText("Bạn chưa nhập từ để thêm!");
            emptyStringAlert.showAndWait();
        } else if (Operate.Dictionary.getData(mode).containsKey(word_target.getText())) {
            Alert existStringAlert = new Alert(Alert.AlertType.CONFIRMATION);
            existStringAlert.setTitle("Lỗi!");
            existStringAlert.setHeaderText("Từ đã tồn tại. Bạn có muốn ghi đè?");
            ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Không", ButtonData.CANCEL_CLOSE);
            existStringAlert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
            Optional<ButtonType> result = existStringAlert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Operate.Dictionary.addWord(mode, word_target.getText(), word_explain.getHtmlText().replace(" contenteditable=\"true\"", ""));
                Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                currentStage.close();
            }
        } else {
            Operate.Dictionary.addWord(mode, word_target.getText(), word_explain.getHtmlText().replace(" contenteditable=\"true\"", ""));
            mainListView.getItems().setAll(Operate.Dictionary.getData(mode).keySet());
            Stage currentStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            currentStage.close();
        }
    }

    public boolean saveConfirmationBox() {
        if (word_target.getText().isBlank()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Bạn có muốn lưu không?");
            ButtonType buttonTypeYes = new ButtonType("Đồng ý", ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Không", ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeYes) {
                Operate.Dictionary.addWord(mode, word_target.getText(), word_explain.getHtmlText().replace(" contenteditable=\"true\"", ""));
                mainListView.getItems().setAll(Operate.Dictionary.getData(mode).keySet());
                return true;
            } else {
                return false;
            }
        }
    }
}
