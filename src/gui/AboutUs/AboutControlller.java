package gui.AboutUs;

import javafx.fxml.FXML;

import java.io.IOException;

public class AboutControlller  {
    @FXML
    public void OpenLinkQuan () throws IOException {
        String url ="https://github.com/anhquan7826";
        Runtime.getRuntime().exec("open " +url);

    }
    @FXML
    public void OpenLinkHa () throws IOException {
        String url ="https://github.com/Lppt12345";
        Runtime.getRuntime().exec("open " +url);
    }
    @FXML
    public void OpenProject() throws IOException {
        String url ="https://github.com/anhquan7826/dictionary";
        Runtime.getRuntime().exec("open " +url);
    }
}
