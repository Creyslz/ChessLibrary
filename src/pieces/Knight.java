package pieces;

import main.Chessboard;

public class Knight extends ChessPiece {

	public Knight(char align, int row, int col, Chessboard board) {
		super(align, 'n', row, col, board);
		// TODO Auto-generated constructor stub
	}

	// Please see ChessPiece super class for descriptions of the following methods.
	@Override
	public boolean canMoveTo(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		
		if(Math.abs(rowDifference) == 2 && Math.abs(colDifference) == 1) {
			return canCapture(row, col, board);
		} else if (Math.abs(rowDifference) == 1 && Math.abs(colDifference) == 2) {
			return canCapture(row, col, board);
		}
		return false;
	}

	@Override
	public boolean canThreaten(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		
		if(Math.abs(rowDifference) == 2 && Math.abs(colDifference) == 1) {
			return true;
		} else if (Math.abs(rowDifference) == 1 && Math.abs(colDifference) == 2) {
			return true;
		}
		return false;
	}



}
