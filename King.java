import java.awt.*;
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King extends Piece
{
    public King(int color, Square square)
    {
        super(color, square, "WhiteKing.png", "BlackKing.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    public int getRank()
    {
        return 6;
    }

    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        if(Math.abs(endRow - startRow) <= 1 && Math.abs(endCol - startCol) <= 1)
        {
            return true;
        }
        return false;
        //King can move anywhere for now
        //Will implement a "check" / "checkmate" function later which will change this code
    }

    public void togglePieceMoveOptions()
    {
        Square temp = getConnectedSquare();

        int row = temp.getRow();
        int col = temp.getCol();

        for(int i = -1; i <= 1; i++)
        {
            for(int j = -1; j <= 1; j++)
            {
                if(Board.withinBoard(row, col, row + i, col + j) && Board.board[row + i][col + j].getColor() != Main.turn)
                {
                    Board.board[row + i][col + j].toggleMoveOption();
                }
            }
        }
    }
}