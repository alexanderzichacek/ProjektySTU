package sk.stuba.fei.uim.oop.hra;

import javax.swing.*;
import java.awt.*;

public class Kamen extends JPanel {
    private final Color farba;
    private final int velkostStvorca;
    public Kamen(Color farbaHraca, int velkostStvorca) {
        this.farba = farbaHraca;
        this.velkostStvorca = velkostStvorca;
        this.setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(farba);
        g.fillOval(velkostStvorca/4,velkostStvorca/4,velkostStvorca/2,velkostStvorca/2);
    }

}
