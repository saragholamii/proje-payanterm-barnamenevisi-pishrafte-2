package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ChapEmtiazPageController implements Initializable {
    @FXML
    private Label emtiazLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void chap(int emtiaz){
        //***** set kardan emtiaz ruye lable
        emtiazLbl.setText("امتیاز شما در این بازی: " + emtiaz);
        emtiazLbl.setVisible(true);
    }
}
