package UML_Project;

import java.awt.*;

public abstract class ConnectionLine {
    Point p1, p2;
    public abstract void drawLine1(Graphics g);
}
class AssociationLine extends ConnectionLine{
    AssociationLine(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    public void drawLine1(Graphics g) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);

    }
}