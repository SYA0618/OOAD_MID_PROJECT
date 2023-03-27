package UML_Project;

import java.awt.*;

public abstract class ConnectionLine {
    Point srcPoint, dstPoint;
    public abstract void drawLine1(Graphics g);
}
class AssociationLine extends ConnectionLine{
    AssociationLine(Point p1, Point p2){
        this.srcPoint = p1;
        this.dstPoint = p2;
    }
    public void drawLine1(Graphics g) {
        g.drawLine(srcPoint.x, srcPoint.y, dstPoint.x, dstPoint.y);

    }
}