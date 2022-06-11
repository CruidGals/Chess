import java.awt.*;
/**
 * Write a description of class Pawn here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Pawn extends Piece
{
    private boolean isDoubleMove = true;
    private boolean showingAttackOptions = false;
    public Pawn(int color, Square square)
    {
        super(color, square, "WhitePawn.png", "BlackPawn.png");
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
    
    @Override
    public int getRank()
    {
        return 1;
    }
    
    public boolean hasDoubleMove()
    {
        return isDoubleMove;
    }
    
    public void turnOffDoubleMove()
    {
        isDoubleMove = false;
    }
    
    public static boolean isValidMove(int startRow, int startCol, int endRow, int endCol)
    {
        boolean output = false;
        
        boolean isDoubleMove = ((Pawn) Board.board[startRow][startCol].getPiece()).hasDoubleMove();
        
        if(Main.turn == 1)
        {
            if((endCol-startCol == 0 && Math.abs(endRow - startRow) <= 2) && Board.board[endRow][endCol].getColor() == 0) 
            {
                output = true;
                if(Math.abs(endRow - startRow) == 2) //Checks if double move is possible
                {
                    if(!isDoubleMove)
                    {
                        output = false;
                    }
                    if(Board.board[endRow + 1][endCol].getColor() != 0)
                    {
                        output = false;
                    }
                }
            }
            
            if((Math.abs(endCol-startCol) == 1 && endRow == (startRow - 1)) && Board.board[endRow][endCol].getColor() == 2)
            {
                output = true;
            }
        }
        else
        {
            if((endCol-startCol == 0 && Math.abs(endRow - startRow) <= 2) && Board.board[endRow][endCol].getColor() == 0) 
            {
                output = true;
                if(Math.abs(endRow - startRow) == 2)//Checks if double move is possible
                {
                    if(!isDoubleMove)
                    {
                        output = false;
                    }
                    if(Board.board[endRow - 1][endCol].getColor() != 0)
                    {
                        output = false;
                    }
                }
            }
            if(Math.abs(endCol-startCol) == 1 && endRow == (startRow + 1) && Board.board[endRow][endCol].getColor() == 1)
            {
                output = true;
            }
        }
        
        return output;
    }
    /**
     * Method has two functions (function toggled by the parameter) It can:
     *  - Highlight the possible spaces the pawn can go
     *  - Update the attackable squares (variable tracked is called timesCheckedByColor) 
     * @param checkAttack
     */
    public void togglePieceMoveOptions(boolean checkAttack)
    {
        Square temp = getConnectedSquare();

        int row = temp.getRow();
        int col = temp.getCol();

        int direction = -1; //Default direction goes from white
        if(getConnectedSquare().getColor() != 1) {
            direction = 1; //Direction goes to black if not correct turn 
        }

        if(Board.board[row + 1 * direction][col].getColor() == 0) 
        {
            if(!checkAttack) Board.board[row + 1 * direction][col].toggleMoveOption();
            if(Board.board[row + 2 * direction][col].getColor() == 0 && isDoubleMove) 
            {
                if(!checkAttack) Board.board[row + 2 * direction][col].toggleMoveOption();
            }
        }
        if(Board.withinBoard(row, col, row + 1 * direction, col + 1))
        {
            if(!checkAttack) {
                if(Board.board[row + 1 * direction][col + 1].getColor() == 2) Board.board[row + 1 * direction][col + 1].toggleMoveOption();
            } else {
                if(direction == -1) {
                    Board.board[row + 1 * direction][col + 1].setCheckedByColor(1, !showingAttackOptions);
                } else {
                    Board.board[row + 1 * direction][col + 1].setCheckedByColor(2, !showingAttackOptions);
                }
            }
        }
        if(Board.withinBoard(row, col, row + 1 * direction, col - 1))
        {
            if(!checkAttack) {
                if(Board.board[row + 1 * direction][col - 1].getColor() == 2) Board.board[row + 1 * direction][col - 1].toggleMoveOption();
            } else {
                if(direction == -1) {
                    Board.board[row + 1 * direction][col - 1].setCheckedByColor(1, !showingAttackOptions);
                } else {
                    Board.board[row + 1 * direction][col - 1].setCheckedByColor(2, !showingAttackOptions);
                }
            }
        }
    

        if(checkAttack) showingAttackOptions = !showingAttackOptions;
    }


}
