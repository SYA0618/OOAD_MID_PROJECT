package UML_Project;

import javax.swing.*;
import java.awt.*;

public class ConnectionPort extends Point {
    void drawPoint(Graphics g){
        int height = 10;
        int width = 10;
        g.fillRect(x,y, width, height);
    }
    protected ConnectionPort(int x, int y){
        super(x, y);
    }

}