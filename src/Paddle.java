import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle {
    int id;
    int yVelocity; //up and down bounce when pressing the button
    int speed=10;

    Paddle(int x, int y,int PADDLE_WIDTH, int PADDLE_HEIGHT, int ID){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=ID;

    }
    public void keyPressed(KeyEvent e){
        switch (id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){ //if someone presses the w key it will execute
                setYDirection(-speed); //move up on the y axes
                move();
            } if(e.getKeyCode()==KeyEvent.VK_S){ //if someone presses the s key it will execute
                setYDirection(speed); //move up on the y axes
                move();
            }
                break;
            case 2:
              if(e.getKeyCode()== KeyEvent.VK_UP){
                  setYDirection(-speed);
                  move();
              }
              if(e.getKeyCode()==KeyEvent.VK_DOWN){
                  setYDirection(speed);
                  move();
              }
              break;


        }

    }
    public void keyReleased(KeyEvent e){
        switch (id){
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W){
                setYDirection(0);
                move();

            }
                if(e.getKeyCode()==KeyEvent.VK_S){
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP){
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode()==KeyEvent.VK_DOWN){
                    setYDirection(0);
                    move();
                }
                break;


        }


    }
    public void setYDirection(int yDirection){
        yVelocity=yDirection;

    }
    public void move(){
        y=y+yVelocity;


    }
    public void draw(Graphics g){
        if(id==1){
            g.setColor(Color.BLUE);
        } else {
            g.setColor(Color.RED);
        }
        g.fillRect(x,y,width,height);


    }
}
