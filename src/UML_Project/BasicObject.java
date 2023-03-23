package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BasicObject {

    protected Canvas canvas;
    void CreateObject(Canvas canvas, MouseEvent e, GraphCanvas drawArea){
        drawArea.setBounds(e.getX()-50,e.getY()-40,drawArea.width+10,drawArea.height+10);
        drawArea.repaint();
        canvas.add(drawArea,0);
        canvas.repaint();
    }
    abstract public void press(MouseEvent e);
    abstract public void drag(MouseEvent e);
    abstract public void release(MouseEvent e);
}

class Select extends BasicObject{
    private Point mousePt;
    private Component select;
    public Canvas selectObject;
    Select(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
        mousePt = e.getPoint();
        select = null;
        for(Component component:canvas.getComponents()){
            int offset_X = component.getX();
            int offset_Y = component.getY();
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if (select == null && result) {
                select = component;
                canvas.remove(component);
                canvas.add(select, 0);
                ((GraphCanvas) select).isSelected=true;
            }else{
                GraphCanvas s = (GraphCanvas) component;
                s.isSelected=false;
            }
        }
        if (select == null){
            selectObject = new Canvas();
        }
        canvas.repaint();
    }
    public void drag(MouseEvent e){
        if(select!=null) {
            int dx = e.getX() - mousePt.x;
            int dy = e.getY() - mousePt.y;
            select.setLocation(select.getX() + dx, select.getY() + dy);

            mousePt = e.getPoint();
        }else{
            selectObject.setBounds(Math.min(mousePt.x,e.getX()),Math.min(mousePt.y,e.getY()),Math.abs(mousePt.x-e.getX()),Math.abs(mousePt.y-e.getY()));
            selectObject.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0));
            selectObject.setBackground(new Color(13,191,140,40));
            canvas.add(selectObject, 0);
        }
        canvas.repaint();
    }
    public void release(MouseEvent e){
        if(selectObject!=null) {
        for(Component component:canvas.getComponents()){
            if(component.getX()>Math.min(mousePt.x,e.getX())&&component.getY()>Math.min(mousePt.y,e.getY())&&component.getX()<Math.max(mousePt.x,e.getX())&&component.getY()<Math.max(mousePt.y,e.getY())){
                ((GraphCanvas) component).isSelected=true;
            }
        }
            canvas.remove(selectObject);

        }
        canvas.repaint();
    }
}
class Paint_My_Class extends BasicObject{
    Paint_My_Class(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
        RectCanvas drawRectArea = new RectCanvas();
        CreateObject(this.canvas, e, drawRectArea);
    }
    public void drag(MouseEvent e){}
    public void release(MouseEvent e){}
}
class Paint_Use_Case extends BasicObject{
    Paint_Use_Case(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
        OvalCanvas drawOvalArea = new OvalCanvas();
        CreateObject(this.canvas, e, drawOvalArea);
    }
    public void drag(MouseEvent e){}
    public void release(MouseEvent e){}
}