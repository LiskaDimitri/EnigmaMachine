package enigmamachine;

import java.util.ArrayList;

/**
 *
 * @author Dim
 */
public class PlugBoard extends DictionaryChain<Character, Character> {

    //10 pairs of 2 'char' to choose by the User (To implement if i have time)
    //6 'char' stay themself.
    private int numberOfPairsForPlugboard;
    private int startingRange;
    private int endingRange;

    public PlugBoard(ArrayList<Character> randomCharacterList, char[] characterArray, int numberOfPairsForPlugboard, int startingRange, int endingRange) {
        this.numberOfPairsForPlugboard = numberOfPairsForPlugboard;
        this.startingRange = startingRange;
        this.endingRange = endingRange;

//        for (int characterIndex = 0; characterIndex < numberOfPairsForPlugboard; characterIndex++) {
//            //        this.add(randomCharacterBag[characterIndex], randomCharacterBag[characterIndex+1]);
//            this.add(randomCharacterBag.remove(0), randomCharacterBag.remove(1));
//        }
//        while (randomCharacterBag.size()!= 0) {
//
//            char tempChar = randomCharacterBag.remove(0);
//            this.add(tempChar, tempChar);
//        }
        


        int index = 0;
        while (randomCharacterList.size() != 0) {
                char key = randomCharacterList.get(0).charValue();
                char value = characterArray[index];
                if (key != value) {
                    this.add(key, value);
                    randomCharacterList.remove(0);
                } else {
                    System.out.println(this.toString() + "DOUBLON --- ERRRORORRR " + key + " " + value);
                    int indexNextInList = 0;
                    while (key == value && indexNextInList < randomCharacterList.size()) {
                        indexNextInList++;
                        key = randomCharacterList.get(indexNextInList).charValue();
                    }
                    this.add(key, value);
                    randomCharacterList.remove(indexNextInList);
                    System.out.println(this.toString() + "DOUBLON --- FIIXXXXXXX " + key + " " + value);
                }
            
            index++;
        }
        System.out.println(" size of the plugboard " + this.size());

    }
}
