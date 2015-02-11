package pieces;

import main.Chessboard;

public class Pawn extends ChessPiece {

	public Pawn(char align, int row, int col, Chessboard board) {
		super(align, 'p', row, col, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMoveTo(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = row - this.getCol();
		int direction = this.getAlignmentDirection();
		
		if(colDifference == 0 && rowDifference == direction)
			return board[row][col] == 0;
		
		if(colDifference == 0 && rowDifference == 2*direction) {
			if(this.getLastTurnMoved() == -1) {
				return board[row][col] == 0 && board[row-direction][col] == 0;
			}
		}
		
		if(Math.abs(colDifference) == 1 && rowDifference == direction) {
			return this.canCapture(row, col, board);
		}
		//implement en passant later
		return false;
	}


}
