import javax.swing.*;
import java.awt.*;
import java.util.Random;

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
 private Random random = new Random();
 private int rows = random.nextInt(5) +4;
 private int columns = random.nextInt(5) +4;
 private int brickWidth = 50;
 private int brickHeight = 10;
 private int spaceInRows= 5;
 private int spaceInColumns =5;
 private int spaceFromLeft = (width -(brickWidth*columns + spaceInColumns*(columns-1)))/2;
 private int spaceFromUp = 10;
 private int brickPositionsX[][] = new int[columns][rows];
 private int brickPositionsY[][] = new int[columns][rows];
 private boolean brickIsAvailable[][] = new boolean[columns][rows];






    public PanelGry() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));


        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                brickPositionsX[i][j] =i*(brickWidth +spaceInColumns) + spaceFromLeft;
                brickPositionsY[i][j] =j*(brickHeight +spaceInRows) + spaceFromUp;
                brickIsAvailable[i][j] = true;


            }

        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics graphics) {
        drawPaddle(graphics);
        drawBall(graphics);
        drawBricks(graphics);
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

    private void drawBricks(Graphics graphics){


        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (brickIsAvailable[i][j]) {
                    graphics.setColor(Color.YELLOW);
                    graphics.fillRect(brickPositionsX[i][j], brickPositionsY[i][j], brickWidth, brickHeight);
                }
            }

        }
    }


}