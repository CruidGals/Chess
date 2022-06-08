
/**
 * Write a description of class Rook here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Rook extends Piece
{
    public Rook(int color)
    {
        super(color, "WhiteRook.png", "BlackRook.png");
    }
    
    @Override
    public int getRank()
    {
        return 4;
    }
}
