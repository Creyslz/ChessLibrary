package pieces;

import main.Chessboard;

public class Queen extends ChessPiece {

	public Queen(char align,  int row, int col, Chessboard board) {
		super(align, 'q', row, col, board);
		// TODO Auto-generated constructor stub
	}

	// Checks to see if target square is a valid move for the Queen.
	// i.e along a horizontal, vertical, or diagonal and there are no pieces in the way.
	@Override
	public boolean canMoveTo(int row, int col, int[][] board) {
		if(checkCollision(this.getRow(), this.getCol(), row, col, board))
			return canCapture(row, col, board);
		return false;
	}


}
