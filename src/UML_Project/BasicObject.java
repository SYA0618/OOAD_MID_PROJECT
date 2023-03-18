package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class BasicObject extends JLabel {
    void createObject(Canvas canvas, String graphPath, MouseEvent e){
        JLabel label = new JLabel();
        label.setIcon(new ImageIcon(graphPath));
        label.setBounds(e.getX()-40, e.getY()-40, 80, 80);
        canvas.add(label);
        canvas.repaint();


    }
    Canvas canvas;
    abstract void press(MouseEvent e);
    abstract void drag(MouseEvent e);

}
class Select extends BasicObject  {
    Select(Canvas canvas){
        this.canvas = canvas;
    }

    private Point mousePt;
    private Component select;
    @Override
    void press(MouseEvent e) {

            for (int i = canvas.getComponents().length - 1; i >= 0; i--) {
                int offset_X = canvas.getComponent(i).getX();
                int offset_Y = canvas.getComponent(i).getY();
                mousePt = e.getPoint();
                boolean result = canvas.getComponent(i).contains(e.getX() - offset_X, e.getY() - offset_Y);
                if (result) {
                    select = canvas.getComponent(i);
                    canvas.getComponent(i);
                    canvas.remove(canvas.getComponent(i));
                    canvas.add(select);
                    break;
                }else{
                    select = null;
                }
            }
        }



    void drag(MouseEvent e){
        if(select!=null) {
            int dx = e.getX() - mousePt.x;
            int dy = e.getY() - mousePt.y;
            select.setLocation(select.getX() + dx, select.getY() + dy);
            select.repaint();
            mousePt = e.getPoint();
        }


        }



}

class Paint_My_Class extends BasicObject {
    Paint_My_Class(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    void press(MouseEvent e) {
        createObject(this.canvas, "src/resource/class.png", e);
    }
    void drag(MouseEvent e){};
}
class Paint_Use_Case extends BasicObject {
    Paint_Use_Case(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    void press(MouseEvent e) {
        createObject(this.canvas, "src/resource/use_case.png", e);
    }
    void drag(MouseEvent e){};
}