
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
    
    public static boolean checkBoardPiece(int sx, int sy, int ex, int ey)
    {
        boolean output = false;
        
        if(Main.turn == 1)
        {
            if(ex == (sx - 1) && Board.board[ex][ey].getColor() != 2) 
            {
                output = true;
            }
            if(Math.abs(ey-sy) == 1 && ex == (sx - 1) && Board.board[ex][ey].getColor() == 2)
            {
                output = true;
            }
        }
        else
        {
            if(ex == (sx + 1) && Board.board[ex][ey].getColor() != 1) 
            {
                output = true;
            }
            if(Math.abs(ey-sy) == 1 && ex == (sx - 1) && Board.board[ex][ey].getColor() == 1)
            {
                output = true;
            }
        }
        
        return output;
    }
}
