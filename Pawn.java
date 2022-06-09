
/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pawn extends Piece
{
    public Pawn(int color)
    {
        super(color, "WhitePawn.png", "BlackPawn.png");
    }
    
    @Override
    public int getRank()
    {
        return 1;
    }
    
    public static boolean isValidMove(int startRow, int startColumn, int endRow, int endCol)
    {
        boolean output = false;
        
        if(Main.turn == 1)
        {
            if(endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() != 2) 
            {
                output = true;
            }
            if(Math.abs(endCol-startColumn) == 1 && endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() == 2)
            {
                output = true;
            }
        }
        else
        {
            if(endRow == (startRow + 1) && Board.board[endRow][endCol].getColor() != 1) 
            {
                output = true;
            }
            if(Math.abs(endCol-startColumn) == 1 && endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() == 1)
            {
                output = true;
            }
        }
        
        return output;
    }
}
