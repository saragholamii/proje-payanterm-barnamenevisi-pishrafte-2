package Model;

public class BaziSamtClient {
    String mozuat;
    String type;
    String tedadDor;
    String idPlayerHa;

    public BaziSamtClient(String mozuat, String type, String tedadDor, String idPlayerHa) {
        this.mozuat = mozuat;
        this.type = type;
        this.tedadDor = tedadDor;
        this.idPlayerHa = idPlayerHa;
    }

    public String toString(){
        String onList = "mozuat: " + mozuat + "\n" + "type bazi: " +  type + "\n" + "tedad dor bazi: " + tedadDor + "\n" + "bazikonan: " + idPlayerHa;
        return onList;
    }
}
