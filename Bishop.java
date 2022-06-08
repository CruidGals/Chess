
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
}
