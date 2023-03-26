package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements MouseListener,MouseMotionListener {
    ButtonState state;
    ArrayList<Component> canvasList;

    Canvas(){
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        state.getMode().press(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        state.getMode().release(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        state.getMode().drag(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}