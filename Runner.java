package ie.gmit.dip;

public class Runner {

	public static void main(String[] args) {

		// Initialize menu to start the program
		try {
			new Menu().go();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
