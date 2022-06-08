import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Write a description of class Square here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Square extends JPanel
{
    private Piece p;
    public static boolean pieceSelected = false;
    private boolean isValid;// says whether or not a square is a valid spot to be
    
    private final int x;
    private final int y;
    public Square(int rank, int color, int x, int y)
    {
        this.x = x;
        this.y = y;
        
        isValid = (x + y) % 2 == 1;
        p = new Piece(rank, color, isValid);
        setGameBackground();
        
        setLayout(new GridLayout(1,1));
        add(p);

        addMouseListener(new ClickListener());
    }

    public Square(int rank, int color, boolean valid, int x, int y)
    {
        this.x = x;
        this.y = y;
        
        isValid = valid;
        p = new Piece(rank, color, isValid);
        setGameBackground();

        setLayout(new GridLayout(1,1));
        add(p);
        addMouseListener(new ClickListener());
    }

    public int getColor()
    {
        return p.getColor();
    }

    public int getRank()
    {
        return p.getRank();
    }
    
    public int getRow()
    {
        return x;
    }
    
    public int getCol()
    {
        return y;
    }

    public void setColor(int c)
    {
        p.setColor(c);
    }

    public void setRank(int r)
    {
        p.setRank(r);
    }

    public boolean getValid()
    {
        return isValid;
    }
    
    public Piece getPiece() 
    {
        return p;
    }

    public void setGameBackground() 
    {
        if(isValid) 
        {
            setBackground(Color.white);
        }
        else 
        {
            setBackground(new Color(40,40,40));
        }
    }


    public void reset()
    {
        this.setRank(0);
        this.setColor(0);
    }

    private class ClickListener extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            Square temp = (Square) e.getSource();

            if(pieceSelected) 
            {
                resetBackground();
                if(temp.getColor() == 0 && isValid)
                {
                    Main.endSquare = temp;
                }
            }
            
            if(isValid && !pieceSelected) 
            {
                if(temp.getColor() == Main.turn) 
                {
                    temp.setBackground(Color.yellow);
                    pieceSelected = true;
                    Main.startSquare = temp;
                }
                else if(Main.endSquare == null)
                {
                    Main.startSquare = null;
                }
            }
            
            if(Main.startSquare != null && Main.endSquare != null)
            {
                Main.move();
            }
            
        }

        public void resetBackground() 
        {
            for(Square[] row : Board.board) {
                for(Square col : row) {
                    if(col.getBackground().equals(Color.yellow)) {
                        col.setGameBackground();
                        pieceSelected = false;
                        return;
                    }
                }
            }
        }
    }
}