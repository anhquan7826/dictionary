package gui.googletranslate_scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField targetVE = new TextField();

    @FXML
    WebView explainVE = new WebView();

    @FXML
    TextField targetEV = new TextField();

    @FXML
    WebView explainEV = new WebView();

    private static String translate (String langFrom, String langTo, String text) throws IOException {

        // URL API google translate.
        String urlStr = "https://script.google.com/macros/s/AKfycbz42DCnvNQ8hOxRkKcwf4Vq7_eoB5W7xRnIb3e0b3z7bJ0ukZxMq2Oa6_6dujen9motaw/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void translateVE(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)){
            String meaning = targetVE.getText();
            WebEngine engine = explainVE.getEngine();
            explainVE.getEngine().loadContent(translate("vi","en", meaning) , "text/html");
        }
    }

    public void translateEV(KeyEvent event) throws IOException {
        if (event.getCode().equals(KeyCode.ENTER)){
            String meaning = targetEV.getText();
            WebEngine engine = explainEV.getEngine();
            explainEV.getEngine().loadContent(translate("en","vi", meaning) , "text/html");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
