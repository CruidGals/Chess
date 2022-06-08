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
                if(r < 3)
                {
                    board[r][c]= new Square(1, 1, r, c);
                }
                else if(r < 5)
                {
                    board[r][c]= new Square(0, 0, r, c);
                }
                else
                {
                    board[r][c]= new Square(1, 2, r, c);
                }
                if(!(board[r][c].getValid()))
                {
                    board[r][c].setRank(0);
                    board[r][c].setColor(0);
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
            if(board[sx][sy].getRank() == )
        }
        return output;
    }
    
    public void print()
    {
        for(int i = 0; i<10; i++)
        {
            System.out.println();
        }
        System.out.println("  0 1 2 3 4 5 6 7 ");
        System.out.println(" _________________");
        for(int r = 0; r < 8; r++)
        {
            System.out.print(r + "|");
            for(int c = 0; c < 8; c++)
            {
                if(board[r][c].getColor() == 0)
                {
                    System.out.print(" ");
                }
                else if(board[r][c].getColor() == 1)
                {
                    if(board[r][c].getRank() == 1)
                    {
                        System.out.print("r");
                    }
                    else
                    {
                        System.out.print("R");
                    }
                }
                else
                {
                    if(board[r][c].getRank() == 1)
                    {
                        System.out.print("b");
                    }
                    else
                    {
                        System.out.print("B");
                    }
                }
                System.out.print("|");
            }
            System.out.println(r);
            System.out.println(" _________________");
        }
        System.out.println("  0 1 2 3 4 5 6 7 ");
    }
    
    public boolean move(int turn, int sx, int sy, int ex, int ey)
    {
        boolean bruh = isMoveValid(turn, sx, sy, ex, ey);
        if(isMoveValid(turn, sx, sy, ex, ey))
        {
            board[ex][ey].setColor(board[sx][sy].getColor());
            board[ex][ey].setRank(board[sx][sy].getRank());
            board[sx][sy].reset();
            if(isAttack(sx, sy, ex, ey))
            {
                board[(sx+ex)/2][(sy+ey)/2].reset();
                board[(sx+ex)/2][(sy+ey)/2].getPiece().updatePiece();
            }
            if((turn == 1 && ex == 7)||(turn == 2 && ex == 0))
            {
                makeKing(ex, ey);
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
    
    public void makeKing(int x, int y)
    {
        board[x][y].setRank(2);
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