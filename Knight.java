
/**
 * Write a description of class Knight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Knight extends Piece
{
    public Knight(int color)
    {
        super(color, "WhiteKnight.png", "BlackKnight.png");
    }
    
    @Override
    public int getRank()
    {
        return 3;
    }
    
    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        boolean output = false;
        
        if((Math.abs(endRow-startRow) == 2 && Math.abs(endCol-startCol) == 1) || (Math.abs(endCol-startCol) == 2 && Math.abs(endRow-startRow) == 1))
        {
            output = true;
        }
        
        return output;
    }
}
