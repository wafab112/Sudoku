package sudoku;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

/**
 * 
 * @author Fabian Wagner & Kai Seegers
 *
 */
public class SodokuFeld extends JPanel {
	
	private Feld feld;
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
	
	protected void setFeld(Feld feld) {
		
		this.feld = feld;
		
		repaint();
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		this.drawField(g);

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
					
					g2d.fillRect(j*100+5, i*100+5, 90, 90);
					
					g2d.setColor(Color.black);
					
					this.drawNumber(i+1, j+1, feld.preFelder[i][j], g2d);
					
				}
				
			}
			
		}
		
	}
	
	private void drawField(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		Color temp = g2d.getColor();
		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, length, length);
		g2d.setColor(temp);
		
//		g2d.setStroke(new BasicStroke(10.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
//		
//		g2d.drawLine(0, 0, length, 0);
//		g2d.drawLine(0, 0, 0, length);
//		g2d.drawLine(length, length, length, 0);
//		g2d.drawLine(length, length, 0, length);
		
		g2d.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		
		g2d.drawLine(length*1/3, 0, length*1/3, length);
		g2d.drawLine(length*2/3, 0, length*2/3, length);
		
		g2d.drawLine(0, length*1/3, length, length*1/3);
		g2d.drawLine(0, length*2/3, length, length*2/3);
		
		g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER));
		
		g2d.drawLine(length*1/9, 0, length*1/9, length);
		g2d.drawLine(length*2/9, 0, length*2/9, length);
		g2d.drawLine(length*4/9, 0, length*4/9, length);
		g2d.drawLine(length*5/9, 0, length*5/9, length);
		g2d.drawLine(length*7/9, 0, length*7/9, length);
		g2d.drawLine(length*8/9, 0, length*8/9, length);
		
		g2d.drawLine(0, length*1/9, length, length*1/9);
		g2d.drawLine(0, length*2/9, length, length*2/9);
		g2d.drawLine(0, length*4/9, length, length*4/9);
		g2d.drawLine(0, length*5/9, length, length*5/9);
		g2d.drawLine(0, length*7/9, length, length*7/9);
		g2d.drawLine(0, length*8/9, length, length*8/9);
		
	}
	
	private void drawNumber (int y, int x, int number, Graphics g) {
		
		int xValue = x*100-72;
		int yValue = y*100-24;
		
		g.drawString(Integer.toString(number), xValue, yValue);
		
	}

}
