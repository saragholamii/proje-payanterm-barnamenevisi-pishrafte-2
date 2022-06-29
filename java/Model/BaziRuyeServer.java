package Model;

import java.util.ArrayList;

public class BaziRuyeServer {
    public static int tedadBazi = 0;
    int id = tedadBazi++;

    ArrayList<String> listMozuat = new ArrayList<>();
    ArrayList<ClientManager> listPlayerHa = new ArrayList<>();
    String type;
    int tedadDor;
    int saat;
    int daghighe;
    int sanie;
    int IDHost;

    public String getTypeBaziBeSuratReshte(){
        if(type.equalsIgnoreCase("zoodtar"))
            return "zoodtar" + "-";
        else
            return "time" + "-" + getSaat() + "-" + getDaghighe() + "-" + getSanie();
    }

    public String mozuatBaziBesuratReshte(){
        String str = "";

        for(String s : listMozuat){
            str += s;
            str += "-";
        }

        return str;
    }

    public String idClientHaBeSuratReshte(){
        String str = null;

        for(ClientManager c : listPlayerHa){
           // str += Integer.toString(c.getIdClientManager());
            str += '-';
        }

        return str;
    }

    public void setDaghighe(int daghighe) {
        this.daghighe = daghighe;
    }

    public void setSanie(int sanie) {
        this.sanie = sanie;
    }

    public void setTedadDor(int tedadDor){
        this.tedadDor = tedadDor;
    }

    public String getType() {
        return type;
    }

    public int getTedadDor() {
        return tedadDor;
    }

    public void addPlayerToGame(ClientManager c){
        listPlayerHa.add(c);
    }

    public void addMozuBeBazi(String s){
        listMozuat.add(s);
    }

    public int getIDBazi(){
        return id;
    }

    public void setType(String type){
        this.type = type;
    }

    public int getSaat() {
        return saat;
    }

    public int getDaghighe() {
        return daghighe;
    }

    public int getSanie() {
        return sanie;
    }

    public void setSaat(int saat) {
        this.saat = saat;
    }

    public void setIDHost(int idHost){
        IDHost = idHost;
    }

}
