import javax.swing.*; //for making windows (Jframe, JPanel)
import java.awt.*; //for color
import java.util.*;
import java.awt.event.*;
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static int turn = 1;

    static Square startSquare = null;
    static Square endSquare = null;
    static Board game = new Board();

    public static void main(String[] args)
    {
        game.setSize(900,900);
        game.setResizable(false);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int gameWon = 0;
        boolean valid;

        game.setVisible(true);
    }

    public static void startRound()
    {
        if(turn == 1)
        {
            System.out.println("Red's's Turn");
        }
        else if(turn == 2)
        {
            System.out.println("Black's Turn");
        }
    }

    /**
     * Precondition: startSquare and endSquare must not be null
     */
    public static void move()
    {
        boolean move = game.move(turn, startSquare.getRow(),startSquare.getCol(),endSquare.getRow(),endSquare.getCol());

        if(!move) 
        {
            endSquare = null;
            startSquare = null;
            return;  
        }
        
        if(turn == 1)
        {
            turn = 2;
        }
        else if(turn == 2)
        {
            turn = 1;
        }
        
        int gameWon = game.gameWinner();
        
        if(gameWon > 0) gameWin(gameWon);
        
        endSquare = null;
        startSquare = null;
    }
    
    public static void gameWin(int gameStatus) 
    {
        if (gameStatus == 1)
        {
            System.out.println("Red Wins");
        }
        else if (gameStatus == 2)
        {
            System.out.println("Black Wins");
        }
        else
        {
            System.out.println("You broke it");
        }
    }
}