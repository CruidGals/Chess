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
    
    private final int row; //Row
    private final int col; // Column
    public Square(int color, int x, int y)
    {
        row = x;
        col = y;
        
        p = getStartingPiece();
        setGameBackground();
        
        setLayout(new GridLayout(1,1));
        add(p);

        addMouseListener(new ClickListener());
    }


    public int getColor()
    {
        return p.getColor();
    }
    
    public int getRow()
    {
        return row;
    }
    
    public int getCol()
    {
        return col;
    }

    public int getRank()
    {
        return p.getRank();
    }
    
    public void setColor(int c)
    {
        p.setColor(c);
    }
    
    public Piece getPiece() 
    {
        return p;
    }

    public void setGameBackground() 
    {
        if((row + col) % 2 == 1) 
        {
            setBackground(new Color(125, 148, 93));
        }
        else 
        {
            setBackground(new Color(238, 238, 213));
        }
    }
    
    private Piece getStartingPiece()
    {
        int color = 0;
        Piece piece;
        if(row < 2) {
            color = 2; //Color is black
        } else if (row > 5) {
            color = 1; //Color is white
        } else {
            color = 0; //No Piece
        }
        
        if(row == 0 || row == 7) { //Checks if row is an end row
            switch(col) { //Creates piece based on starting position
                case 0: piece = new Rook(color);
                        break;
                case 1: piece = new Knight(color);
                        break;
                case 2: piece = new Bishop(color);
                        break;
                case 3: piece = new Queen(color);
                        break;
                case 4: piece = new King(color);
                        break;
                case 5: piece = new Bishop(color);
                        break;
                case 6: piece = new Knight(color);
                        break;
                default: piece = new Rook(color);
                        break;
            }
        }
        else if (row == 1 || row == 6) { //Checks if row is 2nd row from end row
            piece = new Pawn(color);
        }
        else {
            piece = new Piece(color);
        }
        
        return piece;
    }


    public void reset()
    {

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