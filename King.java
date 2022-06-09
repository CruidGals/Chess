
/**
 * Write a description of class King here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class King extends Piece
{
    public King(int color)
    {
        super(color, "WhiteKing.png", "BlackKing.png");
        
    }
    
    @Override
    public int getRank()
    {
        return 6;
    }

    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        return true;
        //King can move anywhere for now
        //Will implement a "check" / "checkmate" function later which will change this code
    }
}
