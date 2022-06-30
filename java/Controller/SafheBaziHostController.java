package Controller;

import Model.ClientFX;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SafheBaziHostController implements Initializable {
    ClientFX clientFX;

    @FXML
    private Label sanieLbl;
    @FXML
    private Label daghigheLbl;
    @FXML
    private Label saatLbl;
    @FXML
    private Label zamanWord;
    @FXML
    private Label harf;
    @FXML
    private Label esmLBL;
    @FXML
    private TextField name;
    @FXML
    private Label familLBL;
    @FXML
    private TextField famil;
    @FXML
    private Label shahrLBL;
    @FXML
    private TextField shahr;
    @FXML
    private Label keshvarLBL;
    @FXML
    private TextField keshvar;
    @FXML
    private Label ghazaLBL;
    @FXML
    private TextField ghaza;
    @FXML
    private Label pushakLBL;
    @FXML
    private TextField pushak;
    @FXML
    private Label miveLBL;
    @FXML
    private TextField mive;
    @FXML
    private Label mashinLBL;
    @FXML
    private TextField mashin;
    @FXML
    private Label golLBL;
    @FXML
    private TextField gol;
    @FXML
    private Label heyvanLBL;
    @FXML
    private TextField heyvan;
    @FXML
    private Label ashyaLBL;
    @FXML
    private TextField ashya;
    @FXML
    private Button tamamBtn;

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
        Scanner sc = new Scanner(type);
        sc.useDelimiter("-");

        String t = sc.next();
        System.out.println("type: " + t);

        //***** agar type, har kas zoodtar tamam kard bashad, timer bayad az safhe hazf shavad.
        if(t.equalsIgnoreCase("zoodtar")){
            zamanWord.setVisible(false);
            saatLbl.setVisible(false);
            daghigheLbl.setVisible(false);
            sanieLbl.setVisible(false);
        }
        //***** agar type, time bashad, dokme harkas zudtar bayad hazf shavad. hamchenin timer bayad shoru be shomaresh konad.
        else {
            tamamBtn.setVisible(false);

            //***** dar avardan saat va daghighe va sanie, chon dar inner class estefade shode bayad final tarif shavad.
            final int[] saat = {Integer.parseInt(sc.next())};
            final int[] daghighe = {Integer.parseInt(sc.next())};
            final int[] sanie = {Integer.parseInt(sc.next())};

            //***** set kardan timer.
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {

                    //***** az sanie kam mishavad.
                    if(sanie[0] > 0){
                        Platform.runLater(() -> {
                            saatLbl.setText(Integer.toString(saat[0]) + ":");
                            daghigheLbl.setText(Integer.toString(daghighe[0]) + ":");
                            sanieLbl.setText(Integer.toString(sanie[0]));
                        });
                        sanie[0]--;
                    }

                    //***** az daghighe yaki kam shode va 59 ta be sanie ezafe mishavad.
                    else if(daghighe[0] > 0){
                        Platform.runLater(() -> {
                            saatLbl.setText(Integer.toString(saat[0]) + ":");
                            daghigheLbl.setText(Integer.toString(daghighe[0]) + ":");
                            sanieLbl.setText(Integer.toString(sanie[0]));
                        });
                        sanie[0] = 59;
                        daghighe[0]--;
                    }

                    //***** az saat yeki kam shode va 59 ta be daghighe ezafe mishavad.
                    else if (saat[0] > 0){
                        Platform.runLater(() -> {
                            saatLbl.setText(Integer.toString(saat[0]) + ":");
                            daghigheLbl.setText(Integer.toString(daghighe[0]) + ":");
                            sanieLbl.setText(Integer.toString(sanie[0]));
                        });
                        daghighe[0] = 59;
                        saat[0]--;
                    }

                    //***** yani mohkat bazi tamam shode, dor jadid bayad shoru shavad.
                    else {
                        //bazi tamam...
                        //reshte javab sakhte shode va zakhire mishavad.
                        //sepas be safhe entezar rafte ta dor badi tavasot yeki shoru shavad.

                        timer.cancel();

                        //***** ersal reshte javab.
                        clientFX.client.addJavab(getJavab());

                        //***** load shodan safhe entezar
                        loadWaitingPage();

                        //***** be ellat tadakhol thread ha lazem ast ke in tread bekhabad.
                        try {
                            Thread.sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        //***** hala mizban bayad be server payam bedahad ke bazi tamam shode, yek nafar digar az player ha ra
                        //entekhab va dokme shoru bazi ra be an bede.
                        clientFX.client.DJadid();

                    }
                }

            }, 1000, 1000);

        }
    }

    //***** in method check mikonad dar reshte mozuat che chizhayi hast va javab ra misazad.
    public String getJavab(){
        String javab = "";
        String mozuat = clientFX.getMozuat();

        if(mozuat.contains("esm")){
            javab += name.getText() + "-";
        }
        if(mozuat.contains("famil")){
            javab += famil.getText() + "-";
        }
        if(mozuat.contains("shahr")){
            javab += shahr.getText() + "-";
        }
        if(mozuat.contains("keshvar")){
            javab += keshvar.getText() + "-";
        }
        if(mozuat.contains("ghaza")){
            javab += ghaza.getText() + "-";
        }
        if(mozuat.contains("pushak")){
            javab += pushak.getText() + "-";
        }
        if(mozuat.contains("mive")){
            javab += mive.getText() + "-";
        }
        if(mozuat.contains("mashin")){
            javab += mashin.getText() + "-";
        }
        if(mozuat.contains("gol")){
            javab += gol.getText() + "-";
        }
        if(mozuat.contains("heyvan")){
            javab += heyvan.getText() + "-";
        }
        if(mozuat.contains("ashya")){
            javab += ashya.getText() + "-";
        }

        return javab;
    }

    //***** in method safhe dar entezar bazi ra load mikonad.
    public void loadWaitingPage(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/DarEntezarShoruBaziPage.fxml"));
                Pane p = null;
                try {
                    p = l.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(p != null){
                    Scene sc = new Scene(p);
                    clientFX.setSceneJadid(sc);
                }
            }
        });
    }
}
