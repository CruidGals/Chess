import java.awt.*;
/**
 * Write a description of class Rook here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rook extends Piece
{
    public Rook(int color, Square square)
    {
        super(color, square, "WhiteRook.png", "BlackRook.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    public int getRank()
    {
        return 4;
    }
    
    public static boolean isValidMove(int sx, int sy, int ex, int ey)
    {
        boolean output = false;
        
        if(sx == ex)
        {
            int direction = (ey-sy)/(Math.abs(sy-ey)); //Positive - right, Negative - left
            output = true;
            for(int i = 0; i < Math.abs(sy-ey) - 1; i++)
            {
                if(Board.board[sx][sy + direction * (i + 1)].getColor() != 0)
                {
                    output = false;
                    break;
                }
            }
        }
        if(sy == ey)
        {
            int direction = (ex-sx)/(Math.abs(sx-ex)); //Positive - down, Negative - up
            output = true;
            for(int i = 0; i < Math.abs(sx-ex) - 1; i++)
            {
                if(Board.board[sx + direction * (i + 1)][sy].getColor() != 0)
                {
                    output = false;
                    break;
                }
            }
        }
        
        return output;
    }
    
    public void togglePieceMoveOptions()
    {
        Square temp = getConnectedSquare();

        int startRow = temp.getRow();
        int startCol = temp.getCol();

        int endRow = startRow;
        int endCol = startCol;

        for(int i = -1; i <= 1; i += 2) { //Checks up first, then down
            int increment = 1;
            while(Board.withinBoard(startRow, startCol, endRow + i * increment, endCol) &&
                  Board.board[endRow + i * increment][endCol].getColor() != Main.turn) {
                Board.board[endRow + i * increment][endCol].toggleMoveOption();
                if(Board.board[endRow + i * increment][endCol].getColor() > 0)
                {
                    break;
                }
                increment++;
            }
        }
        for(int j = -1; j <= 1; j += 2) { //Checks left then right
            int increment = 1;
            while(Board.withinBoard(startRow, startCol, endRow, endCol + j * increment) &&
                  Board.board[endRow][endCol + j * increment].getColor() != Main.turn) {
                Board.board[endRow][endCol + j * increment].toggleMoveOption();
                if(Board.board[endRow][endCol + j * increment].getColor() > 0)
                {
                    break;
                }
                increment++;
            }
        }
    }
}
