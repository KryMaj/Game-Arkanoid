import javax.swing.*;

public class Okno {

    public Okno() {
        JFrame okno = new JFrame();

    PanelGry panel = new PanelGry();

okno.add(panel);
okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
okno.pack();
okno.setVisible(true);
okno.setResizable(false);
okno.setTitle("GAME Arkanoid");

    }



}
