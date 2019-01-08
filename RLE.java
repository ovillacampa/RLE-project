//Osiris Villacampa RLE code
import java.util.Arrays;
import java.util.Scanner;



public class RLE {
    //Tells the code the amount of characters is the input
    public static int numOfDigits(int num) {
        int numberos = String.valueOf(num).length();
        return numberos;

    }

    //creates ancarray for each digit in the number as well as the character
    public static char[] toCharArray(int charCount, char strchar) {
        int arraySize;
        if (charCount == 1)
        {
            arraySize = 1;
        }
        else
            {
            arraySize = numOfDigits(charCount) + 1;
            }
        char[] chArray = new char[arraySize];
        int counter = 0;
        String part = String.valueOf(charCount);
        while (String.valueOf(charCount).length() > counter) {
            for (int i = 0; i < String.valueOf(charCount).length(); i++) {
                chArray[i] = part.charAt(counter++);
            }
            if (charCount == 1)
            {
                chArray[0] = strchar;
            }
            else
                {
                chArray[counter] = strchar;
                }
        }
        if (charCount == 1)
        {
            chArray[0] = strchar;
        }
        if (charCount == 0)
        {
            chArray = null;
        }
        return chArray;
    }

    //Gives the endcoded length used for the encodeRLE
    public static int findEncodeLength(String inputString) {
        int encodeLength = 1;
        if (inputString == null)
        {
            return 0;
        }
        for (int i = 0; i < inputString.length() - 1; i++) {
            if (inputString.charAt(i) != inputString.charAt(i + 1))
            {
                encodeLength++;
            }
        }

        return encodeLength;
    }

    //Finds the length of the decode for the decodeRLE
    public static int findDecodeLength(String rleString) {
        int decodedLength = 0;
        int tempValue = 0;
        String tempString = "";
        for (int i = 0; i < rleString.length(); i++) {
            if (Character.isDigit(rleString.charAt(i)))
            {
                while (Character.isDigit(rleString.charAt(i)))
                {
                    tempString = tempString + rleString.charAt(i);
                    i++;
                }
                tempValue = Integer.valueOf(tempString);
            }
            else
                {
                decodedLength = decodedLength + 1;
                }
            decodedLength = decodedLength + tempValue;
            tempString = "";
            tempValue = 0;
        }
        return decodedLength;
    }

    //Creates array in order to decode the string
    public static char[] decodeRLE(String rleString) {
        char[] decodedArray = new char[findDecodeLength(rleString)];
        int amountOfCharacters = 1;
        char currentCharacter = '\0';
        int counter = 0;
        String newString = "";
        for (int i = 0; i < rleString.length(); i++) {
            if (Character.isDigit(rleString.charAt(i)))
            {
                while (Character.isDigit(rleString.charAt(i)))
                {
                    newString = newString + rleString.charAt(i);
                    i++;
                }
                amountOfCharacters = Integer.valueOf(newString);
                currentCharacter = rleString.charAt(i);
            }
            else
                {
                currentCharacter = rleString.charAt(i);
                }
            for (int j = 0; j < amountOfCharacters; j++) {
                decodedArray[counter] = currentCharacter;
                counter++;
            }
            newString = "";
            amountOfCharacters = 1;
        }

        return decodedArray;
    }
    // Made a multidimensional array for how to encode said string
    public static char[][] encodeRLE(String inputString) {

        char[][] encodedArray = new char[findEncodeLength(inputString)][];
        char currentCharacter = inputString.charAt(0);
        int amountOfCharacters = 0;
        int counter = 0;
        for (int i = 0; i < inputString.length(); i++) {
            currentCharacter = inputString.charAt(i);
            if (currentCharacter == inputString.charAt(i))
            {
                while (i < inputString.length() && currentCharacter == inputString.charAt(i))
                {
                    amountOfCharacters++;
                    i++;
                }
                i--;
            }
            else
                {
                amountOfCharacters = 1;
                }
            encodedArray[counter] = toCharArray(amountOfCharacters, currentCharacter);
            counter++;
            amountOfCharacters = 0;
        }


        return encodedArray;

    }
            public static void main (String[]args){
                Scanner inputUser = new Scanner(System.in);
                boolean Stop = false;
                String userString = "";
                while (Stop != true) {
                    // Display menu
                    System.out.println("What would you like to do?");
                    System.out.println("1.Input string to be encoded");
                    System.out.println("2.View encoded string");
                    System.out.println("3.View decoded string");
                    System.out.println("4.Exit program");
                    System.out.println("Option:");
                    int selection;
                     if (inputUser.hasNextInt())
                        {
                        selection = inputUser.nextInt();
                        }
                   else
                   {
                       selection = 0;
                       inputUser.next();
                   }
                    switch (selection) {                        // Using a switch statement to easily make the menu options
                        case 1:
                            System.out.println("Enter message: ");
                            userString = inputUser.nextLine();
                            while (userString.length() < 1) {
                                userString = inputUser.nextLine();
                            }
                            System.out.println("");
                            break;
                        case 2:
                                System.out.println("");
                                String encodedString = Arrays.deepToString(encodeRLE(userString))
                                        .replace(",", "")
                                        .replace("[", "")
                                        .replace("]", "")
                                        .replace(" ", "");
                                System.out.println("The encoded data is: " + encodedString);
                                System.out.println("");
                                break;
                        case 3:
                                System.out.println("");
                                String decodedString = Arrays.toString(decodeRLE(userString))
                                        .replace(",", "")
                                        .replace("[", "")
                                        .replace("]", "")
                                        .replace(" ", "");
                                System.out.println("The decoded data is: " + decodedString);
                                System.out.println("");
                            break;
                        case 4:
                            Stop = true;
                            System.out.println("");
                            System.out.println("Program terminated");
                            break;
                        default:
                            System.out.println("Error! Invalid input. Please enter an integer from 1-4");
                            break;
                    }
                    System.out.println("");
                    if (Stop == false)
                    {
                        // selection = menu(inputUser);
                    }
                }

            }
        }

//https://stackoverflow.com/questions/4389480/print-array-without-brackets-and-commas/4389690 -- how to replace the array
//https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html -- how to convert array to string
//https://stackoverflow.com/questions/12832006/scanner-error-with-nextint -- in order to get the error test outputs
//http://www.baeldung.com/java-number-of-digits-in-int -- to figure out the numofdigits
//https://stackoverflow.com/questions/1794281/java-infinite-loop-using-scanner-in-hasnextint -- To end the infinite loop that was being caused by my scanner