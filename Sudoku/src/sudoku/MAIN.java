package sudoku;

/**
 * 
 * @author Fabian Wagner & Kai Seegers
 *
 */
public class MAIN {

	public static void main(String[] args) {
		
		if (args.length > 0) {
		
			if (args.length > 1) {
				
				int length = 0;
				
				try {
					
					length = Integer.parseInt(args[0]);
					
				} catch (Exception e) {
					
					e.printStackTrace();
					System.out.println("First statement is not a number. Try again.");
					return;
					
				}

				if (args[1].equals("fast")) {

					Feld feld = new Feld("Sudoku", length, true);

					feld.spielFeld.repaint();

				} else if (args[1].equals("slow")) {

					Feld feld = new Feld("Sudoku", length, false);

					feld.spielFeld.repaint();

				} else {
					
					System.out.println("Second Statement not working. Has to bei either \"slow\" or \"fast\".");
					
				}

			} else {

				if (args[0].equals("fast")) {

					Feld feld = new Feld("Sudoku", 50, true);

					feld.spielFeld.repaint();

				} else if (args[0].equals("slow")) {

					Feld feld = new Feld("Sudoku", 50, false);

					feld.spielFeld.repaint();

				} else {
					
					//manual
					System.out.println("This program needs at least one argument.");
					System.out.println("The first argument has to be either a number that specifies the difficulty of the Sudoku, or a String:");
					System.out.println("A higher number generates a Sudoku with more holes.");
					System.out.println("The String can either be \"slow\" or \"fast\".");
					System.out.println("If the first argument is a number, there HAS to be a second argument, which is the String specified in an upper line.");
					
				}

			}
		
		} else {
			
			System.out.println("No Arguments found that are usable. Please start again.");
			//manual
			System.out.println("This program needs at least one argument.");
			System.out.println("The first argument has to be either a number that specifies the difficulty of the Sudoku, or a String:");
			System.out.println("A higher number generates a Sudoku with more holes.");
			System.out.println("The String can either be \"slow\" or \"fast\".");
			System.out.println("If the first argument is a number, there HAS to be a second argument, which is the String specified in an upper line.");
			
		}
		
	}

}
