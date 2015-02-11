package pieces;

import main.Chessboard;


public class Bishop extends ChessPiece {

	public Bishop(char align, int row, int col, Chessboard board) {
		super(align, 'b', row, col, board);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMoveTo(int row, int col, int[][] board) {
		int rowDifference = row - this.getRow();
		int colDifference = row - this.getCol();
		
		if(rowDifference == 0 && colDifference == 0)
			return false;
		
		if(checkCollision(this.getRow(), this.getCol(), row, col, board))
			return canCapture(row, col, board);
		
		return false;
	}


}
