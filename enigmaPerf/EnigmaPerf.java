/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigmaPerf;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author Dim
 * @author Eric charnesky for the shiftChar method
 */
public class EnigmaPerf {

    public static final String EXIT_PHRASE = "EXIT";
    public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final char[] ALPHABET_ARRAY = ALPHABET.toCharArray();

    public static DictionaryChain<Character, Character> plugboard;
    public static DictionaryChain<Character, Character> rotorI;
    public static DictionaryChain<Character, Character> rotorII;
    public static DictionaryChain<Character, Character> rotorIII;
    public static DictionaryChain<Character, Character> reflector;
    
    public static ArrayList<Character> plugboardInput;
    public static ArrayList<Character> plugboardOutput;

    public static int InitialRotorIPosition = 1;
    public static int InitialRotorIIPosition = 1;
    public static int InitialRotorIIIPosition = 1;

    public static int currentRotorIPosition = 1;
    public static int currentRotorIIPosition = 1;
    public static int currentRotorIIIPosition = 1;

    public static long startTime;
    public static long stopTime;
    public static double previousCypherTime;
    public static double nanoTime = 1e9;
    public static double milliTime = 1e6;

    public static void main(String[] args) {

        startTime();
        //plugboard = plugboardSetup(plugboard);
        plugboardSetupArray();
        
        stopTime();
        double elapsedTimePlugboard = elapsedTime();

        startTime();
        rotorI = rotorISetup(rotorI);
        stopTime();
        double elapsedTimerotorI = elapsedTime();

        startTime();
        rotorII = rotorIISetup(rotorII);
        stopTime();
        double elapsedTimerotorII = elapsedTime();

        startTime();
        rotorIII = rotorIIISetup(rotorIII);
        stopTime();
        double elapsedTimerotorIII = elapsedTime();

        startTime();
        reflector = reflectorSetup(reflector);
        stopTime();
        double elapsedTimeReflector = elapsedTime();

        startTime();
        setRotorsInitialPositions();
        stopTime();
        double elapsedTimeRotorPosition = elapsedTime();

        String inputString = JOptionPane.showInputDialog("Enter string to cypher(no spaces) or \"exit\" to close");
        String inputUpperString = inputString.toUpperCase();

        System.out.printf("Initial Position RotorIII:%d RotorII:%d RotorI:%d\n", currentRotorIIIPosition, currentRotorIIPosition, currentRotorIPosition);

        
        while (!checkExitPhrase(inputUpperString)) {

            char[] inputCharArray = inputUpperString.toCharArray();

            startTime();
            for (int index = 0; index < inputCharArray.length; index++) {
                System.out.print(cypherChar(inputCharArray[index]));
                //cypherChar(inputCharArray[index]);

                ShiftRotorsCheck();
            }
            stopTime();
            double elapsedTimeCypherCharString = elapsedTime();

            
            JTextArea textArea = new JTextArea(String.format(
                    "All times are in milliseconds\n"
                    + " -----Initial Values (Static)-----\n"
                    + "Creation & setups of pairs for plugboard: %,f ms\n"
                    + "Creation & setups of pairs for RotorI: %,f ms\n"
                    + "Creation & setups of pairs for RotorII: %,f ms\n"
                    + "Creation & setups of pairs for RotorIII: %,f ms\n"
                    + "Creation & setups of pairs for Reflector: %,f ms \n"
                    + "Positionning(Rotating Sequence) rotors with user input: %,f ms\n"
                    + "\n------Coutinuous Values (Dynamic)-----\n"
                    + "Current time to Cypher the entire String Input: %,f ms\n"
                    + "Previous Cypher Time: %,f ms"
                            ,elapsedTimePlugboard,elapsedTimerotorI,elapsedTimerotorII,
                            elapsedTimerotorIII,elapsedTimeReflector,elapsedTimeRotorPosition,
                            elapsedTimeCypherCharString,previousCypherTime
            ));
            JOptionPane.showMessageDialog(null, textArea);
            
            previousCypherTime = elapsedTimeCypherCharString;

            
//            cypherHasPrevious = true;

            System.out.println("");
            System.out.printf("Position RotorIII:%d RotorII:%d RotorI:%d\n", currentRotorIIIPosition, currentRotorIIPosition, currentRotorIPosition);

            inputString = JOptionPane.showInputDialog("Enter string to cypher(no spaces) or \"exit\" to close");
            inputUpperString = inputString.toUpperCase();
        }
    }

