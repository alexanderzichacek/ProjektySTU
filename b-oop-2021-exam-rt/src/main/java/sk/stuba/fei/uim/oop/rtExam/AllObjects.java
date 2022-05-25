package sk.stuba.fei.uim.oop.rtExam;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
public class AllObjects {
    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected Color color;
    protected int whichObject;
    public void draw(Graphics g){

    }
    public boolean clicked(int clickX, int clickY) {
        return false;
    }
}
