package pieces;

import main.Chessboard;

public class Artillery extends ChessPiece {

	public Artillery(char align, char name, int row, int col, Chessboard board) {
		super(align, 'a', row, col, board);
	}

	/*
	 * The Artillery moves the same way as a Rook, however it captures much differently.
	 * The Artillery may only capture an opposing piece if there is exactly a single piece between them orthogonally.
	 * e.g.   A   p    k, the Artillery A here can capture the king k but not the pawn p.
	 * @see pieces.ChessPiece#canMoveTo(int, int, int[][])
	 */
	@Override
	public boolean canMoveTo(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		if(!((rowDifference != 0) ^ (colDifference != 0)))
			return false;
		
		if(checkCollision(this.getRow(), this.getCol(), row, col, board))
			if(board[row][col] == 0)
				return true;
		
		if(checkPiecesBetween(this.getRow(), this.getCol(), row, col, board) == 1) {
			return this.canCapture(row, col, board);
		}
		
		return false;
	}
	/*
	 * Tests if the Artillery can capture at target square.
	 * @see pieces.ChessPiece#canThreaten(int, int, int[][])
	 */
	@Override
	public boolean canThreaten(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		
		if(rowDifference != 0 ^ colDifference != 0)
			return false;
		
		if(checkPiecesBetween(this.getRow(), this.getCol(), row, col, board) == 1) {
			return true;
		}
		
		return false;
	}

}
