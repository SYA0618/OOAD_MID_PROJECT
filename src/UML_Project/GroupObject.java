package UML_Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GroupObject extends JPanel {
    JMenuItem group;
    Canvas canvas;

    Component select = null;

    GroupObject(JMenuItem group, Canvas canvas){
        this.canvas = canvas;
        this.group = group;

    }
    ArrayList<Component> groupList;
    protected GroupCanvas groupPanel;
    boolean checkSum(){
        int count = 0;
        for(Component component : canvas.getComponents()){
            if(((GraphCanvas) component).isSelected){count++;}
        }
        if(count > 1){
            return true;
        }else{
            return false;
        }
    }
    int maxY(){
        int max_Y = 0;
        for(Component component:groupList){
            if(component.getY() > max_Y){
                max_Y = component.getY();
            }
        }
        return max_Y;
    }
    int minY(){
        int min_Y = 1000;
        for(Component component:groupList){
            if(component.getY() < min_Y){
                min_Y = component.getY();
            }
        }
        return min_Y;
    }

    int maxX(){
        int max_X = 0;
        for(Component component:groupList){
            if(component.getX() > max_X){
                max_X = component.getX();
            }
        }
        return max_X;
    }
    int minX(){
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
                if(!checkSum()||canvas.getComponentCount() <1) return;
                groupPanel = new GroupCanvas();
                groupList = new ArrayList<Component>();
                for (Component component : canvas.getComponents()){

                    if(((GraphCanvas) component).isSelected){
                        groupList.add(component);
                    }else{
                        continue;
                    }
                }
                groupPanel.setBounds(minX(), minY(), maxX() - minX() + 110, maxY() - minY() + 90);
                groupPanel.setBackground(new Color(13, 191, 140, 40));
                canvas.add(groupPanel,0);
                canvas.repaint();
                System.out.println(groupList);
            }
        });
    }

}
