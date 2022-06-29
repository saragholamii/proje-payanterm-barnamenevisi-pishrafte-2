package Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
    final static int PORT = 9010;
    ArrayList<ClientManager> listClientManager = new ArrayList<>();
    ArrayList<BaziRuyeServer> listBaziHa = new ArrayList<>();


    public Server() throws IOException {

        ServerSocket s = new ServerSocket(PORT);

        while(true){

            System.out.println("waiting for new client");
            //******montazer client badi shodan, pas az darkhast bargharari ertebat yek socket sakhte misjavad ****
            Socket socket = s.accept();
            //****** yek client manager sakhte va client ra be an pas midahim.
            ClientManager cM = new ClientManager(this, socket);
            //******hala method run client manager ra mizanim ta shoru be tabadol etelaat ba client konad.
            Thread t = new Thread(cM);
            t.start();
        }
    }
    public static void main(String[] args) throws IOException {
        new Server();
    }

    //***** in method har client ke sakhte mishavad ra ba kelid id client be list client manager ha ezafe mikonad.
    public void addClientManager(int id, ClientManager c){
        listClientManager.add(c);
    }

    //***** in method yek shey bazi daryaft karde va an ra be list bazi ha ezafe mikonad.
    public void addNewGame(BaziRuyeServer bazi){
        listBaziHa.add(bazi);
        System.out.println(listBaziHa.size());
    }

    //***** list bazi hara be surat array list miferestad.
    public ArrayList<BaziRuyeServer> getListBaziHa(){
        return listBaziHa;
    }

    //***** add kardan yek client manager be yek bazi
    public void addClientManagerToAGame(int idBazi, ClientManager clientManager){

        //***** migardad agr bazi ba id mored nazar peida shod, bazikon ra be list bazikonhayash add mikonad.
        for(BaziRuyeServer b : listBaziHa){
            if(b.getIDBazi() == idBazi) {
                b.addPlayerToGame(clientManager);
                System.out.println(b.listPlayerHa.size());
            }
        }

        System.out.println("added ");
    }

    //***** method shoru bazi baraye ferestadan peygham shoru bazi be bazikonan digar.
    public void startGame(int idBazi, int idBazikonShoruKonande, char harf){
        System.out.println("dakhel method start game server");

        System.out.println("id bazi: " + idBazi);
        System.out.println("id host: " + idBazikonShoruKonande);

        for (BaziRuyeServer b : listBaziHa) {
            System.out.println("id bazi dakhel list: " + b.getIDBazi());
            if (b.getIDBazi() == idBazi) {
                System.out.println("bazi peida shod.");
                System.out.println("size lsit bazikonan bazi: " + b.listPlayerHa.size());
                System.out.println("id list bazikonan bazi: ");
                for(int o = 0; o < b.listPlayerHa.size(); o++){
                    System.out.println(b.listPlayerHa.get(o).idClientManager);
                }
                for (int j = 0; j < b.listPlayerHa.size(); j++) {
                    System.out.println("id bazikonan bazi peida shode " + b.listPlayerHa.get(j).idClientManager);
                    if (b.listPlayerHa.get(j).idClientManager != idBazikonShoruKonande) {
                        System.out.println("method startGameWithTHisLetter seda zade shod");
                        b.listPlayerHa.get(j).startGameWithTHisLetter(harf);
                    }
                }
            }
        }
    }

    public void print(String s){
        System.out.println(s);
    }
}
