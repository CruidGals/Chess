
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
}
