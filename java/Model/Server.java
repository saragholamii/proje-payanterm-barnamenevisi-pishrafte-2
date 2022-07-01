package Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Server {
    final static int PORT = 9010;
    ArrayList<ClientManager> listClientManager = new ArrayList<>();
    ArrayList<BaziRuyeServer> listBaziHa = new ArrayList<>();
    int[] emtiazHa;


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

    }

    //***** method shoru bazi baraye ferestadan peygham shoru bazi be bazikonan digar.
    public void startGameFirst(int idBazi, int idBazikonShoruKonande, char harf){

        for (BaziRuyeServer b : listBaziHa) {
            if (b.getIDBazi() == idBazi) {
                b.addToTedadDorTaAlan();
                for (int j = 0; j < b.listPlayerHa.size(); j++) {
                    if (b.listPlayerHa.get(j).idClientManager != idBazikonShoruKonande) {
                        b.listPlayerHa.get(j).startGameWithTHisLetter(harf);
                    }
                }
            }
        }

    }

    //***** entekhab bazikon shoru konande badi.
    public void choosingPlayerForStart(int idBazi, int idHost){

        for (BaziRuyeServer b : listBaziHa) {
            if (b.getIDBazi() == idBazi) {

                //***** peida kardan yek adad random az beyn player haye bazi, va adad nabayad barabar ba id host bashad.
                Random r = new Random();
                while(true){
                    int n = r.nextInt(b.listPlayerHa.size());

                    if(b.listPlayerHa.get(n).idClientManager != idHost){
                        b.listPlayerHa.get(n).YourTurn();
                        break;
                    }
                }

            }
        }

    }

    //***** ersal shoru bazi
    public void startGame(char harf, int idBazi) throws InterruptedException, FileNotFoundException {
        BaziRuyeServer bazi = null;

        for (BaziRuyeServer b : listBaziHa){
            if(b.getIDBazi() == idBazi){
                //***** bazi peida shode ra dar fiel bazi zakhire mikonad.
                bazi = b;
                //***** check mishavad aya tedad dor ha tamam shode ast ya na
                if(b.getTedadDorTaAlan() < b.getTedadDor()){
                    //*****id host
                    int idHost = b.IDHost;

                    for (int i = 0; i < b.listPlayerHa.size(); i++){
                        if(b.listPlayerHa.get(i).idClientManager != idHost){

                            b.listPlayerHa.get(i).startGameWithTHisLetter(harf);
                        }
                        else {
                            b.listPlayerHa.get(i).startGameWithTHisLetterHOST(harf);
                        }
                    }

                    //***** yek dor digar zade shod, pas be tadad dor ha yeki ezafe mikonim.
                    b.addToTedadDorTaAlan();
                    System.out.println("dor ta alan:" + b.getTedadDorTaAlan());
                }

                else{
                    //***** be client ha miguyad ke javab hayesan ra baraye server ersal konan.
                    ersalJavabHaBeSrever(bazi);
                    Thread.sleep(500);

                    //***** javabha ha bayad tashih shavand
                    tashih(bazi);

                    //***** chon ehtemalan tashih tul mikeshad, yek safhe "bazi tamam shod, dar entezar tashih" baraye hame load mikonim.
                    //.....
                }
            }
        }
    }

    //***** in method be yak nafar az bazikonan dokme shoru va be baghie safhe entezar ra midahad.
    public void someoneFinishedTheGame(int idBazi){

        System.out.println("someOne finished the game");

        for(BaziRuyeServer b : listBaziHa){
            if(b.getIDBazi() == idBazi){

                //***** peida kardan 1 nafar random baraye shoru kardan bazi
                Random r = new Random();
                int chooser;
                while(true){
                    chooser = r.nextInt(b.listPlayerHa.size());
                    if(b.listPlayerHa.get(chooser).idClientManager != b.IDHost) break;
                }

                //***** be bazikon entekhab shode dokme shoru midahad.
                b.listPlayerHa.get(chooser).YourTurn();

                //***** ba baghi bazikonan safhe entezar ra midahad.
                for(ClientManager p : b.listPlayerHa){
                    if(p.idClientManager != b.listPlayerHa.get(chooser).idClientManager) p.waitingPage();
                }
            }
        }

    }

    //***** in method be player haye bazi miguyad javab hayesahn ra baraye server ersal konand.
    public void ersalJavabHaBeSrever(BaziRuyeServer b){
        for(int i = 0; i < b.listPlayerHa.size(); i++){
            b.listPlayerHa.get(i).sendAllAnswers();
        }
    }

    //***** in method gharar ast emtiaz hara mohasebe konad.
    public void tashih(BaziRuyeServer b) throws FileNotFoundException {
        //***** yek araye az int ba sath dastresi class dorost mikonim ta emtiazat ra darun an negah darim
        emtiazHa = new int[b.listPlayerHa.size()];

        //***** dar in method bayad har dor bazi ra be surat joda gane tashih konim.
        for(int i = 0; i < b.getTedadDor(); i++){

            ArrayList<String> javabHayeDor = new ArrayList<>();

            //***** hala be tamam client ha miguyad ke javab haye dore i om ra beferestand.
            for(int j = 0; j < b.listPlayerHa.size(); j++){
                javabHayeDor.add(b.listPlayerHa.get(j).sendAnswerNumber(i));
            }

            TashihDor(javabHayeDor, b.mozuatBaziBesuratReshte());

        }


    }

    //***** in method yek dor ra tashih mikonad.
    public void TashihDor(ArrayList<String> javabHayeDor, String mozuat) throws FileNotFoundException {
        //***** aval javab har bazikon be surat joda chek mishavad ke aya dar file vojud darad ya na, sepas emtiaz ha be surat yek
        //araye az int bargardande mishavad.
        int[] emtiazHa = Tashih.ayaDarFileHast(javabHayeDor, mozuat);

        //***** jam zadan emtiaz ha ba emtiaz haye zakhire shode dar araye emtiazHa
        for(int i = 0; i < emtiazHa.length; i++){
            this.emtiazHa[i] += emtiazHa[i];
        }

        //***** dar in method baraye har dor bazi check mishavad aya kalamat babari vojud darand yana.
        emtiazHa = Tashih.ayaYeksanNeveshteAnd(javabHayeDor);

        //***** jam zadan emtiaz ha ba emtiaz haye zakhire shode dar araye emtiazHa
        for(int i = 0; i < emtiazHa.length; i++){
            this.emtiazHa[i] += emtiazHa[i];
        }
    }


    public void print(String s){
        System.out.println(s);
    }
}
