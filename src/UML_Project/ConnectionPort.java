package UML_Project;

import javax.swing.*;
import java.awt.*;

public class ConnectionPort extends JPanel {
    int x;
    int y;
    int height = 10;
    int width = 10;
    protected ConnectionPort(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2 = (Graphics2D) g;
        Rectangle r = new Rectangle(x,y,width,height);
        graphics2.fillRect(r.x, r.y, width, height);
    }
}
