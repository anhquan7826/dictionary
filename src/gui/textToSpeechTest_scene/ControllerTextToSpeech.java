package gui.textToSpeechTest_scene;

import dictionary.Operate;
import dictionary.manager.word.Word;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTextToSpeech implements Initializable {
    @FXML
    private  ListView <String> English = new ListView<>();
    @FXML
    private  WebView VN = new WebView();
//    private WebEngine engine = VN.getEngine();
    @FXML
    private  TextField search = new TextField();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
//     Speak vs tu trong text field
    public void testspeak(KeyEvent event){
        if (event.getCode().equals(KeyCode.ENTER)){
            String s = search.getText();
            Operate.TextToSpeech.Speak(s);
        }
    }
    // Speak vs tu duoc chon trong listview
    public void speaktest(){
        String s =  English.getSelectionModel().getSelectedItem();
        if (s!= null){
            Operate.TextToSpeech.Speak(s);
        }
    }

//    public void resultOnEnter(KeyEvent event) {
//        if (event.getCode().equals(KeyCode.ENTER)) {
//            if (search.getText().isBlank()) {
//                English.getItems().clear();
//            } else {
//                English.getItems().setAll(Operate.EVDictionary.searchWord(search.getText().trim()));
//            }
//        }
//    }
    @FXML
    public void resultOnType() {
        if (search.getText().isBlank()) {
            English.getItems().clear();
        } else {
            English.getItems().setAll(Operate.EVDictionary.searchWord(search.getText()));
        }
    }
    @FXML
    public void showSelectedWord() {
        String key = English.getSelectionModel().getSelectedItem();
        Word word = Operate.EVDictionary.getWord(key);
        VN.getEngine().loadContent(word.getWord_explain(), "text/html");
    }

}
