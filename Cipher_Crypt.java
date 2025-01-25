import java.util.Scanner;
class Cipher_Crypt{
    public static void main(String args[]) {
        Scanner obj = new Scanner(System.in);

        // Enter the string which is to be encrypted
        System.out.println("Enter a text which is to be encrypted:");
        String text = obj.nextLine();

        // Enter the key which will be used for encryption of text
        System.out.println("Enter key (String with spaces allowed):");
        String key = obj.nextLine(); 
        // Prepare an array to store the encrypted values
        int[] encryptedArray = new int[text.length()];

        // Call the Encryption method
        Encryption(text, encryptedArray, key);

        // Sort the encrypted values using Bubble Sort
        int[] sortedArray = BubbleSort(encryptedArray);

        // Store the sorted encrypted values as characters in a new array
        char[] charArray = new char[sortedArray.length];
        for (int i = 0; i < sortedArray.length; i++) {
            charArray[i] = (char) sortedArray[i]; // Convert each value to its corresponding character
        }

        // Print the character array
        System.out.println("Encrypted Text:");
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i]);
        }
        System.out.println();

        // Decryption attempts
        String decryptionKey;
        boolean keyCorrect = false;

        while (true) { // Outer loop for re-entering key if the user chooses
            int attempts = 0;

            while (attempts < 3) {
                System.out.println("Enter key for decryption (Attempt " + (attempts + 1) + " of 3):");
                decryptionKey = obj.nextLine();

                // Check if the entered decryption key matches the encryption key
                if (decryptionKey.equals(key)) {
                    keyCorrect = true;
                    break; // Exit the inner loop if the key is correct
                } else {
                    System.out.println("Incorrect key! Please try again.");
                }
                attempts++;
            }

            if (keyCorrect) {
                String decryptedText = Decryption(encryptedArray, key);
                System.out.println("Decrypted Text:");
                System.out.println(decryptedText);
                break; // Exit the outer loop after successful decryption
            } else {
                System.out.println("Incorrect key entered 3 times.");
                System.out.println("Do you want to try again? (yes/no):");
                String choice = obj.nextLine().trim().toLowerCase();
                if (!choice.equals("yes")) {
                    System.out.println("Exiting. You cannot view the original text.");
                    break; // Exit the outer loop if the user does not want to retry
                }
            }
        }

        obj.close();
    }

    // Method for Encryption of Text
    public static void Encryption(String text, int[] encryptedArray, String key) {
        for (int i = 0; i < text.length(); i++) {
            int asciiValue = text.charAt(i); // Get ASCII value of the character
            encryptedArray[i] = asciiValue ^ key.charAt(i % key.length());
        }
    }

    // Method for Decryption of Text
    public static String Decryption(int[] encryptedArray, String key) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < encryptedArray.length; i++) {
            char originalChar = (char) (encryptedArray[i] ^ key.charAt(i % key.length()));
            decryptedText.append(originalChar);
        }

        return decryptedText.toString();
    }

    // Method for Bubble Sort
    public static int[] BubbleSort(int[] array) {
        int n = array.length;
        int[] sortedArray = array.clone();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }
        return sortedArray;
    }
}
