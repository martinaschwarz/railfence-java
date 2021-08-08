package ie.gmit.dip;

import java.io.*;

public class FileHandler {
	private String line;

	public String encryptFile(InputStream in, int key, int offset, RailFenceCypher cipher) {

		/*
		 * Read in all content from the given input source Encrypt line by line by calling the encrypt method from RailFenceCypher class 
		 * Write output to a new file called encrypted.txt 
		 * -> Returns a String to pass into displayRailFence method in Menu class
		 */

		StringBuilder sb = new StringBuilder();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			FileWriter fw = new FileWriter(new File("encrypted.txt"));

			while ((line = br.readLine()) != null) {
				fw.write(cipher.encrypt(line, key, offset) + "\n");
				sb.append(line);
			}
			fw.flush();
			fw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void decryptFile(InputStream in, int key, int offset, RailFenceCypher cipher) {

		/*
		 * Read in all content from the created encrypted file encrypted.txt 
		 * Decrypt line by line by calling the decrypt method from RailFenceCypher class 
		 * Write output to a new file called decrypted.txt
		 */

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			FileWriter fw = new FileWriter(new File("decrypted.txt"));

			while ((line = br.readLine()) != null) {
				fw.write(cipher.decrypt(line, key, offset) + "\n");
			}
			br.close();
			fw.flush();
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
