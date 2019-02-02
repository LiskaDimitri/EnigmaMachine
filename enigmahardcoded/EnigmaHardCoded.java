package enigmahardcoded;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Dim
 */
public class EnigmaHardCoded {

    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String ROTOR_I_STRING = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    public static final String ROTOR_II_STRING = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    public static final String ROTOR_III_STRING = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    public static final String REFLECTOR_STRING = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    public static final String EXIT_PHRASE = "EXIT";

    public static final char[] ALPHABET_ARRAY = ALPHABET.toCharArray();

    public static int InitialRotorIPosition = 1;
    public static int InitialRotorIIPosition = 1;
    public static int InitialRotorIIIPosition = 1;

    public static int currentRotorIPosition = 1;
    public static int currentRotorIIPosition = 1;
    public static int currentRotorIIIPosition = 1;

    private static boolean letterOnTheWayBackFromReflector = false;

    public static DictionaryChain<Character, Character> plugboard;
    public static DictionaryChain<Character, Character> rotorIRight;
    public static DictionaryChain<Character, Character> rotorIIRight;
    public static DictionaryChain<Character, Character> rotorIIIRight;
    public static DictionaryChain<Character, Character> reflector;
    public static DictionaryChain<Character, Character> rotorIIILeft;
    public static DictionaryChain<Character, Character> rotorIILeft;
    public static DictionaryChain<Character, Character> rotorILeft;

    public static Scanner scan;

    public static void main(String[] args) {

//        scan = new Scanner(System.in);
        setRotorsInitialPositions();

        plugboard = plugboardSetup(plugboard);
        rotorIRight = rotorRight(rotorIRight, ROTOR_I_STRING);
        rotorIIRight = rotorRight(rotorIIRight, ROTOR_II_STRING);
        rotorIIIRight = rotorRight(rotorIIIRight, ROTOR_III_STRING);
        reflector = reflector(reflector, REFLECTOR_STRING);
        rotorIIILeft = rotorLeft(rotorIIILeft, ROTOR_III_STRING);
        rotorIILeft = rotorLeft(rotorIILeft, ROTOR_II_STRING);
        rotorILeft = rotorLeft(rotorILeft, ROTOR_I_STRING);

        String inputString = JOptionPane.showInputDialog("Enter string to cypher(no spaces) or \"exit\" to close");
        String inputUpperString = inputString.toUpperCase();

        while (!checkExitPhrase(inputUpperString)) {

            char[] inputCharArray = inputUpperString.toCharArray();

            for (int index = 0; index < inputCharArray.length; index++) {
                //System.out.print(cypherChar(inputCharArray[index]));
                cypherChar(inputCharArray[index]);

                currentRotorIPosition++;
                setRotorsCounters();
                letterOnTheWayBackFromReflector = false;
            }

            System.out.println("\n-------");
            System.out.printf("Position RotorI:%d RotorII:%d RotorIII:%d\n", currentRotorIPosition, currentRotorIIPosition, currentRotorIIIPosition);

            inputString = JOptionPane.showInputDialog("Enter string to cypher(no spaces) or \"exit\" to close");
            inputUpperString = inputString.toUpperCase();
        }

        //        System.out.println(cypherChar('B'));
//        System.out.println(cypherChar('T'));
//   System.out.println(cypherChar('U'));
    }

