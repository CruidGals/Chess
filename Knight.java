import java.awt.*;
/**
 * Write a description of class Knight here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Knight extends Piece
{
    public Knight(int color, Square square)
    {
        super(color, square, "WhiteKnight.png", "BlackKnight.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    public int getRank()
    {
        return 3;
    }
    
    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        boolean output = false;
        
        if((Math.abs(endRow-startRow) == 2 && Math.abs(endCol-startCol) == 1) || (Math.abs(endCol-startCol) == 2 && Math.abs(endRow-startRow) == 1))
        {
            output = true;
        }
        
        return output;
    }

    public void togglePieceMoveOptions() //Probably a better way to do this
    {
        Square temp = getConnectedSquare();

        int row = temp.getRow();
        int col = temp.getCol();
        
        if(Board.withinBoard(row, col, row, col - 2)) { //Checks for space left of piece
            if(Board.withinBoard(row, col, row + 1, col - 2)) { //Checks for space below piece
                Board.board[row + 1][col - 2].toggleMoveOption();
            }
            if(Board.withinBoard(row, col, row - 1, col - 2)) { //Checks for space above piece
                Board.board[row - 1][col - 2].toggleMoveOption();
            }
        }
        if(Board.withinBoard(row, col, row, col + 2)) { //Checks for space right of piece
            if(Board.withinBoard(row, col, row + 1, col + 2)) { //Checks for space below piece
                Board.board[row + 1][col + 2].toggleMoveOption();
            }
            if(Board.withinBoard(row, col, row - 1, col + 2)) { //Checks for space above piece
                Board.board[row - 1][col + 2].toggleMoveOption();
            }
        }
        if(Board.withinBoard(row, col, row - 2, col)) { //Checks for space below piece
            if(Board.withinBoard(row, col, row - 2, col + 1)) { //Checks for space right of piece
                Board.board[row - 2][col + 1].toggleMoveOption();
            }
            if(Board.withinBoard(row, col, row - 2, col - 1)) { //Checks for space left of piece
                Board.board[row - 2][col - 1].toggleMoveOption();
            }
        }
        if(Board.withinBoard(row, col, row + 2, col)) { //Checks for space above piece
            if(Board.withinBoard(row, col, row + 2, col + 1)) { //Checks for space right of piece
                Board.board[row + 2][col + 1].toggleMoveOption();
            }
            if(Board.withinBoard(row, col, row + 2, col - 1)) { //Checks for space left of piece
                Board.board[row + 2][col - 1].toggleMoveOption();
            }
        }
    }
}
