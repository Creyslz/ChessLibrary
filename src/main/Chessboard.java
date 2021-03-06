package main;
import java.util.ArrayList;

import pieces.Bishop;
import pieces.ChessPiece;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;

public class Chessboard {
	private int maxRows; // Limited to rectangular boards
	private int maxCols;
	private double currentMove; 
	//	private ArrayList<ArrayList<ChessPiece>> turnLog; // In case a turn log is needed for the future. Currently unimplemented
	private int[][] board; // Within the board white pieces are stored by a unique positive index
	// Black pieces are stored by a unique negative index
	// King is always the first piece within the list.
	private ArrayList<ChessPiece> whitePieces; // Within both of these ArrayLists, pieces are stored in the |index|-1 position, where index is their Id in the board
	private ArrayList<ChessPiece> blackPieces;

	// default constructor
	// Creates a standard 8x8 chess board with standard pieces in standard places.
	public Chessboard() {
		maxRows = 8;
		maxCols = 8;
		currentMove = 0;
		//		turnLog = new ArrayList<ArrayList<ChessPiece>>();
		board = new int[maxRows][maxCols];
		setUpWhite();
		setUpBlack();
	}

	
	// Checks if the target position can be attacked by the specified side. 
	// Useful for determining valid king moves. 
	// @param row column of position to check
	// @param 'b' or 'w' depending on who is doing the threatening
	// @return a boolean noting if the position is threatened or not
	public boolean isPositionThreatened(int targetRow, int targetCol, char byWhom) {
		if(byWhom == 'w') {
			for(int i = 0; i < whitePieces.size(); i++) {
				if(whitePieces.get(i).isAlive())
					if(whitePieces.get(i).canThreaten(targetRow, targetCol, board)) 
						return true;
			}
		} else if(byWhom == 'b') {
			for(int i = 0; i < blackPieces.size(); i++) {
				if(blackPieces.get(i).isAlive())
					if(blackPieces.get(i).canThreaten(targetRow, targetCol, board))
						return true;
			}
		}
		return false;
	}

	// Sets up the white pieces for a standard match
	private void setUpWhite() {
		whitePieces = new ArrayList<ChessPiece>();
		whitePieces.add(new King('w', 0, 4, this));
		for(int i = 0; i < 8; i++){
			whitePieces.add(new Pawn('w', 1, i, this));
			board[1][i] = i+2;
		}
		whitePieces.add(new Rook('w', 0, 0, this));
		whitePieces.add(new Rook('w', 0, 7, this));
		whitePieces.add(new Bishop('w', 0, 2, this));
		whitePieces.add(new Bishop('w', 0, 5, this));
		whitePieces.add(new Knight('w', 0, 1, this));
		whitePieces.add(new Knight('w', 0, 6, this));
		whitePieces.add(new Queen('w', 0, 3, this));
		board[0][0] = 10;
		board[0][7] = 11;
		board[0][2] = 12;
		board[0][5] = 13;
		board[0][1] = 14;
		board[0][6] = 15;
		board[0][3] = 16;
		board[0][4] = 1;
	}

	// The following two methods checks if the kings of the two sides are still alive.
	// At some point, these should be expanded into also checking if the kings are in checkmate.
	// @return true if white king is still alive
	// @return false if it's dead.
	private boolean hasWhiteLost() {
		return whitePieces.get(0).isAlive();
	}

	// @return true if black king is still alive
	// @return false if it's dead.
	private boolean hasBlackLost() {
		return blackPieces.get(0).isAlive();
	}

	// Sets up the black pieces for a standard match
	private void setUpBlack() {
		blackPieces = new ArrayList<ChessPiece>();
		blackPieces.add(new King('b', 7, 4, this));
		for(int i = 0; i < 8; i++){
			blackPieces.add(new Pawn('b', 6, i, this));
			board[6][i] = 0-(i+2);
		}
		blackPieces.add(new Rook('b', 7, 0, this));
		blackPieces.add(new Rook('b', 7, 7, this));
		blackPieces.add(new Bishop('b', 7, 2, this));
		blackPieces.add(new Bishop('b', 7, 5, this));
		blackPieces.add(new Knight('b', 7, 1, this));
		blackPieces.add(new Knight('b', 7, 6, this));
		blackPieces.add(new Queen('b', 7, 3, this));
		board[7][0] = -10;
		board[7][7] = -11;
		board[7][2] = -12;
		board[7][5] = -13;
		board[7][1] = -14;
		board[7][6] = -15;
		board[7][3] = -16;
		board[7][4] = -1;
	}

