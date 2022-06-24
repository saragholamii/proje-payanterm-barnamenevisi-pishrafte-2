package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import Model.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MakeOrJoinController implements Initializable {
    ArrayList<BaziSamtClient> listBaziHa = new ArrayList<>();

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

}
