package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class BasicObject {

    protected Canvas canvas;
    void CreateObject(Canvas canvas, MouseEvent e, GraphCanvas drawArea){
        drawArea.setBounds(e.getX(),e.getY(),drawArea.width+10,drawArea.height+10);
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
            double min = Integer.MAX_VALUE;
            if(((GraphCanvas)component).isGroupPanel) break;
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result && !((GraphCanvas)component).isGroupPanel){
                System.out.println("Press");
                cur_Component = component;
                for(Point connectionPort:((GraphCanvas) component).port){
                    double distance = Math.hypot(mousePt.x-offset_X-connectionPort.x,mousePt.y-offset_Y-connectionPort.y);
                    if(distance < min){
                        min = distance;
                        minPort = connectionPort;
                    }
                }
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
            if(cur_Component == null || ((GraphCanvas)cur_Component).isGroupPanel || ((GraphCanvas)component).isGroupPanel) break;
            int offset_X = component.getX();
            int offset_Y = component.getY();
            double min = Integer.MAX_VALUE;
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result && cur_Component != component && !((GraphCanvas)component).isGroupPanel){
                System.out.println("release");
                for(Point connectionPort: ((GraphCanvas) component).port){
                    double distance = Math.hypot(mousePt.x-offset_X-connectionPort.x,mousePt.y-offset_Y-connectionPort.y);
                    if(distance < min){
                        min = distance;
                        minPort1 = connectionPort;
                    }
                }
                d_canvasMousePt.x = minPort1.x + component.getX()+5;
                d_canvasMousePt.y = minPort1.y + component.getY()+5;
                System.out.println(minPort);
                AssociationLine associationLine = new AssociationLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvasMousePt=null;
                d_canvasMousePt=null;
                cur_Component=null;
                canvas.cls.add(associationLine);

            }
        }
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
            double min = Integer.MAX_VALUE;

            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result&&!((GraphCanvas)component).isGroupPanel){
                System.out.println("Press");
                cur_Component = component;
                for(Point connectionPort: ((GraphCanvas) component).port){
                    double distance = Math.hypot(mousePt.x-offset_X-connectionPort.x,mousePt.y-offset_Y-connectionPort.y);
                    if(distance < min){
                        min = distance;
                        minPort = connectionPort;
                    }
                }
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
            double min = Integer.MAX_VALUE;
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result && cur_Component != component && !((GraphCanvas)component).isGroupPanel){
                System.out.println("release");
                for(Point connectionPort: ((GraphCanvas) component).port){
                    double distance = Math.hypot(mousePt.x-offset_X-connectionPort.x,mousePt.y-offset_Y-connectionPort.y);
                    if(distance < min){
                        min = distance;
                        minPort1 = connectionPort;
                    }
                }
                d_canvasMousePt.x = minPort1.x + component.getX()+5;
                d_canvasMousePt.y = minPort1.y + component.getY()+5;
                GeneralizationLine generalizationLine = new GeneralizationLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvasMousePt=null;
                d_canvasMousePt=null;
                canvas.cls.add(generalizationLine);

            }
        }
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
            double min = Integer.MAX_VALUE;

            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result&&!((GraphCanvas)component).isGroupPanel){
                System.out.println("Press");
                cur_Component = component;
                for(Point connectionPort: ((GraphCanvas) component).port){
                    double distance = Math.hypot(mousePt.x-offset_X-connectionPort.x,mousePt.y-offset_Y-connectionPort.y);
                    if(distance < min){
                        min = distance;
                        minPort = connectionPort;
                    }
                }
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
            double min = Integer.MAX_VALUE;
            boolean result = component.contains(e.getX() - offset_X, e.getY() - offset_Y);
            if(result && cur_Component != component && !((GraphCanvas)component).isGroupPanel){
                System.out.println("release");
                for(Point connectionPort: ((GraphCanvas) component).port){
                    double distance = Math.hypot(mousePt.x-offset_X-connectionPort.x,mousePt.y-offset_Y-connectionPort.y);
                    if(distance < min){
                        min = distance;
                        minPort1 = connectionPort;
                    }
                }
                d_canvasMousePt.x = minPort1.x + component.getX()+5;
                d_canvasMousePt.y = minPort1.y + component.getY()+5;
                CompositionLine compositionLine = new CompositionLine(minPort,minPort1, (GraphCanvas) cur_Component, (GraphCanvas) component);
                canvasMousePt=null;
                d_canvasMousePt=null;
                canvas.cls.add(compositionLine);

            }
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