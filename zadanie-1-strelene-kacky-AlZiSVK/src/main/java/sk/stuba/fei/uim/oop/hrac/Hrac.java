package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.druhykariet.akcnekarty.AkcnaKarta;

import java.util.ArrayList;
import java.util.List;

public class Hrac {
    private final int poradie;
    private int pocetZivotov;
    private final List<AkcnaKarta> kartyNaRuke;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public Hrac(int poradie){
        this.poradie = poradie;
        this.pocetZivotov = 5;
        this.kartyNaRuke = new ArrayList<>(3);
    }

    public int getPoradie() {
        return poradie;
    }

    public int getPocetZivotov() {
        return pocetZivotov;
    }

    public void setKartyNaRuke(AkcnaKarta akcnaKarta) {
        kartyNaRuke.add(akcnaKarta);
    }

    public void pridajKartuNaRuku(AkcnaKarta akcnaKarta, int index){
        kartyNaRuke.set(index, akcnaKarta);
    }

    public AkcnaKarta getKartyNaRuke(int index) {
        return kartyNaRuke.get(index);
    }

    public void strataZivota(){
        System.out.println(ANSI_RED + "Hrac "+this.poradie+" prisiel o kacku." + ANSI_RESET);
        if (this.pocetZivotov-1 == 0){
            this.pocetZivotov -= 1;
            System.out.println(ANSI_RED + "Hracovi "+this.poradie+" zomreli vsetky kacky. Vypadava z hry." + ANSI_RESET);
        }
        else{
            this.pocetZivotov -=1;
        }
    }
}

