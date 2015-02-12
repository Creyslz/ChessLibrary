package pieces;

import main.Chessboard;

public class Pawn extends ChessPiece {

	public Pawn(char align, int row, int col, Chessboard board) {
		super(align, 'p', row, col, board);
		// TODO Auto-generated constructor stub
	}

	// Please see ChessPiece super class for descriptions of the following methods.
	@Override
	public boolean canMoveTo(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		int direction = this.getAlignmentDirection();
		
		if(colDifference == 0 && rowDifference == direction)
			return board[row][col] == 0;
		
		if(colDifference == 0 && rowDifference == 2*direction) {
			if(this.getLastTurnMoved() == -1) {
				return board[row][col] == 0 && board[row-direction][col] == 0;
			}
		}
		
		if(Math.abs(colDifference) == 1 && rowDifference == direction) {
			if(board[row][col] != 0)
				return this.canCapture(row, col, board);
		}
		//implement en passant later
		return false;
	}
	
	@Override
	public boolean canThreaten(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		int direction = this.getAlignmentDirection();
		if(Math.abs(colDifference) == 1 && rowDifference == direction) {
			return true;
		}
		//implement en passant later
		return false;
	}


}
