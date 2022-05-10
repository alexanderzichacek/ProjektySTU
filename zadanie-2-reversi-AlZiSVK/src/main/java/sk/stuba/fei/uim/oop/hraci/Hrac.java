package sk.stuba.fei.uim.oop.hraci;

import sk.stuba.fei.uim.oop.hra.Policko;
import sk.stuba.fei.uim.oop.hra.PripadnyTah;

import java.awt.*;
import java.util.List;

public class Hrac {
    private final Color farba;
    private List<Policko> poziciePolicok;
    private int rozmerPlochy;

    public Hrac() {
        this.farba = Color.WHITE;
    }

    public Color getFarbaHraca() {
        return farba;
    }

    public void moznePolicka(List<Policko> poziciePolicok, int rozmerPlochy) {
        this.poziciePolicok = poziciePolicok;
        this.rozmerPlochy = rozmerPlochy;
        int horeDole = 6;
        int vpravoVlavo = 1;

        if (this.rozmerPlochy == 6) horeDole = 6;
        if (this.rozmerPlochy == 8) horeDole = 8;
        if (this.rozmerPlochy == 10) horeDole = 10;
        if (this.rozmerPlochy == 12) horeDole = 12;

        for (int i = 0; i < this.rozmerPlochy * this.rozmerPlochy; i++) {
            if (this.poziciePolicok.get(i).getTypKamena().equals("Biely")) {

                posun(-horeDole, i);
                posun(horeDole, i);

                posun(vpravoVlavo, i);
                posun(-vpravoVlavo, i);

                posun((-horeDole + 1), i);
                posun((horeDole + 1), i);
                posun((-horeDole - 1), i);
                posun((horeDole - 1), i);
            }
        }
    }

    public void posun( int posunKam, int pomocnyIndex){
        int kolkoCiernych = 0;
        while (true) {
            if (pomocnyIndex + posunKam > (this.rozmerPlochy*this.rozmerPlochy)-1) break;
            else if (pomocnyIndex - posunKam > (this.rozmerPlochy*this.rozmerPlochy)-1) break;
            else if (pomocnyIndex + posunKam < 0) break;
            else if (pomocnyIndex - posunKam < 0) break;
            else if ((posunKam == this.rozmerPlochy) &&
                    (this.poziciePolicok.get(pomocnyIndex).suradnicaY() <= 1 || this.poziciePolicok.get(pomocnyIndex).suradnicaY() >= (this.rozmerPlochy - 1))) break;
            else if ((-posunKam == this.rozmerPlochy) &&
                    (this.poziciePolicok.get(pomocnyIndex).suradnicaY() <= 1 || this.poziciePolicok.get(pomocnyIndex).suradnicaY() >= (this.rozmerPlochy - 1))) break;
            else if ((posunKam == 1) &&
                    (this.poziciePolicok.get(pomocnyIndex).suradnicaY() == 0 || this.poziciePolicok.get(pomocnyIndex).suradnicaY() == this.rozmerPlochy)) break;
            else if ((-posunKam == 1) &&
                    (this.poziciePolicok.get(pomocnyIndex).suradnicaY() == 0 || this.poziciePolicok.get(pomocnyIndex).suradnicaY() == this.rozmerPlochy)) break;
            else if ((this.poziciePolicok.get(pomocnyIndex).suradnicaX() == 0 && this.poziciePolicok.get(pomocnyIndex).suradnicaY() <= 1
                    || this.poziciePolicok.get(pomocnyIndex).suradnicaY() < 1
                    || this.poziciePolicok.get(pomocnyIndex).suradnicaX() == this.rozmerPlochy && this.poziciePolicok.get(pomocnyIndex).suradnicaY() <= 1)
                    && (posunKam == (-rozmerPlochy - 1) || posunKam == (-rozmerPlochy + 1))) break;
            else if ((this.poziciePolicok.get(pomocnyIndex).suradnicaX() == 0 && this.poziciePolicok.get(pomocnyIndex).suradnicaY() == (this.rozmerPlochy - 1)
                    || this.poziciePolicok.get(pomocnyIndex).suradnicaY() > (this.rozmerPlochy - 1)
                    || this.poziciePolicok.get(pomocnyIndex).suradnicaX() == this.rozmerPlochy && this.poziciePolicok.get(pomocnyIndex).suradnicaY() == (this.rozmerPlochy - 1))
                    && (posunKam == (this.rozmerPlochy - 1) || posunKam == (this.rozmerPlochy + 1))) break;
            else if (this.poziciePolicok.get(pomocnyIndex).suradnicaX() == 0 || this.poziciePolicok.get(pomocnyIndex).suradnicaX() == this.rozmerPlochy) break;
            else if (this.poziciePolicok.get(pomocnyIndex + posunKam).getTypKamena().equals("Cierny")) {
                kolkoCiernych += 1;
                pomocnyIndex += posunKam;
            }
            else if (this.poziciePolicok.get(pomocnyIndex + posunKam).getTypKamena().equals("Biely")) {
                break;
            }
            else if (this.poziciePolicok.get(pomocnyIndex + posunKam).getTypKamena().equals("Ziadny")) {
                if (kolkoCiernych > 0) {
                    PripadnyTah pripadnyTah = new PripadnyTah(this.poziciePolicok.get(0).getVelkostStvorca());
                    this.poziciePolicok.get(pomocnyIndex + posunKam).add(pripadnyTah, BorderLayout.CENTER);
                    this.poziciePolicok.get(pomocnyIndex + posunKam).setTypKamena("PripadnyBiely");
                    break;
                } else break;
            }
            else break;
        }
    }
}