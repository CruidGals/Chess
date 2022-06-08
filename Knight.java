
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
}
