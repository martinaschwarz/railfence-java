package ie.gmit.dip;

public class RailFenceCypher {
	private char[][] matrix;

	public RailFenceCypher() {

	}

	public String encrypt(String plainText, int key, int offset) {

		// Call the doCypher method and return encrypted string
		return doCypher(plainText, key, offset, true);
	}

	public String decrypt(String cipherText, int key, int offset) {

		// Call the doCypher method and return decrypted string
		return doCypher(cipherText, key, offset, false);
	}

	private String doCypher(String s, int key, int offset, boolean encrypt) {

		// Initialize matrix
		matrix = new char[key][s.length()];

		StringBuilder sb = new StringBuilder();
		boolean moveDown = true;
		
		// Initialize offset with -1 to counter the row starting point at row 0
		int row = offset -1;
		int c = 0;

		/*
		 * Create a matrix as wide as the length of the string, and as long as the number of the key 
		 * Loop through the length of the string and populate each column with the next character, moving up or down a row each time 
		 * Boolean check in place to decide whether row moves up or down
		 */

		for (int col = 0; col < s.length(); col++) {
			matrix[row][col] = s.charAt(col);

			if (moveDown)
				row++;
			else
				row--;

			if (row == 0 || row == key - 1) {
				moveDown = !moveDown;
			}
		}

		if (encrypt == true) {

			// Loop through the created matrix line by line, appending the characters to a new string
			System.out.println();
			for (int i = 0; i < key; i++) {
				for (int j = 0; j < s.length(); j++) {
					if (matrix[i][j] != 0) {
						sb.append(matrix[i][j]);
					}
				}
			}
		} else {

			// Place each character of the encrypted string back into the matrix, line by line
			for (int i = 0; i < key; i++) {
				for (int j = 0; j < s.length(); j++) {
					if (matrix[i][j] != 0) {
						matrix[i][j] = s.charAt(c);
						c++;
					}
				}
			}

			// Loop through the rail fence matrix, going through each row in each column, and append characters to a new string
			for (int i = 0; i < s.length(); i++) {
				for (int j = 0; j < key; j++) {
					if (matrix[j][i] != 0) {
						sb.append(matrix[j][i]);
					}
				}
			}
		}
		return sb.toString();
	}

	public void displayRailFence(String s, int key) {

		// Loop through each column in each row of the matrix and print the character if present, else print an empty space, to display the Rail Fence pattern
		for (int i = 0; i < key; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] != 0) {
					System.out.print(matrix[i][j]);
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
