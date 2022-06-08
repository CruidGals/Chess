
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * Write a description of class ColorPanel here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Piece extends JPanel
{    
    private JPanel whitePiece = new JPanel();
    private JLabel whitePieceIcon = new JLabel();
    
    private JPanel blackPiece = new JPanel();
    private JLabel blackPieceIcon = new JLabel();
    
    private JPanel noPiece = new JPanel();
    
    private CardLayout cards = new CardLayout();
    
    private int c; // (Color) -- 0 = no piece, 1 = whitePiece, 2 = blackPiece
    public Piece(int color, String whitePieceName, String blackPieceName)
    {
       c = color;
       setOpaque(false);
       setLayout(cards);
       
       whitePiece.setOpaque(false);
       whitePiece.add(whitePieceIcon);
       whitePieceIcon.setIcon(new ImageIcon(whitePieceName));
       
       blackPiece.setOpaque(false);
       blackPiece.add(blackPieceIcon);
       blackPieceIcon.setIcon(new ImageIcon(blackPieceName));
       
       noPiece.setOpaque(false);
       updatePiece();
       
       add(whitePiece, "White");
       add(blackPiece, "Black");
       add(noPiece, "None");
    }
    
    public Piece(int color)
    {
        c = color;
        setOpaque(false);
        setLayout(cards);
        
        whitePiece.setOpaque(false);
        whitePiece.add(whitePieceIcon);
        blackPiece.setOpaque(false);
        blackPiece.add(blackPieceIcon);
        noPiece.setOpaque(false);
        
        updatePiece();
        
        add(whitePiece, "White");
        add(blackPiece, "Black");
        add(noPiece, "None");
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
    
    public void updatePiece()
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