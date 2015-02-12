package pieces.tests;

import static org.junit.Assert.*;
import main.Chessboard;

import org.junit.Test;

public class KnightTest {
	
	@Test
	public void testStraightLines() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 1)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 1, 3, 3);
		assertEquals("Only a Knight on board.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___n____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Knight can't go ne!\n", 2, testBoard.move(3, 3, 6, 6));
		assertEquals("Knight can't go sw!\n", 2, testBoard.move(3, 3, 4, 4));
		assertEquals("Knight can't go se!\n", 2, testBoard.move(3, 3, 1, 5));
		assertEquals("Knight can't go ne!\n", 2, testBoard.move(3, 3, 6, 0));
		assertEquals("Knight can't move left!\n", 2, testBoard.move(3, 3, 3, 7));
		assertEquals("Knight can't move right!\n", 2, testBoard.move(3, 3, 3, 0));
		assertEquals("Knight can't move up!\n", 2, testBoard.move(3, 3, 7, 3));
		assertEquals("Knight can't move down!\n", 2, testBoard.move(3, 3, 0, 3));
	}
	

	
	@Test
	public void testKnightMovement() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 1)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 1, 3, 3);
		assertEquals("Only a Knight on board.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___n____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Knight nne!\n", 0, testBoard.move(3, 3, 5, 4));
		testBoard.forceTurn(0);
		assertEquals("Knight ssw!\n", 0, testBoard.move(5, 4, 3, 3));
		testBoard.forceTurn(0);
		assertEquals("Knight nnw!\n", 0, testBoard.move(3, 3, 5, 2));
		testBoard.forceTurn(0);
		assertEquals("Knight sse!\n", 0, testBoard.move(5, 2, 3, 3));
		testBoard.forceTurn(0);
		assertEquals("Knight wwn!\n", 0, testBoard.move(3, 3, 4, 1));
		testBoard.forceTurn(0);
		assertEquals("Knight ees!\n", 0, testBoard.move(4, 1, 3, 3));
		testBoard.forceTurn(0);
		assertEquals("Knight wws!\n", 0, testBoard.move(3, 3, 2, 1));
		testBoard.forceTurn(0);
		assertEquals("Knight ees!\n", 0, testBoard.move(2, 1, 3, 3));
	}
	
}
