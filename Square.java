import javax.swing.*;
import javax.swing.border.*;

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
    private Border attackMoveOption = BorderFactory.createLineBorder(Color.red, 3);
    private boolean showingMoveOption = false;
    
    private final int row; //Row
    private final int col; // Column
    private boolean isDark;

    private int timesCheckedByWhite = 0;
    private int timesCheckedByBlack = 0;

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
        moveOptionPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

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

    public Piece getPiece() 
    {
        return p;
    }
    
    public boolean isShowingMoveOption()
    {
        return showingMoveOption;
    }

    public boolean isCheckedByWhite()
    {
        if(timesCheckedByWhite > 0) {
            return true;
        }
        return false;
    }
    
    public boolean isCheckedByBlack()
    {
        if(timesCheckedByBlack > 0) {
            return true;
        }
        return false;
    }

    public void setCheckedByColor(int color)
    {
        if(color == 1) {
            timesCheckedByWhite++;
        } else if (color == 2) {
            timesCheckedByBlack++;
        }
    }

    public void clearCheckedByColor()
    {
        timesCheckedByBlack = 0;
        timesCheckedByWhite = 0;
    }

    public void setP(Piece v)
    {
        remove(p);
        p = v;
        cardManager.add(p, "Piece");
        cardManager.revalidate();
        cards.show(cardManager, "Piece");
    } 

    public void setColor(int c)
    {
        p.setColor(c);
    }

    public void createPawn()
    {
        p = new Pawn(2, this);
        cards.show(cardManager, "MoveOption");
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

    //Move Display Options
    public void displayPossibleMoves()
    {
        if(p.getRank() == 1) {
            ((Pawn) p).togglePieceMoveOptions(false);
        } else if(p.getRank() == 2) {
            ((Bishop) p).togglePieceMoveOptions(false);
        } else if(p.getRank() == 3) {
            ((Knight) p).togglePieceMoveOptions(false);
        } else if(p.getRank() == 4) {
            ((Rook) p).togglePieceMoveOptions(false);
        } else if(p.getRank() == 5) {
            ((Queen) p).togglePieceMoveOptions(false);
        } else if(p.getRank() == 6) {
            ((King) p).togglePieceMoveOptions(false);
        }
    }

    public static void hidePossibleMoves()
    {
        for(Square[] row : Board.board)
        {
            for(Square col : row)
            {
                if(col.isShowingMoveOption())
                {
                    col.toggleMoveOption();
                }
            }
        }
    }

    public void toggleMoveOption()
    {
        if(p.getColor() == Main.turn) {
            return;
        }
        if(!showingMoveOption)
        {
            if(p.getColor() > 0)
            {
                setBorder(attackMoveOption);
            }
            else
            {
                cards.show(cardManager, "MoveOption");
            }
            showingMoveOption = true;
        }
        else
        {
            setBorder(null);
            cards.show(cardManager, "Piece");
            showingMoveOption = false;
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
                case 0: piece = new Rook(color, this);
                        break;
                case 1: piece = new Knight(color, this);
                        break;
                case 2: piece = new Bishop(color, this);
                        break;
                case 3: piece = new Queen(color, this);
                        break;
                case 4: piece = new King(color, this);
                        break;
                case 5: piece = new Bishop(color, this);
                        break;
                case 6: piece = new Knight(color, this);
                        break;
                default: piece = new Rook(color, this);
                        break;
            }
        }
        else if (row == 1 || row == 6) { //Checks if row is 2nd row from end row
            piece = new Pawn(color, this);
        }
        else {
            piece = new Piece(color, this);
        }
        
        return piece;
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
                hidePossibleMoves();

                if(temp.getColor() != Main.turn && Main.startSquare != null)
                {
                    Main.endSquare = temp;
                }
            }
 
            if(squareSelected != temp && temp.getColor() > 0)
            {
                if(Main.turn == temp.getColor())
                {
                    colorSquare(temp);
                    temp.displayPossibleMoves();
                    Main.startSquare = temp;
                }
                else if(Main.endSquare == null)
                {
                    Main.startSquare = null;
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

            if(Main.startSquare != null && Main.endSquare != null)
            {
                Main.move();
            }

            //DEBUG OPTION
            if(SwingUtilities.isRightMouseButton(e))
            {
                System.out.println("Times checked by white: " + timesCheckedByWhite);
                System.out.println("Times checked by black: " + timesCheckedByBlack);
                System.out.println();
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