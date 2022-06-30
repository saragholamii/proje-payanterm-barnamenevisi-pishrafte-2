package Model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;

public class ClientListener implements Runnable {
    BufferedReader in;
    ClientFX clientFX;

    ClientListener(BufferedReader in, ClientFX clientFX){
        this.in = in;
        this.clientFX = clientFX;
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
}
