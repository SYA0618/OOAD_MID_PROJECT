package UML_Project;

import javax.swing.*;
import java.awt.*;

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
        groupObject = new GroupObject(group, canvas);
        groupObject.setGroup();
        group.setAccelerator(KeyStroke.getKeyStroke('G',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        JMenuItem unGroup = new JMenuItem("UnGroup");
        unGroup.addActionListener(e -> System.out.println("456"));
        unGroup.setAccelerator(KeyStroke.getKeyStroke('U',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        this.add(group);
        this.add(unGroup);

    }
}
