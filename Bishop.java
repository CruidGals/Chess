import java.awt.*;
/**
 * Write a description of class Bishop here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bishop extends Piece
{
    public Bishop(int color, Square square)
    {
        super(color, square, "WhiteBishop.png", "BlackBishop.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    public int getRank()
    {
        return 2;
    }

    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        boolean output = false;
        
        if(Math.abs(startRow-endRow) != Math.abs(startCol-endCol))
        {
            return output;
        }

        int yDirection = 0;

        if(startRow > endRow) //If bishop is moving up
        {
            yDirection = -1;
        }
        else if(startRow < endRow) //If bishop is moving down
        {
            yDirection = 1;
        }

        int direction = (endCol-startCol)/(Math.abs(startCol-endCol)); //Positive - right, Negative - left
        output = true;
        for(int i = 0; i < Math.abs(startCol-endCol) - 1; i++)
        {
            if(Board.board[startRow + yDirection * (i + 1)][startCol + direction * (i + 1)].getColor() != 0)
            {
                output = false;
                break;
            }
        }
        
        return output;
    }

    public void togglePieceMoveOptions()
    {
        Square temp = getConnectedSquare();

        int row = temp.getRow();
        int col = temp.getCol();

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