package sk.stuba.fei.uim.oop.hraci;

import sk.stuba.fei.uim.oop.hra.Kamen;
import sk.stuba.fei.uim.oop.hra.Policko;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Pocitac {
    private final Color farba;
    private List<Policko> poziciePolicok;
    private int pocetCiernychKamenov;
    private int rozmerPlochy;
    private int indexPridania;
    private boolean zahral;

    public Pocitac() {
        this.farba = Color.BLACK;
        this.pocetCiernychKamenov = 2;
    }

    public void tahPocitaca(List<Policko> poziciePolicok, int rozmerPlochy){
        this.zahral = false;
        this.poziciePolicok = poziciePolicok;
        this.rozmerPlochy = rozmerPlochy;
        int horeDole = 6;
        int vpravoVlavo = 1;

        if (this.rozmerPlochy == 6) horeDole = 6;
        if (this.rozmerPlochy == 8) horeDole = 8;
        if (this.rozmerPlochy == 10) horeDole = 10;
        if (this.rozmerPlochy == 12) horeDole = 12;

        for (int i = 0; i < this.rozmerPlochy*this.rozmerPlochy; i++) {
                if (this.poziciePolicok.get(i).getTypKamena().equals("Cierny")) {

                    if (!zahral) posun(-horeDole, i);
                    if (!zahral) posun(horeDole, i);

                    if (!zahral) posun(vpravoVlavo, i);
                    if (!zahral) posun(-vpravoVlavo, i);

                    if (!zahral) posun((-horeDole + 1), i);
                    if (!zahral) posun((horeDole + 1), i);
                    if (!zahral) posun((-horeDole - 1), i);
                    if (!zahral) posun((horeDole - 1), i);
                }

        }
    }

    public void setPocetCiernychKamenov(int pocetCiernychKamenov) {
        this.pocetCiernychKamenov = pocetCiernychKamenov;
    }

    public int getPocetCiernychKamenov() {
        return pocetCiernychKamenov;
    }

    public void posun( int posunKam, int pomocnyIndex){
        int kolkoBielych = 0;
        while (true) {
            if (pomocnyIndex + posunKam > (this.rozmerPlochy*this.rozmerPlochy)-1) break;
            else if (pomocnyIndex - posunKam > (this.rozmerPlochy*this.rozmerPlochy)-1) break;
            else if (pomocnyIndex + posunKam < 0) break;
            else if (pomocnyIndex - posunKam < 0) break;
            else if ((posunKam == this.rozmerPlochy) &&
                    (this.poziciePolicok.get(pomocnyIndex).suradnicaY() <= 1 || this.poziciePolicok.get(pomocnyIndex).suradnicaY() >= this.rozmerPlochy-1)) break;
            else if ((-posunKam == this.rozmerPlochy) &&
                    (this.poziciePolicok.get(pomocnyIndex).suradnicaY() <= 1 || this.poziciePolicok.get(pomocnyIndex).suradnicaY() >= this.rozmerPlochy-1)) break;
            else if ((posunKam == 1) &&
                    (this.poziciePolicok.get(pomocnyIndex).suradnicaY() == 0 || this.poziciePolicok.get(pomocnyIndex).suradnicaY() == this.rozmerPlochy)) break;
            else if ((-posunKam == 1) &&
                    (this.poziciePolicok.get(pomocnyIndex).suradnicaY() == 0 || this.poziciePolicok.get(pomocnyIndex).suradnicaY() == this.rozmerPlochy)) break;
            else if ((this.poziciePolicok.get(pomocnyIndex).suradnicaX() == 0 && this.poziciePolicok.get(pomocnyIndex).suradnicaY() <= 1
                    || this.poziciePolicok.get(pomocnyIndex).suradnicaY() < 1
                    || this.poziciePolicok.get(pomocnyIndex).suradnicaX() == this.rozmerPlochy && this.poziciePolicok.get(pomocnyIndex).suradnicaY() <= 1)
                    && (posunKam == (-rozmerPlochy - 1) || posunKam == (-rozmerPlochy + 1))) break;
            else if ((this.poziciePolicok.get(pomocnyIndex).suradnicaX() == 0 && this.poziciePolicok.get(pomocnyIndex).suradnicaY() == this.rozmerPlochy-1
                    || this.poziciePolicok.get(pomocnyIndex).suradnicaY() > (this.rozmerPlochy - 1)
                    || this.poziciePolicok.get(pomocnyIndex).suradnicaX() == rozmerPlochy && this.poziciePolicok.get(pomocnyIndex).suradnicaY() == this.rozmerPlochy-1)
                    && (posunKam == (this.rozmerPlochy - 1) || posunKam == (this.rozmerPlochy + 1))) break;
            else if (this.poziciePolicok.get(pomocnyIndex).suradnicaX() == 0 || this.poziciePolicok.get(pomocnyIndex).suradnicaX() == this.rozmerPlochy) break;
            else if (this.poziciePolicok.get(pomocnyIndex + posunKam).getTypKamena().equals("Biely")) {
                kolkoBielych += 1;
                pomocnyIndex += posunKam;
            }
            else if (this.poziciePolicok.get(pomocnyIndex + posunKam).getTypKamena().equals("Cierny")) {
                break;
            }
            else if (this.poziciePolicok.get(pomocnyIndex + posunKam).getTypKamena().equals("Ziadny")) {
                if (kolkoBielych > 0) {
                    this.poziciePolicok.get(pomocnyIndex + posunKam).removeAll();
                    Kamen kamen = new Kamen(this.farba, this.poziciePolicok.get(pomocnyIndex).getVelkostStvorca());
                    this.poziciePolicok.get(pomocnyIndex + posunKam).add(kamen, BorderLayout.CENTER);
                    this.poziciePolicok.get(pomocnyIndex + posunKam).setTypKamena("Cierny");
                    SwingUtilities.updateComponentTreeUI(this.poziciePolicok.get(pomocnyIndex + posunKam));
                    this.pocetCiernychKamenov++;
                    this.indexPridania = pomocnyIndex + posunKam;
                    this.zahral = true;
                    break;
                } else break;
            }
            else break;
        }
    }

    public int getindexPridania() {
        return indexPridania;
    }
}
