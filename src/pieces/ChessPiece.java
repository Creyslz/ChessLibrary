package pieces;
import main.Chessboard;

public abstract class ChessPiece {
	private char alignment;
	private int lastTurnMoved;
	private int row;
	private int col;
	private boolean isAlive;
	private char name;
	private Chessboard curBoard;

	public ChessPiece(char align, char name, int row, int col, Chessboard board)
	{
		if(align == 'b' || align == 'w')
		{
			alignment = align;
		} else {
			System.out.println("Invalid alignment character.");
			alignment = 'f';
		}

		this.name = name;
		this.row = row;
		this.col = col;
		lastTurnMoved = -1;
		isAlive = true;
		curBoard = board;
	}

	// Necessary getters and setters
	public int getLastTurnMoved() {
		return lastTurnMoved;
	}
	public void setLastTurnMoved(int lastTurnMoved) {
		this.lastTurnMoved = lastTurnMoved;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public boolean isAlive() {
		return isAlive;
	}
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	public char getAlignment() {
		return alignment;
	}
	public int getAlignmentDirection() {
		if(alignment == 'w')
			return 1;
		if(alignment == 'b')
			return -1;
		return 0;
	}
	public char getOpposingAlignment() {
		if(alignment == 'w')
			return 'b';
		if(alignment == 'b')
			return 'w';
		return 'f';
	}
	public char getName() {
		return name;
	}
	public Chessboard getBoard() {
		return curBoard;
	}
	
	// Checks for the piece can move to the desired position on the board.
	public abstract boolean canMoveTo(int row, int col, int[][] board);
	
	public boolean isWithinBounds(int row, int col) {
		if(row < 0 || row >= curBoard.getMaxRows() || col < 0 || col < curBoard.getMaxCols())
			return false;
		return true;
	}
	
	// Forces the piece to move to a position
	// Does NOT check for the correctness of the move
	void moveTo(int row, int col)
	{
		this.row = row;
		this.col = col;
	}
	
	// Checks for pieces in between the start and end coordinates. Excludes start and end points.
	// returns true on no collisions
	//		   false on collisions, same start and end points, or bad input (movement not in an accepted direction e.g. (0,0) to (2,4))
	public static boolean checkCollision(int startRow, int startCol, 
										 int endRow, int endCol, int[][] board) {
		int rowDifference = endRow - startRow;
		int colDifference = endCol - startCol;
		int rowDirection = (int) Math.signum(rowDifference);
		int colDirection = (int) Math.signum(colDifference);
		
		if(rowDirection != 0 && colDirection != 0 
				&& Math.abs(rowDifference) != Math.abs(colDifference))
			return false;
		
		if(rowDirection == 0 && colDirection == 0)
			return false;

		int row = startRow + rowDirection;
		int col = startCol + colDirection;
		
		while(row != endRow && col != endCol) {
			if(board[row][col] != 0)
				return false;
			row += rowDirection;
			col += colDirection;
		}
				
		return true;
	}
	
	// returns true if piece occupying target position is of the opposite color or position is empty
	// 		   false if piece is same color
	public boolean canCapture(int targetRow, int targetCol, int[][] board)
	{
		if(Math.signum(board[targetRow][targetCol]) == 0)
			return true;
		return Math.signum(board[targetRow][targetCol]) != this.getAlignmentDirection();
	}
	
}
