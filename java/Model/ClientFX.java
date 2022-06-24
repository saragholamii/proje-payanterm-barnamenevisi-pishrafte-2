package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Controller.*;

import java.util.ArrayList;

public class ClientFX extends Application {
    public Client client;
    ArrayList<BaziSamtClient> listBaziHa = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {

        client = new Client(this);
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
        primaryStage.setScene(sc);
        primaryStage.show();


    }

    public static void main(String[] args) {
        launch(args);
    }

    //***** bazi hayi ke listener client miferestad ra add mikonad ta ruye safhe namayesh dahad.
    public void addBazi(BaziSamtClient b){
        listBaziHa.add(b);
        System.out.println(b);
    }
}
