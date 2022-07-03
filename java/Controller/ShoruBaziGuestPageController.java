package Controller;

import Model.ClientFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class ShoruBaziGuestPageController implements Initializable {
    ClientFX clientFX;

    @FXML
    private TextField harfAval;
    @FXML
    private Button shoruBaziBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //***** in method clientFX ra set mikonad ta be method haye client dastresi dashte bashim.
    public void setClientFX(ClientFX clientFX){
        this.clientFX = clientFX;
    }

    //***** vaghti dokme shoru zade mishavad, be server miguyad ke baraye hameye mehman ha va mizban, safhe bazi ba in harf dobare load shavad.
    public void startGBtn(ActionEvent actionEvent) {

        //*****check mikonad agar text box khali bud, dorash ra ghermez mikonad va event ra consume mikonad.
        if(harfAval.getText().isEmpty()){
            harfAval.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
            harfAval.setPromptText("حرف رو وارد کن");
            actionEvent.consume();
        }
        else{
            harfAval.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

            //hala bayad methodi az client ra seda bezanad ke be server farman dahad baraye hemeye bazikon ha safhe bazi ghabli load shavad.
            clientFX.client.startGameAzSamtMehman(harfAval.getText().charAt(0));
        }
    }
}
