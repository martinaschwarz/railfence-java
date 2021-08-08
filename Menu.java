package ie.gmit.dip;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Menu {
	private RailFenceCypher cipher = new RailFenceCypher();
	private FileHandler fh = new FileHandler();
	private Scanner s;
	private boolean keepRunning = true;
	
	private int key;
	private int offset;
	private String inputSource;
	private String cipherText;

	public Menu() {

		// Initialize scanner
		s = new Scanner(System.in);
	}

	public void go() throws Exception {

		// Display menu options
		printMenu();

		// Jump to below methods depending on option selected and keep the menu running until Option 6 (Quit) is selected
		while (keepRunning) {
			int choice = Integer.parseInt(s.next());

			if (choice == 1) {
				selectFile();
			} else if (choice == 2) {
				enterKey();
			} else if (choice == 3) {
				encrypt();
			} else if (choice == 4) {
				decrypt();
			} else if (choice == 5) {
				displayRailFence();
			} else if (choice == 6) {
				System.out.println("[INFO] Quitting....");
				keepRunning = false;
			} else {
				System.out.println("[ERROR] Invalid Selection!");
			}
		}
	}

	private void printMenu() {

		// Menu options
		System.out.println("1. Select File or URL");
		System.out.println("2. Enter Rail Fence Key");
		System.out.println("3. Encrypt");
		System.out.println("4. Decrypt");
		System.out.println("5. Display Rail Fence");
		System.out.println("6. Quit");
		System.out.println("");
		System.out.println("Select Option [1 - 6] >");
	}

	private void selectFile() {

		// Pass in an input source
		System.out.println("Select File or enter URL >");
		inputSource = s.next();
	}

	private void enterKey() throws Exception {

		// Pass in encryption key
		try {
			System.out.println("Enter Rail Fence Key >");
			key = Integer.parseInt(s.next());

			// Pass in encryption offset, make sure it's smaller than the key
			System.out.println("Enter Offset >");
			offset = Integer.parseInt(s.next());

			if (offset >= key) {
				System.out.println("[ERROR] Offset has to be smaller than key. Enter Offset >");
				offset = Integer.parseInt(s.next());
			}
		} catch (NumberFormatException e) {
			System.out.println("[ERROR] Only numbers are accepted for key and offset!");
			enterKey();
		}
	}

	private void encrypt() throws Exception {

		/*
		 * Check whether input source is URL or not (in that case assume it's a file)
		 * Using the appropriate input stream, read all content and and call encryptFile method from FileHandler class 
		 * Handle File Not Found or IO Exceptions
		 */

		// Make sure a valid input source was passed in, if not call selectFile method again
		if (inputSource == null) {
			System.out.println("[ERROR] No file or URL selected.");
			selectFile();
		}
		
		// Make sure a valid key and offset were passed in, if not call enterKey method again
		if (key <= 1 || offset < 0) {
			System.out.println("[ERROR] Invalid key or offset entered.");
			enterKey();
		}
		
		try {
			if (inputSource.startsWith("http")) {
				URL url = new URL(inputSource);
				InputStream in = url.openStream();
				fh.encryptFile(in, key, offset, cipher);
			} else {
				cipherText = fh.encryptFile(new FileInputStream(new File(inputSource)), key, offset, cipher);
			}
			System.out.println("[INFO] Encryption complete.");
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR] File not found, please try again.");
			selectFile();
		} catch (IOException e) {
			System.out.println("[ERROR] Something went wrong. Please use a different input source.");
			selectFile();
		}
	}

	private void decrypt() {

		// Read all encrypted content from the text file created by encryption method and call decryptFile method from FileHandler class
		try {
			fh.decryptFile(new FileInputStream(new File("./encrypted.txt")), key, offset, cipher);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("[INFO] Decryption complete.");
	}

	private void displayRailFence() {

		// Displays last created Rail Fence matrix, ie. using the last line of the encrypted input source
		System.out.println("Rail Fence:");
		cipher.displayRailFence(cipherText, key);
	}
}
