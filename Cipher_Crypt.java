import java.util.Scanner;

class Cipher_Crypt {
    public static void main(String args[]) {
        Scanner obj = new Scanner(System.in);

        // Enter the string which is to be encrypted
        System.out.println("Enter a text:");
        String text = obj.nextLine();

        // Enter the key which will be used for encryption of text
        System.out.println("Enter key (String with spaces allowed):");
        String key = obj.nextLine(); // Accepting a string key

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
        System.out.println(" Encrypted Text");
        for (int i = 0; i < charArray.length; i++) {
            System.out.print(charArray[i] );
        }
        System.out.println();

        // Prompt for the key again for decryption with attempts
        String decryptionKey;
        int attempts = 0;
        boolean keyCorrect = false;

        while (attempts < 3) {
            System.out.println("Enter key for decryption (String) (Attempt " + (attempts + 1) + " of 3):");
            decryptionKey = obj.nextLine(); // Accepting a string for decryption

            // Check if the entered decryption key matches the encryption key
            if (decryptionKey.equals(key)) {
                keyCorrect = true;
                break;  // Exit the loop if the key is correct
            } else {
                System.out.println("Incorrect key! Please try again.");
            }
            attempts++;
        }

        // If the key is correct, decrypt the text; otherwise, show a failure message
        if (keyCorrect) {
            String decryptedText = Decryption(encryptedArray, key);
            System.out.println("Decrypted Text:");
            System.out.println(decryptedText);
        } else {
            System.out.println("Incorrect key entered 3 times. You cannot view the original text.");
        }

        obj.close();
    }

    // Method for Encryption of Text
    public static void Encryption(String text, int[] encryptedArray, String key) {
        // Step 1: Conversion and XOR Encryption
        for (int i = 0; i < text.length(); i++) {
            int asciiValue = text.charAt(i); // Get ASCII value of the character
            // Apply XOR encryption using the corresponding character in the key
            // Use modulo to loop through the key if it's shorter than the text
            encryptedArray[i] = asciiValue ^ key.charAt(i % key.length());
        }
        System.out.println(); // For better formatting
    }

    // Method for Decryption of Text
    public static String Decryption(int[] encryptedArray, String key) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < encryptedArray.length; i++) {
            // Reverse the XOR operation to retrieve the original character
            char originalChar = (char) (encryptedArray[i] ^ key.charAt(i % key.length()));
            decryptedText.append(originalChar);
        }

        return decryptedText.toString();
    }

    // Method for Bubble Sort
    public static int[] BubbleSort(int[] array) {
        int n = array.length;
        int[] sortedArray = array.clone(); // Clone the original array to keep it intact

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    // Swap adjacent elements if they are in the wrong order
                    int temp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = temp;
                }
            }
        }
        return sortedArray;
    }
}
