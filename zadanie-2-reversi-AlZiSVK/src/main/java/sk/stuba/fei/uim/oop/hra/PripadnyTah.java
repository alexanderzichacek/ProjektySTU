package sk.stuba.fei.uim.oop.hra;

import javax.swing.*;
import java.awt.*;

public class PripadnyTah extends JPanel {
    private final int velkostStvorca;

    public PripadnyTah(int velkostStvorca) {
        this.velkostStvorca = velkostStvorca;
        this.setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawOval(velkostStvorca/4,velkostStvorca/4,velkostStvorca/2,velkostStvorca/2);
    }
}
