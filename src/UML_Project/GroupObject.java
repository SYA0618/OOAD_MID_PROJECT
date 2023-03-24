package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GroupObject extends GraphCanvas{
    Canvas canvas;
    ArrayList<Component> groupList;
    protected GroupObject groupPanel;

    GroupObject(){
        groupList = new ArrayList<Component>();
    }
    boolean checkSum(){
        int count = 0;
        for(Component component : canvas.getComponents()){
            if(((GraphCanvas) component).isSelected){count++;}
        }
        return count > 1;
    }
    int maxY(){
        int max_Y = 0;
        for(Component component:groupList){
            if(component.getY() > max_Y){
                max_Y = component.getY();
            }
        }
        return max_Y;
    }
    int minY(){
        int min_Y = 1000;
        for(Component component:groupList){
            if(component.getY() < min_Y){
                min_Y = component.getY();
            }
        }
        return min_Y;
    }

    int maxX(){
        int max_X = 0;
        for(Component component:groupList){
            if(component.getX() > max_X){
                max_X = component.getX();
            }
        }
        return max_X;
    }
    int minX(){
        int min_X = 1000;
        for(Component component:groupList){
            if(component.getX() < min_X){
                min_X = component.getX();
            }
        }
        return min_X;
    }

    void groupObject1 (Canvas canvas){
        this.canvas = canvas;
        if(!checkSum()||canvas.getComponentCount() <1) return;
        for (Component component : canvas.getComponents()){
//            groupPanel = new GroupObject();
            if(((GraphCanvas) component).isSelected){
                groupList.add(component);
            }
        }
        this.setBounds(minX(), minY(), maxX() - minX() + 110, maxY() - minY() + 90);
        this.setBackground(new Color(13, 191, 140, 40));
        canvas.add(this,0);
        canvas.repaint();
        System.out.println(groupList);

    }
    @Override
    public void setLocation(Point p) {

        System.out.println(p);
        Point offset = new Point();
        offset.x = p.x-this.getX();
        offset.y = p.y-this.getY();
        for (Component component:groupList){
            component.setLocation(newPoint(offset, component));
        }
        super.setLocation(p);
    }

    Point newPoint(Point p, Component component){
        Point new_Point = new Point();
        new_Point.x = component.getX()+p.x;
        new_Point.y = component.getY()+p.y;
        return new_Point;
    }
}
