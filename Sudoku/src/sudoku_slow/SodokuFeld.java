package sudoku_slow;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Fabian Wagner & Kai Seegers
 *
 */
public class SodokuFeld extends JPanel {
	
	private Feld_Slow feld;
	private int arr[][] = new int[9][9];
	private Font font;
	private int rand = 50, length = 900;
	
	public SodokuFeld(int width, int height) {
		
		super();
		
		font = new Font("Indie Flower", Font.PLAIN, 72);
		
		this.setBackground(Color.LIGHT_GRAY);
		
		this.setBounds(0,0,width, height);
		
		this.setVisible(true);
		
	}
	
	protected void setFeld(Feld_Slow feld) {
		
		this.feld = feld;
		
		repaint();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		this.drawField(g2d);

		g2d.setColor(Color.blue);
		
		g2d.setFont(font);

		for (int i = 0; i < this.arr.length; i++) {

			for (int j = 0; j < this.arr[i].length; j++) {

				if (this.feld.felder[i][j] > 0) {

					this.drawNumber(i + 1, j + 1, feld.felder[i][j], g2d);

				}

			}

		}
		
		arr = feld.felder;
		
		
		g2d.setFont(new Font("Sans Serif", Font.PLAIN, 72));
		
		for (int i = 0; i<feld.preFelder.length;i++) {
			
			for (int j = 0; j<feld.preFelder[i].length;j++) {
				
				if (feld.preFelder[i][j] > 0) {
					
					g2d.setColor(Color.white);
					
					g2d.fillRect(rand+j*100+5, rand+i*100+5, 90, 90);
					
					g2d.setColor(Color.black);
					
					this.drawNumber(i+1, j+1, feld.preFelder[i][j], g2d);
					
				}
				
			}
			
		}

	}
	

	private void drawField(Graphics2D g2d) {
		
		Color temp = g2d.getColor();
		g2d.setColor(Color.white);
		g2d.fillRect(rand, rand, length, length);
		g2d.setColor(temp);
		
		g2d.setStroke(new BasicStroke(10.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		
		g2d.drawLine(rand, rand, length+rand, rand);
		g2d.drawLine(rand, rand, rand, 950);
		g2d.drawLine(length+rand, length+rand, length+rand, rand);
		g2d.drawLine(length+rand, length+rand, rand, length+rand);
		
		g2d.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		
		g2d.drawLine(length*1/3+rand, rand, length*1/3+rand, length+rand);
		g2d.drawLine(length*2/3+rand, rand, length*2/3+rand, length+rand);
		
		g2d.drawLine(rand, length*1/3+rand, length+rand, length*1/3+rand);
		g2d.drawLine(rand, length*2/3+rand, length+rand, length*2/3+rand);
		
		g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		
		g2d.drawLine(length*1/9+rand, rand, length*1/9+rand, length+rand);
		g2d.drawLine(length*2/9+rand, rand, length*2/9+rand, length+rand);
		g2d.drawLine(length*4/9+rand, rand, length*4/9+rand, length+rand);
		g2d.drawLine(length*5/9+rand, rand, length*5/9+rand, length+rand);
		g2d.drawLine(length*7/9+rand, rand, length*7/9+rand, length+rand);
		g2d.drawLine(length*8/9+rand, rand, length*8/9+rand, length+rand);
		
		g2d.drawLine(rand, length*1/9+rand, length+rand, length*1/9+rand);
		g2d.drawLine(rand, length*2/9+rand, length+rand, length*2/9+rand);
		g2d.drawLine(rand, length*4/9+rand, length+rand, length*4/9+rand);
		g2d.drawLine(rand, length*5/9+rand, length+rand, length*5/9+rand);
		g2d.drawLine(rand, length*7/9+rand, length+rand, length*7/9+rand);
		g2d.drawLine(rand, length*8/9+rand, length+rand, length*8/9+rand);
		
	}
	
	private void drawNumber (int y, int x, int number, Graphics2D g) {
		
		int xValue = 50+x*100-72;
		int yValue = 50+y*100-24;
		
		g.drawString(Integer.toString(number), xValue, yValue);
		
	}

}
