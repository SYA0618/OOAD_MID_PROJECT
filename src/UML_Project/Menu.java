package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public abstract class Menu extends JMenu {
    GroupObject groupObject ;
    protected Canvas canvas;

}

class FileMenu extends Menu{
    FileMenu(){
        this.setText("File");
        this.add(new JMenuItem("no.1"));
    }


}

class EditMenu extends Menu{
    EditMenu(Canvas canvas){
        this.canvas = canvas;
        this.setText("Edit");
        JMenuItem group = new JMenuItem("Group");
        group.addActionListener(actionListener);
        group.setAccelerator(KeyStroke.getKeyStroke('G',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        JMenuItem unGroup = new JMenuItem("UnGroup");
        unGroup.addActionListener(e -> System.out.println("456"));
        unGroup.setAccelerator(KeyStroke.getKeyStroke('U',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        this.add(group);
        this.add(unGroup);

    }

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            groupObject = new GroupObject();
            groupObject.groupObject1(canvas);
        }
    };

}
