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
    public static Square squareSelected = null;

    private JPanel cardManager = new JPanel();
    private JPanel moveOptionPanel = new JPanel();
    private JLabel moveOptionIcon = new JLabel(new ImageIcon(getClass().getResource("resources/moveOption.png")));
    
    private final int row; //Row
    private final int col; // Column
    private boolean isDark;

    private CardLayout cards = new CardLayout();

    public Square(int color, int x, int y)
    {
        row = x;
        col = y;
        isDark = (row + col) % 2 == 1;

        cardManager.setLayout(cards);
        cardManager.setOpaque(false);

        p = getStartingPiece();
        p.setOpaque(false);

        moveOptionPanel.setOpaque(false);
        moveOptionPanel.add(moveOptionIcon);

        cardManager.add(p, "Piece");
        cardManager.add(moveOptionPanel, "MoveOption");

        add(cardManager);

        setGameBackground();
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
        if(isDark) 
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

            if(squareSelected != null) 
            {
                squareSelected.setGameBackground();
            }
            
            if(squareSelected != temp && temp.getColor() > 0)
            {
                if(Main.turn == temp.getColor())
                {
                    colorSquare(temp);
                }
            }
            
            if(squareSelected == temp)
            {
                squareSelected = null;
            }
            else
            {
                squareSelected = temp;
            }
        }
        
        private void colorSquare(Square temp)
        {
            if(isDark)
            {
                temp.setBackground(new Color(186, 202, 43));
            }
            else
            {
                temp.setBackground(new Color(246, 246, 105));
            }
        }

    }
}