    public static char cypherChar(char charToCypher) {

//        char charCyphered = plugboard.get(rotorI.get(rotorII.get(rotorIII.get(reflector.get(rotorIII.get(rotorII.get(rotorI.get(
//                plugboard.get(charToCypher)
//        ))))))));

          char charCyphered = plugboard.get(rotorI.get(rotorII.get(rotorIII.get(reflector.get(rotorIII.get(rotorII.get(rotorI.get(
                plugboard.get(charToCypher)
          ))))))));

        // Code with path Debuging
//        char charGoingThrought;
//        System.out.print(charToCypher);
//        charGoingThrought = plugboard.get(charToCypher);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorI.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorII.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorIII.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = reflector.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorIII.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorII.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = rotorI.get(charGoingThrought);
//        System.out.print(charGoingThrought);
//        System.out.print("-");
//        System.out.print(charGoingThrought);
//        charGoingThrought = plugboard.get(charGoingThrought);
//        System.out.println(charGoingThrought);
//        char charCyphered = charGoingThrought;
        return charCyphered;
    }

    public static DictionaryChain<Character, Character> shiftRotor(DictionaryChain<Character, Character> rotorToShift) {
        DictionaryChain<Character, Character> shiftingRotor = new DictionaryChain<>();
        char currentKey;
//        int currentKeyUnicode;
        char newKey;
        char currentValue;
//        int currentValueUnicode;
        char newValue;

        for (int index = 0; index < ALPHABET_ARRAY.length; index++) {
            currentKey = ALPHABET_ARRAY[index];
//                currentKeyUnicode = Character.getNumericValue(currentKey);
            newKey = shiftChar(currentKey, 1);

            currentValue = rotorToShift.get(ALPHABET_ARRAY[index]);
//                currentValueUnicode = Character.getNumericValue(currentValue);
            newValue = shiftChar(currentValue, 1);

            shiftingRotor.add(newKey, newValue);
        }

        return shiftingRotor;
    }

    /**
     * ShiftChar was written in class by Prof.Eric Charnesky
     */
    public static char shiftChar(char c, int shift) {
        int value = Character.getNumericValue(c) + 55 + shift;
        if (value > 90) {
            value -= 26;
        }
        return Character.toChars(value)[0];
    }

    public static void ShiftRotorsCheck() {

        currentRotorIPosition++;
        rotorI = shiftRotor(rotorI);

        if (currentRotorIPosition >= 26) {
            currentRotorIIPosition++;
            rotorII = shiftRotor(rotorII);
            currentRotorIPosition = 1;

        }
        if (currentRotorIIPosition >= 26) {
            currentRotorIIIPosition++;
            rotorIII = shiftRotor(rotorIII);
            currentRotorIIPosition = 1;

        }
        if (currentRotorIIIPosition >= 26) {
            currentRotorIPosition++;
            rotorI = shiftRotor(rotorI);
            currentRotorIIIPosition = 1;
        }
    }

    public static void setRotorsInitialPositions() {
        String inputString = JOptionPane.showInputDialog("Enter position of Rotor I: 1 - 26");
        InitialRotorIPosition = Integer.parseInt(inputString);
        for (int rotation = 1; rotation < InitialRotorIPosition; rotation++) {
            rotorI = shiftRotor(rotorI);
            currentRotorIPosition++;
        }

        inputString = JOptionPane.showInputDialog("Enter position of Rotor II: 1 - 26");
        InitialRotorIIPosition = Integer.parseInt(inputString);
        for (int rotation = 1; rotation < InitialRotorIIPosition; rotation++) {
            rotorI = shiftRotor(rotorI);
            currentRotorIIPosition++;
        }

        inputString = JOptionPane.showInputDialog("Enter position of Rotor III: 1 - 26");
        InitialRotorIIIPosition = Integer.parseInt(inputString);
        for (int rotation = 1; rotation < InitialRotorIIIPosition; rotation++) {
            rotorI = shiftRotor(rotorI);
            currentRotorIIIPosition++;
        }
    }

