package sk.stuba.fei.uim.oop.rtExam;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Setter
@Getter
public class Road {
    private int x;
    private int y;
    private int xEnd;
    private int yEnd;

    public Road(int x, int y, int xEnd, int yEnd) {
        this.x = x;
        this.y = y;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
    }

    public void draw(Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(x,y,xEnd,yEnd);
    }
}
