package enigmamachine;

import java.util.ArrayList;

/**
 *
 * @author Dim
 */
public class Rotor extends DictionaryChain<Character, Character> {

    // 3 rotors start with a specific possition ( 1-26)
    //each time the smallest rotor does a complete circle the next one is incremented.
    //TO - DO make sure a Char is not itself.
    //TO- DO if time. Would Be best to use a link Chain array 
    private int initialPosition;
    private int startingRange;
    private int endingRange;
    private int unicodeRange;

    public Rotor(ArrayList<Character> randomCharacterList, char[] characterArray, int initialPosition, int startingRange, int endingRange, int unicodeRange) {
        this.initialPosition = initialPosition;
        this.startingRange = startingRange;
        this.endingRange = endingRange;
        this.unicodeRange = unicodeRange;

//        for (int index = 0; index < unicodeRange; index++){
//            this.add(randomCharacterList.remove(), characterBucket[index]);
//        }
//        for (int characterIndex = 0; characterIndex < unicodeRange; characterIndex++) {
//            //        this.add(randomCharacterList[characterIndex], randomCharacterList[characterIndex+1]);
//            this.add(randomCharacterList.remove(), randomCharacterList.remove());
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
        System.out.println(" size of the rotor " + this.size());
    }
}
