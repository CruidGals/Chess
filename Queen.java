
/**
 * Write a description of class Queen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queen extends Piece
{
    public Queen(int color)
    {
        super(color, "WhiteQueen.png", "BlackQueen.png");
    }
    
    @Override
    public int getRank()
    {
        return 5;
    }
}
