package Controller;

import Model.ClientFX;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SafheBaziController implements Initializable {

    ClientFX clientFX;

    @FXML
    private Label esmLBL;
    @FXML
    private Label familLBL;
    @FXML
    private Label shahrLBL;
    @FXML
    private Label keshvarLBL;
    @FXML
    private Label ghazaLBL;
    @FXML
    private Label pushakLBL;
    @FXML
    private Label miveLBL;
    @FXML
    private Label mashinLBL;
    @FXML
    private Label golLBL;
    @FXML
    private Label heyvanLBL;
    @FXML
    private Label ashyaLBL;
    @FXML
    private BorderPane safheBazi;
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

        if(mozuat.contains("esm")){
            name.setVisible(true);
            esmLBL.setVisible(true);
        }
        if(mozuat.contains("famil")){
            famil.setVisible(true);
            familLBL.setVisible(true);
        }
        if(mozuat.contains("shahr")){
            shahr.setVisible(true);
            shahrLBL.setVisible(true);
        }
        if(mozuat.contains("keshvar")){
            keshvar.setVisible(true);
            keshvarLBL.setVisible(true);
        }
        if(mozuat.contains("ghaza")){
            ghaza.setVisible(true);
            ghazaLBL.setVisible(true);
        }
        if(mozuat.contains("pushak")){
            pushak.setVisible(true);
            pushakLBL.setVisible(true);
        }
        if(mozuat.contains("mive")){
            mive.setVisible(true);
            miveLBL.setVisible(true);
        }
        if(mozuat.contains("mashin")){
            mashin.setVisible(true);
            mashinLBL.setVisible(true);
        }
        if(mozuat.contains("gol")){
            gol.setVisible(true);
            golLBL.setVisible(true);
        }
        if(mozuat.contains("heyvan")){
            heyvan.setVisible(true);
            heyvanLBL.setVisible(true);
        }
        if(mozuat.contains("ashya")){
            ashya.setVisible(true);
            ashyaLBL.setVisible(true);
        }

    }

    
    //***** gereftan harf bazi baraye set kardan dar lable
    public void getHarf(char harff){
        harf.setText("حرف این دور: " + harff);
    }



    //***** gereftan type bazi baraye inke moshakhas shavad bazi dokme payan dashte bashad ya timer.
    public void getType(String type){
//        Scanner sc = new Scanner(type);
//        sc.useDelimiter("-");
//
//        int typee = Integer.parseInt()
    }
}
