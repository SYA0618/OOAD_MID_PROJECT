package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GroupObject extends JPanel {
    JMenuItem group;
    Canvas canvas;
    GroupObject(JMenuItem group, Canvas canvas){
        this.canvas = canvas;
        this.group = group;

    }
    ArrayList<Component> groupList = new ArrayList<Component>();
    protected GroupCanvas groupPanel;

    int maxHeight(){
        int max_Y = 0;
        for(Component component:groupList){
            if(component.getY() > max_Y){
                max_Y = component.getY();
            }
        }
        return max_Y;
    }
    int minHeight(){
        int min_Y = 1000;
        for(Component component:groupList){
            if(component.getY() < min_Y){
                min_Y = component.getY();
            }
        }
        return min_Y;
    }

    int maxWidth(){
        int max_X = 0;
        for(Component component:groupList){
            if(component.getX() > max_X){
                max_X = component.getX();
            }
        }
        return max_X;
    }
    int minWidth(){
        int min_X = 1000;
        for(Component component:groupList){
            if(component.getX() < min_X){
                min_X = component.getX();
            }
        }
        return min_X;
    }
    void setGroup(){
        group.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(canvas.getComponentCount() <1) return;
                for (Component component : canvas.getComponents()){
                    groupPanel = new GroupCanvas();
                    if(((GraphCanvas) component).isSelected){
                        groupList.add(component);
                    }
                }
                groupPanel.setBounds(minWidth(), minHeight(), maxWidth() - minWidth() + groupList.get(0).getWidth(), maxHeight() - minHeight() + groupList.get(0).getHeight());
                groupPanel.setBackground(new Color(13, 191, 140, 40));
                canvas.add(groupPanel);
                canvas.repaint();
                System.out.println(groupList);
//
//                if (canvas.getComponentCount() < 1) return;
//                    groupPanel = new GroupCanvas();
//                    for (Component component : canvas.getComponents()) {
//                        if (((GraphCanvas) component).isSelected) {
//                            ((GraphCanvas) component).isSelected = false;
//                            canvas.repaint();
//                            groupPanel.add(component);
//                            canvas.remove(component);
//                        }
//                    }
//                    groupPanel.setBounds(minWidth(), minHeight(), maxWidth() - minWidth() + groupPanel.getComponent(0).getWidth(), maxHeight() - minHeight() + groupPanel.getComponent(0).getHeight());
//                    groupPanel.setBackground(new Color(13, 191, 140, 40));
//
//                    canvas.add(groupPanel);
//                    canvas.repaint();

            }
        });
    }

}
