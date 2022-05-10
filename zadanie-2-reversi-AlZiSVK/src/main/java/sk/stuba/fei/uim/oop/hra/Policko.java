package sk.stuba.fei.uim.oop.hra;

import javax.swing.*;
import java.awt.*;

public class Policko extends JPanel {
    private final int velkostStvorca;
    private final int x;
    private final int y;
    private String typKamena;

    public Policko(Color farba, int velkostStvorca, int x, int y) {
        this.x = x;
        this.y = y;
        this.velkostStvorca = velkostStvorca;
        this.setBackground(farba);
        this.setLayout(new BorderLayout());
        this.typKamena = "";
    }

    public void setTypKamena(String typKamena) {
        this.typKamena = typKamena;
    }

    public String getTypKamena() {
        return typKamena;
    }

    public int getVelkostStvorca() {
        return velkostStvorca;
    }

    public int suradnicaX() {
        return x;
    }

    public int suradnicaY() {
        return y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
