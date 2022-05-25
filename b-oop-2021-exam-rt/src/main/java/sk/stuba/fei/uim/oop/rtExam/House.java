package sk.stuba.fei.uim.oop.rtExam;

import java.awt.*;

public class House extends AllObjects {
    public House(int x, int y, int height, int width, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
        this.whichObject = 2;
    }

    @Override
    public void draw(Graphics g){
        g.setColor(color);
        g.fillPolygon(new int[]{x+width/4,x+width/2,x+3*width/4}, new int[]{y+height/2,y,y+height/2}, 3);
        g.fillRect(x+width/4,y+height/2,width/2,height/2);
    }
    @Override
    public boolean clicked(int clickX, int clickY) {
        Rectangle rect = new Rectangle(x+width/4,y+height/2,width/2,height/2);
        Polygon polygon = new Polygon(new int[]{x+width/4,x+width/2,x+3*width/4}, new int[]{y+height/2,y,y+height/2}, 3);
        return rect.contains(clickX, clickY) || polygon.contains(clickX, clickY);
    }
}