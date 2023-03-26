package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BasicObject {

    protected Canvas canvas;
    void CreateObject(Canvas canvas, MouseEvent e, GraphCanvas drawArea){
        drawArea.setBounds(e.getX(),e.getY(),drawArea.width+10,drawArea.height+10);
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
                canvas.setComponentZOrder(component,0);
                //canvas.remove(component);
                //canvas.add(select, 0);
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
            select.setLocation(new Point(select.getX() + dx, select.getY() + dy));

            mousePt = e.getPoint();
        }else{
            selectObject.setBounds(Math.min(mousePt.x,e.getX()),Math.min(mousePt.y,e.getY()),Math.abs(mousePt.x-e.getX()),Math.abs(mousePt.y-e.getY()));
            selectObject.setBorder(BorderFactory.createLineBorder(Color.BLUE, 0));
            selectObject.setBackground(new Color(135,206,250,80));
            canvas.add(selectObject, 0);
        }
        canvas.repaint();
    }
    public void release(MouseEvent e){
        if(selectObject!=null) {
            for(Component component:canvas.getComponents()){
                if(component.getX()+component.getWidth()>Math.min(mousePt.x,e.getX())&&component.getY()+component.getHeight()>Math.min(mousePt.y,e.getY())&&component.getX()+component.getWidth()<Math.max(mousePt.x,e.getX())&&component.getY()+component.getHeight()<Math.max(mousePt.y,e.getY())){
                    if(!((GraphCanvas) component).isGroup) {
                        ((GraphCanvas) component).isSelected = true;
                    }
                }
            }
            canvas.remove(selectObject);
        }
        canvas.repaint();
    }
}
class Association_Line extends BasicObject{
    private Point mousePt;

    Association_Line(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
        mousePt = e.getPoint();
        for(Component component:canvas.getComponents()){
            int offset_X = component.getX();
            int offset_Y = component.getY();
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result){
                ((GraphCanvas) component).isSelected=true;
                for(Point connectionPort: ((GraphCanvas) component).port){
                    double distance = Math.hypot(mousePt.x-offset_X-connectionPort.x,mousePt.y-offset_Y-connectionPort.y);

                    System.out.println(distance);
                }

            }
        }
        canvas.repaint();
    }
    public void drag(MouseEvent e){

    }

    public void release(MouseEvent e){

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