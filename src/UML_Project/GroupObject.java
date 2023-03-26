package UML_Project;

import java.awt.*;
import java.util.ArrayList;

public class GroupObject extends GroupCanvas{
    Canvas canvas;
    ArrayList<Component> groupList;

    ArrayList<Component> tempList;


    GroupObject(){
        groupList = new ArrayList<Component>();
        tempList = new ArrayList<Component>();
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
        for(Component component:tempList){
            if(component.getY()+component.getHeight() > max_Y){
                max_Y = component.getY()+component.getHeight();
            }
        }
        return max_Y;
    }
    int minY(){
        int min_Y = 1000;
        for(Component component:tempList){
            if(component.getY() < min_Y){
                min_Y = component.getY();
            }
        }
        return min_Y;
    }

    int maxX(){
        int max_X = 0;
        for(Component component:tempList){
            if(component.getX()+component.getWidth() > max_X){
                max_X = component.getX()+component.getWidth();
            }
        }
        return max_X;
    }
    int minX(){
        int min_X = 1000;
        for(Component component:tempList){
            if(component.getX() < min_X){
                min_X = component.getX();
            }
        }
        return min_X;
    }
    @Override
    public void setLocation(Point p) {
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
    void groupFunction(Canvas canvas){
        this.canvas = canvas;
        if(!checkSum()||canvas.getComponentCount() <1) return;
        for (Component component : canvas.getComponents()){
            if(((GraphCanvas) component).isSelected) {
                tempList.add(component);
            }
//            groupPanel = new GroupObject();
            if(((GraphCanvas) component).isSelected&&!((GraphCanvas) component).isGroup){
                ((GraphCanvas) component).isGroup = true;
                ((GraphCanvas) component).isSelected = false;
                groupList.add(component);
            }
        }
        this.setBounds(minX(), minY(), maxX() - minX() , maxY() - minY());
        this.setBackground(new Color(13, 191, 140, 40));
        canvas.add(this,0);
        canvas.repaint();
        System.out.println(groupList);

    }
}
