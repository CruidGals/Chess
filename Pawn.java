
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
}
