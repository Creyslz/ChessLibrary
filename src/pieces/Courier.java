package pieces;

import main.Chessboard;

public class Courier extends ChessPiece {

	public Courier(char align, char name, int row, int col, Chessboard board) {
		super(align, 'c', row, col, board);
	}

	/*
	 * The courier moves like the king OR it may move to any open space next to an allied piece
	 * The courier may capture pieces on adjacent squares ONLY if an allied artillery is in the same row or column as the target piece.
	 * Note: The artillery does not have to be able to legal move into the target square, simply being in the same row or column is enough.
	 * Lore: The courier is a skilled messenger that is able to deliver messages to it's allies no matter where they are on the battlefield.
	 * By himself, the courier is incapable of combat, however, he is able to mark targets for allied artillery to take down.
	 * @see pieces.ChessPiece#canMoveTo(int, int, int[][])
	 */
	@Override
	public boolean canMoveTo(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		
		if(Math.abs(rowDifference) <= 1 || Math.abs(colDifference) <= 1) {
			if(board[row][col] == 0)
					return true;
			if(canCapture(row, col, board)) {
				return findArtillery(row, col, board);
			}
		}
		
		if(board[row][col] == 0) {
			return findAlly(row, col, board);
		}
		
		return false;
	}
	
	/*
	 * Helper function to check if there is an artillery piece in the designated row or column.
	 */
	private boolean findArtillery(int row, int col, int[][] board) {
		ChessPiece test;
		for(int rowI = 0; rowI < this.getBoard().getMaxRows(); rowI ++) {
			test = this.getBoard().getPieceByPos(rowI, col);
			if(test.getAlignment() == this.getAlignment() && test.getName() == 'a')
				return true;
		}
		for(int colI = 0; colI < this.getBoard().getMaxCols(); colI ++) {
			test = this.getBoard().getPieceByPos(row, colI);
			if(test.getAlignment() == this.getAlignment() && test.getName() == 'a')
				return true;
		}
		return false;
	}
	
	private boolean findAlly(int row, int col, int[][] board) {
		for(int rowI = -1; rowI < 2; rowI ++) {
			for(int colI = -1; colI < 2; colI++) {
				if(this.getBoard().checkBounds(rowI, colI)) {
					if(Math.signum(board[rowI][colI]) == this.getAlignmentDirection())
						return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * @see pieces.ChessPiece#canThreaten(int, int, int[][])
	 */

	@Override
	public boolean canThreaten(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = col - this.getCol();
		
		if(Math.abs(rowDifference) <= 1 || Math.abs(colDifference) <= 1) {
			return findArtillery(row, col, board);
		}
		
		return false;
	}

}
