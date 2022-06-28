package Controller;

import Model.ClientFX;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SafheBaziController implements Initializable {
    ClientFX clientFX;

    @FXML
    private Label harf;
    @FXML
    private Button tamamBtn;
    @FXML
    private TextField name;
    @FXML
    private TextField famil;
    @FXML
    private TextField shahr;
    @FXML
    private TextField keshvar;
    @FXML
    private TextField ghaza;
    @FXML
    private TextField pushak;
    @FXML
    private TextField mive;
    @FXML
    private TextField mashin;
    @FXML
    private TextField gol;
    @FXML
    private TextField heyvan;
    @FXML
    private TextField ashya;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //***** set kardan clientFX baraye estefade az method haey client va ferestadan payam be server
    public void setClientFX(ClientFX clientFX){
        this.clientFX = clientFX;
    }

    //***** gereftan reshte mozuat baraye in ke chek shavad che mozuati negah dashte shavand.
    public void getMozuat(String mozuat){

    }
}
