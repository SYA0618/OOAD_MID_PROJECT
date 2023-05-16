package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BasicObject {
    protected Canvas canvas;
    protected Point minPort;
    abstract public void press(MouseEvent e);
    abstract public void drag(MouseEvent e);
    abstract public void release(MouseEvent e);
    protected void createObject(Canvas canvas, MouseEvent e, GraphCanvas drawArea){
        int offset = 10;
        drawArea.setBounds(e.getX(),e.getY(),drawArea.width + offset,drawArea.height + offset);
        canvas.add(drawArea,0);
        canvas.repaint();
    }
    protected Point selectPort(Component component, Point mousePt, int offset_X, int offset_Y){
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

    protected void initPoint(Point canvasMousePt, Point d_canvasMousePt, Component cur_Component){
        canvasMousePt = null;
        d_canvasMousePt = null;
        cur_Component = null;
    }



}

class LineObject extends BasicObject {
     public void press(MouseEvent e){

     };
     public void drag(MouseEvent e){

     };
     public void release(MouseEvent e){

     };
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
class Association_Line1 extends BasicObject{
    private Point mousePt;
    private Point canvasMousePt;
    private Point d_canvasMousePt;
    private Component cur_Component;
    private Point minPort;
    private Point minPort1;
    Association_Line1(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
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
                minPort = selectPort(component, mousePt, offset_X, offset_Y);
                canvasMousePt.x = minPort.x + component.getX()+5;
                canvasMousePt.y = minPort.y + component.getY()+5;
            }

        }
        canvas.repaint();
    }

    public void drag(MouseEvent e){
        canvas.repaint();
    }

    public void release(MouseEvent e){
        mousePt = e.getPoint();
        minPort1 = new Point();
        d_canvasMousePt = new Point();
        for(Component component:canvas.getComponents()){
            int offset_X = component.getX();
            int offset_Y = component.getY();
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result && cur_Component != component && cur_Component != null && !((GraphCanvas)component).isGroupPanel && !((GraphCanvas)component).isGroup){
                minPort1 = selectPort(component, mousePt, offset_X, offset_Y);
//              if(cur_Component == null || ((GraphCanvas)cur_Component).isGroupPanel || ((GraphCanvas)component).isGroupPanel) break;
                d_canvasMousePt.x = minPort1.x + component.getX()+5;
                d_canvasMousePt.y = minPort1.y + component.getY()+5;
                AssociationLine associationLine = new AssociationLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvas.cls.add(associationLine);
                break;
            }
        }
        initPoint(canvasMousePt, d_canvasMousePt, cur_Component);
        canvas.repaint();

    }


}
class GeneralizationLine1 extends BasicObject{
    private Point mousePt;
    private Point canvasMousePt;
    private Point d_canvasMousePt;
    Component cur_Component;
    Point minPort;
    Point minPort1;
    GeneralizationLine1(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
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
                minPort = selectPort(component, mousePt, offset_X, offset_Y);
                canvasMousePt.x = minPort.x + component.getX()+5;
                canvasMousePt.y = minPort.y + component.getY()+5;
            }

        }
        canvas.repaint();
    }
    public void drag(MouseEvent e){
        canvas.repaint();
    }

    public void release(MouseEvent e){
        mousePt = e.getPoint();
        minPort1 = new Point();
        d_canvasMousePt = new Point();
        for(Component component:canvas.getComponents()){
            int offset_X = component.getX();
            int offset_Y = component.getY();
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result && cur_Component != component && cur_Component != null && !((GraphCanvas)component).isGroupPanel && !((GraphCanvas)component).isGroup){
                minPort1 = selectPort(component, mousePt, offset_X, offset_Y);
//              if(cur_Component == null || ((GraphCanvas)cur_Component).isGroupPanel || ((GraphCanvas)component).isGroupPanel) break;
                d_canvasMousePt.x = minPort1.x + component.getX()+5;
                d_canvasMousePt.y = minPort1.y + component.getY()+5;
                GeneralizationLine generalizationLine = new GeneralizationLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvas.cls.add(generalizationLine);
                break;
            }
        }
        initPoint(canvasMousePt, d_canvasMousePt, cur_Component);
        canvas.repaint();
    }


}
class CompositionLine1 extends BasicObject{
    private Point mousePt;
    private Point canvasMousePt;
    private Point d_canvasMousePt;
    Component cur_Component;
    Point minPort;
    Point minPort1;
    CompositionLine1(Canvas canvas){
        this.canvas = canvas;
    }
    public void press(MouseEvent e){
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
                minPort = selectPort(component, mousePt, offset_X, offset_Y);
                canvasMousePt.x = minPort.x + component.getX()+5;
                canvasMousePt.y = minPort.y + component.getY()+5;
            }
        }
        canvas.repaint();
    }
    public void drag(MouseEvent e){
        canvas.repaint();
    }

    public void release(MouseEvent e){
        mousePt = e.getPoint();
        minPort1 = new Point();
        d_canvasMousePt = new Point();
        for(Component component:canvas.getComponents()){
            int offset_X = component.getX();
            int offset_Y = component.getY();
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result && cur_Component != component && cur_Component != null && !((GraphCanvas)component).isGroupPanel && !((GraphCanvas)component).isGroup){
                minPort1 = selectPort(component, mousePt, offset_X, offset_Y);
//              if(cur_Component == null || ((GraphCanvas)cur_Component).isGroupPanel || ((GraphCanvas)component).isGroupPanel) break;
                d_canvasMousePt.x = minPort1.x + component.getX()+5;
                d_canvasMousePt.y = minPort1.y + component.getY()+5;
                CompositionLine compositionLine = new CompositionLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvas.cls.add(compositionLine);
                break;
            }
        }
        initPoint(canvasMousePt, d_canvasMousePt, cur_Component);
        canvas.repaint();
    }


}
class Paint_My_Class extends BasicObject{
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
class Paint_Use_Case extends BasicObject{
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