
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
    private JPanel redPiece = new JPanel();
    private JLabel redPieceIcon = new JLabel();
    
    private JPanel redKing = new JPanel();
    private JLabel redKingIcon = new JLabel();
    
    private JPanel blackPiece = new JPanel();
    private JLabel blackPieceIcon = new JLabel();
    
    private JPanel blackKing = new JPanel();
    private JLabel blackKingIcon = new JLabel();
    
    private JPanel noPiece = new JPanel();
    
    private CardLayout cards = new CardLayout();
    
    private int r;
    private int c;
    private boolean v;
    public Piece(int rank, int color, boolean valid)
    {
       r = rank;
       c = color;
       v= valid;
       setOpaque(false);
       setLayout(cards);
       
       redPiece.setOpaque(false);
       redPiece.add(redPieceIcon);
       redPieceIcon.setIcon(new ImageIcon("RedPiece.png"));
       
       redKing.setOpaque(false);
       redKing.add(redKingIcon);
       redKingIcon.setIcon(new ImageIcon("RedKing.png"));
       
       blackPiece.setOpaque(false);
       blackPiece.add(blackPieceIcon);
       blackPieceIcon.setIcon(new ImageIcon("BlackPiece.png"));
       
       blackKing.setOpaque(false);
       blackKing.add(blackKingIcon);
       blackKingIcon.setIcon(new ImageIcon("BlackKing.png"));
       
       noPiece.setOpaque(false);
       
       this.add(redPiece, "Red");
       this.add(blackPiece, "Black");
       this.add(redKing, "RedKing");
       this.add(blackKing, "BlackKing");
       this.add(noPiece, "None");
        
       updatePiece();
    }
    public Piece()
    {
        
    }
    
    public int getRank() {
        return r;
    }
    public int getColor() {
        return c;
    }
    
    public void setRank(int r) {
        this.r = r;
    }
    public void setColor(int c) {
        this.c = c;
    }
    
    public void setBRnC(int rank, int color)
    {
        r = rank;
        c = color;
        
    }
    
    public void updatePiece()
    {
        if(r >= 1 && v) 
       {
           if(c == 1) 
           {
               if(r > 1)
               {
                   cards.show(this, "RedKing");
               }
               else
               {
                   cards.show(this, "Red");
               }
           }
           else
           {
               if(r > 1)
               {
                   cards.show(this, "BlackKing");
               }
               else
               {
                   cards.show(this, "Black");
               }
           }
       }
       else
       {
           cards.show(this, "None");
       }
    }
}