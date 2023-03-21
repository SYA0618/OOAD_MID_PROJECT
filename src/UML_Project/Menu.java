package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Menu extends JMenu {
    protected Canvas groupPanel = new Canvas();

    protected Canvas canvas;
    int maxHeight(){
        int max_Y = 0;
        for(Component component:groupPanel.getComponents()){
            if(component.getY() > max_Y){
                max_Y = component.getY();
            }
        }
        return max_Y;
    }
    int minHeight(){
        int min_Y = 1000;
        for(Component component:groupPanel.getComponents()){
            if(component.getY() < min_Y){
                min_Y = component.getY();
            }
        }
        return min_Y;
    }

    int maxWidth(){
        int max_X = 0;
        for(Component component:groupPanel.getComponents()){
            if(component.getX() > max_X){
                max_X = component.getX();
            }
        }
        return max_X;
    }
    int minWidth(){
        int min_X = 1000;
        for(Component component:groupPanel.getComponents()){
            if(component.getX() < min_X){
                min_X = component.getX();
            }
        }
        return min_X;
    }

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
        JMenuItem item1 = new JMenuItem("Group");


        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Component component:canvas.getComponents() ){
                    if (((GraphCanvas) component).isSelected) {
                        groupPanel.add(component);
                        canvas.remove(component);
                    }
                }
                groupPanel.setBounds(minWidth(),minHeight(),maxWidth()-minWidth()+groupPanel.getComponent(0).getWidth(),maxHeight()-minHeight()+groupPanel.getComponent(0).getHeight());
                groupPanel.setBackground(new Color(13,191,140,40));
                canvas.add(groupPanel);
                canvas.repaint();
            }
        });
        item1.setAccelerator(KeyStroke.getKeyStroke('G',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        JMenuItem item2 = new JMenuItem("UnGroup");
        item2.addActionListener(e -> System.out.println("456"));
        item2.setAccelerator(KeyStroke.getKeyStroke('U',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        this.add(item1);
        this.add(item2);

    }
}
