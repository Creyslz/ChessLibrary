package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ChessboardGUI implements ActionListener {

    private final JPanel appGUI = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares;
    private Image[][] chessPieceImages = new Image[2][9]; //Images stored in order kqrbnac, white row one, black row two.
    private JPanel chessboardGUI;
    private final JLabel message = new JLabel("Chess board is ready!");
    private Chessboard boardData;
    private static final String COLS = "ABCDEFGH";
	
	public ChessboardGUI(Chessboard chessData) {
		boardData = chessData;
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception e) {
            //silently ignore
        }
        JFrame window = new JFrame("Chess");
        window.setSize(720, 720);
        chessBoardSquares = new JButton[boardData.getMaxRows()][boardData.getMaxCols()];
        createImages();
        initializeAppGUI();
        drawPieces();
        window.add(appGUI);
        setUpMenu(window);
       // window.setContentPane(myPanel);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initializeAppGUI() {
        // set up the main GUI
        appGUI.setBorder(new EmptyBorder(5, 5, 5, 5));
        setupToolbar();
        appGUI.add(new JLabel("?"), BorderLayout.LINE_START);
        chessboardGUI = new JPanel(new GridLayout(0, 9));
        chessboardGUI.setBorder(new LineBorder(Color.BLACK));
        appGUI.add(chessboardGUI);
        setupBoard();
        //fill the chess board
        chessboardGUI.add(new JLabel(""));
        // fill the top row
        for (int index = 0; index < 8; index++) {
            chessboardGUI.add(new JLabel(COLS.substring(index, index + 1), SwingConstants.CENTER));
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                switch (col) {
                    case 0:
                        chessboardGUI.add(new JLabel("" + (8-row),
                                SwingConstants.CENTER));
                    default:
                        chessboardGUI.add(chessBoardSquares[boardData.getMaxRows()-row-1][boardData.getMaxCols() - col-1]);
                }
            }
        }
	}
	
	private void setupToolbar() {
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        appGUI.add(toolbar, BorderLayout.PAGE_START);
        toolbar.add(new JButton("Buttons!"));
        toolbar.addSeparator();
        toolbar.add(new JButton("More Buttons!")); // TODO - add functionality!
        toolbar.addSeparator();
        toolbar.add(message);
	}
	
	private void setupBoard() {
        // create the chess board squares
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int row = 0; row < chessBoardSquares.length; row++) {
            for (int col = 0; col < chessBoardSquares[row].length; col++) {
                JButton button = new JButton();
                button.setMargin(buttonMargin);
                //ImageIcon icon = new ImageIcon( new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)); // Transparent image
                //b.setIcon(icon);
                if ((col % 2 == 1 && row % 2 == 1) || (col % 2 == 0 && row % 2 == 0)) {
                    button.setBackground(Color.WHITE);
                } else {
                    button.setBackground(Color.BLACK);
                }
                button.setContentAreaFilled(false);
                button.setOpaque(true);

                chessBoardSquares[row][col] = button;
            }
        }
	}
 
    private void setUpMenu(JFrame window) {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        menubar.add(file);
        window.setJMenuBar(menubar);
    }
    
    // Images stored in order kqrbnpac, white row 0, black row 1.
    // Loads in images for future use. 
    private final void createImages() {
        try {
            chessPieceImages[0][0] = ImageIO.read(new File("Images/wk.png"));
            chessPieceImages[0][1] = ImageIO.read(new File("Images/wq.png"));
            chessPieceImages[0][2] = ImageIO.read(new File("Images/wr.png"));
            chessPieceImages[0][3] = ImageIO.read(new File("Images/wb.png"));
            chessPieceImages[0][4] = ImageIO.read(new File("Images/wn.png"));
            chessPieceImages[0][5] = ImageIO.read(new File("Images/wp.png"));
            chessPieceImages[0][6] = ImageIO.read(new File("Images/wa.png"));
            chessPieceImages[0][7] = ImageIO.read(new File("Images/wc.png"));
            chessPieceImages[1][0] = ImageIO.read(new File("Images/bk.png"));
            chessPieceImages[1][1] = ImageIO.read(new File("Images/bq.png"));
            chessPieceImages[1][2] = ImageIO.read(new File("Images/br.png"));
            chessPieceImages[1][3] = ImageIO.read(new File("Images/bb.png"));
            chessPieceImages[1][4] = ImageIO.read(new File("Images/bn.png"));
            chessPieceImages[1][5] = ImageIO.read(new File("Images/bp.png"));
            chessPieceImages[1][6] = ImageIO.read(new File("Images/ba.png"));
            chessPieceImages[1][7] = ImageIO.read(new File("Images/bc.png"));
            chessPieceImages[0][8] = ImageIO.read(new File("Images/e.png"));
            chessPieceImages[1][8] = ImageIO.read(new File("Images/e.png"));
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void drawPieces() {
    	for(int row = 0; row < boardData.getMaxRows(); row++) {
    		for(int col = 0; col < boardData.getMaxCols(); col++) {
    			int pieceId = boardData.getBoard()[row][col];
    			if(pieceId > 0) {
    				chessBoardSquares[row][col].setIcon(new ImageIcon(
    						chessPieceImages[0][
    							getImageId(boardData.getPieceById(pieceId, 1).getName()) ]));
    			} else if(pieceId < 0) {
    				chessBoardSquares[row][col].setIcon(new ImageIcon(
    						chessPieceImages[1][
    							getImageId(boardData.getPieceById(-pieceId, -1).getName()) ]));
    			}
    		}
    	}
    }
    
    private int getImageId(char pieceName) {
    	switch(pieceName) {
    		case 'k':
    			return 0;
    		case 'q':
    			return 1;
    		case 'r':
    			return 2;
    		case 'b':
    			return 3;
    		case 'n':
    			return 4;
    		case 'p':
    			return 5;
    		case 'a':
    			return 6;
    		case 'c':
    			return 7;
    	}
    	return 8;
    }

	@Override
	public void actionPerformed(ActionEvent event) {
        //JOptionPane.showMessageDialog(null,
                //"I was clicked by "+event.getActionCommand(),
                //"Title here", JOptionPane.INFORMATION_MESSAGE);

	}

	public static void main(String[] args) {
		new ChessboardGUI(new Chessboard());

	}

}
