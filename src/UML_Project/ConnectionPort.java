package UML_Project;

import javax.swing.*;
import java.awt.*;

public class ConnectionPort extends JPanel {
    private final int x;
    private final int y;

    protected ConnectionPort(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        int height = 10;
        int width = 10;
        g.fillRect(x,y, width, height);
    }
}