package pieces.tests;

import static org.junit.Assert.*;
import main.Chessboard;

import org.junit.Test;

public class KingTest {

	@Test
	public void testMoveOne() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 4)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 4, 3, 3);
		assertEquals("Board has just a king.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___k____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("King up one.\n", 0, testBoard.move(3, 3, 4, 3));
		testBoard.forceTurn(0);
		assertEquals("King ne one.\n", 0, testBoard.move(4, 3, 5, 4));
		testBoard.forceTurn(0);
		assertEquals("King right one.\n", 0, testBoard.move(5, 4, 5, 5));
		testBoard.forceTurn(0);
		assertEquals("King se one.\n", 0, testBoard.move(5, 5, 4, 6));
		testBoard.forceTurn(0);
		assertEquals("King down one.\n", 0, testBoard.move(4, 6, 3, 6));
		testBoard.forceTurn(0);
		assertEquals("King sw one.\n", 0, testBoard.move(3, 6, 2, 5));
		testBoard.forceTurn(0);
		assertEquals("King left one.\n", 0, testBoard.move(2, 5, 2, 4));
		testBoard.forceTurn(0);
		assertEquals("King nw one.\n", 0, testBoard.move(2, 4, 3, 3));
	}
	
	@Test
	public void testMoveMore() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 4)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 4, 3, 3);
		assertEquals("Board has just a king.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___k____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("King moving too much.\n", 2, testBoard.move(3, 3, 5, 3));
		assertEquals("King moving too much.\n", 2, testBoard.move(3, 3, 3, 5));
		assertEquals("King moving too much.\n", 2, testBoard.move(3, 3, 4, 5));
		assertEquals("King moving too much.\n", 2, testBoard.move(3, 3, 2, 1));
	}
	
	@Test
	public void testMoveToCheck() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if((row != 0 && row != 6)|| col != 4)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 4, 3, 3);
		testBoard.forceMove(6, 4, 5, 3);
		assertEquals("Board has just a king and a pawn.\n", 
				  "________\n"
				+ "________\n"
				+ "___p____\n"
				+ "________\n"
				+ "___k____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("King can't move into check.\n", 2, testBoard.move(3, 3, 5, 3));
	}
}
