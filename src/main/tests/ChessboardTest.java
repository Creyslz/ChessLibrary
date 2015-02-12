package main.tests;

import static org.junit.Assert.*;
import main.Chessboard;

import org.junit.Test;

public class ChessboardTest {

	@Test
	public void testInitialSetup() {
		Chessboard testBoard = new Chessboard();
		assertEquals("Initial board position is correct.\n", 
				  "rnbqkbnr\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "pppppppp\n"
				+ "rnbqkbnr\n", testBoard.printBoard());
		
	}
	
	@Test
	public void testValidMovement() {
		Chessboard testBoard = new Chessboard();
		assertEquals("King's pawn opening move returned sucessfully.\n", 0, testBoard.move(1, 4, 3, 4));
		assertEquals("King's pawn opening board position is correct.\n", 
				  "rnbqkbnr\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "____p___\n"
				+ "________\n"
				+ "pppp_ppp\n"
				+ "rnbqkbnr\n", testBoard.printBoard());
		
		assertEquals("King's pawn mirror move returned sucessfully.\n", 0, testBoard.move(6, 4, 4, 4));
		assertEquals("King's pawn mirror board position is correct.\n", 
				  "rnbqkbnr\n"
				+ "pppp_ppp\n"
				+ "________\n"
				+ "____p___\n"
				+ "____p___\n"
				+ "________\n"
				+ "pppp_ppp\n"
				+ "rnbqkbnr\n", testBoard.printBoard());
		
		
	}
	
	@Test
	public void testWrongTurn() {
		Chessboard testBoard = new Chessboard();
		assertEquals("Black wrong turn move command returned correctly.\n", 3, testBoard.move(6, 4, 4, 4));
		assertEquals("Board state is unchanged.\n", 
				  "rnbqkbnr\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "pppppppp\n"
				+ "rnbqkbnr\n", testBoard.printBoard());
		testBoard.move(1, 4, 3, 4);
		assertEquals("White wrong turn move command returned correctly.\n", 3, testBoard.move(1, 2, 3, 2));
		assertEquals("Board state is unchanged.\n", 
				  "rnbqkbnr\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "____p___\n"
				+ "________\n"
				+ "pppp_ppp\n"
				+ "rnbqkbnr\n", testBoard.printBoard());
		
	}
	
	@Test
	public void testOutOfBounds() {
		Chessboard testBoard = new Chessboard();
		assertEquals("Out of Bounds move command returned correctly.\n", 4, testBoard.move(-1, 4, 4, 4));
		assertEquals("Out of Bounds move command returned correctly.\n", 4, testBoard.move(1, -1, 3, 4));
		assertEquals("Out of Bounds move command returned correctly.\n", 4, testBoard.move(1, 4, -1, 4));
		assertEquals("Out of Bounds move command returned correctly.\n", 4, testBoard.move(1, 4, 3, -1));
		assertEquals("Out of Bounds move command returned correctly.\n", 4, testBoard.move(8, 4, 4, 4));
		assertEquals("Out of Bounds move command returned correctly.\n", 4, testBoard.move(1, 8, 3, 4));
		assertEquals("Out of Bounds move command returned correctly.\n", 4, testBoard.move(1, 4, 8, 4));
		assertEquals("Out of Bounds move command returned correctly.\n", 4, testBoard.move(1, 4, 3, 8));
		assertEquals("Board state is unchanged.\n", 
				  "rnbqkbnr\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "pppppppp\n"
				+ "rnbqkbnr\n", testBoard.printBoard());
		
	}	
	
	@Test
	public void testNoPiece() {
		Chessboard testBoard = new Chessboard();
		assertEquals("Out of Bounds move command returned correctly.\n", 1, testBoard.move(4, 4, 5, 4));
		assertEquals("Board state is unchanged.\n", 
				  "rnbqkbnr\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "pppppppp\n"
				+ "rnbqkbnr\n", testBoard.printBoard());
		
	}
	
	@Test
	public void testForceRemove() {
		Chessboard testBoard = new Chessboard();
		assertEquals("Force remove command returned correctly.\n", 0, testBoard.forceRemove(0, 0));
		assertEquals("Force remove command returned correctly.\n", 0, testBoard.forceRemove(7, 7));
		assertEquals("Board state is unchanged.\n", 
				  "rnbqkbn_\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "pppppppp\n"
				+ "_nbqkbnr\n", testBoard.printBoard());
		
	}	
	
	@Test
	public void testThreaten() {
		Chessboard testBoard = new Chessboard();
		assertEquals("Board state is unchanged.\n", 
				  "rnbqkbnr\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "pppppppp\n"
				+ "rnbqkbnr\n", testBoard.printBoard());
		assertEquals("Pawns threaten.\n", true, testBoard.isPositionThreatened(5, 5, 'b'));
		assertEquals("Pawns threaten.\n", true, testBoard.isPositionThreatened(2, 0, 'w'));
		testBoard.forceRemove(1, 0);
		testBoard.forceRemove(1, 2);
		testBoard.forceRemove(0, 3);
		assertEquals("Board state is missing two pawns.\n", 
				  "rnbqkbnr\n"
				+ "pppppppp\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "________\n"
				+ "_p_ppppp\n"
				+ "rnb_kbnr\n", testBoard.printBoard());
		assertEquals("Removed pawns shouldn't threaten.\n", false, testBoard.isPositionThreatened(2, 1, 'w'));
		
	}


}
