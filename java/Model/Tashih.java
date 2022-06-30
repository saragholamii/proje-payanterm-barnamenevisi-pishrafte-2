package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Tashih {

    //***** in method check mikonad aya javab ha dar file ha hastan ya na
    public static int[] ayaDarFileHast(ArrayList<String> javabHa, String mozuat) throws FileNotFoundException {
        int[] emtiazHa = new int[javabHa.size()];
        Scanner sc = new Scanner(mozuat);
        sc.useDelimiter("-");
        Scanner scAnswer;

        //***** baraye javab hameye bazikonan in ravan anjam mishavad.
        for(int i = 0; i < javabHa.size(); i++){
            //***** javab bazikon ra be scanner midahim ta pardazash shavad.
            scAnswer = new Scanner(javabHa.get(i));
            scAnswer.useDelimiter("-");

            while(sc.hasNext()){

                //***** mozu khande mishavad.
                String mozu = sc.next();
                String javab = scAnswer.next();

                //***** check mishavad ba har kodam az mozuat barabar bud, method an mozu be hamrah kalame khande shode az reshte javab ha seda zade mishavad.
                switch (mozu){
                    case "esm":
                        //***** check mishvad agar javab reshte khali nabashad dakhel file donbal az migardad.
                        //***** method esm seda zade shode ta check shavad kalame dar file hast ya na
                        if(!javab.equalsIgnoreCase("") && esm(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "famil":
                        if(!javab.equalsIgnoreCase("") && famil(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "ghaza":
                        if(!javab.equalsIgnoreCase("") && ghaza(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "gol":
                        if(!javab.equalsIgnoreCase("") && gol(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "heyvan":
                        if(!javab.equalsIgnoreCase("") && heyvan(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "keshvar":
                        if(!javab.equalsIgnoreCase("") && keshvar(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "mashin":
                        if(!javab.equalsIgnoreCase("") && mashin(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "mive":
                        if(!javab.equalsIgnoreCase("") && mive(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "pushak":
                        if(!javab.equalsIgnoreCase("") && pushak(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "shahr":
                        if(!javab.equalsIgnoreCase("") && shahr(javab)){ emtiazHa[i] += 10; }
                        break;
                    case "ashya":
                        if(!javab.equalsIgnoreCase("") && ashya(javab)){ emtiazHa[i] += 10; }
                        break;
                }
            }

        }

        return emtiazHa;
    }

    private static boolean ashya(String javab) throws FileNotFoundException {
        //***** file ashya ra baz mikonad va dakhel an donbal kalame migardad
        File ashya = new File("/words/ashya.txt");
        Scanner sc = new Scanner(ashya);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean shahr(String javab) throws FileNotFoundException {
        //***** file shahr ra baz mikonad va dakhel an donbal kalame migardad
        File shahr = new File("/words/shahr.txt");
        Scanner sc = new Scanner(shahr);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean pushak(String javab) throws FileNotFoundException {
        //***** file pushak ra baz mikonad va dakhel an donbal kalame migardad
        File pushak = new File("/words/pushak.txt");
        Scanner sc = new Scanner(pushak);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean mive(String javab) throws FileNotFoundException {
        //***** file mive ra baz mikonad va dakhel an donbal kalame migardad
        File mive = new File("/words/mive.txt");
        Scanner sc = new Scanner(mive);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean mashin(String javab) throws FileNotFoundException {
        //***** file mashin ra baz mikonad va dakhel an donbal kalame migardad
        File mashin = new File("/words/mashin.txt");
        Scanner sc = new Scanner(mashin);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean keshvar(String javab) throws FileNotFoundException {
        //***** file keshvar ra baz mikonad va dakhel an donbal kalame migardad
        File keshvar = new File("/words/keshvar.txt");
        Scanner sc = new Scanner(keshvar);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean heyvan(String javab) throws FileNotFoundException {
        //***** file heyvan ra baz mikonad va dakhel an donbal kalame migardad
        File heyvan = new File("/words/heyvan.txt");
        Scanner sc = new Scanner(heyvan);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean gol(String javab) throws FileNotFoundException {
        //***** file gol ra baz mikonad va dakhel an donbal kalame migardad
        File gol = new File("/words/gol.txt");
        Scanner sc = new Scanner(gol);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean ghaza(String javab) throws FileNotFoundException {
        //***** file ghaza ra baz mikonad va dakhel an donbal kalame migardad
        File ghaza = new File("/words/ghaza.txt");
        Scanner sc = new Scanner(ghaza);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean famil(String javab) throws FileNotFoundException {
        //***** file famil ra baz mikonad va dakhel an donbal kalame migardad
        File famil = new File("/words/famil.txt");
        Scanner sc = new Scanner(famil);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }

    private static boolean esm(String javab) throws FileNotFoundException {
        //***** file esm ra baz mikonad va dakhel an donbal kalame migardad
        File esm = new File("/words/esm.txt");
        Scanner sc = new Scanner(esm);

        //***** ta akhar file khat be khat donbal kalame migardad.
        while (sc.hasNext()){
            if(javab.equals(sc.next())) { return true; }
        }

        //***** agar az halghe birun biayad yani kalame peida nashode ast.
        return false;
    }
}
