import javax.swing.*;
import java.awt.*;

public class PanelGry extends JPanel {
 private final int width = 600;
 private final int height = 500;
 private int paddleWidth = 50;
 private final int paddleHeight = 10;
 private int positionX = 300;
 private final int positionY = 480;
 private final int ballDiameter = 20;
 private int ballPositionX = 100;
 private int ballPositionY = 100;



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
        drawPaddle(graphics);
        drawBall(graphics);
    }

    private void drawPaddle(Graphics graphics){
        graphics.setColor(Color.RED);
        graphics.fillRect(positionX, positionY, paddleWidth, paddleHeight);
        graphics.setColor(Color.WHITE);
        graphics.drawRect(positionX, positionY, paddleWidth, paddleHeight);
    }

    private void drawBall(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.fillOval(ballPositionX, ballPositionY, ballDiameter, ballDiameter);

    }
}