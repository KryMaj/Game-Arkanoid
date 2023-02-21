import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PanelGry extends JPanel implements ActionListener {
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
    private int rows = random.nextInt(5) + 4;
    private int columns = random.nextInt(5) + 4;
    private int brickWidth = 50;
    private int brickHeight = 10;
    private int spaceInRows = 5;
    private int spaceInColumns = 5;
    private int spaceFromLeft = (width - (brickWidth * columns + spaceInColumns * (columns - 1))) / 2;
    private int spaceFromUp = 10;
    private int brickPositionsX[][] = new int[columns][rows];
    private int brickPositionsY[][] = new int[columns][rows];
    private boolean brickIsAvailable[][] = new boolean[columns][rows];
    private int dx = 2;
    private int dy = -2;
    private Timer timer;
    private String gameStatus = "active";
    private int life = 1;
    JButton button1 = new JButton("Easy");
    JButton button2 = new JButton("Medium");
    JButton button3 = new JButton("Hard");



    public PanelGry() {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));


        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                brickPositionsX[i][j] = i * (brickWidth + spaceInColumns) + spaceFromLeft;
                brickPositionsY[i][j] = j * (brickHeight + spaceInRows) + spaceFromUp;
                brickIsAvailable[i][j] = true;
            }
        }
        timer = new Timer(10, this);
        timer.start();
        addKeyListener(new Control());
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics graphics) {

        if (gameStatus =="active"){
            drawPaddle(graphics);
            drawBall(graphics);
            drawBricks(graphics);
            life(graphics);
        } else{

            finish(graphics);
        }


    }


    private void drawPaddle(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(positionX, positionY, paddleWidth, paddleHeight);
        graphics.setColor(Color.WHITE);
        graphics.drawRect(positionX, positionY, paddleWidth, paddleHeight);
    }

    private void drawBall(Graphics graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillOval(ballPositionX, ballPositionY, ballDiameter, ballDiameter);

    }

    private void drawBricks(Graphics graphics) {


        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (brickIsAvailable[i][j]) {
                    graphics.setColor(Color.YELLOW);
                    graphics.fillRect(brickPositionsX[i][j], brickPositionsY[i][j], brickWidth, brickHeight);
                }
            }

        }
    }

    private void ballMovement() {
        if (ballPositionX <= 0 || ballPositionX > width - ballDiameter) {
            dx = dx * (-1);
        }

        if (ballPositionY <= 0 || ballPositionY + ballDiameter >= positionY && ballPositionX > positionX
                && ballPositionX < positionX + paddleWidth) {
            dy = dy * (-1);
        }
        ballPositionX = ballPositionX + dx;
        ballPositionY = ballPositionY + dy;
    }

    private void collisionWithBricks() {
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (brickIsAvailable[i][j] == true) {
                    if (ballPositionX > brickPositionsX[i][j]
                            && ballPositionX < brickPositionsX[i][j] + brickWidth
                            && ballPositionY + ballDiameter > brickPositionsY[i][j]
                            && ballPositionY < brickPositionsY[i][j] + brickHeight) {
                        dy = -dy;
                        brickIsAvailable[i][j] = false;
                    } else if (ballPositionY > brickPositionsY[i][j]
                            && ballPositionY < brickPositionsY[i][j] + brickHeight
                            && ballPositionX + ballDiameter > brickPositionsX[i][j]
                            && ballPositionX < brickPositionsX[i][j] + brickWidth) {
                        dx = -dx;
                        brickIsAvailable[i][j] = false;

                    }
                }
            }
        }
    }
private void setDifficulty(){
        add(button1);
        add(button3);
        add(button2);

}
    private void checkGameStatus(){
        if (ballPositionY> height){
            life = life -1;
            dy = dy * (-1);
        }

        if (life == 0){
            gameStatus = "You lose!";
        }
        int indestructible =0;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                if (brickIsAvailable[i][j] ==true)
                    indestructible +=1;
            }
        }

        if (indestructible == 0){
            gameStatus = "You Win!";
        }

    }

    private void finish(Graphics graphics){
        Font text = new Font("Arial", Font.BOLD, 20);
        FontMetrics dimension = getFontMetrics(text);
        graphics.setColor(Color.WHITE);
        graphics.setFont(text);
        graphics.drawString(gameStatus, (width-dimension.stringWidth(gameStatus))/2, height/2);

    }

    private void life(Graphics graphics){
        Font text = new Font("Arial", Font.BOLD, 20);
        graphics.setColor(Color.RED);
        graphics.setFont(text);
        graphics.drawString("life = " + life, 20, 20);
    }
   @Override
    public void actionPerformed(ActionEvent e) {
        checkGameStatus();
        collisionWithBricks();
        ballMovement();
        repaint();
    }

    private class Control extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_LEFT && positionX > 0) {
                positionX = positionX - 10;
            }
            if (keyCode == KeyEvent.VK_RIGHT && positionX < width - paddleWidth) {
                positionX = positionX + 10;
            }
        }
    }



}