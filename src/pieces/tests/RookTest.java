package pieces.tests;

import static org.junit.Assert.*;
import main.Chessboard;

import org.junit.Test;

public class RookTest {

	@Test
	public void testOrthagonal() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 0)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 0, 3, 3);
		assertEquals("Only a Rook on board.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___r____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Rook moved left!\n", 0, testBoard.move(3, 3, 3, 7));
		testBoard.forceTurn(0);
		assertEquals("Rook moved right!\n", 0, testBoard.move(3, 7, 3, 2));
		testBoard.forceTurn(0);
		assertEquals("Rook moved up!\n", 0, testBoard.move(3, 2, 6, 2));
		testBoard.forceTurn(0);
		assertEquals("Rook moved down!\n", 0, testBoard.move(6, 2, 2, 2));
		assertEquals("Still only a Rook on board.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "__r_____\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
	}
	
	@Test
	public void testDiagonals() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 0)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 0, 3, 3);
		assertEquals("Only a Rook on board.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___r____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Rook can't go ne!\n", 2, testBoard.move(3, 3, 6, 6));
		assertEquals("Rook can't go sw!\n", 2, testBoard.move(3, 3, 4, 4));
		assertEquals("Rook can't go se!\n", 2, testBoard.move(3, 3, 1, 5));
		assertEquals("Rook can't go ne!\n", 2, testBoard.move(3, 3, 6, 0));
	}
	
	@Test
	public void testCollisionOrthagonal() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if((row != 0 && row != 6) || col != 0)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 0, 3, 3);
		testBoard.forceMove(6, 0, 5, 3);
		assertEquals("A rook and a pawn on board.\n", 
				  "________\n"
				+ "________\n"
				+ "___p____\n"
				+ "________\n"
				+ "___r____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Rook can't move through other pieces!\n", 2, testBoard.move(3, 3, 7, 3));
		assertEquals("Nothing's moved.\n", 
				  "________\n"
				+ "________\n"
				+ "___p____\n"
				+ "________\n"
				+ "___r____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
	}
	
}
