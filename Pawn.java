
/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pawn extends Piece
{
    public Pawn(int color, Square square)
    {
        super(color, square, "WhitePawn.png", "BlackPawn.png");
    }
    
    @Override
    public int getRank()
    {
        return 1;
    }
    
    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        boolean output = false;
        
        if(Main.turn == 1)
        {
            if(endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() != 2) 
            {
                output = true;
            }
            if(Math.abs(endCol-startCol) == 1 && endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() == 2)
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
            if(Math.abs(endCol-startCol) == 1 && endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() == 1)
            {
                output = true;
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

        if(Main.turn == 1)
        {
            endRow -= 1;
            if(endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() != 2) 
            {
                Board.board[endRow][endCol].toggleMoveOption();
            }
            if(Math.abs(endCol-startCol) == 1 && endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() == 2)
            {
                Board.board[endRow][endCol + 1].toggleMoveOption();
                Board.board[endRow][endCol - 1].toggleMoveOption();
            }
        }
        else
        {
            endRow += 1;
            if(endRow == (startRow + 1) && Board.board[endRow][endCol].getColor() != 1) 
            {
                Board.board[endRow][endCol].toggleMoveOption();
            }
            if(Math.abs(endCol-startCol) == 1 && endRow == (startRow - 1) && Board.board[endRow][endCol].getColor() == 1)
            {
                Board.board[endRow][endCol + 1].toggleMoveOption();
                Board.board[endRow][endCol - 1].toggleMoveOption();
            }
        }
    }
}
