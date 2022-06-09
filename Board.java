import javax.swing.*;
import java.awt.*;
public class Board extends JFrame
{
    public static Square[][] board = new Square[8][8];

    public Board()
    {
        setLayout(new GridLayout(8,8));
        for(int r = 0; r < 8; r++)
        {
            for(int c = 0; c < 8; c++)
            {
                if(r < 2)
                {
                    board[r][c]= new Square(1, r, c);
                }
                else if(r < 6)
                {
                    board[r][c]= new Square(0, r, c);
                }
                else
                {
                    board[r][c]= new Square(2, r, c);
                }
            }
        }
        
        for(Square[] row : board) {
            for(Square col : row) {
                getContentPane().add(col);
            }
        }
    }
    
    private boolean isMoveValid(int turn, int sx, int sy, int ex, int ey)
    {
        boolean output = ((0 <= sx && sx <= 7)&&(0 <= sy && sy <= 7) && (0 <= ex && ex <= 7)&&(0 <= ey && ey <= 7));
        if(output && turn != board[sx][sy].getColor())
        {
            output = false;
        }
        if(output && turn == board[ex][ey].getColor())
        {
            output = false;
        }
        if(output)
        {
            if(board[sx][sy].getRank() == 1)
            {
                
            }
        }
        return output;
    }
    
    
    public boolean move(int turn, int sx, int sy, int ex, int ey)
    {
        boolean bruh = isMoveValid(turn, sx, sy, ex, ey);
        if(isMoveValid(turn, sx, sy, ex, ey))
        {
            board[ex][ey].setColor(board[sx][sy].getColor());
            board[sx][sy].reset();
            if(isAttack(sx, sy, ex, ey))
            {
                board[(sx+ex)/2][(sy+ey)/2].reset();
                board[(sx+ex)/2][(sy+ey)/2].getPiece().updatePiece();
            }
            if((turn == 1 && ex == 7)||(turn == 2 && ex == 0))
            {
                //makeKing(ex, ey);
            }
            
            board[ex][ey].getPiece().updatePiece();
            board[sx][sy].getPiece().updatePiece();
        }
        return bruh;
    }
    
    private boolean isAttack(int sx, int sy, int ex, int ey)
    {
        return ((sx-1 > ex || ex > sx+1) || (sy-1 > ey || ey > sy+1));
    }
    
    public int gameWinner()
    {
        boolean isWDed = true;
        boolean isBDed = true;
        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                if (board[x][y].getColor()== 1)
                {
                    isWDed = false;
                }
                if (board[x][y].getColor()== 2)
                {
                    isBDed = false;
                }
            }
        }
        if(isBDed)
        {
            return 1;
        }
        else if(isWDed)
        {
            return 2;
        }
        else
        {
            return 0;
        }
    }
}