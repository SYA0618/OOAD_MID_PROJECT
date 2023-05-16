package UML_Project;

import java.awt.*;
import java.nio.channels.Pipe;

public abstract class ConnectionLine {
    protected GraphCanvas starObj, endObj;
    protected Point startPort, endPort;

    public abstract void drawLine1(Graphics g);

    public Point[] culPoint(){
        int offset = 5;
        Point star = new Point(startPort.x + starObj.getX()+ offset, startPort.y + starObj.getY()+ offset);
        Point end = new Point(endPort.x + endObj.getX()+ offset, endPort.y + endObj.getY()+ offset);
        return  new Point[]{star,end};

    }
}
class AssociationLine extends ConnectionLine{
    AssociationLine(Point p1, Point p2, GraphCanvas o1, GraphCanvas o2){
        starObj = o1;
        endObj = o2;
        startPort = p1;
        endPort = p2;

    }
    public void drawLine1(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Point[] points = culPoint();
        double nx, ny;
        double lineLength = Math.hypot(points[0].x - points[1].x, points[0].y - points[1].y);
        nx = (points[0].x - points[1].x) / lineLength;
        ny = (points[0].y - points[1].y) / lineLength;
        int c1x, c1y, c2x, c2y;
        int w=8, h=16;
        c1x = (int)(points[1].x+nx*w) + (int) (ny * w);
        c1y = (int)(points[1].y+ny*w) + (int) (-nx * w);
        c2x = (int)(points[1].x+nx*w) + (int) (-ny * w);
        c2y = (int)(points[1].y+ny*w) + (int) (nx * w);
        g2.drawLine(c1x, c1y, points[1].x, points[1].y);
        g2.drawLine(c2x, c2y, points[1].x, points[1].y);
        g2.drawLine(points[0].x,points[0].y,points[1].x,points[1].y);

    }
}

class GeneralizationLine extends ConnectionLine{
    GeneralizationLine(Point p1, Point p2, GraphCanvas o1, GraphCanvas o2){
        starObj = o1;
        endObj = o2;
        startPort = p1;
        endPort = p2;

    }
    public void drawLine1(Graphics g) {
        Point[] points = culPoint();
        double nx, ny;
        double lineLength = Math.hypot(points[0].x - points[1].x, points[0].y - points[1].y);
        nx = (points[0].x - points[1].x) / lineLength;
        ny = (points[0].y - points[1].y) / lineLength;
        int c1x, c1y, c2x, c2y;
        int w=8, h=16;
        c1x = (int)(points[1].x+nx*w) + (int) (ny * w);
        c1y = (int)(points[1].y+ny*w) + (int) (-nx * w);
        c2x = (int)(points[1].x+nx*w) + (int) (-ny * w);
        c2y = (int)(points[1].y+ny*w) + (int) (nx * w);
        g.drawLine(c1x, c1y, points[1].x, points[1].y);
        g.drawLine(c2x, c2y, points[1].x, points[1].y);
        g.drawLine(c1x, c1y, c2x, c2y);
        g.drawLine(points[0].x,points[0].y,(c2x+c1x)/2,(c2y+c1y)/2);
    }
}

class CompositionLine extends ConnectionLine{
    CompositionLine(Point p1, Point p2, GraphCanvas o1, GraphCanvas o2){
        starObj = o1;
        endObj = o2;
        startPort = p1;
        endPort = p2;

    }
    public void drawLine1(Graphics g) {
        Point[] points = culPoint();
        double nx, ny;
        double lineLength = Math.hypot(points[0].x - points[1].x, points[0].y - points[1].y);
        nx = (points[0].x - points[1].x) / lineLength;
        ny = (points[0].y - points[1].y) / lineLength;
        int c1x, c1y, c2x, c2y, c3x, c3y;
        int w=8, h=16;
        c1x = (int)(points[1].x+nx*w) + (int) (ny * w);
        c1y = (int)(points[1].y+ny*w) + (int) (-nx * w);
        c2x = (int)(points[1].x+nx*w) + (int) (-ny * w);
        c2y = (int)(points[1].y+ny*w) + (int) (nx * w);
        c3x = (int)(points[1].x+nx*h);
        c3y = (int)(points[1].y+ny*h);
        g.drawLine(c1x, c1y, points[1].x, points[1].y);
        g.drawLine(c2x, c2y, points[1].x, points[1].y);
        g.drawLine(c1x, c1y, c3x, c3y);
        g.drawLine(c2x, c2y, c3x, c3y);
        g.drawLine(points[0].x,points[0].y,c3x,c3y);

    }
}