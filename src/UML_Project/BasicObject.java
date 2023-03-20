package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BasicObject {
    protected Canvas canvas;
    void CreateObject(Canvas canvas, MouseEvent e, GraphCanvas drawArea){
        drawArea.setBounds(e.getX()-50,e.getY()-40,121,101);

        drawArea.repaint();
        canvas.add(drawArea,0);
        canvas.repaint();
        System.out.println(drawArea.getComponents().length);
    }
    abstract void press(MouseEvent e);
    abstract void drag(MouseEvent e);
}

class Select extends BasicObject{
    private Point mousePt;
    private Component select;
    Select(Canvas canvas){
        this.canvas = canvas;
    }
    void press(MouseEvent e){
        select = null;
        for(int i = 0; i < canvas.getComponents().length; i++){
            int offset_X = canvas.getComponent(i).getX();
            int offset_Y = canvas.getComponent(i).getY();
            mousePt = e.getPoint();
            boolean result = canvas.getComponent(i).contains(e.getX() - offset_X, e.getY() - offset_Y);
            if (select == null && result) {
                select = canvas.getComponent(i);
                GraphCanvas s = (GraphCanvas) select;
                s.isSelected=true;

            }else{
                GraphCanvas s = (GraphCanvas) canvas.getComponent(i);
                s.isSelected=false;
            }
            if(select!=null){
                canvas.remove(select);
                canvas.add(select, 0);
            }

            canvas.repaint();

        }
    }
    void drag(MouseEvent e){
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
    void press(MouseEvent e){
        RectCanvas drawRectArea = new RectCanvas();
        CreateObject(this.canvas, e, drawRectArea);
    }
    void drag(MouseEvent e){}
}
class Paint_Use_Case extends BasicObject{
    Paint_Use_Case(Canvas canvas){
        this.canvas = canvas;
    }
    void press(MouseEvent e){
        OvalCanvas drawOvalArea = new OvalCanvas();
        CreateObject(this.canvas, e, drawOvalArea);
    }
    void drag(MouseEvent e){}
}