package sudoku;

import sudoku_slow.*;

/**
 * 
 * @author Fabian Wagner & Kai Seegers
 *
 */
public class MAIN {

	public static void main(String[] args) {
		
		if (args.length > 0) {
		
			if (args[0].equals("fast")) {

				Feld feld = new Feld("Sudoku", 50);

				feld.spielFeld.repaint();

			} else if (args[0].equals("slow")) {

				Feld_Slow feld = new Feld_Slow("Sudoku", 50);

				feld.spielFeld.repaint();

			}

		} else {
			
			System.out.println("No Arguments found that are usable. Please start again.");
			
		}
		
	}

}
