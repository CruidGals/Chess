
/**
 * Write a description of class Bishop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bishop extends Piece
{
    public Bishop(int color)
    {
        super(color, "WhiteBishop.png", "BlackBishop.png");
    }
    
    @Override
    public int getRank()
    {
        return 2;
    }

    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        boolean output = false;
        
        if(Math.abs(startRow-endRow) != Math.abs(startCol-endCol))
        {
            return output;
        }

        int yDirection = 0;

        if(startRow > endRow) //If bishop is moving up
        {
            yDirection = -1;
        }
        else if(startRow < endRow) //If bishop is moving down
        {
            yDirection = 1;
        }

        int direction = (endCol-startCol)/(Math.abs(startCol-endCol)); //Positive - right, Negative - left
        output = true;
        for(int i = 0; i < Math.abs(startCol-endCol) - 1; i++)
        {
            if(Board.board[startRow + yDirection * (i + 1)][startCol + direction * (i + 1)].getColor() != 0)
            {
                output = false;
                break;
            }
        }
        
        return output;
    }
}
