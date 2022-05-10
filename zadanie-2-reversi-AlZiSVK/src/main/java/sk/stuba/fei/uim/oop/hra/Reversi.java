package sk.stuba.fei.uim.oop.hra;

import sk.stuba.fei.uim.oop.hraci.Hrac;
import sk.stuba.fei.uim.oop.logikahry.Logika;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Reversi implements ItemListener, ActionListener, KeyListener {
    private final JFrame frame;
    private JPanel plocha;
    private final JButton restartButton;
    private final JLabel rozmerLabel;
    private final JLabel priebehLabel;
    private final JComboBox<String> sizeBox;
    private int rozmerPlochy;
    private List<Policko> poziciePolicok;
    private Logika logika;
    private final Hrac hrac;

    public Reversi() {
        this.frame = new JFrame("Reversi");

        this.rozmerLabel = new JLabel();
        this.rozmerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.rozmerLabel.setVerticalAlignment(SwingConstants.CENTER);
        this.priebehLabel = new JLabel();
        this.priebehLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.priebehLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel menu = new JPanel();
        this.restartButton = new JButton("RESTART");
        this.restartButton.setFocusable(false);

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(900,965);
        this.frame.setResizable(false);
        this.rozmerPlochy = 6;

        this.hrac = new Hrac();

        inicializacia();
        pridanieKamenov();
        this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);

        menu.setBackground(Color.WHITE);
        this.priebehLabel.setText("Hrac: " + this.logika.getPocetBielychKamenov() + " | Pocitac: " + this.logika.getPocetCiernychKamenov());

        String[] rozmery = {"6x6", "8x8", "10x10", "12x12"};
        this.sizeBox = new JComboBox<>(rozmery);
        this.sizeBox.setFocusable(false);
        this.sizeBox.addItemListener(this);

        this.restartButton.addActionListener(this);
        this.frame.addKeyListener(this);

        menu.setLayout(new GridLayout(1, 3));
        String aktualnyRozmerLabelText = "Aktualny rozmer plochy je: " + Objects.requireNonNull(this.sizeBox.getSelectedItem());
        this.rozmerLabel.setText(aktualnyRozmerLabelText);

        menu.add(this.restartButton);
        menu.add(this.rozmerLabel);
        menu.add(this.sizeBox);
        menu.add(this.priebehLabel);

        this.frame.add(menu, BorderLayout.SOUTH);
        this.frame.setVisible(true);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == this.sizeBox){
            String aktualnyRozmer = Objects.requireNonNull(this.sizeBox.getSelectedItem()).toString();
            this.logika.setPriebehLabel("Hrac: " + logika.getPocetBielychKamenov() + " | Pocitac: " + logika.getPocetCiernychKamenov());
            switch (aktualnyRozmer) {
                case "6x6":
                    this.frame.remove(this.plocha);
                    this.rozmerPlochy = 6;
                    inicializacia();
                    pridanieKamenov();
                    this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);
                    break;
                case "8x8":
                    this.frame.remove(this.plocha);
                    this.rozmerPlochy = 8;
                    inicializacia();
                    pridanieKamenov();
                    this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);
                    break;
                case "10x10":
                    this.frame.remove(this.plocha);
                    this.rozmerPlochy = 10;
                    inicializacia();
                    pridanieKamenov();
                    this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);
                    break;
                case "12x12":
                    this.frame.remove(this.plocha);
                    this.rozmerPlochy = 12;
                    inicializacia();
                    pridanieKamenov();
                    this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);
                    break;
            }
            this.rozmerLabel.setText("Aktualny rozmer plochy je: " + aktualnyRozmer);
        }
    }

    public void inicializacia(){
        this.plocha = new JPanel();
        this.plocha.setLayout(new GridLayout(this.rozmerPlochy, this.rozmerPlochy));
        this.poziciePolicok = new ArrayList<>();

        Color lightGreen = new Color(0,150,0);
        Color green = new Color(0, 120, 0);
        int velkostStvorca = 900/this.rozmerPlochy;

        for (int i = 0;i < this.rozmerPlochy;i++){
            for (int j = 0;j < this.rozmerPlochy;j++){
                Policko policko;
                if ((i + j) % 2 == 0){
                    policko = new Policko(lightGreen, velkostStvorca, j, i);
                }
                else{
                    policko = new Policko(green, velkostStvorca, j, i);
                }
                this.plocha.add(policko);
                this.poziciePolicok.add(policko);
            }
        }

        this.frame.add(this.plocha);

        this.logika = new Logika(this.poziciePolicok, this.rozmerPlochy, this.priebehLabel);

        SwingUtilities.updateComponentTreeUI(this.frame);
    }

    public void pridanieKamenov(){
        Kamen kamen = new Kamen(Color.BLACK, this.poziciePolicok.get(0).getVelkostStvorca());
        this.poziciePolicok.get(((this.rozmerPlochy*this.rozmerPlochy)/2)-(this.rozmerPlochy/2)-1).add(kamen, BorderLayout.CENTER);
        kamen = new Kamen(Color.BLACK, this.poziciePolicok.get(0).getVelkostStvorca());
        this.poziciePolicok.get(((this.rozmerPlochy*this.rozmerPlochy)/2)+(this.rozmerPlochy/2)).add(kamen, BorderLayout.CENTER);

        kamen = new Kamen(Color.WHITE, this.poziciePolicok.get(0).getVelkostStvorca());
        this.poziciePolicok.get(((this.rozmerPlochy*this.rozmerPlochy)/2)-(this.rozmerPlochy/2)).add(kamen, BorderLayout.CENTER);
        kamen = new Kamen(Color.WHITE, this.poziciePolicok.get(0).getVelkostStvorca());
        this.poziciePolicok.get(((this.rozmerPlochy*this.rozmerPlochy)/2)+(this.rozmerPlochy/2)-1).add(kamen, BorderLayout.CENTER);

        this.poziciePolicok.get(((this.rozmerPlochy*this.rozmerPlochy)/2)-(this.rozmerPlochy/2)-1).setTypKamena("Cierny");
        this.poziciePolicok.get(((this.rozmerPlochy*this.rozmerPlochy)/2)+(this.rozmerPlochy/2)).setTypKamena("Cierny");
        this.poziciePolicok.get(((this.rozmerPlochy*this.rozmerPlochy)/2)-(this.rozmerPlochy/2)).setTypKamena("Biely");
        this.poziciePolicok.get(((this.rozmerPlochy*this.rozmerPlochy)/2)+(this.rozmerPlochy/2)-1).setTypKamena("Biely");
    }

    public void restart(){
        switch (this.rozmerPlochy) {
            case 6:
                this.frame.remove(this.plocha);
                this.sizeBox.setSelectedItem("6x6");
                inicializacia();
                pridanieKamenov();
                this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);
                break;
            case 8:
                this.frame.remove(this.plocha);
                this.sizeBox.setSelectedItem("8x8");
                inicializacia();
                pridanieKamenov();
                this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);
                break;
            case 10:
                this.frame.remove(this.plocha);
                this.sizeBox.setSelectedItem("10x10");
                inicializacia();
                pridanieKamenov();
                this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);
                break;
            case 12:
                this.frame.remove(this.plocha);
                this.sizeBox.setSelectedItem("12x12");
                inicializacia();
                pridanieKamenov();
                this.hrac.moznePolicka(poziciePolicok, rozmerPlochy);
                break;
        }
        this.logika.setPriebehLabel("Hrac: " + logika.getPocetBielychKamenov() + " | Pocitac: " + this.logika.getPocetCiernychKamenov());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.restartButton) restart();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);

        if (e.getKeyCode() == KeyEvent.VK_R) restart();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
