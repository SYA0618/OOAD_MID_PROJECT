package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BasicObject {
    protected Canvas canvas;
    abstract protected void press(MouseEvent e);
    abstract protected void drag(MouseEvent e);
    abstract protected void release(MouseEvent e);
    protected void createObject(Canvas canvas, MouseEvent e, GraphCanvas drawArea){
        int offset = 10;
        drawArea.setBounds(e.getX(),e.getY(),drawArea.width + offset,drawArea.height + offset);
        canvas.add(drawArea,0);
        canvas.repaint();
    }

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
            int offset_X = component.getX(), offset_Y = component.getY();
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if (select == null && result) {
                select = component;
                canvas.setComponentZOrder(component,0);
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
        if(selectObject == null) {
            return;
        }
        for(Component component:canvas.getComponents()){
            if(component.getX()+component.getWidth()>Math.min(mousePt.x,e.getX())&&component.getY()+component.getHeight()>Math.min(mousePt.y,e.getY())&&component.getX()+component.getWidth()<Math.max(mousePt.x,e.getX())&&component.getY()+component.getHeight()<Math.max(mousePt.y,e.getY())){
                if(!((GraphCanvas) component).isGroup) {
                    ((GraphCanvas) component).isSelected = true;
                }
            }
        }
        canvas.remove(selectObject);
        canvas.repaint();
    }

}
class LineObject extends BasicObject {
    protected Point mousePt;
    protected Point canvasMousePt;
    protected Point d_MousePt;
    protected Component cur_Component;
    protected Point minPort;
    protected Point minPort1;
    private static final int offset = 5;
    public void press(MouseEvent e){
        pressObject(e);
        canvas.repaint();
    }
    public void drag(MouseEvent e){
        canvas.repaint();
    }
    public void release(MouseEvent e){}
    protected void pressObject(MouseEvent e){
        mousePt = e.getPoint();
        minPort = new Point();
        canvasMousePt = new Point();
        for(Component component:canvas.getComponents()){
            int offset_X = component.getX();
            int offset_Y = component.getY();
            //if(((GraphCanvas)component).isGroupPanel) break;
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result && !((GraphCanvas)component).isGroupPanel && !((GraphCanvas)component).isGroup){
                cur_Component = component;
                minPort = culClosestPort(component, mousePt, offset_X, offset_Y);
                canvasMousePt.x = minPort.x + component.getX() + offset;
                canvasMousePt.y = minPort.y + component.getY() + offset;
            }

        }
    }
    protected Point culClosestPort(Component component, Point mousePt, int offset_X, int offset_Y){
        double min = Integer.MAX_VALUE;
        for(Point connectionPort:((GraphCanvas) component).port){
            double distance = Math.hypot(mousePt.x-offset_X-connectionPort.x, mousePt.y-offset_Y-connectionPort.y);
            if(distance < min){
                min = distance;
                minPort = connectionPort;
            }
        }
        return minPort;
    }
    protected boolean releaseObject(MouseEvent e, Component component){
        int offset_X = component.getX();
        int offset_Y = component.getY();
        boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
        if(result && cur_Component != component && cur_Component != null && !((GraphCanvas)component).isGroupPanel && !((GraphCanvas)component).isGroup){
            minPort1 = culClosestPort(component, mousePt, offset_X, offset_Y);
            d_MousePt.x = minPort1.x + component.getX() + offset;
            d_MousePt.y = minPort1.y + component.getY() + offset;
            return true;
        }
        return false;
    }
    protected void initPoint(){
        canvasMousePt = null;
        d_MousePt = null;
        cur_Component = null;
    }
}
class Association_Line1 extends LineObject{

    Association_Line1(Canvas canvas){
        this.canvas = canvas;
    }


    public void release(MouseEvent e){
        mousePt = e.getPoint();
        minPort1 = new Point();
        d_MousePt = new Point();
        for(Component component:canvas.getComponents()){
            if(releaseObject(e, component)){
                AssociationLine associationLine = new AssociationLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvas.cls.add(associationLine);
                break;
            }
        }
        initPoint();
        canvas.repaint();

    }


}
class GeneralizationLine1 extends LineObject{
    GeneralizationLine1(Canvas canvas){
        this.canvas = canvas;
    }

    public void release(MouseEvent e){
        mousePt = e.getPoint();
        minPort1 = new Point();
        d_MousePt = new Point();
        for(Component component:canvas.getComponents()){
            if(releaseObject(e, component)){
                GeneralizationLine generalizationLine = new GeneralizationLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvas.cls.add(generalizationLine);
                break;
            }
        }
        initPoint();
        canvas.repaint();
    }


}
class CompositionLine1 extends LineObject{
    CompositionLine1(Canvas canvas){
        this.canvas = canvas;
    }

    public void release(MouseEvent e){
        mousePt = e.getPoint();
        minPort1 = new Point();
        d_MousePt = new Point();
        for(Component component:canvas.getComponents()){
            if(releaseObject(e, component)){
                CompositionLine compositionLine = new CompositionLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvas.cls.add(compositionLine);
                break;
            }
        }
        initPoint();
        canvas.repaint();
    }


}
class Paint_My_Class extends BasicObject {
    Paint_My_Class(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
        RectCanvas drawRectArea = new RectCanvas();
        createObject(this.canvas, e, drawRectArea);
    }
    public void drag(MouseEvent e){}
    public void release(MouseEvent e){}
}
class Paint_Use_Case extends BasicObject {
    Paint_Use_Case(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
        OvalCanvas drawOvalArea = new OvalCanvas();
        createObject(this.canvas, e, drawOvalArea);
    }
    public void drag(MouseEvent e){}
    public void release(MouseEvent e){}
}