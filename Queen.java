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

        int row = temp.getRow();
        int col = temp.getCol();

        //Rook toggleMethod
        for(int i = -1; i <= 1; i += 2) { //Checks up first, then down
            int increment = 1;
            while(Board.withinBoard(row, col, row + i * increment, col) &&
                  Board.board[row + i * increment][col].getColor() != Main.turn) {
                Board.board[row + i * increment][col].toggleMoveOption();
                if(Board.board[row + i * increment][col].getColor() > 0)
                {
                    break;
                }
                increment++;
            }
        }
        for(int j = -1; j <= 1; j += 2) { //Checks left then right
            int increment = 1;
            while(Board.withinBoard(row, col, row, col + j * increment) &&
                  Board.board[row][col + j * increment].getColor() != Main.turn) {
                Board.board[row][col + j * increment].toggleMoveOption();
                if(Board.board[row][col + j * increment].getColor() > 0)
                {
                    break;
                }
                increment++;
            }
        }
        
        //Bishop toggleMethods
        for(int i = -1; i <= 1; i += 2) { //Checks up diagonals first, then down diagonals
            for(int j = -1; j <= 1; j += 2) { //Checks left then right
                int increment = 1;
                while(Board.withinBoard(row, col, row + i * increment, col + j * increment) &&
                      Board.board[row + i * increment][col + j * increment].getColor() != Main.turn) {
                    Board.board[row + i * increment][col + j * increment].toggleMoveOption();
                    if(Board.board[row + i * increment][col + j * increment].getColor() > 0)
                    {
                        break;
                    }
                    increment++;
                }
            }
        }
    }
}