    public static char cypherChar(char charToCypher) {
        char charGoingThrought;

        //samecode that below working
//        char charCyphered = plugboard.get(rotorIRight.get(rotorIIRight.get(rotorIIIRight.get(reflector.get(rotorIIILeft.get(rotorIILeft.get(rotorILeft.get(
////                plugboard.get(charToCypher)
////        ))))))));
        // Code that works without rotation
//        System.out.print(charToCypher);
//        charGoingThrought = plugboard.get(charToCypher);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorIRight.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorIIRight.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorIIIRight.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = reflector.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorIIILeft.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorIILeft.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorILeft.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = plugboard.get(charGoingThrought);
//        System.out.println(charGoingThrought);

        System.out.print(charToCypher);
        charGoingThrought = plugboard.get(charToCypher);
        System.out.print(charGoingThrought);
        System.out.print("-");
        System.out.print(charGoingThrought);
        charGoingThrought = rotorIRight.get(charGoingThrought);
        charGoingThrought = shiftLetter(charGoingThrought, ROTOR_I_STRING, currentRotorIPosition);
        System.out.print(charGoingThrought);
        System.out.print("-");
        System.out.print(charGoingThrought);
        charGoingThrought = rotorIIRight.get(charGoingThrought);
        charGoingThrought = shiftLetter(charGoingThrought, ROTOR_II_STRING, currentRotorIIPosition);
        //charGoingThrought = rotorIIRight.get(shiftLetter(rotorIRight.get(charGoingThrought), ROTOR_I_STRING, currentRotorIPosition));
        System.out.print(charGoingThrought);
        System.out.print("-");
        System.out.print(charGoingThrought);
        charGoingThrought = rotorIIIRight.get(charGoingThrought);
        charGoingThrought = shiftLetter(charGoingThrought, ROTOR_III_STRING, currentRotorIIIPosition);
        //charGoingThrought = rotorIIIRight.get(shiftLetter(rotorIIRight.get(charGoingThrought), ROTOR_II_STRING, currentRotorIIPosition));
        System.out.print(charGoingThrought);
        System.out.print("-");
        System.out.print(charGoingThrought);
        charGoingThrought = reflector.get(charGoingThrought);
        System.out.print(charGoingThrought);
        System.out.print("-");
        letterOnTheWayBackFromReflector = true;
        System.out.print(charGoingThrought);
        charGoingThrought = rotorIIILeft.get(charGoingThrought);
        charGoingThrought = shiftLetter(charGoingThrought, ROTOR_III_STRING, currentRotorIIIPosition);
        System.out.print(charGoingThrought);
        System.out.print("-");
        System.out.print(charGoingThrought);
        charGoingThrought = rotorIILeft.get(charGoingThrought);
        charGoingThrought = shiftLetter(charGoingThrought, ROTOR_II_STRING, currentRotorIIPosition);
        //charGoingThrought = rotorIIRight.get(shiftLetter(rotorIIIRight.get(charGoingThrought), ROTOR_III_STRING, currentRotorIIIPosition));
        System.out.print(charGoingThrought);
        System.out.print("-");
        System.out.print(charGoingThrought);
        charGoingThrought = rotorILeft.get(charGoingThrought);
        charGoingThrought = shiftLetter(charGoingThrought, ROTOR_I_STRING, currentRotorIPosition);
        //charGoingThrought = rotorIRight.get(shiftLetter(rotorIIRight.get(charGoingThrought), ROTOR_II_STRING, currentRotorIIPosition));
        System.out.print(charGoingThrought);
        System.out.print("-");
        System.out.print(charGoingThrought);
        charGoingThrought = plugboard.get(charGoingThrought);
        System.out.println(charGoingThrought);
        char charCyphered = charGoingThrought;
        return charCyphered;

    }

    public static char shiftLetter(char charToShift, String rotorString, int rotorCurrentPosition) {
        char[] rotorCharArray = rotorString.toCharArray();
        char letterShifted = ' ';
        int indexOfCharInRotor;

        if (!letterOnTheWayBackFromReflector) {
            for (int index = 0; index < rotorCharArray.length; index++) {
                if (charToShift == rotorCharArray[index]) {
                    indexOfCharInRotor = index + (rotorCurrentPosition - 1);
                    if (indexOfCharInRotor > 25) {
                        indexOfCharInRotor -= 26;
                    }
                    return letterShifted = rotorCharArray[indexOfCharInRotor];
                }
            }
        } else {
            for (int index = 0; index < ALPHABET_ARRAY.length; index++) {
                if (charToShift == ALPHABET_ARRAY[index]) {
                    indexOfCharInRotor = index + (rotorCurrentPosition - 1);
                    if (indexOfCharInRotor > 25) {
                        indexOfCharInRotor -= 26;
                    }
//                    for (int index = 25; index > ALPHABET_ARRAY.length; index--) {
//                        if (charToShift == ALPHABET_ARRAY[index]) {
//                            indexOfCharInRotor = index - (rotorCurrentPosition - 1);
//                            if (indexOfCharInRotor < 1) {
//                                indexOfCharInRotor += 26;
//                            }
                            return letterShifted = ALPHABET_ARRAY[indexOfCharInRotor];
                        }
                    }
                }
                return letterShifted;
            }

    

