package sk.stuba.fei.uim.oop.rtExam;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Render extends JPanel {
    private final List<Tree> trees;
    private final List<House> houses;
    private final List<AllObjects> allObjectsList;
    private final List<Road> roads;
    private Tree rendered;
    private House renderedHouse;
    private AllObjects renderedAll;
    private Road renderedRoad;
    private int startX;
    private int startY;
    private int absX;
    private int absY;
    private boolean onObject;
    private int whichObject;

    public Render() {
        this.setBackground(Color.WHITE);
        this.trees = new ArrayList<>();
        this.houses = new ArrayList<>();
        this.allObjectsList = new ArrayList<>();
        this.roads = new ArrayList<>();
        this.onObject = false;
        this.whichObject = 0;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.allObjectsList.forEach(renderedAll -> renderedAll.draw(g));
        this.roads.forEach(renderedRoad -> renderedRoad.draw(g));
    }

    public void startDraw(MouseEvent e, Color drawColor){
        this.startX = e.getX();
        this.startY = e.getY();
        this.rendered = new Tree(e.getX(), e.getY(), 0, 0, drawColor);
        this.renderedAll = new Tree(e.getX(), e.getY(), 0, 0, drawColor);
        this.trees.add(rendered);
        this.allObjectsList.add(renderedAll);
        this.repaint();
    }

    public void startDrawHouse(MouseEvent e, Color drawColor){
        this.startX = e.getX();
        this.startY = e.getY();
        this.renderedHouse = new House(e.getX(), e.getY(), 0, 0, drawColor);
        this.renderedAll = new House(e.getX(), e.getY(), 0, 0, drawColor);
        this.houses.add(renderedHouse);
        this.allObjectsList.add(renderedAll);
        this.repaint();
    }

    public void startDrawRoad(MouseEvent e){
//        for (int i = trees.size()-1;i >= 0;i--){
//            Tree tree = trees.get(i);
//            if (tree.clicked(e.getX(),e.getY())){
//                this.startX = tree.getX()+tree.getWidth()/2;
//                this.startY = tree.getY()+tree.getHeight()/2;
//                this.renderedRoad = new Road(startX,startY,startX,startY);
//                this.roads.add(renderedRoad);
//                this.onObject = true;
//                this.whichObject = 1;
//                break;
//            }
//        }
//        for (int j = houses.size()-1;j >= 0;j--) {
//            House house = houses.get(j);
//            if (house.clicked(e.getX(), e.getY())){
//                this.startX = house.getX()+house.getWidth()/2;
//                this.startY = house.getY()+house.getHeight()/2;
//                this.renderedRoad = new Road(startX,startY,startX,startY);
//                this.roads.add(renderedRoad);
//                this.onObject = true;
//                this.whichObject = 2;
//                break;
//            }
//        }
        for (AllObjects allObjects : this.allObjectsList){
            if (allObjects.clicked(e.getX(), e.getY())){
                this.startX = allObjects.getX()+allObjects.getWidth()/2;
                this.startY = allObjects.getY()+allObjects.getHeight()/2;
                this.renderedRoad = new Road(startX,startY,startX,startY);
                this.roads.add(renderedRoad);
                this.onObject = true;
                break;
            }
        }
        this.repaint();
    }

    public void draw(MouseEvent e) {
        this.rendered.setX(Math.min(startX, e.getX()));
        this.rendered.setY(Math.min(startY, e.getY()));

        this.renderedAll.setX(Math.min(startX, e.getX()));
        this.renderedAll.setY(Math.min(startY, e.getY()));

        if (startX < e.getX()) this.absX = (e.getX()-startX);
        else this.absX = (startX-e.getX());
        if (startY < e.getY()) this.absY = (e.getY()-startY);
        else this.absY = (startY-e.getY());

        this.rendered.setWidth(this.absX);
        this.rendered.setHeight(this.absY);

        this.renderedAll.setWidth(this.absX);
        this.renderedAll.setHeight(this.absY);
        this.repaint();
    }

    public void drawHouse(MouseEvent e) {
        this.renderedHouse.setX(Math.min(startX, e.getX()));
        this.renderedHouse.setY(Math.min(startY, e.getY()));

        this.renderedAll.setX(Math.min(startX, e.getX()));
        this.renderedAll.setY(Math.min(startY, e.getY()));

        if (startX < e.getX()) this.absX = (e.getX()-startX);
        else this.absX = (startX-e.getX());
        if (startY < e.getY()) this.absY = (e.getY()-startY);
        else this.absY = (startY-e.getY());

        this.renderedHouse.setWidth(this.absX);
        this.renderedHouse.setHeight(this.absY);

        this.renderedAll.setWidth(this.absX);
        this.renderedAll.setHeight(this.absY);
        this.repaint();
    }

    public void drawRoad(MouseEvent e){
        if (this.onObject){
            this.renderedRoad.setX(this.startX);
            this.renderedRoad.setY(this.startY);
            for (AllObjects allObjects : this.allObjectsList) {
                if (allObjects.whichObject == 1) {
                    for (House house : this.houses) {
                        if (house.clicked(e.getX(), e.getY())) {
                            this.renderedRoad.setXEnd(e.getX());
                            this.renderedRoad.setYEnd(e.getY());
                        }
                    }
                } else if (allObjects.whichObject == 2) {
                    for (Tree tree : this.trees) {
                        if (tree.clicked(e.getX(), e.getY())) {
                            this.renderedRoad.setXEnd(e.getX());
                            this.renderedRoad.setYEnd(e.getY());
                        }
                    }
                }
            }

            this.renderedRoad.setXEnd(e.getX());
            this.renderedRoad.setYEnd(e.getY());

            this.repaint();
        }
    }

    public void deleteWrongRoad(MouseEvent e){
        if (this.renderedRoad != null) {
            int startedX = this.renderedRoad.getX();
            int startedY = this.renderedRoad.getY();

            this.roads.remove(this.renderedRoad);
            this.renderedRoad = null;

            for (AllObjects allObjects : this.allObjectsList) {
                if (allObjects.clicked(e.getX(), e.getY())) {
                    this.renderedRoad = new Road(startedX, startedY, allObjects.getX() + allObjects.getWidth() / 2, allObjects.getY() + allObjects.getHeight() / 2);
                    this.roads.add(this.renderedRoad);
                }
            }

            this.repaint();
        }
    }

    public void startDrag(MouseEvent e){
        for (AllObjects allObjects : this.allObjectsList){
                if (allObjects.clicked(e.getX(), e.getY())){
                this.renderedAll = allObjects;
                this.startX = allObjects.getX()-e.getX();
                this.startY = allObjects.getY()-e.getY();
            }
        }
    }

    public void drag(MouseEvent e){
        if (this.renderedAll != null){
            this.renderedAll.setX(startX+e.getX());
            this.renderedAll.setY(startY+e.getY());
        }
        this.repaint();
    }

    public void end(){
        this.startX = 0;
        this.startY = 0;
        this.rendered = null;
        this.renderedHouse = null;
        this.renderedAll = null;
        this.renderedRoad = null;
        this.onObject = false;
        this.whichObject = 0;
    }
}
