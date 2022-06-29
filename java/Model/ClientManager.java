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
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ferestadanEtelaatPlatformHaBeClient(){
        //***** aval payam midahad ke mikhahad list bazi hara beferestad.
        out.println("listG");

        ServerHolder.print("dakhel method");

        //***** list bazi hara az server daryaft mikonad.
        ArrayList<BaziRuyeServer> listBazi = new ArrayList<>();
        listBazi = ServerHolder.getListBaziHa();
        ServerHolder.print("size bazi ha: " + listBazi.size());

        //***** tedad bazi hara miferestad.
        out.println(listBazi.size());

        //***** hala etelaat har bazi ra miferestad. 1-mozuat besurat reshte(joda shode ba -) 2-id clientha be surat
        // reshte (joda shode ba -) 3- type bazi(agar zamani bashad(2), joda shode ba -) 4- tedad dor bazi. 5-idBazi
        for(BaziRuyeServer b : listBazi){
            ServerHolder.print("dakhel halghe");

            out.println(b.mozuatBaziBesuratReshte());
            ServerHolder.print(b.mozuatBaziBesuratReshte());

            out.println(b.idClientHaBeSuratReshte());
            ServerHolder.print(b.idClientHaBeSuratReshte());

            out.println(b.getTypeBaziBeSuratReshte());
            ServerHolder.print(b.getTypeBaziBeSuratReshte());

            out.println(b.getTedadDor());
            ServerHolder.print(Integer.toString(b.getTedadDor()));

            out.println(b.getIDBazi());
            ServerHolder.print(Integer.toString(b.getIDBazi()));
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
        ServerHolder.print("id client manager: " + idClientManager);
    }

    //***** in method zamani seda zade mishavad ke client dokme start bazi ra, be hamrah yek harf baraye shoru bazi bezanad.
    public void startGameBtnWhenIAmHost() throws IOException {
        //***** harf mored nazar ra daryaft mikonad.
        char harf = (in.readLine()).charAt(0);
        ServerHolder.print(String.valueOf(harf));

        ServerHolder.print("method start game samt server seda zade shod");
        //method shoru bazi ruye server seda zade mishavad ta be baghi bazikonan peyhgam shoru bazi ferestade shavad.
        ServerHolder.print("id host dar client manager: " + idClientManager);

        ServerHolder.startGame(game_That_I_Am_The_Host_ID, idClientManager, harf);

    }

    //***** in method az taraf server seda zade mishavad va harf bazi baraye in dor ra be client ersal mikonad.
    public void startGameWithTHisLetter(char harf){
        ServerHolder.print("dakhel method startGameWithTHisLetter");
        //***** ba in command be client mifahmanad ke mikhahad harf jadid ra baraye shoru bazi jadid beferestad.
        out.println("startNRound");

        //***** hala harf ra baraye client ersal mikonad.
        out.println(harf);

        ServerHolder.print("harf ferestade shod");
    }
}
