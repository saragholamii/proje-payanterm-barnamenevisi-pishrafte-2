package Controller;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import Model.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MakeOrJoinController implements Initializable {
    ArrayList<BaziSamtClient> listBaziHa = new ArrayList<>();
    ClientFX clientFX;

    @FXML
    private Button goruhJadid;
    @FXML
    private ListView<BaziSamtClient> lBHa;
    @FXML
    private BorderPane MakeOrJoinPagePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    //***** set kardan list bazi ha dar field listBaziHa ta initialize az an baraye listView khod estefade konad.
    public void setListBaziHa(ArrayList<BaziSamtClient> list){
        lBHa.getItems().addAll(list);
    }

    //***** set kardan clientFX marbute dar yek field clientFX, baraye dastresi dashtan be method haye an, baraye ertebat ba server.
    public void setClientFX(ClientFX clientFX){
        this.clientFX = clientFX;
    }

    public void ijadBaziJadidBtn(ActionEvent actionEvent) throws IOException {
        //***** hazf item haye ruye Scene.
        ((Pane) goruhJadid.getParent()).getChildren().remove(goruhJadid);
        ((Pane) lBHa.getParent()).getChildren().remove(lBHa);

        //***** ijad loader jadid baraye load kardan safhe ijadBaziJadid.
        FXMLLoader l = new FXMLLoader(getClass().getResource("/FXML/EntekhabMozuBarayeBaziJadid.fxml"));
        Pane p = l.load();

        //***** gereftan controller pane jadid baraye seda zadan method hayash.
        EntekhabItemBarayeIjadBaziController c = (EntekhabItemBarayeIjadBaziController) l.getController();

        //***** ferestadan ClientFX be in safhe.
        c.setClientFX(clientFX);

        //***** sakht Scene jadid va set kardan on ruye primary stage safhe asli.
        Scene sc = new Scene(p);
        clientFX.setSceneJadid(sc);
    }
}
