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
    ArrayList<BaziRuyeServer> listBaziToShow = new ArrayList<>();
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
        listBaziToShow.add(bazi);
        System.out.println(listBaziHa.size());
    }

    //***** list bazi hara be surat array list miferestad.
    public ArrayList<BaziRuyeServer> getListBaziHa(){
        return listBaziToShow;
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
                //***** chon bazi shoru shode ast az listBaziToShow hazf mishavad ta digar kasi be bazi napeyvandad.
                listBaziToShow.remove(b);

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
    public void choosingPlayerForStart(int idBazi, int idHost) throws FileNotFoundException, InterruptedException {

        for (BaziRuyeServer b : listBaziHa) {
            if (b.getIDBazi() == idBazi) {

                //***** check mikonad aya be tedad dor moshakhas shode reside iem ya na.
                if(b.getTedadDorTaAlan() < b.getTedadDor()){

                    //***** peida kardan yek adad random az beyn player haye bazi, va adad nabayad barabar ba id host bashad.
                    Random r = new Random();
                    while(true){
                        int n = r.nextInt(b.listPlayerHa.size());

                        //***** baraye baghie bazikonan ( ham host ham guest ) safhe waiting ra load mikonad.
                        for(int i = 0; i < b.listPlayerHa.size(); i++){
                            if(i != n) b.listPlayerHa.get(i).waitingPage();
                        }

                        if(b.listPlayerHa.get(n).idClientManager != idHost){
                            b.listPlayerHa.get(n).YourTurn();
                            break;
                        }

                    }

                }
                //***** yani bazi tamam shode va safhe dar hal mohasebe emtiaz ha bayad chap shavad.
                else{
                    for(int i = 0; i < b.listPlayerHa.size(); i++){
                        b.listPlayerHa.get(i).darEntezarMohasebeEmtiaz();
                    }

                    for(int i = 0; i < b.listPlayerHa.size(); i++){
                        System.out.println("number " + i);
                        for(int j = 0; j < b.listPlayerHa.get(i).javabHa.size(); j++){
                            System.out.println(b.listPlayerHa.get(i).javabHa.get(j));
                        }
                    }

                    //***** javabha ha bayad tashih shavand
                    tashih(b);

                }

            }
        }

    }

    //***** ersal shoru bazi
    public void startGame(char harf, int idBazi) throws InterruptedException, FileNotFoundException {
        BaziRuyeServer bazi = null;

        for (BaziRuyeServer b : listBaziHa){
            if(b.getIDBazi() == idBazi){
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
        }
    }

    //***** in method be yak nafar az bazikonan dokme shoru va be baghie safhe entezar ra midahad.
    public void someoneFinishedTheGame(int idBazi) throws InterruptedException, FileNotFoundException {

        for(BaziRuyeServer b : listBaziHa){
            if(b.getIDBazi() == idBazi){

                //***** check mishavad aya dor ha tamam shode ast ya na
                if(b.getTedadDorTaAlan() < b.getTedadDor()){
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

                else{
                    //***** be client ha miguyad ke jabav dor akhar ra ham dar araye javab zakhire konand.
                    javabDorAkhar(b);

                    //***** chon ehtemalan tashih tul mikeshad, yek safhe "bazi tamam shod, dar entezar tashih" baraye hame load mikonim.
                    for(int i = 0; i < b.listPlayerHa.size(); i++){
                        b.listPlayerHa.get(i).darEntezarMohasebeEmtiaz();
                    }

                    Thread.sleep(1000);

                    for(int i = 0; i < b.listPlayerHa.size(); i++){
                        System.out.println("number " + i);
                        for(int j = 0; j < b.listPlayerHa.get(i).javabHa.size(); j++){
                            System.out.println(b.listPlayerHa.get(i).javabHa.get(j));
                        }
                    }


                    //***** javabha ha bayad tashih shavand
                    tashih(b);

                }
            }
        }

    }

//    //***** in method be player haye bazi miguyad javab hayesahn ra baraye server ersal konand.
//    public void ersalJavabHaBeSrever(BaziRuyeServer b){
//        for(int i = 0; i < b.listPlayerHa.size(); i++){
//            b.listPlayerHa.get(i).sendAllAnswers();
//        }
//    }

    //***** in method gharar ast emtiaz hara mohasebe konad.
    public void tashih(BaziRuyeServer b) throws FileNotFoundException, InterruptedException {
        System.out.println("dar method tashih");


        //***** yek araye az int ba sath dastresi class dorost mikonim ta emtiazat ra darun an negah darim
        emtiazHa = new int[b.listPlayerHa.size()];

        //***** dar in method bayad har dor bazi ra be surat joda gane tashih konim.
        for(int i = 0; i < b.getTedadDor(); i++){

            ArrayList<String> javabHayeDor = new ArrayList<>();

            Thread.sleep(1000);
            //***** hala be tamam client ha miguyad ke javab haye dore i om ra beferestand.
            for(int j = 0; j < b.listPlayerHa.size(); j++){
                javabHayeDor.add(b.listPlayerHa.get(j).sendAnswerNumber(i));
            }

            TashihDor(javabHayeDor, b.mozuatBaziBesuratReshte());

        }

        //***** hala methodi az client manager ra seda mizanad ke emtiaz bazikon ra ruye safhe namayesh midahad.
        for(int i = 0; i < b.listPlayerHa.size(); i++){
            b.listPlayerHa.get(i).chapEmtiaz(emtiazHa[i]);
        }


    }

    //***** in method yek dor ra tashih mikonad.
    public void TashihDor(ArrayList<String> javabHayeDor, String mozuat) throws FileNotFoundException, InterruptedException {
        Tashih tashih = new Tashih(this);

        //***** aval javab har bazikon be surat joda chek mishavad ke aya dar file vojud darad ya na, sepas emtiaz ha be surat yek
        //araye az int bargardande mishavad.
        int[] emtiazHa = tashih.ayaDarFileHast(javabHayeDor, mozuat);

        //***** jam zadan emtiaz ha ba emtiaz haye zakhire shode dar araye emtiazHa
        for(int i = 0; i < emtiazHa.length; i++){
            System.out.println("bad az check shodan mojod dar file");
            System.out.println("emtiaz nafar " + i + "om:  " + emtiazHa[i]);
            this.emtiazHa[i] += emtiazHa[i];
        }

        //***** dar in method baraye har dor bazi check mishavad aya kalamat babari vojud darand yana.
        emtiazHa = tashih.ayaYeksanNeveshteAnd(javabHayeDor);

        //***** jam zadan emtiaz ha ba emtiaz haye zakhire shode dar araye emtiazHa
        for(int i = 0; i < emtiazHa.length; i++){
            System.out.println("bad az check shodan yeksan budan");
            System.out.println("emtiaz nafar " + i + "om:  " + emtiazHa[i]);
            this.emtiazHa[i] += emtiazHa[i];
        }

        //***** tread ra be khab mibarad ta ba safhe entezar tadakhol peida nakonad.
        Thread.sleep(1500);

    }

    //***** im method be client miguyad ke javab dor akhar ra ham ke zakhire nashode, zakhire konand.
    public void javabDorAkhar(BaziRuyeServer bazi) throws InterruptedException {

        for(int i = 0; i < bazi.listPlayerHa.size(); i++){
            if(bazi.listPlayerHa.get(i).idClientManager != bazi.IDHost){
                System.out.println("dar method jabab akhar dar shart mehman: " + bazi.listPlayerHa.get(i).idClientManager);
                bazi.listPlayerHa.get(i).ersalJavabAkhar();
            }
            else {
                System.out.println("dar method jabab akhar dar shart mizban: " + bazi.listPlayerHa.get(i).idClientManager);
                bazi.listPlayerHa.get(i).ersalJavabAkharHOST();
            }
        }
    }


    public void print(String s){
        System.out.println(s);
    }
}
