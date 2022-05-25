package sk.stuba.fei.uim.oop.rtExam;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Tree extends AllObjects {
    public Tree(int x, int y, int height, int width, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
        this.whichObject = 1;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(x,y,width,2*height/3);
        g.fillRect(x+width/3,y+height/2,width/3,height/2);
    }
    @Override
    public boolean clicked(int clickX, int clickY) {
        Rectangle rect = new Rectangle(x+width/3,y+height/2,width/3,height/2);
        Ellipse2D ellipse = new Ellipse2D.Double(x, y, width, (double)2*height/3);
        return rect.contains(clickX, clickY) || ellipse.contains(clickX, clickY);
    }
}
