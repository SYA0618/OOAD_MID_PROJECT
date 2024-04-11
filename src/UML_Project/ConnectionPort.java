package UML_Project;

import javax.swing.*;
import java.awt.*;

public class ConnectionPort extends Point {
    public void drawPoint(Graphics g){
        int height = 10;
        int width = 10;
        g.fillRect(x,y, width, height);
    }
    ConnectionPort(int x, int y){
        super(x, y);
    }

}