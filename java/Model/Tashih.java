package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tashih {

    Server server;
    Tashih(Server server){
        this.server = server;
    }

    //***** in method check mikonad aya javab ha dar file ha hastan ya na
    public int[] ayaDarFileHast(ArrayList<String> javabHa, String mozuat) throws FileNotFoundException {

        int[] emtiazHa = new int[javabHa.size()];
        Scanner sc;
        Scanner scAnswer;

        server.print("tedad sherkat konandegan: " + javabHa.size());

        //***** baraye javab hameye bazikonan in ravan anjam mishavad.
        for(int i = 0; i < javabHa.size(); i++){

            server.print("nafar " + i);
            server.print("javab: " + javabHa.get(i));

            //***** mozuat baraye har bazikon dobare bayad be scanner dade shavad ta az aval itrate va moghayese shavad.
            sc = new Scanner(mozuat);
            sc.useDelimiter("-");

            //***** javab bazikon ra be scanner midahim ta pardazash shavad.
            scAnswer = new Scanner(javabHa.get(i));
            scAnswer.useDelimiter("-");

            while(sc.hasNext()){

                //***** mozu khande mishavad.
                String mozu = sc.next();
                server.print("mozu: " + mozu);
                String javab = scAnswer.next();
                server.print("javab: " + javab);

                //***** check mishavad ba har kodam az mozuat barabar bud, method an mozu be hamrah kalame khande shode az reshte javab ha seda zade mishavad.
                switch (mozu){
                    case "esm":
                        server.print("dakhel block esm");
                        //***** check mishvad agar javab reshte khali nabashad dakhel file donbal az migardad.
                        //***** method esm seda zade shode ta check shavad kalame dar file hast ya na
                        if(!javab.equalsIgnoreCase("hich") && esm(javab)){
                            server.print("dakhel shart esm");
                            emtiazHa[i] += 10; }
                        break;
                    case "famil":
                        server.print("dakhel block famil");
                        if(!javab.equalsIgnoreCase("hich") && famil(javab)){
                            server.print("dakhel sart famil");
                            emtiazHa[i] += 10; }
                        break;
                    case "ghaza":
                        if(!javab.equalsIgnoreCase("hich") && ghaza(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "gol":
                        if(!javab.equalsIgnoreCase("hich") && gol(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "heyvan":
                        if(!javab.equalsIgnoreCase("hich") && heyvan(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "keshvar":
                        if(!javab.equalsIgnoreCase("hich") && keshvar(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "mashin":
                        if(!javab.equalsIgnoreCase("hich") && mashin(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "mive":
                        if(!javab.equalsIgnoreCase("hich") && mive(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "pushak":
                        if(!javab.equalsIgnoreCase("hich") && pushak(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "shahr":
                        if(!javab.equalsIgnoreCase("hich") && shahr(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "ashya":
                        if(!javab.equalsIgnoreCase("hich") && ashya(javab)){ emtiazHa[i] += 10; }
                        break;
                }
            }

        }

        return emtiazHa;
    }

    private boolean ashya(String javab) throws FileNotFoundException {
        //***** file ashya ra baz mikonad va dakhel an donbal kalame migardad
        File ashya = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\ashya.txt");
        Scanner sc = new Scanner(ashya);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean shahr(String javab) throws FileNotFoundException {
        //***** file shahr ra baz mikonad va dakhel an donbal kalame migardad
        File shahr = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\shahr.txt");
        Scanner sc = new Scanner(shahr);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean pushak(String javab) throws FileNotFoundException {
        //***** file pushak ra baz mikonad va dakhel an donbal kalame migardad
        File pushak = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\pushak.txt");
        Scanner sc = new Scanner(pushak);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean mive(String javab) throws FileNotFoundException {
        //***** file mive ra baz mikonad va dakhel an donbal kalame migardad
        File mive = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\mive.txt");
        Scanner sc = new Scanner(mive);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean mashin(String javab) throws FileNotFoundException {
        //***** file mashin ra baz mikonad va dakhel an donbal kalame migardad
        File mashin = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\mashin.txt");
        Scanner sc = new Scanner(mashin);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean keshvar(String javab) throws FileNotFoundException {
        //***** file keshvar ra baz mikonad va dakhel an donbal kalame migardad
        File keshvar = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\keshvar.txt");
        Scanner sc = new Scanner(keshvar);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean heyvan(String javab) throws FileNotFoundException {
        //***** file heyvan ra baz mikonad va dakhel an donbal kalame migardad
        File heyvan = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\heyvan.txt");
        Scanner sc = new Scanner(heyvan);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean gol(String javab) throws FileNotFoundException {
        //***** file gol ra baz mikonad va dakhel an donbal kalame migardad
        File gol = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\gol.txt");
        Scanner sc = new Scanner(gol);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean ghaza(String javab) throws FileNotFoundException {
        //***** file ghaza ra baz mikonad va dakhel an donbal kalame migardad
        File ghaza = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\ghaza.txt");
        Scanner sc = new Scanner(ghaza);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean famil(String javab) throws FileNotFoundException {
        //***** file famil ra baz mikonad va dakhel an donbal kalame migardad
        File famil = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\famil.txt");
        Scanner sc = new Scanner(famil);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private boolean esm(String javab) throws FileNotFoundException {
        server.print("dakhel method esm");

        //***** file esm ra baz mikonad va dakhel an donbal kalame migardad
        File esm = new File("C:\\Users\\RaYa\\IdeaProjects\\FinalProjectPishrafte2\\src\\main\\java\\Model\\words\\esm.txt");
        Scanner sc = new Scanner(esm);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) {
                server.print("esm peida shod.");
                return true;
            }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        server.print("esm peida nashod");
        return false;
    }

    //***** in method yek araye az reshte ra migirad, ruye har kalame har reshte, baghi reshte hara check mikonad ke an kalame ra darand ya na.
    public int[] ayaYeksanNeveshteAnd(ArrayList<String> javabHayeDor){
        int[] emtiazHa = new int[javabHayeDor.size()];

        //tarif 2 scanner baraye itrate kardan reshte javab Ha
        Scanner sc1;
        Scanner sc2;

        //2 halghe baraye moghayese har yek az reshte ha ba hameye reshte haye digar.
        for(int i = 0; i < javabHayeDor.size(); i++){
            for(int j = 0; j < javabHayeDor.size(); j++){

                //***** agar be reshte haye barabr resid in marhale ra rad konad.
                if(i == j) continue;

                //meghdar dehi scanner ha
                sc1 = new Scanner(javabHayeDor.get(i));
                sc1.useDelimiter("-");
                sc2 = new Scanner(javabHayeDor.get(j));
                sc2.useDelimiter("-");

                //***** hala tak tak kalamat 2 scanner ra check mikonad.
                while(sc1.hasNext()){
                    String s1 = sc1.next();
                    String s2 = sc2.next();

                    //***** check mishavad agar khali budand az emtiaz kam nemikonad chon ghablan emtiaz dade nashode ast.
                    if(s1.equalsIgnoreCase("hich") || s2.equalsIgnoreCase("hich")){ continue; }

                    //***** hala ke khali nistand, check mikonad agar barabar bashand az emtiazeshan kam mikonad.
                    if(s1.equalsIgnoreCase(s2)) { emtiazHa[i] -= 5;}
                }
            }
        }

        //***** emtiaz hara barmigardanad.
        return emtiazHa;
    }
}
