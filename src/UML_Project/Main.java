package UML_Project;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

public class Main {

    public static void main(String[] args) throws IOException {
        Canvas panelofCanvas = null;
        JPanel panelofButton = null;
        /*框架 設定*/
        JFrame frame = new JFrame("UML editor");
        frame.setAlwaysOnTop(false);
        frame.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int)screenSize.getWidth() / 4 - frame.getWidth() / 2;
        int y = (int)screenSize.getHeight() / 4 - frame.getHeight() / 2;
        frame.setBounds(x, y, 800, 700);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setCursor(new Cursor(Cursor.HAND_CURSOR)); //改鼠標圖案
        frame.setIconImage(ImageIO.read(new File("src/resource/icon.png")));

        /*Menu 設定*/
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.add(new JMenuItem("No.1"));
        bar.add(file);
        JMenu edit = new JMenu("Edit");
        edit.add(new JMenuItem("No.1"));
        bar.add(edit);
        frame.setJMenuBar(bar);
        /*分割面板*/
        JSplitPane pane = new JSplitPane();
        pane.setEnabled(false);
        panelofButton = new JPanel();
        panelofCanvas = new Canvas();
        ButtonState state = new ButtonState(panelofCanvas);
        /*實作左半邊*/
        panelofButton.setLayout(new GridLayout(6,0));
        IconButton[] btn = {
                new SelectButton("src/resource/select.png", "select", 0, state, panelofCanvas),
                new AssociationButton("src/resource/association_line.png", "association line", 1, state, panelofCanvas),
                new Generalization("src/resource/generalization_line.png", "generalization line", 2, state, panelofCanvas),
                new Composition("src/resource/composition_line.png", "composition line", 3, state, panelofCanvas),
                new my_Class("src/resource/class.png", "class", 4, state, panelofCanvas),
                new use_Case("src/resource/use_case.png", "use case", 5, state, panelofCanvas)
        };
        ButtonGroup bg = new ButtonGroup();
        for(int i = 0; i < 6; i++) {
            bg.add(btn[i]);
            panelofButton.add(btn[i]);
        }
        btn[0].setSelected(true);
        pane.setLeftComponent(panelofButton);
        /*右半邊畫畫*/

        panelofCanvas.setBackground(Color.WHITE);
        panelofCanvas.setLayout(null);//layout set null防止自動排版
        pane.setRightComponent(panelofCanvas);
        frame.add(pane);




        frame.setVisible(true);
    }
}