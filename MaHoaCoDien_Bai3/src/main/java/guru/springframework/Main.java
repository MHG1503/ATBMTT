package guru.springframework;

import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
	public static void main(String[] args) {
		String result = "";
		String plaintext = "";
		String line;
		String key = "";
		try(
			BufferedReader br = new BufferedReader(new FileReader("input.txt"))){
			while((line = br.readLine()) != null){
				String[] tempLine = line.split(" ");
				plaintext = tempLine[0];
				key = tempLine[1];
			}
			result = encryptVigenereAutokey(plaintext, key);
			System.out.println(result);

		}catch (Exception e){
			e.printStackTrace();
		}

		try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))){
			bw.write("Mã hóa: ");
			bw.write(result);
			bw.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public static String encryptVigenereAutokey(String plaintext, String key) {
		StringBuilder ciphertext = new StringBuilder();
		StringBuilder tempKey = new StringBuilder(key);

		String tempK = tempKey.append(plaintext.substring(0, plaintext.length() - key.length())).toString();

		for (int i = 0; i < plaintext.length(); i++) {
			char currentCharPlainText = plaintext.charAt(i);
			int indexCurrentChar = (int) currentCharPlainText - 'A';

			char currentCharKey = tempK.charAt(i);
			int indexCurrentCharKey = (int) currentCharKey - 'A';

			char encryptedChar = (char) ((currentCharKey + currentCharPlainText) % 26 + 'A');
			ciphertext.append(encryptedChar);
		}
		return ciphertext.toString();
	}
}