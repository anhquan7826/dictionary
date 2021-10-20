package gui.about_scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutControlller implements Initializable {
    // MAC use Runtime.getRuntime().exec(new String[]{"/usr/bin/open", "-a", "/Applications/Google Chrome.app", "write URL here"});
    @FXML
    public void OpenLinkQuan () throws IOException{
        // Window
        //Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome https://github.com/anhquan7826"});
        // Linux
        // Runtime.getRuntime().exec(new String[]{"bash", "-c", "/path/to/chrome https://github.com/anhquan7826"});
        //ALl
        String url_open ="https://github.com/anhquan7826";
        Desktop.getDesktop().browse(URI.create(url_open));
    }
    @FXML
    public void OpenLinkHa () throws IOException {
        // Window
        //Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome https://github.com/Lppt12345"});
        // Linux
        // Runtime.getRuntime().exec(new String[]{"bash", "-c", "/path/to/chrome https://github.com/Lppt12345"});
        // ALL
        String url_open ="https://github.com/Lppt12345";
        Desktop.getDesktop().browse(URI.create(url_open));
    }
    @FXML
    public void OpenProject() throws IOException {
        // Window
        //Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start chrome https://github.com/anhquan7826/dictionary"});
        // Linux
        // Runtime.getRuntime().exec(new String[]{"bash", "-c", "/path/to/chrome https://github.com/anhquan7826/dictionar"});
        // ALL
        String url_open ="https://github.com/anhquan7826/dictionary";
        Desktop.getDesktop().browse(URI.create(url_open));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
