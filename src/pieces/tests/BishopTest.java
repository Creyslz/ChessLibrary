package pieces.tests;

import static org.junit.Assert.*;
import main.Chessboard;

import org.junit.Test;

public class BishopTest {

	@Test
	public void testOrthagonal() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 2)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 2, 3, 3);
		assertEquals("Only a Bishop on board.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___b____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Bishop can't move left!\n", 2, testBoard.move(3, 3, 3, 7));
		assertEquals("Bishop can't move right!\n", 2, testBoard.move(3, 3, 3, 0));
		assertEquals("Bishop can't move up!\n", 2, testBoard.move(3, 3, 7, 3));
		assertEquals("Bishop can't move down!\n", 2, testBoard.move(3, 3, 0, 3));
	}
	
	@Test
	public void testDiagonals() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 2)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 2, 3, 3);
		assertEquals("Only a Bishop on board.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___b____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Bishop to ne!\n", 0, testBoard.move(3, 3, 6, 6));
		testBoard.forceTurn(0);
		assertEquals("Bishop to sw!\n", 0, testBoard.move(6, 6, 4, 4));
		testBoard.forceTurn(0);
		assertEquals("Bishop to se!\n", 0, testBoard.move(4, 4, 2, 6));
		testBoard.forceTurn(0);
		assertEquals("Bishop to ne!\n", 0, testBoard.move(2, 6, 7, 1));
		assertEquals("Only a Bishop on board.\n", 
				  "_b______\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
	}
	
	@Test
	public void testCollisionDiagonal() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if((row != 0 && row != 6) || col != 2)
					testBoard.forceRemove(row, col);
			}
		}
		testBoard.forceMove(0, 2, 3, 3);
		testBoard.forceMove(6, 2, 5, 5);
		assertEquals("A Bishop and some pawns on board.\n", 
				  "________\n"
				+ "________\n"
				+ "_____p__\n"
				+ "________\n"
				+ "___b____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Bishop can't move through other pieces!\n", 2, testBoard.move(3, 3, 7, 7));
		assertEquals("Nothing's moved.\n", 
				  "________\n"
				+ "________\n"
				+ "_____p__\n"
				+ "________\n"
				+ "___b____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
	}
	
}
