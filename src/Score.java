import java.awt.*;

public class Score extends Rectangle{
    static int GAME_WIDTH;
    static int GAME_HEIGHT;
    int player1;//current score of the players
    int player2;
    Score(int GAME_WIDTH,int GAME_HEIGHT){
        Score.GAME_WIDTH=GAME_WIDTH;
        Score.GAME_HEIGHT=GAME_HEIGHT;

    }
    public void draw (Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consola",Font.PLAIN,60));
        g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT);
        g.drawString(String.valueOf(player1),(GAME_WIDTH/2)-85,50); //we design this on the left because player 1
        //-85 is to gave a space from the left to write even bigger scores
        g.drawString(String.valueOf(player2),(GAME_WIDTH/2)+20,50);

    }
}
