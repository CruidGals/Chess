
import javax.swing.*;
import java.awt.*;
/**
 * Write a description of class ColorPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Piece extends JPanel implements Cloneable
{    
    private Square connectedSquare;

    private JPanel whitePiece = new JPanel();
    private JLabel whitePieceIcon = new JLabel();
    
    private JPanel blackPiece = new JPanel();
    private JLabel blackPieceIcon = new JLabel();
    
    private JPanel noPiece = new JPanel();
    
    private CardLayout cards = new CardLayout();
    
    private int c; // (Color) -- 0 = no piece, 1 = whitePiece, 2 = blackPiece
    public Piece(int color, Square connectedSquare, String whitePieceName, String blackPieceName)
    {
       c = color;
       this.connectedSquare = connectedSquare;
       setOpaque(false);
       setLayout(cards);
       
       whitePiece.setOpaque(false);
       whitePiece.add(whitePieceIcon);
       whitePieceIcon.setIcon(new ImageIcon("resources/" + whitePieceName));
       
       blackPiece.setOpaque(false);
       blackPiece.add(blackPieceIcon);
       blackPieceIcon.setIcon(new ImageIcon("resources/" + blackPieceName));
       
       noPiece.setOpaque(false);
       
       add(whitePiece, "White");
       add(blackPiece, "Black");
       add(noPiece, "None");

       updatePieceUI();
    }
    
    public Piece(int color, Square connectedSquare)
    {
        c = color;
        this.connectedSquare = connectedSquare;
        setOpaque(false);
        setLayout(cards);
        
        whitePiece.setOpaque(false);
        whitePiece.add(whitePieceIcon);
        blackPiece.setOpaque(false);
        blackPiece.add(blackPieceIcon);
        noPiece.setOpaque(false);
        
        add(whitePiece, "White");
        add(blackPiece, "Black");
        add(noPiece, "None");

        updatePieceUI();
    }
    
    public int getColor() {
        return c;
    }
    
    public void setColor(int c) {
        this.c = c;
    }
    
    public CardLayout getCardLayout() {
        return cards;
    }
    
    public int getRank() 
    {
        return 0;
    }

    public Square getConnectedSquare()
    {
        return connectedSquare;
    }

    public void setConnectedSquare(Square sq)
    {
        connectedSquare = sq;
    }
    
    public static boolean isValidMove(int sx, int sy, int ex, int ey)
    {
        return false;
    }

    public void togglePieceMoveOptions(boolean checkAttack) {} //Abstract function : Meant to be overridable

    public void updatePieceUI()
    {
        if(c == 1)
        {
            cards.show(this, "White");
        }
        else if(c == 2)
        {
            cards.show(this, "Black");
        }
        else
        {
            cards.show(this, "None");
        }
    }
}