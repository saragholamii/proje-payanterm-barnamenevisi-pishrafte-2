package Model;

import java.util.ArrayList;

public class BaziRuyeServer {
    public static int tedadBazi = 0;
    int id = tedadBazi++;

    ArrayList<String> listMozuat = new ArrayList<>();
    ArrayList<ClientManager> listPlayerHa = new ArrayList<>();
    int type; //1:zudtar 2-time
    int tedadDor;
    int saat;
    int daghighe;
    int sanie;

    public void setType(int type){
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

    public String getTypeBaziBeSuratReshte(){
        if(type == 1)
            return Integer.toString(1);
        else
            return "2" + '-' + Integer.toString(getSaat()) + '-' + Integer.toString(getDaghighe()) + '-' + Integer.toString(getSanie());
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

    public int getType() {
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
}
