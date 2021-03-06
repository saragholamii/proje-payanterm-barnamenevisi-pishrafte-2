package Model;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    BufferedReader in;
    PrintWriter out;
    ClientFX clientGUI;
    ClientListener listener;

    ArrayList<String> javabHa = new ArrayList<>();

    public Client(ClientFX c){
        //****** baraye inke be field haye GUI dastresi dashte bashim yek eshare gar be an ra inja gharar midahim.
        clientGUI = c;
    }

    public void start() throws IOException {

        InetAddress serverIP = InetAddress.getByName("127.0.0.1");
        Socket socket = new Socket(serverIP, Server.PORT);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        //***** be listener clientGUI ham dade shod ta rahattar betavanad method haye an ra seda bezanad va panjere ra taghir dahad.
        listener = new ClientListener(in, clientGUI, this);

        //***** az in be bad, client hameye daryafti hayash az server ra dar listener migirad.
        Thread t = new Thread(listener);
        t.start();

        //*****  vahgti client sakhte mishavad ab client manager dastur midahad ke khodash ra dar list client ha dar server zakhire konad.
        out.println("addClientManager");
    }

    public void DaryaftListBaziHayeDarhalEjra(){
        //***** be srver payam midahad ke list baziHayi ke az ghabl ijad shode and ra beferestad.
        out.println("ListG");
    }

    public void sakhtBazi(String mozuatBazi, String typeBazi, String tedadDor){
        //***** be server payam midahad ke mikhahad yak bazi jadid dorost konad.
        //***** sepas moshakhasat bazi ra be surat yek reshte be server miferestad.
        //***** server ba in moshakhasat yek shey az class bazi misazad.
        //***** sepas shey sakhte shode be list bazi ha dar server asli add mishavad.
        out.println("NewG");
        //***** aval mozuat Bazi ersal mishavad ke ba khat fasele az ham joda shode and.
        out.println(mozuatBazi);
        /***** sepas type bazi ersal mishavad. 1- har ki zudtar tamam kard. 2- time moshakhas( agar time bashad, saat-daghighe-sanie
        * badash amade ast)*/
        out.println(typeBazi);
        //***** sepas tedad dor bazi ersal mishavad.
        out.println(tedadDor);
    }

    public void addShodanBeBazi(int idBazi){
        //***** be server payam midahad ke mikhahad be yek bazi add shavad.
        out.println("JoinG");
        //***** sepas id bazi mored nazar ra miferestad.
        out.println(idBazi);
    }

    public void startGameAzSamtMizban(char harf){
        //***** be server mifahmanad ke mikhahad bazi ra shoru konad.
        out.println("startGWhenIAmHost");

        //***** harf shoru konande bazi ra baraye server ersal mikonad.
        out.println(harf);


    }

    //***** in method baraye shoru dor jadid ast.
    public void DJadid(){
        //***** be server payam midahad dor ghabli tamam shode ast.
        out.println("NDor");
    }

    //***** in method be server miguyad be hame bazikon ha payam shoru bazi ba in harf ra bedahad.
    public void startGameAzSamtMehman(char harf){
        //***** farman shoru bazi jadid
        out.println("startGameWhenIAmGuest");

        //***** harf shoru konande bazi ra baraye server ersal mikonad.
        out.println(harf);
    }

    //***** in method be server payam midahad bazi tavasot yeki tamam shod, be baghie aza in peygham ra bede
    public void dorTamamShod(){
        //***** command
        out.println("SomeoneFinished");
    }

    //***** in method be server javab hara ersal mikonad.
    public void sendAnswers(){
        //***** command
        out.println("AnswersComming");

        //***** tedad javab hara ersal mikonad
        out.println(javabHa.size());

        //***** khod javab hara ersal mikonad.
        clientGUI.print("dakhel method send answer client");
        for(String s : javabHa){
            clientGUI.print(s);
            out.println(s);
        }
    }

    //***** in method javab haye bazi ra be clietn manager miferestad
    public void addJavab(String javab){
        clientGUI.print("dar method add javab, javab: " + javab);
        //***** command
        out.println("JavabYekDor");

        clientGUI.print("dar method add javab, baz az command");
        //***** ferestadan javab
        out.println(javab);

        clientGUI.print("dar method add javab, bad az ferestadan javab");

    }

    //***** in method be server miguyad host mikhahad az bazi kharej shavad
    public void exitFromGameHost(){
        //***** command
        out.println("exiteGameHost");
    }

    //***** in method be server miguyad yek bazikon mikhahad az bazi kharej shavad.
    public void exitFromGame(){
        //***** command
        out.println("exitGameGuest");
    }

}
