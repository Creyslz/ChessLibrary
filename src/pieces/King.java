package pieces;

import main.Chessboard;

public class King extends ChessPiece {

	public King(char align, int row, int col, Chessboard board) {
		super(align, 'k', row, col, board);
		// TODO Auto-generated constructor stub
	}

	// Please see ChessPiece super class for descriptions of the following methods.
	@Override
	public boolean canMoveTo(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		
		if(Math.abs(rowDifference) > 1 || Math.abs(colDifference) > 1)
				return false;
		
		//check for check
		char otherColor = this.getOpposingAlignment();
		return !this.getBoard().isPositionThreatened(row, col, otherColor) && this.canCapture(row, col, board);
		
		//Add castling later
		
	}

	@Override
	public boolean canThreaten(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		
		if(Math.abs(rowDifference) > 1 || Math.abs(colDifference) > 1)
				return false;
		return true;
	}

}
