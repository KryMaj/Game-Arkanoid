import javax.swing.*;
import java.awt.*;

public class Okno {


    public Okno() {
        JFrame okno = new JFrame();

    PanelGry panel = new PanelGry();
//        panel.add(button1);
//        panel.add(button2);
//        panel.add(button3);
//

okno.add(panel);
okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
okno.pack();
okno.setVisible(true);
okno.setResizable(false);
okno.setTitle("GAME Arkanoid");


    }



}
