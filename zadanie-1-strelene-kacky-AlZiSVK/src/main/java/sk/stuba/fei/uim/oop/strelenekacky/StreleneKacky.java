package sk.stuba.fei.uim.oop.strelenekacky;

import sk.stuba.fei.uim.oop.druhykariet.akcnekarty.*;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Kacka;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.NeakcnaKarta;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Voda;
import sk.stuba.fei.uim.oop.druhykariet.neakcnekarty.Zameriavac;
import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StreleneKacky {
    private final List<Hrac> hraci;
    private final List<Zameriavac> zameriavace;
    private List<NeakcnaKarta> neakcneKarty;
    private int pocetHracov;
    private final List<AkcnaKarta> akcneKarty;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public StreleneKacky(){
        boolean test = false;
        while (!test){
            this.pocetHracov = KeyboardInput.readInt("Zadaj pocet hracov (2-6)");
            if (pocetHracov > 1 && pocetHracov < 7) test = true;
            else if (pocetHracov < 2) System.out.println("Minimalny pocet hracov je 2");
            else System.out.println("Maximalny pocet hracov je 6");
        }
        this.hraci = new ArrayList<>();
        int pocetKarietDoRybnika = (pocetHracov*5)+5;
        this.zameriavace = new ArrayList<>(6);
        this.neakcneKarty = new ArrayList<>(pocetKarietDoRybnika);
        this.akcneKarty = new ArrayList<>();

        for (int i = 0;i < pocetHracov;i++) {
            hraci.add(new Hrac(i+1));
        }

        for (int i = 0;i < 6;i++){
            zameriavace.add(new Zameriavac("Nezamierene"));
        }

        balikNeakcnychKariet(pocetKarietDoRybnika);
        balikAkcnychKariet();
        hranieHry();
    }

    private void hranieHry(){
        vypisRybnika();
        int indexSmrti = 0;
        while (true){
            for (int i = 0;i < pocetHracov;i++){
                if (this.hraci.get(i).getPocetZivotov() == 0){
                    for (int j = 0;j < 3;j++){
                        akcneKarty.add(hraci.get(i).getKartyNaRuke(j));
                    }
                    pocetHracov -= 1;
                    indexSmrti = i;
                }
            }

            if (this.hraci.get(indexSmrti).getPocetZivotov() == 0){
                this.hraci.remove(indexSmrti);
                indexSmrti = 0;
            }
            if (pocetHracov < 2){
                koniecHry();
                break;
            }
            else zahrajKolo();
        }
    }

    private void vypisRybnika() {
        System.out.println(ANSI_BLUE + "°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°" + ANSI_RESET);
        for (int i = 0; i < 6; i++) {
            System.out.println(ANSI_BLUE + (i + 1) + " ○ " + this.zameriavace.get(i).getZameriavac() + " | " + this.neakcneKarty.get(i).getMeno() + ANSI_RESET);
        }
        System.out.println(ANSI_BLUE + "°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°" + ANSI_RESET);
    }

    private void zahrajKolo(){
            int counterZameriavacov;
            boolean vsetkyZamierene = false;
            int kartyVystrelitNaRuke = 0;
            int kartyZamieritNaRuke = 0;
            boolean testVyberuKarty = false;
            int zahrajKartuIndex = 0;

            for (int i = 0;i < hraci.size();i++){
                if (hraci.get(i).getPocetZivotov() >= 1){
                    System.out.println("Na rade je hrac cislo "+hraci.get(i).getPoradie()+" | Pocet zivotov: "+hraci.get(i).getPocetZivotov());
                    System.out.println("Tvoje karty: ");
                    for (int j = 0;j < 3;j++){
                        System.out.println((j+1)+" ○ "+hraci.get(i).getKartyNaRuke(j).getNazovKarty());
                    }

                    System.out.println("°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°");

                    while (!testVyberuKarty){
                        zahrajKartuIndex = KeyboardInput.readInt("Zadaj cislo karty ktoru chces hrat");
                        if (zahrajKartuIndex > 0 && zahrajKartuIndex < 4) testVyberuKarty = true;
                        else System.out.println("Mas len 3 karty. Vyber jednu z nich");
                    }
                    testVyberuKarty = false;

                    counterZameriavacov = 0;
                    for (int j = 0;j < 6;j++){
                        if (this.zameriavace.get(j).getZameriavac().equals("Zamierene")) counterZameriavacov++;
                    }
                    if (counterZameriavacov == 6){
                        vsetkyZamierene = true;
                    }

                    for (int j = 0;j < 3;j++){
                        if (hraci.get(i).getKartyNaRuke(j).getNazovKarty().equals("Vystrelit")) kartyVystrelitNaRuke++;
                    }
                    if (counterZameriavacov == 0 && kartyVystrelitNaRuke < 3
                            && hraci.get(i).getKartyNaRuke(zahrajKartuIndex-1).getNazovKarty().equals("Vystrelit")){
                        while(hraci.get(i).getKartyNaRuke(zahrajKartuIndex-1).getNazovKarty().equals("Vystrelit")){
                            zahrajKartuIndex = KeyboardInput.readInt("Nemozes zahrat vystrelit. Vyber inu kartu");
                        }
                    }
                    kartyVystrelitNaRuke = 0;

                    for (int j = 0;j < 3;j++){
                        if (hraci.get(i).getKartyNaRuke(j).getNazovKarty().equals("Zamierit")) kartyZamieritNaRuke++;
                    }
                    if (vsetkyZamierene && kartyZamieritNaRuke < 3
                            && hraci.get(i).getKartyNaRuke(zahrajKartuIndex-1).getNazovKarty().equals("Zamierit")){
                        while(hraci.get(i).getKartyNaRuke(zahrajKartuIndex-1).getNazovKarty().equals("Zamierit")){
                            zahrajKartuIndex = KeyboardInput.readInt("Nemozes zahrat Zamierit. Vyber inu kartu");
                        }
                    }
                    kartyZamieritNaRuke = 0;

                    hraci.get(i).getKartyNaRuke(zahrajKartuIndex-1).zahrajKartu(hraci, neakcneKarty, zameriavace);
                    hraci.get(i).pridajKartuNaRuku(akcneKarty.get(0), zahrajKartuIndex-1);
                    akcneKarty.add(akcneKarty.get(0));
                    akcneKarty.remove(0);

                    vypisRybnika();
                }
            }
    }

    private void balikAkcnychKariet(){
        for (int i = 0;i < 10;i++) akcneKarty.add(new Zamierit());

        for (int i = 0;i < 12;i++) akcneKarty.add(new Vystrelit());

        for(int i = 0; i < 2; i++) akcneKarty.add(new DivokyBill());

        for(int i = 0; i < 6; i++) akcneKarty.add(new KacaciPochod());

        akcneKarty.add(new TurboKacka());

        for(int i = 0; i < 2; i++) akcneKarty.add(new Rosambo());

        akcneKarty.add(new KacaciTanec());

        Collections.shuffle(akcneKarty);

        for (Hrac hrac : hraci) {
            for (int j = 0; j < 3; j++) {
                hrac.setKartyNaRuke(akcneKarty.get(0));
                akcneKarty.remove(0);
            }
        }
    }

    private void balikNeakcnychKariet(int pocetNeakcnychKariet){
        int counterNaKartyKaciek = 0;
        int aktualnyPocetHracov = pocetHracov;
        this.neakcneKarty = new ArrayList<>(pocetNeakcnychKariet);
        for (int i = 0;i < 5;i++){
            this.neakcneKarty.add(new Voda());
        }
        for (int i = 5;i < pocetNeakcnychKariet;i++){
            this.neakcneKarty.add(new Kacka(aktualnyPocetHracov));
            counterNaKartyKaciek++;
            if (counterNaKartyKaciek == 5){
                counterNaKartyKaciek = 0;
                aktualnyPocetHracov -= 1;
            }
        }
        Collections.shuffle(neakcneKarty);
    }

    private void koniecHry(){
        if (pocetHracov == 1){
            for (Hrac hrac : hraci) {
                if (hrac.getPocetZivotov() > 0) {
                    System.out.println(ANSI_GREEN + "Vyhral hrac cislo " + hrac.getPoradie() + ANSI_RESET);
                }
            }
        }
    }
}
