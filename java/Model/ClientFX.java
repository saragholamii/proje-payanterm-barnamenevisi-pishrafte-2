package Model;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Controller.*;

import java.io.IOException;
import java.util.ArrayList;

public class ClientFX extends Application {
    public Client client;
    ClientFX thisClientFX;
    ArrayList<BaziSamtClient> listBaziHa = new ArrayList<>();
    Stage stageAsli;
    String mozuat;
    String type;

    @Override
    public void start(Stage primaryStage) throws Exception {
        thisClientFX = this;
        //***** sakht yek client.
        client = new Client( this);
        //***** dar in method karhaye avalie ertebat ba server anjam mishavad.
        client.start();
        //***** ghabl az baz kardan panjere, in method be server miguyad list bazi hara beferesad.
        //sepas client list bazi hara daryaft va az tarigh method addBazi cleintFX, an ra dar araye listBaziHa add mikonad.
        client.DaryaftListBaziHayeDarhalEjra();

        FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/MakeOrJoinPage.fxml"));
        Pane p = l.load();

        //***** yel shey az kelas MakeOrJoinController sakhte va Controller safle MakeOrJoinPage ra darun an mirizim
        // ta be method haye setter va getter an dastresi dashte bashim.
        MakeOrJoinController c = (MakeOrJoinController) l.getController();

        //***** list bazi hara be controller miferestim ta betavanad dar zaman bala amadn page, ruye safhe namayesh dade shavad.
        c.setListBaziHa(listBaziHa);
        //***** ferestadan clientFX be controller safhe baraye dastresi dashtan be method haye client mojud dar in clientFX.
        c.setClientFX(this);


        Scene sc = new Scene(p);
        //***** sakht yek stage ba scope dar sath class.
        stageAsli = new Stage();
        stageAsli.setScene(sc);
        stageAsli.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    //***** bazi hayi ke listener client miferestad ra add mikonad ta ruye safhe namayesh dahad.
    public void addBazi(BaziSamtClient b){
        listBaziHa.add(b);
        System.out.println(b);
    }

    //***** set kardan scene jadid ruye stage Asli.
    public void setSceneJadid(Scene sc){
        stageAsli.setScene(sc);
    }

    //***** safhe bazi ra ba harf mored nazar va elemant haye dakhel string mozuat bala, misazad va ruye stage asli miandazad.
    public void sakht_Safhe_Ba_In_Harf(char harf) throws IOException {


        //***** chon component haye javaFX ra faghat dakhel hamin tread mitavan taghir dad, bayad dakhel yek
        //platform.runLater component hara taghir dahim.
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //***** sakht FXML loader...
                FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/SafheBazi.fxml"));
                Pane p = null;
                try {
                    p = l.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                //***** gereftan controller bazi
                SafheBaziController c = (SafheBaziController) l.getController();

                //***** set kardan client FX baraye dashtan dastresi be method haye client
                c.setClientFX(thisClientFX);

                //***** mozuat va type ra be controller midahad ta safhe sakhte shavad.
                c.getMozuat(mozuat);

                //***** gozashan harf entekhab shode ruye lable
                c.getHarf(harf);

                //***** gereftan type bazi, baraye set kardan dokme tamam ya timer.
                c.getType(type);

                //***** chon p momken ast null bashad dakhel if gozashtim.
                if(p != null){
                    //***** sakht Scene baraye in pane
                    Scene sc = new Scene(p);

                    //***** andakhtan in scene ruye safhe asli
                    stageAsli.setScene(sc);
                }


            }
        });
    }

    //***** sakht safhe bazi ba yek harf baraye host
    public void sakht_Safhe_Ba_In_Harf_HOST(char harf){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/SafheBaziHost.fxml"));
                Pane p = null;
                try{
                    p = l.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //***** gereftan controller
                SafheBaziHostController c = (SafheBaziHostController) l.getController();

                //***** set kardan clientFX
                c.setClientFX(thisClientFX);

                //***** set kardan mozuat
                c.getMozuat(mozuat);

                //***** set kardan type
                c.getType(type);

                //***** set kardan harf
                c.getHarf(harf);

                //***** sakht scene va gozashtan an ruye stage asli
                if(p != null){
                    Scene sc = new Scene(p);
                    stageAsli.setScene(sc);
                }

            }
        });
    }

    //***** in method safhe daraye dokme bazi ra load mikonad.
    public void myTurn(){

        System.out.println("dakhel method myturn");
        //***** chon ruye yek tread digar hastim bayad dakhel runLater benevisim.
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                System.out.println("ghabl load");
                FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/ShoruBaziGuestPage.fxml"));

                Pane p = null;
                try {
                    p = l.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("bad load");
                ShoruBaziGuestPageController c = (ShoruBaziGuestPageController) l.getController();

                //***** set kardan client fx baraye dashtan dastresi be method haye client.
                c.setClientFX(thisClientFX);

                if(p != null){
                    System.out.println("dakhel shart");
                    Scene sc = new Scene(p);
                    setSceneJadid(sc);
                }
            }
        });
    }

    //***** in method safhe entezar ra load mikonad.
    public void waitingPage(){
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
                    stageAsli.setScene(sc);
                }

            }
        });
    }

    //***** set kardan mozuat bazi
    public void setMozuat(String mozuat){
        this.mozuat = mozuat;
        System.out.println(mozuat);
    }

    //***** set kardan type bazi
    public void setType(String type){
        this.type = type;
        System.out.println(type);
    }

    //method baraye chap
    public void print(String p){
        System.out.println(p);
    }

    //getters...
    public String getType(){
        return type;
    }
    public String getMozuat(){
        return mozuat;
    }
}
