import java.awt.*;
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
        setCursor(new Cursor(Cursor.HAND_CURSOR));
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
    
    public void togglePieceMoveOptions()
    {
        Square temp = getConnectedSquare();

        int startRow = temp.getRow();
        int startCol = temp.getCol();

        int endRow = startRow;
        int endCol = startCol;

        for(int i = -1; i <= 1; i += 2) { //Checks up first, then down
            int increment = 1;
            while(Board.withinBoard(startRow, startCol, endRow + i * increment, endCol) &&
                  Board.board[endRow + i * increment][endCol].getColor() != Main.turn) {
                Board.board[endRow + i * increment][endCol].toggleMoveOption();
                if(Board.board[endRow + i * increment][endCol].getColor() > 0)
                {
                    break;
                }
                increment++;
            }
        }
        for(int j = -1; j <= 1; j += 2) { //Checks left then right
            int increment = 1;
            while(Board.withinBoard(startRow, startCol, endRow, endCol + j * increment) &&
                  Board.board[endRow][endCol + j * increment].getColor() != Main.turn) {
                Board.board[endRow][endCol + j * increment].toggleMoveOption();
                if(Board.board[endRow][endCol + j * increment].getColor() > 0)
                {
                    break;
                }
                increment++;
            }
        }
        
        for(int i = -1; i <= 1; i += 2) { //Checks up diagonals first, then down diagonals
            for(int j = -1; j <= 1; j += 2) { //Checks left then right
                int increment = 1;
                while(Board.withinBoard(startRow, startCol, endRow + i * increment, endCol + j * increment) &&
                      Board.board[endRow + i * increment][endCol + j * increment].getColor() != Main.turn) {
                    Board.board[endRow + i * increment][endCol + j * increment].toggleMoveOption();
                    if(Board.board[endRow + i * increment][endCol + j * increment].getColor() > 0)
                    {
                        break;
                    }
                    increment++;
                }
            }
        }
    }
}
