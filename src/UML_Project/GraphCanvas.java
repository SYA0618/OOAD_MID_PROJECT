package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

interface I_GraphCanvas{
    void paint(Graphics g);
}
abstract class GraphCanvas extends JPanel implements I_GraphCanvas{
    public boolean isSelected = false;
    public boolean isGroup = false;
    public boolean isGroupPanel =false;
    final int width = 100;
    final int height = 80;
    final int offset = 5;
    String objName = "";
    ConnectionPort port_n = new ConnectionPort(width/2, 0);
    ConnectionPort port_e = new ConnectionPort(width,height/2);
    ConnectionPort port_s = new ConnectionPort(width/2,height);
    ConnectionPort port_w = new ConnectionPort(0,height/2);

    ConnectionPort[] port = {port_n,port_e,port_s,port_w};

    public abstract void setName(String name);
}
class RectCanvas extends GraphCanvas{
    @Override
    public void paint(Graphics g) {
        if(isSelected){
            port_e.drawPoint(g);
            port_n.drawPoint(g);
            port_s.drawPoint(g);
            port_w.drawPoint(g);
        }
        g.setColor(Color.BLACK);
        g.drawLine(offset,height/3+offset,width+offset,height/3+offset);
        g.drawLine(offset,height/3*2+offset,width+offset,height/3*2+offset);
        g.drawRect(offset, offset, width, height);
        g.drawString(objName, width/2-offset, height/2+(2*offset));

    }
    public void setName(String name){
        objName = name;
    }
}
class OvalCanvas extends GraphCanvas{
    @Override
    public void paint(Graphics g) {
        if(isSelected){
            port_e.drawPoint(g);
            port_n.drawPoint(g);
            port_s.drawPoint(g);
            port_w.drawPoint(g);
        }
        g.setColor(Color.BLACK);
        g.drawOval(offset, offset, width, height);
        g.drawString(objName, width/2-offset, height/2+(2*offset));
    }
    public void setName(String name){
        objName = name;
    }
}

class GroupCanvas extends GraphCanvas{
    GroupCanvas(){
        isGroupPanel = true;
    }
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g);
//        if(isSelected) {
//            setBackground(new Color(240, 128, 128, 40));
//        }else {
//            setBackground(new Color(13, 191, 140, 40));
//        }
//        System.out.println(this);
//    }
    public void setName(String name){}
}





