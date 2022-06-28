package Model;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientManager implements Runnable{
    Socket socketClient;
    Server ServerHolder;
    DataInputStream in;
    PrintWriter out;
    int game_That_I_Am_The_Host_ID;
    int game_That_I_Join_ID;
    int idClient;

    public ClientManager(Server server, Socket socket) {
        socketClient = socket;
        ServerHolder = server;
    }

    @Override
    public void run() {
        try {

            in = new DataInputStream(socketClient.getInputStream());
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
                    case "setID":
                        setID();
                        break;
                    case "startG":
                        startGameBtnWhenIAmHost();
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

        //***** list bazi hara az server daryaft mikonad.
        ArrayList<BaziRuyeServer> listBazi = new ArrayList<>();
        listBazi = ServerHolder.getListBaziHa();

        //***** tedad bazi hara miferestad.
        out.println(Integer.toString(listBazi.size()));

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
        int typee = Integer.parseInt(sc.next());
        if(typee == 1)
            bazi.setType(1);
        else{
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
    public void setID() throws IOException {
        int id = Integer.parseInt(in.readLine());
        out.println(id);
        idClient = id;
        ServerHolder.addClientManager(id, this);
    }

    //***** in method zamani seda zade mishavad ke client dokme start bazi ra, be hamrah yek harf baraye shoru bazi bezanad.
    public void startGameBtnWhenIAmHost() throws IOException {
        //***** harf mored nazar ra daryaft mikonad.
        char harf = (in.readLine()).charAt(0);

        //method shoru bazi ruye server seda zade mishavad ta be baghi bazikonan peyhgam shoru bazi ferestade shavad.
        ServerHolder.startGame(game_That_I_Am_The_Host_ID, idClient, harf);
    }

    //***** in method az taraf server seda zade mishavad va harf bazi baraye in dor ra be client ersal mikonad.
    public void startGameWithTHisLetter(char harf){
        //***** ba in command be client mifahmanad ke mikhahad harf jadid ra baraye shoru bazi jadid beferestad.
        out.println("startNRound");

        //***** hala harf ra baraye client ersal mikonad.
        out.println(harf);
    }
}
