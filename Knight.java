
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
    
    public static boolean checkBoardPiece(int sx, int sy, int ex, int ey)
    {
        boolean output = false;
        
        if((Math.abs(ex-sx) == 2 && Math.abs(ey-sy) == 1) || (Math.abs(ey-sy) == 2 && Math.abs(ex-sx) == 1))
        {
            output = true;
        }
        
        return output;
    }
}