	// Simple getters for board dimensions
	// @return maximum number of rows. 
	public int getMaxRows() {
		return maxRows;
	}


	// @return maximum number of columns. 
	public int getMaxCols() {
		return maxCols;
	}
	
	// @return the int array that represents the board
	public int[][] getBoard() {
		return board;
	}

	// Methods for checking if coordinates are within bounds.
	// @param	Start row and column
	// @param	End row and column
	// @return boolean for if the positions are in bounds.
	public boolean checkBounds(int startRow, int startCol, int endRow, int endCol) {
		if(startRow < 0 || startRow >= maxRows)
			return false;
		if(endRow < 0 || endRow >= maxRows)
			return false;
		if(startCol < 0 || startCol >= maxCols)
			return false;
		if(endCol < 0 || endCol >= maxCols)
			return false;

		return true;
	}

	public boolean checkBounds(int row, int col) {
		if(row < 0 || row >= maxRows)
			return false;
		if(col < 0 || col >= maxRows)
			return false;
		return true;
	}


	// Moves the piece in the start coordinate to the end coordinate. 
	// Also handles the updating of relevent data.
	// @param  Position of piece to move
	// @param  Where to move the piece to. 
	// @return 0 on success
	// @return 1 on no piece at start position
	// @return 2 on invalid move.
	// @return 3 on incorrect turn.
	// @return 4 on out of bounds
	// @return -1 unknown error
	public int move(int startRow, int startCol, int endRow, int endCol)
	{
		if(checkBounds(startRow, startCol, endRow, endCol) == false)
			return 4;
		int pieceId = Math.abs(board[startRow][startCol]);
		int pieceAlignment = (int) Math.signum(board[startRow][startCol]);
		int targetId = Math.abs(board[endRow][endCol]);
		int targetAlignment = (int) Math.signum(board[endRow][endCol]);

		if(pieceId == 0)
			return 1;
		if((pieceAlignment == -1 && currentMove % 1 == 0) || (pieceAlignment == 1 && currentMove % 1 == .5))
			return 3;
		
		ChessPiece movingPiece = getPieceById(pieceId, pieceAlignment);
		if (!movingPiece.canMoveTo(endRow, endCol, board))
			return 2;
		setPieceDead(targetId, targetAlignment);
		movingPiece.moveTo(endRow, endCol);
		movingPiece.setLastTurnMoved(currentMove);
		currentMove += .5;
		board[endRow][endCol] = board[startRow][startCol];
		board[startRow][startCol] = 0;
		return 0;
	}

	// Set's a piece as not alive given it's id and alignment
	// Safe to pass an id of 0
	// @param piece id and alignment
	private void setPieceDead(int pieceId, int alignment) {
		if(pieceId == 0)
			return;
		if(alignment == 1) 
			whitePieces.get(pieceId - 1).setAlive(false);
		if (alignment == -1) 
			blackPieces.get(pieceId - 1).setAlive(false);
	}

	// @param  a piece id and alignment
	// @return the desired chess piece given its id and alignment
	// @return null on fail.
	public ChessPiece getPieceById(int pieceId, int alignment) {
		if(alignment == 1) 
			return whitePieces.get(pieceId - 1);
		if (alignment == -1) 
			return blackPieces.get(pieceId - 1);
		return null;
	}
	
	public ChessPiece getPieceByPos(int row, int col) {
		int id = board[row][col];
		if(id == 0) 
			return null;
		if(id > 0)
			return whitePieces.get(id - 1);
		return blackPieces.get(-(id + 1));
		
	}

