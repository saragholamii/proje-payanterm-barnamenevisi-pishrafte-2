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
            //***** sakht shey bazi va add an be list dar ClientFX
            clientFX.addBazi(new BaziSamtClient(mozuat, client, type, tedadDor));
        }
    }
}
