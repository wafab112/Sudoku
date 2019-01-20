package sudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import generator.SudokuGenerator;

/**
 * 
 * @author Fabian Wagner & Kai Seegers
 *
 */
public class Feld extends JFrame {
	
	protected int[][] felder = new int[9][9];
	
	protected int[][] preFelder = new int[9][9];
	
	protected long waitLength = 10;
	
	protected SodokuFeld spielFeld;
	
	public Feld(String name, int difficulty, boolean fast) {
		
		super(name);
		
		Font customFont = this.setFont();
		
		try {
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(customFont);
			
		} catch (NullPointerException e) {
			
			e.printStackTrace();
			
		}
		
		this.setBounds(0,0,910,940);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		felder = this.generateNew(difficulty);
		
		for (int i = 0; i<felder.length; i++) {
			
			for (int j = 0; j<felder[i].length; j++) {
				
				preFelder[i][j] = felder[i][j];
				
			}
			
		}
		
		spielFeld = new SodokuFeld(910,940);
		
		this.add(spielFeld);
		
		spielFeld.setFeld(this);
		
		this.setVisible(true);
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {
				
				long START = System.currentTimeMillis();
				
				solve(0,0,fast);
				
				long END = System.currentTimeMillis();
				
				System.out.println("Das Lösen des Sudokus dauerte " + (END - START) + "ms");
				
				spielFeld.repaint();
				
			}
			
		});
		
	}
	
	protected boolean solve(int x, int y, boolean fast) {
		
		if (x<9) {
			
			if (y<9) {
				
				if (felder[y][x] == 0) {
		
					for (int i = 1; i < 10; i++) {

						if (checkNumber(x, y, i)) {

							felder[y][x] = i;
							
							if (!fast) {
							
								waitAmount(waitLength);
								
								spielFeld.paintImmediately(0,0,1000,1000);
								
							}
							
							if (solve(x, y+1, fast)) return true;

						} 

					}
					
					boolean solved = true;
					
					for (int i = 0; i<9; i++) {
						
						for (int j = 0; j<9; j++) {
							
							if (felder[i][j] == 0) solved = false;
							
						}
						
					}
					
					if (solved) {
						
						return true;
						
					} else {
						
						felder[y][x] = 0;
						
						return false;
						
					}
				
				} else {
					
					solve(x,y+1, fast);
					
				}
			
			} else {
				
				x++;
				y = 0;
				
				solve(x,y, fast);
				
			}
		
		} 
		
		return false;
		
	}
	
	private Font setFont() {
		
		Font font = null;
		
		String path = System.getProperty("user.dir");
		
		System.out.println(path + "\\Resource\\IndieFlower.ttf");
		
		try {
			
		    font = Font.createFont(Font.TRUETYPE_FONT, new File(path + "\\Resource\\IndieFlower.ttf")).deriveFont(12f);
		    
		} catch (IOException e) {
			
		    //e.printStackTrace();
		    
		} catch(FontFormatException e) {
			
		    e.printStackTrace();
		    
		}
		
		
		try {
			
			font = Font.createFont(Font.TRUETYPE_FONT, new File("./Resource/IndieFlower.ttf")).deriveFont(12f);
			
		} catch (IOException e) {
			
		    //e.printStackTrace();
		    
		} catch(FontFormatException e) {
			
		    e.printStackTrace();
		    
		}
		
		return font;
		
	}
	
	private void waitAmount(long millis) {

		try {

			synchronized (this) {

				this.wait(millis);

			}

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	}
	
	private boolean checkNumber(int x, int y, int number) {
		
		if (checkHor(y, number) && checkVer(x, number) && checkSquare(x,y,number)) {
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	private boolean checkHor(int y, int number) {
		
		for (int i = 0; i<this.felder.length; i++) {
			
			if (felder[y][i] == number) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	private boolean checkVer(int x, int number) {
		
		for (int i = 0; i<this.felder.length; i++) {
			
			if (felder[i][x] == number) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	private boolean checkSquare(int x, int y, int number) {
		
		if (x>=0 && x<3 && y>=0 && y<3) {
			
			for (int i = 0; i<3; i++) {
				
				for (int j = 0; j<3; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// oben links
			
		} else if (x>2 && x<6 && y>= 0 && y<3) {
			
			for (int i = 0; i<3; i++) {
				
				for (int j = 3; j<6; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// oben mitte
			
		} else if (x>5 && x<9 && y>= 0 && y<3) {
			
			for (int i = 0; i<3; i++) {
				
				for (int j = 6; j<9; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// oben rechts
			
		} else if (x>=0 && x<3 && y> 2 && y<6) {
			
			for (int i = 3; i<6; i++) {
				
				for (int j = 0; j<3; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// mitte links
			
		} else if (x>2 && x<6 && y> 2 && y<6) {
			
			for (int i = 3; i<6; i++) {
				
				for (int j = 3; j<6; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// mitte mitte
			
		} else if (x>5 && x<9 && y> 2 && y<6) {
			
			for (int i = 3; i<6; i++) {
				
				for (int j = 6; j<9; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// mitte rechts
			
		} else if (x>=0 && x<3 && y> 5 && y<9) {
			
			for (int i = 6; i<9; i++) {
				
				for (int j = 0; j<3; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// unten links
			
		} else if (x>2 && x<6 && y> 5 && y<9) {
			
			for (int i = 6; i<9; i++) {
				
				for (int j = 3; j<6; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// unten mitte
			
		} else if (x>5 && x<9 && y> 5 && y<9) {
			
			for (int i = 6; i<9; i++) {
				
				for (int j = 6; j<9; j++) {
					
					if (felder[i][j] == number) return false;
					
				}
				
			}
			
			return true;
			
			// unten rechts
			
		}
		
		return false;
		
	}
	
	public int[][] generateNew(int difficulty) {
		
		int[][] arr = new int[9][9];
		
		SudokuGenerator SG = new SudokuGenerator();
		
		arr = SG.nextBoard(difficulty);
		
		return arr;
		
	}
	
	

}
