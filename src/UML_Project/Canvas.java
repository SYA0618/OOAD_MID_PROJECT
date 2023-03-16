package UML_Project;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class Canvas extends JPanel implements MouseListener {
    ButtonState state;

    Canvas(){
        this.addMouseListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        state.getMode().paint(e);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
abstract class BasicMode {
    Canvas canvas;
abstract void paint(MouseEvent e);

}
class PaintSelct extends BasicMode  {
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

class Paint_My_Class extends BasicMode {
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
class Paint_Use_Case extends BasicMode {
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