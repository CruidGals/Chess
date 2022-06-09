
/**
 * Write a description of class Queen here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Queen extends Piece
{
    public Queen(int color, Square square)
    {
        super(color, square, "WhiteQueen.png", "BlackQueen.png");
    }
    
    @Override
    public int getRank()
    {
        return 5;
    }

    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        boolean output = Rook.isValidMove(startRow, startCol, endRow, endCol) || Bishop.isValidMove(startRow, startCol, endRow, endCol);
        return output;
    }
}