    private static DictionaryChain<Character, Character> plugboardSetup(DictionaryChain<Character, Character> plugboard) {
        plugboard = new DictionaryChain<>();

        plugboard.add('A', 'T');
        plugboard.add('B', 'L');
        plugboard.add('C', 'C');
        plugboard.add('D', 'F');
        plugboard.add('E', 'E');
        plugboard.add('F', 'D');
        plugboard.add('G', 'J');
        plugboard.add('H', 'M');
        plugboard.add('I', 'I');
        plugboard.add('J', 'G');
        plugboard.add('K', 'K');
        plugboard.add('L', 'B');
        plugboard.add('M', 'H');
        plugboard.add('N', 'W');
        plugboard.add('O', 'O');
        plugboard.add('P', 'P');
        plugboard.add('Q', 'Y');
        plugboard.add('R', 'Z');
        plugboard.add('S', 'S');
        plugboard.add('T', 'A');
        plugboard.add('U', 'U');
        plugboard.add('V', 'X');
        plugboard.add('W', 'N');
        plugboard.add('X', 'V');
        plugboard.add('Y', 'Q');
        plugboard.add('Z', 'R');

        return plugboard;
    }

    private static DictionaryChain<Character, Character> rotorRight(DictionaryChain<Character, Character> rotorRight, String rotorString) {
        char[] rotorCharArray = rotorString.toCharArray();
        rotorRight = new DictionaryChain<>();

        for (int index = 0; index < rotorCharArray.length; index++) {
            rotorRight.add(ALPHABET_ARRAY[index], rotorCharArray[index]);
        }
        return rotorRight;
    }

    private static DictionaryChain<Character, Character> rotorLeft(DictionaryChain<Character, Character> rotorLeft, String rotorString) {
        char[] rotorCharArray = rotorString.toCharArray();
        rotorLeft = new DictionaryChain<>();

        for (int index = 0; index < rotorCharArray.length; index++) {
            rotorLeft.add(rotorCharArray[index], ALPHABET_ARRAY[index]);
        }
        return rotorLeft;
    }

    private static DictionaryChain<Character, Character> reflector(DictionaryChain<Character, Character> reflector, String reflectorString) {
        return rotorRight(reflector, reflectorString);
    }

    public static void setRotorsInitialPositions() {
        String inputString = JOptionPane.showInputDialog("Enter position of Rotor I: 1 - 26");
        InitialRotorIPosition = Integer.parseInt(inputString);
        currentRotorIPosition = InitialRotorIPosition;

        inputString = JOptionPane.showInputDialog("Enter position of Rotor II: 1 - 26");
        InitialRotorIIPosition = Integer.parseInt(inputString);
        currentRotorIIPosition = InitialRotorIIPosition;

        inputString = JOptionPane.showInputDialog("Enter position of Rotor III: 1 - 26");
        InitialRotorIIIPosition = Integer.parseInt(inputString);
        currentRotorIIIPosition = InitialRotorIIIPosition;
    }

    public static void setRotorsCounters() {

        if (currentRotorIPosition >= 26) {
            currentRotorIIPosition++;
            currentRotorIPosition = 1;

        }
        if (currentRotorIIPosition >= 26) {
            currentRotorIIIPosition++;
            currentRotorIIPosition = 1;

        }
        if (currentRotorIIIPosition >= 26) {
            currentRotorIPosition++;
            currentRotorIIIPosition = 1;
        }
    }

    public static boolean checkExitPhrase(String inputUpperString) {

        if (inputUpperString.equals(EXIT_PHRASE)) {
            return true;
        }
        return false;
    }

}
