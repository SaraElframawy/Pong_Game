import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH =1000;
    static final int GAME_HEIGHT =(int)(GAME_WIDTH*(0.555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH,GAME_HEIGHT);
    static final int BALL_DIAMETER =20;
    static final int PADDLE_WIDTH =25;
    static final int PADDLE_HEIGHT=100;
    Thread GameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball; //used in move() and draw
    Score score;
    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH,GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        GameThread = new Thread(this);
        GameThread.start();


    }
    public void newBall(){
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);

    }
    public void  newPaddles(){
        paddle1=new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);//unik id number 1
        paddle2= new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);


    }
    public void paint(Graphics g){
        image=createImage(getWidth(),getHeight());
        graphics=image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0,this);

    }
    public void draw(Graphics g){
        paddle1.draw(g); //to draw the paddle of two players
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);

    }
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();

    }
    public void checkCollusion(){ //stop paddle at the frame edge
        if(paddle1.y<=0)
            paddle1.y=0; //stop the paddle at the top through setting its y axe to 0
        if(paddle1.y>=GAME_HEIGHT-PADDLE_HEIGHT)
            paddle1.y=GAME_HEIGHT-PADDLE_HEIGHT;
//stop the paddle to the very button through setting the y axe to  the game height -the top part of the paddle
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y>=GAME_HEIGHT-PADDLE_HEIGHT)
            paddle2.y=GAME_HEIGHT-PADDLE_HEIGHT;
        //set limits for the ball to bounce within the edges
        if(ball.y<=0) {
            ball.setYDirection(-ball.yVelocity);
            //when it touches the edges it is going to return the ball in the opposite direction
        }
        if(ball.y >= (GAME_HEIGHT-BALL_DIAMETER)){
            ball.setYDirection(-ball.yVelocity);
        }

        //bounces ball off paddle
        //check if the ball is intersecting with paddle1
        //since the ball class is inheriting all attributes from the rectangle super class including the intersects method
        if(ball.intersects(paddle1)){
            ball.xVelocity= Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--; //that means it is going upwards
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);

        }
        if (ball.intersects(paddle2)) {
            ball.xVelocity=Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.xVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity); //flip the direction to go to the left
            ball.setYDirection(ball.yVelocity);

        }
        // Give a player one point and creates new paddles &ball
        if(ball.x<=0){
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("player 2:"+score.player2);
        }
        if(ball.x>=GAME_WIDTH-BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("player1:"+score.player1);
        }






    }
    public void run(){
        //game loop
        long lastTime= System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000/amountOfTicks;
        double delta=0;
        while (true){
            long now=System.nanoTime();
            delta +=(now-lastTime)/ns;
            lastTime= now;
            if (delta>=1){
                move();
                checkCollusion();
                repaint();
                delta--;

            }

        }

    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);



        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }
}
