package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


abstract class IconButton extends JToggleButton {
    private final int id;
    private ButtonState state;
    private Canvas canvas;

    IconButton(String iconPath, String tipText, int id, ButtonState state,Canvas canvas) {
        ImageIcon icon = new ImageIcon(iconPath);
        this.setSize(80, 80);
        Image temp1 = icon.getImage().getScaledInstance(this.getWidth(), this.getHeight(), icon.getImage().SCALE_DEFAULT);//根據Button Size調整
        icon = new ImageIcon(temp1);
        //this.setContentAreaFilled(false); //按鈕邊框顯示
        this.setIcon(icon);
        this.setToolTipText(tipText);
        this.addActionListener(listener);
        this.id = id;
        this.state = state;
        this.canvas = canvas;

    }
    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            state.setMode(id);
            for(Component component:canvas.getComponents()){
                ((GraphCanvas) component).isSelected = false;
                canvas.repaint();
            }
        }
    };

}

class SelectButton extends IconButton {
    SelectButton(String path, String tip, int id, ButtonState state, Canvas canvas) {
        super(path, tip, id, state, canvas);
    }


}

class AssociationButton extends IconButton {
    AssociationButton(String path, String tip, int id, ButtonState state, Canvas canvas) {
        super(path, tip, id, state, canvas);
    }
}

class Generalization extends IconButton {
    Generalization(String path, String tip, int id, ButtonState state, Canvas canvas) {
        super(path, tip, id, state, canvas);
    }

}

class Composition extends IconButton {
    Composition(String path, String tip, int id, ButtonState state, Canvas canvas) {
        super(path, tip, id, state, canvas);
    }
}

class my_Class extends IconButton {
    my_Class(String path, String tip, int id, ButtonState state, Canvas canvas) {
        super(path, tip, id, state, canvas);
    }


}

class use_Case extends IconButton {
    use_Case(String path, String tip, int id, ButtonState state, Canvas canvas) {
        super(path, tip, id, state, canvas);
    }
}
