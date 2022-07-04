package Controller;

import Model.ClientFX;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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

        //***** dar in method check mishavad aya had aghal 5 ta item ra entekhab karde ast ya na
        if(!checkForMin5() || (time.isSelected() && soonerChBox.isSelected()) || !(time.isSelected() || soonerChBox.isSelected()) || tedadDor.getText().isEmpty() || Integer.parseInt(tedadDor.getText()) < 1){

            if(tedadDor.getText().isEmpty() || Integer.parseInt(tedadDor.getText()) < 1){
                //***** agar field khali bashad, dorash ra ghermaz mikonad.
                tedadDor.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                tedadDor.setPromptText("تعداد دورها باید بیشتر از ۰ باشد!");
            }

            if(!tedadDor.getText().isEmpty() && Integer.parseInt(tedadDor.getText()) > 1){
                //***** border dor field tedad dor ra sefid mikonim ta agar dorost shode bud, ghermez bardashte shavad.
                tedadDor.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
            }

            if(!checkForMin5()){
                //***** yek message neshan midahim ta be karbar ekhtar dahim bayad bish tar az 5 item ra entekhab konad.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("!!نکته");
                alert.setContentText("باید بیشتر از ۵ آیتم رو برای ایجاد بازی انتخاب کنی!");
                alert.showAndWait();
                actionEvent.consume();
            }

            if(time.isSelected() && soonerChBox.isSelected()){
                //***** yek message neshan midahim ta be karbar ekhtar dahim faghat bayad yeki az 2 type ra entekhab konad.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("!!نکته");
                alert.setContentText("فقط یکی از ۲ تایپ رو باید انتخاب کنی!");
                alert.showAndWait();
                actionEvent.consume();
            }

            if(!(time.isSelected() || soonerChBox.isSelected())){
                //***** be karbar miguyad ke yeki az 2 type ra bayad entekhab konad.
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("!!نکته");
                alert.setContentText("حتما یکی از دو تایپ رو باید انتخاب کنی!");
                alert.showAndWait();
                actionEvent.consume();
            }
        }
        else {

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
                type += "time" + "-" + saat.getText() + "-" + daghighe.getText() + "-" + sanie.getText();
            }
            else if(soonerChBox.isSelected()){
                type += "zoodtar";
            }

            //***** mashakhas kardan tedad dor bazi
            String tedadDor = this.tedadDor.getText();

            //***** seda zadan method sakht bazi va dadan string moshakhasat be an, ta baraye client manager beferestad.
            // sepas client manager yek shey bazi ruye server sakhte va dar list bazi ha add mikonad.
            clientFX.client.sakhtBazi(mozuat, type, tedadDor);

            //***** mozuat va type ra dar reshte dar client fx zakhire mikonad ta safhe bazi ra az ruye an besazad.
            clientFX.setMozuat(mozuat);
            clientFX.setType(type);

            //***** AmIHost ra true mikonad.
            clientFX.setAmIHost(true);

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

    //***** in method check mikonad aya had aghal 5 ta item ra entekhab karde ast ya na
    public boolean checkForMin5(){
       int selectedCount = 0;

        if(esm.isSelected()){ selectedCount++;}
        if(famil.isSelected()){ selectedCount++;}
        if(shahr.isSelected()){ selectedCount++;}
        if(keshvar.isSelected()){ selectedCount++;}
        if(ghaza.isSelected()){ selectedCount++;}
        if(pushak.isSelected()){ selectedCount++;}
        if(mive.isSelected()){ selectedCount++;}
        if(mashin.isSelected()){ selectedCount++;}
        if(gol.isSelected()){ selectedCount++;}
        if(heyvan.isSelected()){ selectedCount++;}
        if(ashya.isSelected()){ selectedCount++;}

        if(selectedCount < 5) return false;
        return true;

    }
}
