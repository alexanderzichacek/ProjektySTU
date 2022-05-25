package sk.stuba.fei.uim.oop.rtExam;

import javax.swing.*;
import java.awt.*;

public class Paint {
    public Paint(){
        JFrame frame = new JFrame("Tree draw");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        JPanel menu = new JPanel(new GridLayout());
        JButton draw = new JButton("Tree");
        JButton house = new JButton("House");
        JButton road = new JButton("Road");
        JButton move = new JButton("Move");
        JButton color = new JButton("Color");
        JLabel colorLabel = new JLabel("Tree", SwingConstants.CENTER);
        Logic logic = new Logic(menu, draw, move, color, colorLabel, house, road);
        frame.add(logic.getRender());

        draw.addActionListener(logic);
        move.addActionListener(logic);
        color.addActionListener(logic);
        house.addActionListener(logic);
        road.addActionListener(logic);
        menu.setBackground(Color.RED);

        menu.add(draw);
        menu.add(house);
        menu.add(road);
        menu.add(move);
        menu.add(color);
        menu.add(colorLabel);

        frame.add(menu, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
