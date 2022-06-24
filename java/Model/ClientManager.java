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
        // reshte (joda shode ba -) 3- type bazi(agar zamani bashad(2), joda shode ba -) 4- tedad dor bazi.
        for(BaziRuyeServer b : listBazi){
            out.println(b.mozuatBaziBesuratReshte());
            out.println(b.idClientHaBeSuratReshte());
            out.println(b.getTypeBaziBeSuratReshte());
            out.println(b.getTedadDor());
        }
    }

    public void joinBePlatform(){
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

        //***** ferestadn bazi sakhte shode be saever asli ta dar list bazi ha adash konad.
        ServerHolder.addNewGame(bazi);
    }

    //***** in method client manager ra ba kelid id client da list server zakhire mikonad.
    public void setID() throws IOException {
        int id = Integer.parseInt(in.readLine());
        out.println(id);
        ServerHolder.addClientManager(id, this);
    }
}
