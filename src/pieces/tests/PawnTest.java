package pieces.tests;

import static org.junit.Assert.*;
import main.Chessboard;

import org.junit.Test;

public class PawnTest {

	@Test
	public void openingMoves() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 6 && row != 1)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Only pawns on board.\n", 
				  "________\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "pppppppp\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("King's pawn opening move returned sucessfully.\n", 0, testBoard.move(1, 4, 3, 4));
		assertEquals("Rook's pawn move returned sucessfully.\n", 0, testBoard.move(6, 7, 4, 7));
		assertEquals("Attempting to move a pawn 2 spaces after it has already moved fails.\n", 2, testBoard.move(3, 4, 5, 4));
		//System.out.print(testBoard.printBoard());
	}

	@Test
	public void takeVsMove() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 6 && row != 1)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Only pawns on board.\n", 
				  "________\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "pppppppp\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("King's pawn opening move returned sucessfully.\n", 0, testBoard.move(1, 4, 3, 4));
		assertEquals("King's pawn mirror move returned sucessfully.\n", 0, testBoard.move(6, 4, 4, 4));
		assertEquals("Moving forward collides and DOES NOT take.\n", 2, testBoard.move(3, 4, 4, 4));
		assertEquals("Queen's pawn move returned sucessfully.\n", 0, testBoard.move(1, 3, 3, 3));
		assertEquals("Take!\n", 0, testBoard.move(4, 4, 3, 3));
		System.out.print(testBoard.printBoard());
	}
	
	
	@Test
	public void testBadMoves() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 1 || col != 1)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(1, 1, 3, 3);
		assertEquals("Only a Pawn on board.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___p____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Pawn can't go ne!\n", 2, testBoard.move(3, 3, 6, 6));
		assertEquals("Pawn can't go sw!\n", 2, testBoard.move(3, 3, 4, 4));
		assertEquals("Pawn can't go se!\n", 2, testBoard.move(3, 3, 1, 5));
		assertEquals("Pawn can't go ne!\n", 2, testBoard.move(3, 3, 6, 0));
		assertEquals("Pawn can't move left!\n", 2, testBoard.move(3, 3, 3, 7));
		assertEquals("Pawn can't move right!\n", 2, testBoard.move(3, 3, 3, 0));
		assertEquals("Pawn can't move up!\n", 2, testBoard.move(3, 3, 7, 3));
		assertEquals("Pawn can't move down!\n", 2, testBoard.move(3, 3, 0, 3));
	}

}
