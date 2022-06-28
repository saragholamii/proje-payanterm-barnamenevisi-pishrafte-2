package Model;

import java.util.Scanner;

public class BaziSamtClient {
    int idBazi;
    String mozuat;
    String type;
    String tedadDor;
    String idPlayerHa;

    public BaziSamtClient(String mozuat, String idPlayerHa, String type, String tedadDor,  String idBazi) {
        this.idBazi = Integer.parseInt(idBazi);
        this.mozuat = mozuat;
        this.type = type;
        this.tedadDor = tedadDor;
        this.idPlayerHa = idPlayerHa;
    }

    public String toString(){
        String onList = "mozuat: " + mozuat + "\n" + "tedad dor bazi: " + tedadDor + "\n" + "bazikonan: " + idPlayerHa + "\n";

        //***** type agar saati bashad, saat daghighe va sanie ba - az ham joda shode and.
        Scanner sc = new Scanner(type);
        sc.useDelimiter("-");

        //***** agar type 1 bashad, yani harkas zudtar tamam kard.
        /*if(Integer.parseInt(sc.next()) == 1){
            onList += "type bazi: " + "har kas zudtar ramam kard.";
        }

        //***** agar 2 bud, yani time darad va saat, daghighe va sanie bad az ba - az ham joda shode and.
        else{
            sc.next();
            onList += "type bazi: " + sc.next() + " h " + sc.next() + " min " + sc.next() + " sec ";
        }*/

        return onList;
    }

    public int getIdBazi(){
        return idBazi;
    }

    public String getMozuatBazi(){
        return mozuat;
    }
}
