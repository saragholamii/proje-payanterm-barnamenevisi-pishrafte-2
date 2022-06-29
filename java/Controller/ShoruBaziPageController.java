package Controller;

import Model.ClientFX;
import javafx.event.ActionEvent;
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

    //***** bayad vaghti ruye dokme shru click kard, method startAzSamtMizban samt server seda zade shavad ta be bahgi
    //player ha payam shoru ferestade shavad.
    public void startGBtn(ActionEvent actionEvent) {
        //***** seda zadan method startGameAzSamtMizban va dadan harf dakhel textField.
        clientFX.client.startGameAzSamtMizban(harfAval.getText().charAt(0));
        clientFX.print("method start game az samt mizban seda zade shod");

        //***** hala bayad safhe bazi load shavad.
        //....


    }
}
