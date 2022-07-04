package Controller;

import Model.ClientFX;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class SafheBaziHostController implements Initializable {
    ClientFX clientFX;
    char harff;

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
        this.harff = harff;
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

                        //***** chon javab hamija add mishavad bayad boolean didIFinishLastTime 1 shavad ta dobare add nashavad.
                        clientFX.setDidIFinishedLastTime(true);

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
            if(name.getText().isEmpty()) { javab += "hich" + "-"; }
            else {javab += name.getText() + "-"; }
        }
        if(mozuat.contains("famil")){
            if(famil.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += famil.getText() + "-"; }
        }
        if(mozuat.contains("shahr")){
            if(shahr.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += shahr.getText() + "-"; }
        }
        if(mozuat.contains("keshvar")){
            if(keshvar.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += keshvar.getText() + "-"; }
        }
        if(mozuat.contains("ghaza")){
            if(ghaza.getText().isEmpty()) { javab += "hich" + "-"; }
            else {javab += ghaza.getText() + "-";}
        }
        if(mozuat.contains("pushak")){
            if(pushak.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += pushak.getText() + "-"; }
        }
        if(mozuat.contains("mive")){
            if(mive.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += mive.getText() + "-";}
        }
        if(mozuat.contains("mashin")){
            if(mashin.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += mashin.getText() + "-";}
        }
        if(mozuat.contains("gol")){
            if(gol.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += gol.getText() + "-"; }
        }
        if(mozuat.contains("heyvan")){
            if(heyvan.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += heyvan.getText() + "-"; }
        }
        if(mozuat.contains("ashya")){
            if(ashya.getText().isEmpty()) { javab += "hich" + "-"; }
            else { javab += ashya.getText() + "-"; }
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

    public void finish(ActionEvent actionEvent) {
        //***** dor akhar ra zakhire mikonad.
        clientFX.client.addJavab(getJavab());

        //***** boolean didIfinisheLastTime ra true mikonim ta dafe badi dobare reshte ra zakhire nakonad.
        clientFX.setDidIFinishedLastTime(true);

        //***** in ja methodi az client ra seda mizanad ke be server miguyad yek nafar bazi ra tamam karde ast.
        clientFX.client.dorTamamShod();
    }

    public void esmKeyType(KeyEvent keyEvent) {
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    name.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    name.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    name.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void familKeyType(KeyEvent keyEvent) {
        famil.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    famil.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    famil.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    famil.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void shahrKeyType(KeyEvent keyEvent) {
        shahr.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    shahr.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    shahr.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    shahr.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void keshvarKeyType(KeyEvent keyEvent) {
        keshvar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    keshvar.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    keshvar.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    keshvar.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void ghazaKeyType(KeyEvent keyEvent) {
        ghaza.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    ghaza.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    ghaza.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    ghaza.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void pushakKeyType(KeyEvent keyEvent) {
        pushak.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    pushak.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    pushak.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    pushak.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void miveKeyType(KeyEvent keyEvent) {
        mive.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    mive.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    mive.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    mive.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void mashinKeyType(KeyEvent keyEvent) {
        mashin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    mashin.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    mashin.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    mashin.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void golKeyType(KeyEvent keyEvent) {
        gol.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    gol.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    gol.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    gol.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void heyvanKeyType(KeyEvent keyEvent) {
        heyvan.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    heyvan.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    heyvan.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    heyvan.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }

    public void ashyaKeyType(KeyEvent keyEvent) {
        ashya.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!newValue.isEmpty() && newValue.charAt(0) != harff){
                    ashya.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    ashya.setText(String.valueOf(newValue.charAt(0)));
                }
                else{
                    ashya.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
            }
        });
    }
}
