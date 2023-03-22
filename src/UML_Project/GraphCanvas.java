package UML_Project;

import javax.swing.*;
import java.awt.*;

interface I_GraphCanvas{
    void paint(Graphics g);
}
abstract class GraphCanvas extends JPanel implements I_GraphCanvas{
    ConnectionPort port_n = new ConnectionPort(45, 0);
    ConnectionPort port_e = new ConnectionPort(90,35);
    ConnectionPort port_s = new ConnectionPort(45,70);
    ConnectionPort port_w = new ConnectionPort(0,35);
    boolean isSelected = false;
    final int width = 100;
    final int height = 80;
}
class RectCanvas extends GraphCanvas{
    @Override
    public void paint(Graphics g) {
        if(isSelected){
            port_e.paint(g);
            port_n.paint(g);
            port_s.paint(g);
            port_w.paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawLine(0,height/3,width,height/3);
        g.drawLine(0,height/3*2,width,height/3*2);
        g.drawRect(0, 0, width, height);

    }
}
class OvalCanvas extends GraphCanvas{
    @Override
    public void paint(Graphics g) {
        if(isSelected){
            port_e.paint(g);
            port_n.paint(g);
            port_s.paint(g);
            port_w.paint(g);
        }
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, width, height);
    }
}

class GroupCanvas extends GraphCanvas{

}