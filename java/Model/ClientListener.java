package Model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ClientListener implements Runnable {
    BufferedReader in;
    ClientFX clientFX;
    Client client;

    ClientListener(BufferedReader in, ClientFX clientFX, Client client){
        this.in = in;
        this.clientFX = clientFX;
        this.client = client;
    }

    @Override
    public void run() {

        while(true){
            //***** az in be bad, client hame chiz ra inja daryaft mikonad.
            try {
                String cammand = in.readLine();
                switch (cammand) {

                    //***** yani mikhahad list bazi hara bafrestad.
                    case "listG" :
                        addGameBeClientFX();
                        break;
                    //***** yani mikhahad harf shoru dor jadid ra beferestad.
                    case "startNRound":
                        shoruDorJadidBaInHarf();
                        break;
                    //***** yani nobat mast ke bazi ra shoru konim.
                    case "YourTurn":
                        myTurn();
                        break;
                    //***** yai harf shoru konande ra baraye host miferestad.
                    case "startNRoundHOST":
                        shoruDorJadidBaInHarfHOST();
                        break;
                    //***** yani safhe entezar ra load kon
                    case "waitingPage":
                        waitingPage();
                        break;
                    //***** yani javab hara ersal kon.
                    case "sendAnswers":
                        sendAnswers();
                        break;
                    //***** yani mikhahad emtiaz karbar ra beferestad.
                    case "chapEmtiaz":
                        chapEmtiaz();
                        break;
                    //***** yani safhe dar entezar mohasebe emtiaz ra chap kon
                    case "safheEntezarEmtiazHa":
                        safheEntezarEmtiaz();
                        break;
                    //***** yani javab dor akhar ra ke zakhire nashode zakhire kon.
                    case "javabDorAkhar":
                        zakhireJavabAkhar();
                        break;
                    //***** yani javab akhar host ra ke zakhire nashode zakhire kon
                    case "javabAkharHOST":
                        zakhireJavabAkharHOST();
                        break;
                }





            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addGameBeClientFX() throws IOException {
        //***** aval tedad bazi hara mikhanad ta haman tedad shey bazi besazad.
        int tedadBazi = Integer.parseInt(in.readLine());

        for(int i = 0; i < tedadBazi; i++){
            //***** mozuat be surat reshte
            String mozuat = in.readLine();
            //***** clientHaye Bazi be surat reshte
            String client = in.readLine();
            //***** type bazi
            String type = in.readLine();
            //***** tedad dor
            String tedadDor = in.readLine();
            //***** id bazi
            String idBazi = in.readLine();
            //***** sakht shey bazi va add an be list dar ClientFX
            clientFX.addBazi(new BaziSamtClient(mozuat, client, type, tedadDor, idBazi));
        }
    }

    public void shoruDorJadidBaInHarf() throws IOException {

        //***** daryaft harf baraye dor jadid.
        char harf = (in.readLine()).charAt(0);

        //***** seda zadan method clientFX ke safhe bazi ra ijad konad va ruye stage biandazad.
        clientFX.sakht_Safhe_Ba_In_Harf(harf);
    }

    public void myTurn(){
        //***** seda zadan method my turn dar clientFX.
        clientFX.print("dakhel method my turn");
        clientFX.myTurn();
    }

    public void shoruDorJadidBaInHarfHOST() throws IOException {
        //***** khandan harf
        char harf = in.readLine().charAt(0);

        //***** seda zadan method load kardan safhe bazi baraye host
        clientFX.sakht_Safhe_Ba_In_Harf_HOST(harf);
    }

    public void waitingPage(){
        //***** seda zadan methodi ke safhe entezar ra load mikonad.
        clientFX.waitingPage();
    }

    public void sendAnswers(){
        //***** methodi dar client ra seda mizanad ke javab hara baraye server ersal konad.
        client.sendAnswers();
    }

    public void chapEmtiaz() throws IOException {
        //***** khandan emtiaz
        int emtiaz = Integer.parseInt(in.readLine());

        //***** seda zadan methodi az clientFX ke safhe emtiaz ra load mikonad.
        clientFX.chapEmtiaz(emtiaz);
    }

    public void safheEntezarEmtiaz(){
        //***** seda zadan methodi az clientFX ke safhe dar entezar emtiaz ra load mokonad.
        clientFX.safheEntezarEmtiaz();
    }

    public void zakhireJavabAkhar(){
        //***** methodi az clientFX seda zade mishavad ke javab zakhire shode dar guestController ra ba javab haye client add mikonad.
        clientFX.zakhireJavabAkhar();
    }

    public void zakhireJavabAkharHOST(){
        //***** methodi az clientFX seda zade mishavad ke javab zakhire shode dar hostController ra ba javab haye client add mikonad.
        clientFX.zakhireJavabAkharHost();
    }

}
