package main.tests;

import static org.junit.Assert.*;
import main.Chessboard;

import org.junit.Test;

public class QueenTest {

	@Test
	public void testSidetoSide() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 3)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Board state is nearly empty.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n", testBoard.printBoard());
		assertEquals("Queen all the way to left.\n", 0, testBoard.move(0, 3, 0, 7));
		assertEquals("Board state is nearly empty.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "_______q\n", testBoard.printBoard());
		testBoard.forceTurn(0);
		assertEquals("Queen all the way to right.\n", 0, testBoard.move(0, 7, 0, 0));
		assertEquals("Board state is nearly empty.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "q_______\n", testBoard.printBoard());
	}
	
	@Test
	public void testForwardAndBack() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 3)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Board state is nearly empty.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n", testBoard.printBoard());
		assertEquals("Queen all the way to top.\n", 0, testBoard.move(0, 3, 7, 3));
		assertEquals("Board state is nearly empty.\n", 
				  "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		testBoard.forceTurn(0);
		assertEquals("Queen all the way to top.\n", 0, testBoard.move(7, 3, 3, 3));
		assertEquals("Board state is nearly empty.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
	}
	
	@Test
	public void testDiagonals() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 3)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Board state is nearly empty.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n", testBoard.printBoard());
		assertEquals("Queen all the way to left.\n", 0, testBoard.move(0, 3, 0, 7));
		assertEquals("Queen lower right.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "_______q\n", testBoard.printBoard());
		testBoard.forceTurn(0);
		assertEquals("Queen all the way to top right.\n", 0, testBoard.move(0, 7, 7, 0));
		assertEquals("Queen upper left.\n", 
				  "q_______\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		testBoard.forceTurn(0);
		assertEquals("Queen all the way to top right.\n", 0, testBoard.move(7, 0, 0, 0));
		testBoard.forceTurn(0);
		assertEquals("Queen all the way to top right.\n", 0, testBoard.move(0, 0, 7, 7));
		assertEquals("Queen upper right.\n", 
				  "_______q\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
	}
	
	
	@Test
	public void testCollisionVertical() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if((row != 0 && row != 7) || col != 3)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Board state is nearly empty.\n", 
				  "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n", testBoard.printBoard());
		assertEquals("White queen forward.\n", 0, testBoard.move(0, 3, 5, 3));
		assertEquals("Two queens at 5,3 and 7,3.\n", 
				  "___q____\n"
				+ "________\n"
				+ "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Black queen can't move past\n", 2, testBoard.move(7, 3, 3, 3));
		assertEquals("Two queens at 5,3 and 7,3.\n", 
				  "___q____\n"
				+ "________\n"
				+ "___q____\n"
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
				if((row != 0 && row != 7) || col != 3)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Board state is nearly empty.\n", 
				  "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n", testBoard.printBoard());
		assertEquals("White queen forward.\n", 0, testBoard.move(0, 3, 5, 3));
		assertEquals("Black queen sideways.\n", 0, testBoard.move(7, 3, 7, 5));
		assertEquals("Two queens at 5,3 and 7,3.\n", 
				  "_____q__\n"
				+ "________\n"
				+ "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		testBoard.forceTurn(.5);
		assertEquals("Black queen can't move past\n", 2, testBoard.move(7, 5, 4, 2));
		assertEquals("Two queens at 5,3 and 7,3.\n", 
				  "_____q__\n"
				+ "________\n"
				+ "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
	}
	
	@Test
	public void testInvalid() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if(row != 0 || col != 3)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Board state is nearly empty.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n", testBoard.printBoard());
		assertEquals("Queen should not move.\n", 2, testBoard.move(0, 3, 7, 7));
		assertEquals("Queen should not move.\n", 2, testBoard.move(0, 3, 7, 0));
		assertEquals("Queen should not move.\n", 2, testBoard.move(0, 3, 1, 5));
		assertEquals("Board state is nearly empty.\n", 
				  "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n", testBoard.printBoard());
	}
	
	@Test
	public void testDeath() {
		Chessboard testBoard = new Chessboard();
		for(int row = 0; row < testBoard.getMaxRows(); row++) {
			for(int col = 0; col < testBoard.getMaxCols(); col++) {
				if((row != 0 && row != 7) || col != 3)
					testBoard.forceRemove(row, col);
			}
		}
		assertEquals("Board state is nearly empty.\n", 
				  "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "___q____\n", testBoard.printBoard());
		assertEquals("Queen should capture move.\n", 0, testBoard.move(0, 3, 7, 3));
		assertEquals("Board state is nearly empty.\n", 
				  "___q____\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n", testBoard.printBoard());
		assertEquals("Black queen is now dead and should no longer threaten.\n", false, testBoard.isPositionThreatened(7, 7, 'b'));
	}
}
