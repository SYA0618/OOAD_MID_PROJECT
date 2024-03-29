package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Menu extends JMenu {
    GroupObject groupObject;
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
        group.addActionListener(group_ActionListener);
        group.setAccelerator(KeyStroke.getKeyStroke('G',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        JMenuItem unGroup = new JMenuItem("UnGroup");
        unGroup.addActionListener(unGroup_ActionListener);
        unGroup.setAccelerator(KeyStroke.getKeyStroke('U',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        JMenuItem chName = new JMenuItem("Change Name");
        chName.addActionListener(changeObjectName);
        chName.setAccelerator(KeyStroke.getKeyStroke('N',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        this.add(group);
        this.add(unGroup);
        this.add(chName);

    }
    boolean checkSum(){
        int count = 0;
        for(Component component : canvas.getComponents()){
            if(((GraphCanvas) component).isSelected){count++;}
        }
        return count > 1;
    }
    ActionListener group_ActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            groupObject = new GroupObject();
            groupObject.groupFunction(canvas);
        }
    };

    ActionListener unGroup_ActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (Component component:canvas.getComponents()){
                if(((GraphCanvas)component).isSelected&&((GraphCanvas)component).isGroupPanel){
                    for (Component component1:((GroupObject)component).groupList){
                        ((GraphCanvas)component1).isGroup=false;
                    }
                    canvas.remove(component);
                }
            }
            canvas.repaint();
        }
    };

    ActionListener changeObjectName = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame jFrame = new JFrame();
            String getMessage = JOptionPane.showInputDialog(jFrame, "Enter Object Name");
            if (getMessage==null||checkSum()) {
                JOptionPane.showMessageDialog(jFrame, "Can't enter null or select greater than two object");
                return;
            }
            for(Component component: canvas.getComponents()){
                if(((GraphCanvas)component).isSelected){
                    component.setName(getMessage);
                }
            }
            canvas.repaint();
        }
    };
}
