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
                    board[r][c]= new Square(2, r, c);
                }
                else if(r < 6)
                {
                    board[r][c]= new Square(0, r, c);
                }
                else
                {
                    board[r][c]= new Square(1, r, c);
                }
            }
        }
        
        for(Square[] row : board) {
            for(Square col : row) {
                getContentPane().add(col);
            }
        }
    }
    
    public static boolean withinBoard(int sx, int sy, int ex, int ey)
    {
        return ((0 <= sx && sx <= 7)&&(0 <= sy && sy <= 7) && (0 <= ex && ex <= 7)&&(0 <= ey && ey <= 7));
    }

    private boolean isMoveValid(int turn, int sx, int sy, int ex, int ey)
    {
        boolean output = withinBoard(sx, sy, ex, ey);
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
                output = Pawn.isValidMove(sx, sy, ex, ey);
            }
            if(board[sx][sy].getRank() == 2)
            {
                output = Bishop.isValidMove(sx, sy, ex, ey); 
            }
            if(board[sx][sy].getRank() == 3)
            {  
                output = Knight.isValidMove(sx, sy, ex, ey); 
            }
            if(board[sx][sy].getRank() == 4)
            {
                output = Rook.isValidMove(sx, sy, ex, ey);
            }
            if(board[sx][sy].getRank() == 5)
            {
                output = Queen.isValidMove(sx, sy, ex, ey); 
            }
            if(board[sx][sy].getRank() == 6)
            {
                output = King.isValidMove(sx, sy, ex, ey); 
            }
        }
        return output;
    }
    
    public boolean isCheck(int kingColor, int row, int col)
    {
        boolean output = false;
        int otherTurn;
        if(kingColor == 1)
        {
            otherTurn = 2;
        }
        else
        {
            otherTurn = 1;
        }
        for(int r = -7; r < 8; r++)
        {
            for(int c = -7; c < 8; c++)
            {
                 if(!output)
                 {
                     output=isMoveValid(otherTurn, row+r, col+c, row, col);
                 }
            }
        }
        
        
        return output;
    }
    
    public boolean move(int turn, int sx, int sy, int ex, int ey)
    {
        boolean bruh = isMoveValid(turn, sx, sy, ex, ey);
        if(bruh)
        {
            Piece temp = board[ex][ey].getPiece();
            
            if(board[sx][sy].getRank() == 1)
            {
                ((Pawn) Board.board[sx][sy].getPiece()).turnOffDoubleMove();
            }
            
            board[ex][ey].setP(board[sx][sy].getPiece());
            board[ex][ey].getPiece().setConnectedSquare(board[ex][ey]);
            board[sx][sy].setP(temp);
            board[sx][sy].setP(new Piece(0, board[sx][sy]));
            
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