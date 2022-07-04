package Model;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Controller.*;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

public class ClientFX extends Application {
    public Client client;
    ClientFX thisClientFX;

    SafheBaziHostController hostController;
    SafheBaziController guestController;

    ArrayList<BaziSamtClient> listBaziHa = new ArrayList<>();

    Stage stageAsli;
    String mozuat;
    String type;

    boolean didIFinishedLastTime;
    boolean AmIHost;

    MakeOrJoinController makeOrJoinController = null;

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

        //***** controller ra dar sath kelass ghabel dastresi mikonim ta agar kasi bazi jadid sakht, ruye safhe namayesh dade shavad.
        makeOrJoinController = c;

        //***** list bazi hara be controller miferestim ta betavanad dar zaman bala amadn page, ruye safhe namayesh dade shavad.
        c.setListBaziHa(listBaziHa);
        //***** ferestadan clientFX be controller safhe baraye dastresi dashtan be method haye client mojud dar in clientFX.
        c.setClientFX(this);


        Scene sc = new Scene(p);
        //***** sakht yek stage ba scope dar sath class.
        stageAsli = new Stage();
        stageAsli.setScene(sc);
        stageAsli.setTitle("!اسم و فاميل بزن");
        stageAsli.show();

        //***** ba zadan dokme khoruj, barname client motevaghef mishavad.
        stageAsli.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                exitFromGame();
                System.exit(0);
            }
        });


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

    //***** safhe bazi ra ba harf mored nazar va element haye dakhel string mozuat bala, misazad va ruye stage asli miandazad.
    public void sakht_Safhe_Ba_In_Harf(char harf) throws IOException {

        //***** gereftan javab dor ghabli, agar null bashad yani dor aval ast, hamchenin agar khodash bazi ra tamam nakarde bud.
        // chon agar khodash bazi ra tamam karde bashad dar zaman zadan dokme javab zakhire shode ast.
        if(guestController != null && !didIFinishedLastTime){
            client.addJavab(guestController.getJavab());
        }
        //***** baraye dor haye badi dobare bayad ba halat false bargardad.
        if(didIFinishedLastTime) {didIFinishedLastTime = false; }

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

                //***** set kardan guestController ba controller in dor
                guestController = c;

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

        //***** gereftan javab dor ghabli agar kkhodash bazi ra tamam nakarde bud.
        if(!didIFinishedLastTime) { client.addJavab(hostController.getJavab()); }

        //***** baraye dor haye badi dobare bayad ba halat false bargardad.
        if(didIFinishedLastTime) {didIFinishedLastTime = false; }

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

                //***** set kardan hostController ba controller in dor
                hostController = c;

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

        //***** chon ruye yek tread digar hastim bayad dakhel runLater benevisim.
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/ShoruBaziGuestPage.fxml"));

                Pane p = null;
                try {
                    p = l.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ShoruBaziGuestPageController c = (ShoruBaziGuestPageController) l.getController();

                //***** set kardan client fx baraye dashtan dastresi be method haye client.
                c.setClientFX(thisClientFX);

                if(p != null){
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

    //***** in method safhe emtiaz ra ba adad dade shode load mikonad.
    public void chapEmtiaz(int emtiaz){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/ChapEmtiazPage.fxml"));
                Pane p = null;
                try {
                    p = l.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ChapEmtiazPageController c = (ChapEmtiazPageController) l.getController();

                //***** set kardan clientFX
                c.setClientFX(thisClientFX);

                //***** set kardan matn lable
                c.chap(emtiaz);

                if(p != null){
                    Scene sc = new Scene(p);
                    stageAsli.setScene(sc);
                }
            }
        });
    }

    //***** in method safhe dar entezar emtiaz ra chap mikonad.
    public void safheEntezarEmtiaz(){

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/DarEntezarEmtiaz.fxml"));
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

    //***** in method javab akhar ra ke zakhire nashode dar list javab haye client add mikonad.
    public void zakhireJavabAkhar(){
        System.out.println("dar method zakhire javab akhar mehamn");
        client.addJavab(guestController.getJavab());
    }

    //***** in method javab akhar ra ke zakhire nashode dar list javab haye client add mikonad.
    public void zakhireJavabAkharHost(){
        System.out.println("dar method zakhire javab akhar mizban");
        client.addJavab(hostController.getJavab());
    }

    //***** in method be server payam midahad ta bazikon ra az list player haye bazi hazf konad.
    public void exitFromGame(){
        if(AmIHost) { client.exitFromGameHost(); }
        else { client.exitFromGame();}
    }

    //***** in method bazi taze sakhte shode ra be list bazi hayi ke neshan midahad ezafe mikonad.
    public void ezafeShodanBaziJadid(BaziSamtClient b){
        System.out.println("dakhel method add bazi jadid");
        listBaziHa.add(b);

        //***** method set kardan list bazi ha va neshan dadan anha dar make or join controller ra seda mizanim ta bazi jadid ra ham neshan dahad.
        makeOrJoinController.setListBaziHa(listBaziHa);
    }

    //***** in method safhe avalie ra dobare load mikonad.
    public void bazGashtBeSafheAval() throws IOException {

        FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/MakeOrJoinPage.fxml"));
        Pane p = l.load();

        //***** yel shey az kelas MakeOrJoinController sakhte va Controller safle MakeOrJoinPage ra darun an mirizim
        // ta be method haye setter va getter an dastresi dashte bashim.
        MakeOrJoinController c = (MakeOrJoinController) l.getController();

        //***** controller ra dar sath kelass ghabel dastresi mikonim ta agar kasi bazi jadid sakht, ruye safhe namayesh dade shavad.
        makeOrJoinController = c;

        //***** list bazi hara be controller miferestim ta betavanad dar zaman bala amadn page, ruye safhe namayesh dade shavad.
        c.setListBaziHa(listBaziHa);

        //***** ferestadan clientFX be controller safhe baraye dastresi dashtan be method haye client mojud dar in clientFX.
        c.setClientFX(this);

        Scene sc = new Scene(p);
        stageAsli.setScene(sc);
    }

    //***** in method agar bazi ba in id vojud dashte bashad ra az list bazi ha hazf mikonad.
    public void hazfBazi(int idBazi){
        for(int i = 0; i < listBaziHa.size(); i++){
            if(listBaziHa.get(i).getIdBazi() == idBazi) { listBaziHa.remove(i); }
        }
    }

    //***** setters...
    public void setMozuat(String mozuat){
        this.mozuat = mozuat;
        System.out.println(mozuat);
    }
    public void setType(String type){
        this.type = type;
        System.out.println(type);
    }
    public void setHostController(SafheBaziHostController c){
        hostController = c;
    }
    public void setDidIFinishedLastTime(boolean b){
        didIFinishedLastTime = b;
    }
    public void setAmIHost(boolean b){ AmIHost = b; }

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