	// Forcible piece movement that is used to debug purposes. Does no checking of move validity
	// @param  A start position and end position. Moves piece at start position to end position.
	// @return 0 on success
	// @return 4 on out of bounds
	public int forceMove(int startRow, int startCol, int endRow, int endCol) {
		if(checkBounds(startRow, startCol, endRow, endCol) == false)
			return 4;
		int pieceId = Math.abs(board[startRow][startCol]);
		int pieceAlignment = (int) Math.signum(board[startRow][startCol]);

		int targetId = Math.abs(board[endRow][endCol]);
		int targetAlignment = (int) Math.signum(board[endRow][endCol]);
		
		if(targetAlignment == 1)
			whitePieces.get(targetId - 1).setAlive(false);
		if(targetAlignment == -1)
			blackPieces.get(targetId - 1).setAlive(false);
		board[endRow][endCol] = board[startRow][startCol];
		board[startRow][startCol] = 0;		

		ChessPiece movingPiece = null;
		if(pieceAlignment == 1) {
			movingPiece = whitePieces.get(pieceId-1);
		} else if (pieceAlignment == -1) {
			movingPiece = blackPieces.get(pieceId-1);
		}

		movingPiece.moveTo(endRow, endCol);
		return 0;
	}

	// Forcibly removes a piece from the game.
	// @param  Position of piece to remove
	// @return 0 on success
	// @return 4 on out of bounds
	public int forceRemove(int targetRow, int targetCol) {
		if(checkBounds(targetRow, targetCol) == false)
			return 4;

		int targetId = Math.abs(board[targetRow][targetCol]);
		int targetAlignment = (int) Math.signum(board[targetRow][targetCol]);

		if(targetAlignment == 1) {
			whitePieces.get(targetId - 1).setAlive(false);
		}
		if(targetAlignment == -1)
			blackPieces.get(targetId - 1).setAlive(false);
		board[targetRow][targetCol] = 0;		

		return 0;
	}
	
	/*
	 * Inserts a piece at the target location
	 * If a piece was already there, it kills the piece
	 * @param Position to insert
	 * @param piece to insert
	 * @return 0 on success
	 * @return 1 on failure due to out of bounds
	 */
	public int insertPiece(int targetRow, int targetCol, ChessPiece newPiece) {
		if(checkBounds(targetRow, targetCol) == false)
			return 1;
		char alignment = newPiece.getAlignment();
		forceRemove(targetRow, targetCol);
		if(alignment == 'w') {
			whitePieces.add(newPiece);
			board[targetRow][targetCol] = whitePieces.size();
		}
		if(alignment == 'b') {
			blackPieces.add(newPiece);
			board[targetRow][targetCol] = -blackPieces.size();
		}
		return 0;
	}

	// @param a double representing the current move.
	public void forceTurn(double move) {
		currentMove = move;
	}

	// returns the board in text format
	// Used for debugging, If possible, use the toString method. 
	// @return a string representation on the board
	public String printBoard() {
		String output = "";
		for(int row = maxRows-1; row >= 0; row --) {
			for(int col = 0; col < maxCols; col ++) {
				int pieceId = board[row][col];
				if(Math.signum(pieceId) == 0) {
					output += "_";
				} else if(Math.signum(pieceId) == 1) {
					output += whitePieces.get(pieceId - 1).getName();
				} else if(Math.signum(pieceId) == -1) {
					output += blackPieces.get(-(pieceId) - 1).getName();
				}
			}
			output += "\n";
		}
		return output;
	}
	
	// returns the board in text format
	// White pieces are lowercase and Black pieces are Uppercase
	// @return a string representation on the board
	public String toString() {
		String output = "";
		for(int row = maxRows-1; row >= 0; row --) {
			for(int col = 0; col < maxCols; col ++) {
				int pieceId = board[row][col];
				if(Math.signum(pieceId) == 0) {
					output += "_";
				} else if(Math.signum(pieceId) == 1) {
					output += whitePieces.get(pieceId - 1).getName();
				} else if(Math.signum(pieceId) == -1) {
					output += String.valueOf(blackPieces.get(-(pieceId) - 1).getName()).toUpperCase();
				}
			}
			output += "\n";
		}
		return output;
	}
	

	/* Things delegated to game loop
	 * Alert if king is currently in check
	 * Checking if the game has ended (is the King alive? or if there's time, run all possible moves to see if checkmate can be avoided)
	 * Checking if moves are valid (Has it left the king in check?)
	 * Moving pieces and updating relevant values (piece last turn moved, is the piece alive)
	 * Updating game log
	 */


}
