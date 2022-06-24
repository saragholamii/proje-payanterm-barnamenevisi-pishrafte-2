package Controller;

import Model.ClientFX;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoruBaziPageController implements Initializable {
    ClientFX clientFX;

    @FXML
    private Button shoruBaziBtn;
    @FXML
    private TextField harfAval;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //***** set kardan clientFX in safhe, baraye dashtan dastresi be method haye client.
    public void setClientFX(ClientFX clientFX){
        this.clientFX = clientFX;
    }
}
