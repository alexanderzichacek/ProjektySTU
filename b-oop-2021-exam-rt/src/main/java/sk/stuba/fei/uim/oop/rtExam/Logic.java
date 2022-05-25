package sk.stuba.fei.uim.oop.rtExam;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@Setter
@Getter
public class Logic implements MouseListener, MouseMotionListener, ActionListener {
    private JPanel menu;
    private JButton draw;
    private JButton move;
    private JButton house;
    private JButton road;
    private JButton color;
    private JLabel colorLabel;
    private Color drawColor;
    private int colorCounter;
    private Render render;
    private String labelText;

    public Logic(JPanel menu, JButton draw, JButton move, JButton color, JLabel colorLabel, JButton house, JButton road) {
        this.menu = menu;
        this.draw = draw;
        this.move = move;
        this.house = house;
        this.road = road;
        this.color = color;
        this.colorLabel = colorLabel;
        this.drawColor = Color.RED;
        this.colorCounter = 0;
        this.render = new Render();
        this.render.addMouseListener(this);
        this.render.addMouseMotionListener(this);
        this.labelText = colorLabel.getText();
    }

    public void getDrawColor(){
        colorCounter += 1;
        if (colorCounter > 2) colorCounter = 0;

        if (colorCounter == 0) drawColor = Color.RED;
        else if (colorCounter == 1) drawColor = Color.GREEN;
        else if (colorCounter == 2) drawColor = Color.BLUE;

        menu.setBackground(drawColor);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.draw){
            colorLabel.setText("Tree");
        }
        else if (e.getSource() == this.move){
            colorLabel.setText("Move");
        }
        else if (e.getSource() == this.house){
            colorLabel.setText("House");
        }
        else if (e.getSource() == this.road){
            colorLabel.setText("Road");
        }
        else if (e.getSource() == this.color){
            getDrawColor();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.labelText = this.colorLabel.getText();
        switch (labelText) {
            case "Tree":
                this.render.startDraw(e, drawColor);
                break;
            case "House":
                this.render.startDrawHouse(e, drawColor);
                break;
            case "Road":
                this.render.startDrawRoad(e);
                break;
            case "Move":
                this.render.startDrag(e);
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.labelText = this.colorLabel.getText();
        if (labelText.equals("Tree") || labelText.equals("House") || labelText.equals("Move")) this.render.end();
        if (labelText.equals("Road"))this.render.deleteWrongRoad(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.labelText = this.colorLabel.getText();
        switch (labelText) {
            case "Tree":
                this.render.draw(e);
                break;
            case "House":
                this.render.drawHouse(e);
                break;
            case "Road":
                this.render.drawRoad(e);
                break;
            case "Move":
                this.render.drag(e);
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
