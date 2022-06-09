
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
    public static boolean checkBoardPiece(int sx, int sy, int ex, int ey)
    {
        boolean output = false;
        
        
        if(sx == ex)
        {
            int direction = (ey-sy)/(Math.abs(sy-ey)); //Positive - up,right, Negative - down,left
            output = true;
            for(int i = 0; i < Math.abs(sy-ey) - 1; i++)
            {
                if(Board.board[sx][sy + direction * (i + 1)].getColor() != 0)
                {
                    output = false;
                    break;
                }
            }
        }
        if(sy == ey)
        {
            int direction = (ex-sx)/(Math.abs(sx-ex)); //Positive - down,right, Negative - up,left
            output = true;
            for(int i = 0; i < Math.abs(sx-ex) - 1; i++)
            {
                if(Board.board[sx + direction * (i + 1)][sy].getColor() != 0)
                {
                    output = false;
                    break;
                }
            }
        }
        
        return output;
    }
}
