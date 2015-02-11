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
	private int currentMove; 
//	private ArrayList<ArrayList<ChessPiece>> turnLog; // In case a turn log is needed for the future. Currently unimplemented
	private int[][] board; // Within the board white pieces are stored by their index + 1 in their ArrayList
						   // Black pieces are stored by their -(index + 1) in their ArrayList
						   // King is always the final piece within the list.
	private ArrayList<ChessPiece> whitePieces;
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

	public boolean isPositionThreatened(int targetRow, int targetCol, char byWhom) {
		if(byWhom == 'w') {
			for(int i = 0; i < whitePieces.size(); i++) {
				if(whitePieces.get(i).canMoveTo(targetRow, targetCol, board))
					return true;
			}
		} else if(byWhom == 'b') {
			for(int i = 0; i < blackPieces.size(); i++) {
				if(blackPieces.get(i).canMoveTo(targetRow, targetCol, board))
					return true;
			}
		}
		return false;
	}
	
	// Sets up the white pieces for a standard match
	private void setUpWhite() {
		whitePieces = new ArrayList<ChessPiece>();
		for(int i = 0; i < 8; i++){
			whitePieces.add(new Pawn('w', 1, i, this));
			board[1][i] = i+1;
		}
		whitePieces.add(new Rook('w', 0, 0, this));
		whitePieces.add(new Rook('w', 0, 7, this));
		whitePieces.add(new Bishop('w', 0, 2, this));
		whitePieces.add(new Bishop('w', 0, 5, this));
		whitePieces.add(new Knight('w', 0, 1, this));
		whitePieces.add(new Knight('w', 0, 6, this));
		whitePieces.add(new Queen('w', 0, 3, this));
		whitePieces.add(new King('w', 0, 4, this));
		board[0][0] = 9;
		board[0][7] = 10;
		board[0][2] = 11;
		board[0][5] = 12;
		board[0][1] = 13;
		board[0][6] = 14;
		board[0][3] = 15;
		board[0][4] = 16;
	}

	private boolean hasWhiteLost() {
		return whitePieces.get(whitePieces.size()-1).isAlive();
	}
	
	private boolean hasBlackLost() {
		return blackPieces.get(blackPieces.size()-1).isAlive();
	}
	
	// Sets up the black pieces for a standard match
	private void setUpBlack() {
		blackPieces = new ArrayList<ChessPiece>();
		for(int i = 0; i < 8; i++){
			blackPieces.add(new Pawn('b', 6, i, this));
			board[6][i] = 0-(i+1);
		}
		blackPieces.add(new Rook('b', 7, 0, this));
		blackPieces.add(new Rook('b', 7, 7, this));
		blackPieces.add(new Bishop('b', 7, 2, this));
		blackPieces.add(new Bishop('b', 7, 5, this));
		blackPieces.add(new Knight('b', 7, 1, this));
		blackPieces.add(new Knight('b', 7, 6, this));
		blackPieces.add(new Queen('b', 7, 3, this));
		blackPieces.add(new King('b', 7, 4, this));
		board[7][0] = -9;
		board[7][7] = -10;
		board[7][2] = -11;
		board[7][5] = -12;
		board[7][1] = -13;
		board[7][6] = -14;
		board[7][3] = -15;
		board[7][4] = -16;
	}

	public int getMaxRows() {
		return maxRows;
	}

	public int getMaxCols() {
		return maxCols;
	}
	
	/* Things delegated to game loop
	 * Alert if king is currently in check
	 * Checking if the game has ended (is the King alive? or if there's time, run all possible moves to see if checkmate can be avoided)
	 * Checking if moves are valid (Has it left the king in check?)
	 * Moving pieces and updating relevant values (piece last turn moved, is the piece alive)
	 * Updating game log
	 */
	

}