    public static boolean checkExitPhrase(String inputUpperString) {

        if (inputUpperString.equals(EXIT_PHRASE)) {
            return true;
        }
        return false;
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
    private static void plugboardSetupArray() {
        plugboardInput = new ArrayList<>();
        plugboardOutput = new ArrayList<>();
        
        for(int i = 0; i < ALPHABET_ARRAY.length; i++){
            plugboardInput.add(ALPHABET_ARRAY[i]);
        }
        plugboardOutput.add('F');
        plugboardOutput.add('D');
        plugboardOutput.add('N');
        plugboardOutput.add('B');
        plugboardOutput.add('J');
        plugboardOutput.add('A');
        plugboardOutput.add('W');
        plugboardOutput.add('K');
        plugboardOutput.add('Q');
        plugboardOutput.add('E');
        plugboardOutput.add('H');
        plugboardOutput.add('Y');
        plugboardOutput.add('S');
        plugboardOutput.add('C');
        plugboardOutput.add('V');
        plugboardOutput.add('Z');
        plugboardOutput.add('I');
        plugboardOutput.add('T');
        plugboardOutput.add('M');
        plugboardOutput.add('R');
        plugboardOutput.add('X');
        plugboardOutput.add('O');
        plugboardOutput.add('G');
        plugboardOutput.add('U');
        plugboardOutput.add('L');
        plugboardOutput.add('P');
        
        
        
    }

    private static DictionaryChain<Character, Character> rotorISetup(DictionaryChain<Character, Character> rotorI) {
        rotorI = new DictionaryChain<>();
        rotorI.add('A', 'F');
        rotorI.add('B', 'D');
        rotorI.add('C', 'N');
        rotorI.add('D', 'B');
        rotorI.add('E', 'J');
        rotorI.add('F', 'A');
        rotorI.add('G', 'W');
        rotorI.add('H', 'K');
        rotorI.add('I', 'Q');
        rotorI.add('J', 'E');
        rotorI.add('K', 'H');
        rotorI.add('L', 'Y');
        rotorI.add('M', 'S');
        rotorI.add('N', 'C');
        rotorI.add('O', 'V');
        rotorI.add('P', 'Z');
        rotorI.add('Q', 'I');
        rotorI.add('R', 'T');
        rotorI.add('S', 'M');
        rotorI.add('T', 'R');
        rotorI.add('U', 'X');
        rotorI.add('V', 'O');
        rotorI.add('W', 'G');
        rotorI.add('X', 'U');
        rotorI.add('Y', 'L');
        rotorI.add('Z', 'P');
        return rotorI;
    }

    private static DictionaryChain<Character, Character> rotorIISetup(DictionaryChain<Character, Character> rotorII) {
        rotorII = new DictionaryChain<>();
        rotorII.add('A', 'R');
        rotorII.add('B', 'I');
        rotorII.add('C', 'S');
        rotorII.add('D', 'P');
        rotorII.add('E', 'O');
        rotorII.add('F', 'H');
        rotorII.add('G', 'N');
        rotorII.add('H', 'F');
        rotorII.add('I', 'B');
        rotorII.add('J', 'X');
        rotorII.add('K', 'U');
        rotorII.add('L', 'W');
        rotorII.add('M', 'M');
        rotorII.add('N', 'G');
        rotorII.add('O', 'E');
        rotorII.add('P', 'D');
        rotorII.add('Q', 'Y');
        rotorII.add('R', 'A');
        rotorII.add('S', 'C');
        rotorII.add('T', 'Z');
        rotorII.add('U', 'K');
        rotorII.add('V', 'V');
        rotorII.add('W', 'L');
        rotorII.add('X', 'J');
        rotorII.add('Y', 'Q');
        rotorII.add('Z', 'T');
        return rotorII;
    }

    private static DictionaryChain<Character, Character> rotorIIISetup(DictionaryChain<Character, Character> rotorIII) {
        rotorIII = new DictionaryChain<>();
        rotorIII.add('A', 'S');
        rotorIII.add('B', 'V');
        rotorIII.add('C', 'Z');
        rotorIII.add('D', 'Y');
        rotorIII.add('E', 'P');
        rotorIII.add('F', 'F');
        rotorIII.add('G', 'U');
        rotorIII.add('H', 'W');
        rotorIII.add('I', 'M');
        rotorIII.add('J', 'Q');
        rotorIII.add('K', 'N');
        rotorIII.add('L', 'T');
        rotorIII.add('M', 'I');
        rotorIII.add('N', 'K');
        rotorIII.add('O', 'O');
        rotorIII.add('P', 'E');
        rotorIII.add('Q', 'J');
        rotorIII.add('R', 'R');
        rotorIII.add('S', 'A');
        rotorIII.add('T', 'L');
        rotorIII.add('U', 'G');
        rotorIII.add('V', 'B');
        rotorIII.add('W', 'H');
        rotorIII.add('X', 'X');
        rotorIII.add('Y', 'D');
        rotorIII.add('Z', 'C');
        return rotorIII;
    }

    private static DictionaryChain<Character, Character> reflectorSetup(DictionaryChain<Character, Character> reflector) {
        reflector = new DictionaryChain<>();
        reflector.add('A', 'J');
        reflector.add('B', 'S');
        reflector.add('C', 'W');
        reflector.add('D', 'O');
        reflector.add('E', 'U');
        reflector.add('F', 'X');
        reflector.add('G', 'N');
        reflector.add('H', 'T');
        reflector.add('I', 'Y');
        reflector.add('J', 'A');
        reflector.add('K', 'P');
        reflector.add('L', 'Z');
        reflector.add('M', 'Q');
        reflector.add('N', 'G');
        reflector.add('O', 'D');
        reflector.add('P', 'K');
        reflector.add('Q', 'M');
        reflector.add('R', 'V');
        reflector.add('S', 'B');
        reflector.add('T', 'H');
        reflector.add('U', 'E');
        reflector.add('V', 'R');
        reflector.add('W', 'C');
        reflector.add('X', 'F');
        reflector.add('Y', 'I');
        reflector.add('Z', 'L');
        return reflector;
    }

    public static void startTime() {
        startTime = System.nanoTime();
    }

    public static void stopTime() {
        stopTime = System.nanoTime();
    }

    public static double elapsedTime() {
        double elapsedTime = ((stopTime - startTime) / milliTime);
        return elapsedTime;
    }
}
