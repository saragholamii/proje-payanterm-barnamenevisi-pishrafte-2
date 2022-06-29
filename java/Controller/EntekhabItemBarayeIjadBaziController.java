package Controller;

import Model.ClientFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntekhabItemBarayeIjadBaziController implements Initializable {
    ClientFX clientFX;

    @FXML
    private TextField tedadDor;
    @FXML
    private CheckBox time;
    @FXML
    private TextField saat;
    @FXML
    private TextField daghighe;
    @FXML
    private TextField sanie;
    @FXML
    private CheckBox soonerChBox;
    @FXML
    private Button sakhtBaziBtn;
    @FXML
    private CheckBox esm;
    @FXML
    private CheckBox famil;
    @FXML
    private CheckBox shahr;
    @FXML
    private CheckBox keshvar;
    @FXML
    private CheckBox ghaza;
    @FXML
    private CheckBox pushak;
    @FXML
    private CheckBox mive;
    @FXML
    private CheckBox mashin;
    @FXML
    private CheckBox gol;
    @FXML
    private CheckBox heyvan;
    @FXML
    private CheckBox ashya;
    @FXML

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //***** set kardan clientFX marbute dar yek field clientFX, baraye dastresi dashtan be method haye an, baraye ertebat ba server.
    public void setClientFX(ClientFX clientFX){
        this.clientFX = clientFX;
    }

    public void sakhtanSafheBazi(ActionEvent actionEvent) throws IOException {
        //***** sakhtan string mozuat bazi ke ba - az ham joda mishavand.
        String mozuat = "";
        if(esm.isSelected()){ mozuat += "esm-";}
        if(famil.isSelected()){ mozuat += "famil-";}
        if(shahr.isSelected()){ mozuat += "shahr-";}
        if(keshvar.isSelected()){ mozuat += "keshvar-";}
        if(ghaza.isSelected()){ mozuat += "ghaza-";}
        if(pushak.isSelected()){ mozuat += "pushak-";}
        if(mive.isSelected()){ mozuat += "mive-";}
        if(mashin.isSelected()){ mozuat += "mashin-";}
        if(gol.isSelected()){ mozuat += "gol-";}
        if(heyvan.isSelected()){ mozuat += "heyvan-";}
        if(ashya.isSelected()){ mozuat += "ashya-";}

        //***** moshakhas kardan type bazi.
        String type = "";
        if(time.isSelected()){
            type += "zaman" + "-" + saat.getText() + "-" + daghighe.getText() + "-" + sanie.getText();
        }
        else if(soonerChBox.isSelected()){
            type += "sooner";
        }

        //***** mashakhas kardan tedad dor bazi
        String tedadDor = this.tedadDor.getText();

        //***** seda zadan method sakht bazi va dadan string moshakhasat be an, ta baraye client manager beferestad.
        // sepas client manager yek shey bazi ruye server sakhte va dar list bazi ha add mikonad.
        clientFX.client.sakhtBazi(mozuat, type, tedadDor);

        //***** hala bayad dockme shoru bazi ruye safhe namayesh dade shavad.
        FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/ShorueBaziPage.fxml"));
        Pane p = l.load();

        //***** gereftan controller shoruBaziPage baraye dashtan dastresi be method hayash.
        ShoruBaziPageController c = (ShoruBaziPageController) l.getController();

        //***** ferestadan clientFX.
        c.setClientFX(clientFX);

        //***** sakht scene jadid va gozashtan an ruye stage asli.
        Scene sc = new Scene(p);
        clientFX.setSceneJadid(sc);
    }
}
