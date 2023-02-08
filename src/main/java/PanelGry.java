import javax.swing.*;
import java.awt.*;

public class PanelGry extends JPanel {
 private final int width = 600;
 private final int height = 500;


    public PanelGry() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics graphics) {
        graphics.setColor(Color.BLUE);
        graphics.fillRect(100, 100, 100, 100);
    }
}