package UML_Project;

import javax.swing.*;
import java.awt.event.*;

public abstract class BasicObject extends JLabel {
    Canvas canvas;
    abstract void paint(MouseEvent e);
}
class PaintSelct extends BasicObject  {
    PaintSelct(Canvas canvas){
        this.canvas = canvas;
    }
    @Override
    void paint(MouseEvent e) {
        if(e != null) {
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/resource/select.png"));
            label.setBounds(e.getX(), e.getY(), 80, 80);
            canvas.add(label);
            canvas.repaint();

        }
    }
}

class Paint_My_Class extends BasicObject {
    Paint_My_Class(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    void paint(MouseEvent e) {
        if (e != null) {
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/resource/class.png"));
            label.setBounds(e.getX(), e.getY(), 80, 80);
            canvas.add(label);
            canvas.repaint();
        }
    }
}
class Paint_Use_Case extends BasicObject {
    Paint_Use_Case(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    void paint(MouseEvent e) {
        if (e != null) {
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/resource/use_case.png"));
            label.setBounds(e.getX(), e.getY(), 80, 80);
            canvas.add(label);
            canvas.repaint();
        }
    }
}