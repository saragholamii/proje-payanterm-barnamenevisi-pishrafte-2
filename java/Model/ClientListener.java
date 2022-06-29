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
                }





            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addGameBeClientFX() throws IOException {
        //***** aval tedad bazi hara mikhanad ta haman tedad shey bazi besazad.
        int tedadBazi = Integer.parseInt(in.readLine());
        clientFX.print("dakhel method addgame");

        for(int i = 0; i < tedadBazi; i++){
            clientFX.print("dakhel halghe");
            //***** mozuat be surat reshte
            String mozuat = in.readLine();
            clientFX.print("mozuat: " + mozuat);
            //***** clientHaye Bazi be surat reshte
            String client = in.readLine();
            clientFX.print("list client ha: " + client);
            //***** type bazi
            String type = in.readLine();
            clientFX.print("type bazi: " + type);
            //***** tedad dor
            String tedadDor = in.readLine();
            clientFX.print("tedad dor: " + tedadDor);
            //***** id bazi
            String idBazi = in.readLine();
            clientFX.print("id : " + idBazi);
            //***** sakht shey bazi va add an be list dar ClientFX
            clientFX.addBazi(new BaziSamtClient(mozuat, client, type, tedadDor, idBazi));
        }
    }

    public void shoruDorJadidBaInHarf() throws IOException {
        clientFX.print("dakhel method shoru dor jadid dar client listener");

        //***** daryaft harf baraye dor jadid.
        char harf = (in.readLine()).charAt(0);

        //***** seda zadan method clientFX ke safhe bazi ra ijad konad va ruye stage biandazad.
        clientFX.sakht_Safhe_Ba_In_Harf(harf);
        clientFX.print("seda zadan method sakht safhe ba in harf");
    }
}
