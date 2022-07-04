package Controller;

import Model.ClientFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChapEmtiazPageController implements Initializable {
    @FXML
    private Button tamam;
    @FXML
    private Label emtiazLbl;

    ClientFX clientFX = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //***** set kardan clientFX baraye dashtan dastresi be method haye an
    public void setClientFX(ClientFX clientFX){
        this.clientFX = clientFX;
    }

    public void chap(int emtiaz){
        //***** set kardan emtiaz ruye lable
        emtiazLbl.setText("امتیاز شما در این بازی: " + emtiaz);
        emtiazLbl.setVisible(true);
    }

    public void bazGashtBeSafheAsli(ActionEvent actionEvent) throws IOException {
        //***** pas az in ja, bayad safhe avalie baraye karbar neshan dade shavad.
        clientFX.bazGashtBeSafheAval();
    }
}
