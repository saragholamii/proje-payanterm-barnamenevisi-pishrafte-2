package Model;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientManager implements Runnable{
    Socket socketClient;
    Server ServerHolder;
    BufferedReader in;
    PrintWriter out;
    int game_That_I_Am_The_Host_ID;
    int game_That_I_Join_ID;
    ArrayList<String> javabHa = new ArrayList<>();

    static int tedadClientManager;
    int idClientManager = tedadClientManager++;

    public ClientManager(Server server, Socket socket) {
        socketClient = socket;
        ServerHolder = server;
    }

    @Override
    public void run() {
        try {

            in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socketClient.getOutputStream()), true);
            //***** connection ba client bargharar ast, ba pol ertebati in va out.

            //porotocol ha....
            //estefade az esharegarhaye dakhel server ba estefade az method haye an.....

            while (true){
                //***** hamishe dar hal khandan dasturat ast. yek dastur ke tamam mishavad, montazer badi mimanad.
                String command = in.readLine();
                switch (command){
                    case "ListG":
                        ferestadanEtelaatPlatformHaBeClient();
                        break;
                    case "JoinG":
                        joinBePlatform();
                        break;
                    case "NewG":
                        sakhtBaziJadid();
                        break;
                    case "startGWhenIAmHost": //shoru bazi baraye avalin bar ke harf tavasot mizban tayin mishavad.
                        startGameBtnWhenIAmHost();
                        break;
                    case "addClientManager":
                        addClientManagerToList();
                        break;
                    case "NDor":
                        DorJadid();
                        break;
                    case "startGameWhenIAmGuest":
                        startGameBtnWhenIAmGuest();
                        break;
                    case "SomeoneFinished":
                        someoneFinishedTheGame();
                        break;
//                    //***** yani mikhahad javab hara beferestad.
//                    case "AnswersComming":
//                        getAnswers();
//                        break;
                    //***** yani javab 1 dor ke anjam shode dar hal ferestade shodan ast.
                    case "JavabYekDor":
                        getAnswers();
                        break;
                }
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void ferestadanEtelaatPlatformHaBeClient(){
        //***** aval payam midahad ke mikhahad list bazi hara beferestad.
        out.println("listG");


        //***** list bazi hara az server daryaft mikonad.
        ArrayList<BaziRuyeServer> listBazi = new ArrayList<>();
        listBazi = ServerHolder.getListBaziHa();

        //***** tedad bazi hara miferestad.
        out.println(listBazi.size());

        //***** hala etelaat har bazi ra miferestad. 1-mozuat besurat reshte(joda shode ba -) 2-id clientha be surat
        // reshte (joda shode ba -) 3- type bazi(agar zamani bashad(2), joda shode ba -) 4- tedad dor bazi. 5-idBazi
        for(BaziRuyeServer b : listBazi){

            out.println(b.mozuatBaziBesuratReshte());

            out.println(b.idClientHaBeSuratReshte());

            out.println(b.getTypeBaziBeSuratReshte());

            out.println(b.getTedadDor());

            out.println(b.getIDBazi());
        }
    }

    public void joinBePlatform() throws IOException {
        //***** id bazi mored nazar ra migirad.
        int id = Integer.parseInt(in.readLine());

        //***** id bazi ke be an add shode ast ra negah midarim.
        game_That_I_Join_ID = id;

        //***** ba seda zadan method addBazikonBeYekBazi, in client ra be list bazikon haye bazi add mikonad.
        ServerHolder.addClientManagerToAGame(id, this);
    }

    public void sakhtBaziJadid() throws IOException {
        //***** inja yek bazi jadid sakhte meshavad, sapas method addNewGame server seda zade shode va bazi be list ezafe mishavad.
        BaziRuyeServer bazi = new BaziRuyeServer();
        //***** negah dashtan id bazi, baraye ferestadan payam shoru be bazikonan an.
        game_That_I_Am_The_Host_ID = bazi.getIDBazi();

        //***** set kardan id host bazi ruye bazi.
        bazi.setIDHost(idClientManager);

        //***** khandan 3 reshte mozuaat, type, tedad dor, baraye sakht yek shey bazi samt server.
        String mozuat = in.readLine();
        String type = in.readLine();
        String tedadDor = in.readLine();

        //***** add kardan be list mozuaat bazi ke ba surat reshte daryaft shode ke ba - az ham joda mishavand.
        Scanner sc = new Scanner(mozuat);
        sc.useDelimiter("-");
        while (sc.hasNext()){
            bazi.addMozuBeBazi(sc.next());
        }

        //***** set kardan type bazi. 1-zudtar. 2-time(ke saat-daghighe-sanie badash miayad.)
        sc = new Scanner(type);
        sc.useDelimiter("-");
        String typee = sc.next();
        if(typee.equals("zoodtar"))
            bazi.setType("zoodtar");
        else{
            bazi.setType("time");
            bazi.setSaat(Integer.parseInt(sc.next()));
            bazi.setDaghighe(Integer.parseInt(sc.next()));
            bazi.setSanie(Integer.parseInt(sc.next()));
        }

        //***** set kardan tedad dor bazi(int).
        bazi.setTedadDor(Integer.parseInt(tedadDor));

        //***** add kardan khod mizban be list playerHaye bazi.
        bazi.addPlayerToGame(this);

        //***** ferestadn bazi sakhte shode be saever asli ta dar list bazi ha adash konad.
        ServerHolder.addNewGame(bazi);
    }

    //***** in method client manager ra ba kelid id client da list server zakhire mikonad.
    public void addClientManagerToList() throws IOException {
        ServerHolder.addClientManager(idClientManager, this);
    }

    //***** in method zamani seda zade mishavad ke client dokme start bazi ra, be hamrah yek harf baraye shoru bazi bezanad.
    public void startGameBtnWhenIAmHost() throws IOException {
        //***** harf mored nazar ra daryaft mikonad.
        char harf = (in.readLine()).charAt(0);

        //method shoru bazi ruye server seda zade mishavad ta be baghi bazikonan peyhgam shoru bazi ferestade shavad.
        ServerHolder.startGameFirst(game_That_I_Am_The_Host_ID, idClientManager, harf);

    }

    //***** in method az taraf server seda zade mishavad va harf bazi baraye in dor ra be client ersal mikonad.
    public void startGameWithTHisLetter(char harf){

        //***** ba in command be client mifahmanad ke mikhahad harf jadid ra baraye shoru bazi jadid beferestad.
        out.println("startNRound");

        //***** hala harf ra baraye client ersal mikonad.
        out.println(harf);

    }

    //***** in method be server miguyad az beyn bazikonan bazi, yek nafar baraye shoru kardan bazi entekhab shavad.
    public void DorJadid(){
        //***** id host va id bazi be method server ferestad shode ta yek nafar entekhab gardad.
        ServerHolder.choosingPlayerForStart(game_That_I_Am_The_Host_ID, idClientManager);
    }

    //***** in method be client miguyad ke nobat ust ke bazi ra shoru konad.
    public void YourTurn(){
        //***** be client pegham midahad ke safhe daraye dokme shoru ra load konad.
        out.println("YourTurn");
    }

    //***** in method be server miguyad payam shoru bazi ba in harf ra be bazikon ha beferestad.
    public void startGameBtnWhenIAmGuest() throws IOException, InterruptedException {
        //***** khandan harf va seda zadan method.
        ServerHolder.startGame(in.readLine().charAt(0), game_That_I_Join_ID);
    }

    //***** in method be clientHOST miguyad ke safhe bazi ghabli ra load konad.
    public void startGameWithTHisLetterHOST(char harf){
        //***** command
        out.println("startNRoundHOST");

        //***** ferestadan harf
        out.println(harf);
    }

    //***** in method be server miguyad be player haye bazi payam payan bedahad, sepas dokme shoru ra random be yek nafar bedahad.
    public void someoneFinishedTheGame() throws FileNotFoundException, InterruptedException {
        ServerHolder.someoneFinishedTheGame(game_That_I_Join_ID);
    }

    //***** in method be client miguyad safhe entezar ra load konad
    public void waitingPage(){
        //***** command
        out.println("waitingPage");
    }

    //***** in method be client miguyad ke javab hayash ra ersal konad.
    public void sendAllAnswers(){
        //***** command
        out.println("sendAnswers");
    }

    //***** in method java haye client ra gerefte va dar araye javab ha zakhire mikonad.
    public void getAnswers() throws IOException {
//        //***** tedad javab hara migirad.
//        int tedad = Integer.parseInt(in.readLine());
//
//        //***** be tedad ferestade shode javab daryaft karde va dar array list javab ha zakhire mikonad.
//        for(int i = 0; i< tedad; i++){
//            String javab = in.readLine();
//            javabHa.add(javab);
//            ServerHolder.print("dar method get ansers: " + javab);
//        }


        //***** alan faghat reshte javab ra mikhanad va dar araye javab zakhire mikonad.
        javabHa.add(in.readLine());
        ServerHolder.print("javab zakhire shod baraye id: " + idClientManager);
    }

    //***** in method javab zakhire sode dar khune i om ra barmigardanad.
    public String sendAnswerNumber(int i){
        return javabHa.get(i);
    }

    //***** in method be client peygham midahad ke emtiaz ra ruye safhe chap konad.
    public void chapEmtiaz(int emtiaz){
        //***** command
        out.println("chapEmtiaz");

        //***** ferestdan emtiaz karbar
        out.println(emtiaz);
    }

    //***** in method be client miguyad ke safhe dar entezar mohasebe emtiaz ra baraye karbar load konad.
    public void darEntezarMohasebeEmtiaz(){
        //***** command
        out.println("safheEntezarEmtiazHa");
    }

    //***** in method be be client miguyad javab dor akhar ra ham ke zakhire nashode ra zakhire konad.
    public void ersalJavabAkhar() throws InterruptedException {
        Thread.sleep(2000);
        //***** command
        ServerHolder.print("dar method ersal javab akhar dakhel client manager mehman:" + idClientManager);
        out.println("javabDorAkhar");
    }

    //***** in method be be client ke host ast miguyad javab dor akhar ra ham ke zakhire nashode ra zakhire konad.
    public void ersalJavabAkharHOST(){
        //***** command
        ServerHolder.print("dar method ersal javab akhar dakhel client manager host:" + idClientManager);
        out.println("javabAkharHOST");
    }

    public int idClientManager() {
        return idClientManager;
    }
}
