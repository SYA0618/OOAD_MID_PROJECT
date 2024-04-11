package UML_Project;

import java.awt.*;
import java.util.ArrayList;

public class GroupObject extends GroupCanvas{
    private Canvas canvas;
    public ArrayList<Component> groupList;

    private ArrayList<Component> tempList;


    GroupObject(){
        groupList = new ArrayList<>();
        tempList = new ArrayList<>();
    }
    private boolean checkSum(){
        int count = 0;
        for(Component component : canvas.getComponents()){
            if(((GraphCanvas) component).isSelected){count++;}
        }
        return count > 1;
    }
    private int maxY(){
        int max_Y = 0;
        for(Component component:tempList){
            if(component.getY()+component.getHeight() > max_Y){
                max_Y = component.getY()+component.getHeight();
            }
        }
        return max_Y;
    }
    private int minY(){
        int min_Y = Integer.MAX_VALUE;
        for(Component component:tempList){
            if(component.getY() < min_Y){
                min_Y = component.getY();
            }
        }
        return min_Y;
    }

    private int maxX(){
        int max_X = 0;
        for(Component component:tempList){
            if(component.getX()+component.getWidth() > max_X){
                max_X = component.getX()+component.getWidth();
            }
        }
        return max_X;
    }
    private int minX(){
        int min_X = Integer.MAX_VALUE;
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

    private Point newPoint(Point p, Component component){
        Point new_Point = new Point();
        new_Point.x = component.getX()+p.x;
        new_Point.y = component.getY()+p.y;
        return new_Point;
    }
    public void groupFunction(Canvas canvas){
        this.canvas = canvas;
        if(!checkSum()||canvas.getComponentCount() <1) return;

        for (Component component : canvas.getComponents()){
            if(((GraphCanvas) component).isSelected) {
                tempList.add(component);
            }
            if(((GraphCanvas) component).isSelected&&!((GraphCanvas) component).isGroup){
                ((GraphCanvas) component).isGroup = true;
                ((GraphCanvas) component).isSelected = false;
                groupList.add(component);
            }
        }
        setBounds(minX(), minY(), maxX() - minX() , maxY() - minY());
        setBackground(new Color(13, 191, 140, 40));
        canvas.add(this,0);
        canvas.repaint();
        System.out.println(groupList);
    }
}
