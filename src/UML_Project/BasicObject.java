package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BasicObject {
    ConnectionPort port_n = new ConnectionPort(45, 0);

    protected Canvas canvas;
    void CreateObject(Canvas canvas, MouseEvent e, GraphCanvas drawArea){
        drawArea.setBounds(e.getX()-50,e.getY()-40,120,100);
        drawArea.repaint();
        canvas.add(drawArea,0);
        canvas.repaint();
    }
    abstract public void press(MouseEvent e);
    abstract public void drag(MouseEvent e);
}

class Select extends BasicObject{
    private Point mousePt;
    private Component select;
    Select(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
        select = null;
        for(int i = 0; i < canvas.getComponents().length; i++){
            int offset_X = canvas.getComponent(i).getX();
            int offset_Y = canvas.getComponent(i).getY();
            mousePt = e.getPoint();
            boolean result = canvas.getComponent(i).contains(e.getX() - offset_X, e.getY() - offset_Y);
            if (select == null && result) {
                select = canvas.getComponent(i);
                canvas.remove(canvas.getComponent(i));
                canvas.add(select, 0);
                ((GraphCanvas) select).isSelected=true;
                ((GraphCanvas) select).repaint();
                canvas.repaint();

            }else{
                GraphCanvas s = (GraphCanvas) canvas.getComponent(i);
                s.isSelected=false;
                s.repaint();
                canvas.repaint();
            }

        }
    }
    public void drag(MouseEvent e){
        if(select!=null) {
            int dx = e.getX() - mousePt.x;
            int dy = e.getY() - mousePt.y;
            select.setLocation(select.getX() + dx, select.getY() + dy);
            select.repaint();
            mousePt = e.getPoint();
            select.repaint();
        }
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
}