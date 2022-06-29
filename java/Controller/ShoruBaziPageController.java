package Controller;

import Model.ClientFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
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
    public void startGBtn(ActionEvent actionEvent) throws IOException {

        //***** seda zadan method startGameAzSamtMizban va dadan harf dakhel textField.
        clientFX.client.startGameAzSamtMizban(harfAval.getText().charAt(0));
        clientFX.print("method start game az samt mizban seda zade shod");

        //***** hala bayad safhe bazi load shavad.
        //....

        FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/SafheBaziHost.fxml"));
        Pane p = l.load();

        SafheBaziHostController c = (SafheBaziHostController) l.getController();

        //***** set kardan client fx baraye in safhe jahat dashhtan dastrest be method haye client.
        c.setClientFX(clientFX);

        //***** ferestadn list mozuat ta field ha bar asas an entekhab shavad.
        c.getMozuat(clientFX.getMozuatHost());

        //***** ferestadan harf bazi baraye nashan dadan balaye safhe bazi.
        c.getHarf(harfAval.getText().charAt(0));

        //sakht scene
        Scene sc = new Scene(p);

        //***** ferestadan scene baraye add shodan dar stage asli.
        clientFX.setSceneJadid(sc);
    }
}
