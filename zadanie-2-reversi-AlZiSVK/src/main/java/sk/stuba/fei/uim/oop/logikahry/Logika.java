package sk.stuba.fei.uim.oop.logikahry;

import sk.stuba.fei.uim.oop.hra.Kamen;
import sk.stuba.fei.uim.oop.hra.Policko;
import sk.stuba.fei.uim.oop.hraci.Hrac;
import sk.stuba.fei.uim.oop.hraci.Pocitac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class Logika implements MouseListener {
    private final List<Policko> poziciePolicok;
    private final int rozmerPlochy;
    private final Hrac hrac;
    private final Pocitac pocitac;
    private int pocetBielychKamenov;
    private final int pocetCiernychKamenov;
    private boolean prekreslilSupera;
    private boolean prekreslilHraca;
    private final JLabel priebehLabel;
    private Color povodnaFarba;

    public Logika(List<Policko> poziciePolicok, int rozmerPlochy, JLabel priebehLabel) {
        this.prekreslilSupera = false;
        this.prekreslilHraca = false;
        this.poziciePolicok = poziciePolicok;
        this.rozmerPlochy = rozmerPlochy;
        this.pocetBielychKamenov = 2;
        this.pocetCiernychKamenov = 2;
        this.hrac = new Hrac();
        this.pocitac = new Pocitac();
        this.priebehLabel = priebehLabel;

        for(int i = 0; i < rozmerPlochy*rozmerPlochy; i++){
            this.poziciePolicok.get(i).addMouseListener(this);
        }
        for(int i = 0; i < rozmerPlochy*rozmerPlochy; i++){
            this.poziciePolicok.get(i).setTypKamena("Ziadny");
        }

    }

    public int getPocetCiernychKamenov() {
        return pocetCiernychKamenov;
    }

    public int getPocetBielychKamenov() {
        return pocetBielychKamenov;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (int k = 0;k < this.rozmerPlochy*this.rozmerPlochy;k++){
            if (e.getSource() == this.poziciePolicok.get(k)){
                if (this.poziciePolicok.get(k).getTypKamena().equals("PripadnyBiely")){
                    int pomocneBiele = this.pocetBielychKamenov;
                    int horeDole = 6;
                    int vpravoVlavo = 1;
                    this.prekreslilSupera = false;
                    this.prekreslilHraca = false;

                    if (this.rozmerPlochy == 6) horeDole = 6;
                    if (this.rozmerPlochy == 8) horeDole = 8;
                    if (this.rozmerPlochy == 10) horeDole = 10;
                    if (this.rozmerPlochy == 12) horeDole = 12;

                    for (int i = 0;i < this.rozmerPlochy*this.rozmerPlochy;i++){
                        if (e.getSource() == this.poziciePolicok.get(i)){
                            if (this.poziciePolicok.get(i).getTypKamena().equals("PripadnyBiely")){
                                Kamen kamen = new Kamen(hrac.getFarbaHraca(), this.poziciePolicok.get(i).getVelkostStvorca());
                                this.poziciePolicok.get(i).add(kamen, BorderLayout.CENTER);
                                SwingUtilities.updateComponentTreeUI(this.poziciePolicok.get(i));
                                this.pocetBielychKamenov += 1;
                                this.poziciePolicok.get(i).setTypKamena("Biely");
                            }
                        }
                    }

                    for (int i = 0; i < this.rozmerPlochy*this.rozmerPlochy; i++) {
                        if (this.poziciePolicok.get(i).getTypKamena().equals("PripadnyBiely")) {
                            this.poziciePolicok.get(i).removeAll();
                            this.poziciePolicok.get(i).setTypKamena("Ziadny");
                            SwingUtilities.updateComponentTreeUI(this.poziciePolicok.get(i));
                        }
                    }

                    for (int i = 0; i < this.rozmerPlochy * this.rozmerPlochy; i++) {
                        if (this.poziciePolicok.get(i).getTypKamena().equals("Biely")) {

                            if (!this.prekreslilSupera) prekreslenieSupera(-horeDole, i);
                            if (!this.prekreslilSupera) prekreslenieSupera(horeDole, i);

                            if (!this.prekreslilSupera) prekreslenieSupera(vpravoVlavo, i);
                            if (!this.prekreslilSupera) prekreslenieSupera(-vpravoVlavo, i);

                            if (!this.prekreslilSupera) prekreslenieSupera((-horeDole + 1), i);
                            if (!this.prekreslilSupera) prekreslenieSupera((horeDole + 1), i);
                            if (!this.prekreslilSupera) prekreslenieSupera((-horeDole - 1), i);
                            if (!this.prekreslilSupera) prekreslenieSupera((horeDole - 1), i);
                        }
                    }

                    if (pomocneBiele != pocetBielychKamenov) pocitac.tahPocitaca(poziciePolicok, rozmerPlochy);

                    for (int i = 0; i < this.rozmerPlochy * this.rozmerPlochy; i++) {
                        if (this.poziciePolicok.get(i).getTypKamena().equals("Cierny")) {

                            if (!this.prekreslilHraca) prekreslenieHraca(-horeDole);
                            if (!this.prekreslilHraca) prekreslenieHraca(horeDole);

                            if (!this.prekreslilHraca) prekreslenieHraca(vpravoVlavo);
                            if (!this.prekreslilHraca) prekreslenieHraca(-vpravoVlavo);

                            if (!this.prekreslilHraca) prekreslenieHraca((-horeDole + 1));
                            if (!this.prekreslilHraca) prekreslenieHraca((horeDole + 1));
                            if (!this.prekreslilHraca) prekreslenieHraca((-horeDole - 1));
                            if (!this.prekreslilHraca) prekreslenieHraca((horeDole - 1));
                        }
                    }

                    this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);

                    int finalnyPocetBielych = 0;
                    int finalnyPocetCiernych = 0;
                    for (int i = 0;i < this.rozmerPlochy*this.rozmerPlochy;i++){
                        if (this.poziciePolicok.get(i).getTypKamena().equals("Biely")) finalnyPocetBielych += 1;
                        if (this.poziciePolicok.get(i).getTypKamena().equals("Cierny")) finalnyPocetCiernych += 1;
                    }
                    this.priebehLabel.setText("Hrac: " + finalnyPocetBielych + " | Pocitac: " + finalnyPocetCiernych);

                    int pocetMoznych = this.rozmerPlochy*this.rozmerPlochy;
                    for (int i = 0;i < this.rozmerPlochy*this.rozmerPlochy;i++){
                        if (!this.poziciePolicok.get(i).getTypKamena().equals("PripadnyBiely")) pocetMoznych -= 1;
                    }

                    if (pocetMoznych == 0 && finalnyPocetBielych > finalnyPocetCiernych)
                        this.priebehLabel.setText("Vyhral hrac s poctom kamenov: " + finalnyPocetBielych);
                    else if (pocetMoznych == 0 && finalnyPocetBielych < finalnyPocetCiernych)
                        this.priebehLabel.setText("Vyhral pocitac s poctom kamenov: " + finalnyPocetCiernych);
                }
            }
        }
    }

    public void setPriebehLabel(String priebeh) {
        this.priebehLabel.setText(priebeh);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        for (int i = 0;i < this.rozmerPlochy*rozmerPlochy;i++){
            if (e.getSource() == this.poziciePolicok.get(i)){
                this.povodnaFarba = this.poziciePolicok.get(i).getBackground();
                this.poziciePolicok.get(i).setBackground(Color.LIGHT_GRAY);
                SwingUtilities.updateComponentTreeUI(this.poziciePolicok.get(i));
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        for (int i = 0;i < this.rozmerPlochy*rozmerPlochy;i++){
            if (e.getSource() == this.poziciePolicok.get(i)){
                this.poziciePolicok.get(i).setBackground(this.povodnaFarba);
                SwingUtilities.updateComponentTreeUI(this.poziciePolicok.get(i));
            }
        }
    }

    public void prekreslenieSupera(int posunKam, int indexKliku){
        int pocetNaPrekreslenie = 0;
        int pomocnyIndex = indexKliku;

        while (true){
            if (indexKliku + posunKam > (this.rozmerPlochy*this.rozmerPlochy)-1) break;
            if (indexKliku - posunKam > (this.rozmerPlochy*this.rozmerPlochy)-1) break;
            if (indexKliku + posunKam < 0) break;
            if (indexKliku - posunKam < 0) break;
            else if ((this.poziciePolicok.get(indexKliku).suradnicaX() == 0 && this.poziciePolicok.get(indexKliku).suradnicaY() <= 1
                    || this.poziciePolicok.get(indexKliku).suradnicaY() < 1
                    || this.poziciePolicok.get(indexKliku).suradnicaX() == this.rozmerPlochy && this.poziciePolicok.get(indexKliku).suradnicaY() <= 1)
                    && (posunKam == (-rozmerPlochy - 1) || posunKam == (-rozmerPlochy + 1))) break;
            else if ((this.poziciePolicok.get(indexKliku).suradnicaX() == 0 && this.poziciePolicok.get(indexKliku).suradnicaY() == (this.rozmerPlochy - 1)
                    || this.poziciePolicok.get(indexKliku).suradnicaY() > rozmerPlochy-1
                    || this.poziciePolicok.get(indexKliku).suradnicaX() == rozmerPlochy && this.poziciePolicok.get(indexKliku).suradnicaY() == (this.rozmerPlochy - 1))
                    && (posunKam == (this.rozmerPlochy - 1) || posunKam == (this.rozmerPlochy + 1))) break;
            else if (this.poziciePolicok.get(indexKliku).suradnicaX() == 0 || this.poziciePolicok.get(indexKliku).suradnicaX() == this.rozmerPlochy) break;

            else if (this.poziciePolicok.get(indexKliku + posunKam).getTypKamena().equals("Cierny")) {
                pocetNaPrekreslenie += 1;
                indexKliku += posunKam;
            }
            else if (this.poziciePolicok.get(indexKliku + posunKam).getTypKamena().equals("Biely")) {
                if (pocetNaPrekreslenie > 0){
                    this.poziciePolicok.get(indexKliku).removeAll();
                    Kamen kamen = new Kamen(hrac.getFarbaHraca(), this.poziciePolicok.get(0).getVelkostStvorca());
                    this.poziciePolicok.get(indexKliku).add(kamen, BorderLayout.CENTER);
                    SwingUtilities.updateComponentTreeUI(this.poziciePolicok.get(indexKliku));
                    this.pocetBielychKamenov++;
                    this.pocitac.setPocetCiernychKamenov(this.pocitac.getPocetCiernychKamenov()-1);
                    this.poziciePolicok.get(indexKliku).setTypKamena("Biely");
                }
                indexKliku -= posunKam;
                if (indexKliku == pomocnyIndex) {
                    this.prekreslilSupera = true;
                    break;
                }
            }
            else if (this.poziciePolicok.get(indexKliku + posunKam).getTypKamena().equals("Ziadny")) {
                break;
            }
            else break;
        }
    }

    public void prekreslenieHraca(int posunKam){
        int pocetNaPrekreslenie = 0;
        int indexPridania = this.pocitac.getindexPridania();
        int pomocnyIndex = this.pocitac.getindexPridania();

        while (true){
            if (indexPridania + posunKam > (this.rozmerPlochy*this.rozmerPlochy)-1) break;
            if (indexPridania - posunKam > (this.rozmerPlochy*this.rozmerPlochy)-1) break;
            if (indexPridania + posunKam < 0) break;
            if (indexPridania - posunKam < 0) break;
            else if ((this.poziciePolicok.get(indexPridania).suradnicaX() == 0 && this.poziciePolicok.get(indexPridania).suradnicaY() <= 1
                    || this.poziciePolicok.get(indexPridania).suradnicaY() < 1
                    || this.poziciePolicok.get(indexPridania).suradnicaX() == this.rozmerPlochy && this.poziciePolicok.get(indexPridania).suradnicaY() <= 1)
                    && (posunKam == (-rozmerPlochy - 1) || posunKam == (-rozmerPlochy + 1))) break;
            else if ((this.poziciePolicok.get(indexPridania).suradnicaX() == 0 && this.poziciePolicok.get(indexPridania).suradnicaY() == (this.rozmerPlochy - 1)
                    || this.poziciePolicok.get(indexPridania).suradnicaY() > (this.rozmerPlochy - 1)
                    || this.poziciePolicok.get(indexPridania).suradnicaX() == this.rozmerPlochy && this.poziciePolicok.get(indexPridania).suradnicaY() == (this.rozmerPlochy - 1))
                    && (posunKam == (this.rozmerPlochy - 1) || posunKam == (this.rozmerPlochy + 1))) break;
            else if (this.poziciePolicok.get(indexPridania).suradnicaX() == 0 || this.poziciePolicok.get(indexPridania).suradnicaX() == this.rozmerPlochy) break;

            else if (this.poziciePolicok.get(indexPridania + posunKam).getTypKamena().equals("Biely")) {
                pocetNaPrekreslenie += 1;
                indexPridania += posunKam;
            }
            else if (this.poziciePolicok.get(indexPridania + posunKam).getTypKamena().equals("Cierny")) {
                if (pocetNaPrekreslenie > 0){
                    this.poziciePolicok.get(indexPridania).removeAll();
                    Kamen kamen = new Kamen(Color.BLACK, this.poziciePolicok.get(0).getVelkostStvorca());
                    this.poziciePolicok.get(indexPridania).add(kamen, BorderLayout.CENTER);
                    SwingUtilities.updateComponentTreeUI(this.poziciePolicok.get(indexPridania));
                    this.pocetBielychKamenov++;
                    this.pocitac.setPocetCiernychKamenov(this.pocitac.getPocetCiernychKamenov()-1);
                    this.poziciePolicok.get(indexPridania).setTypKamena("Cierny");
                }
                indexPridania -= posunKam;
                if (indexPridania == pomocnyIndex) {
                    this.prekreslilSupera = true;
                    break;
                }
            }
            else if (this.poziciePolicok.get(indexPridania + posunKam).getTypKamena().equals("Ziadny")) {
                break;
            }
            else break;
        }
    }
}
