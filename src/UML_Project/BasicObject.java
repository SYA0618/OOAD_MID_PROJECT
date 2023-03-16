package UML_Project;

import javax.swing.*;
import java.awt.event.*;

public class BasicObject extends JLabel {

    MouseListener mouseListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
        }
    };
}
