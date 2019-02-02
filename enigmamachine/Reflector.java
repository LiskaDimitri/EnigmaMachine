package enigmamachine;

import java.util.ArrayList;

/**
 *
 * @author Dim
 */
public class Reflector extends DictionaryChain<Character, Character> {
    
    private int initialPosition;
    private int startingRange;
    private int endingRange;
    private int unicodeRange;

    public Reflector(ArrayList<Character> randomCharacterList,char[] characterArray, int startingRange, int endingRange, int unicodeRange) {
        this.initialPosition = initialPosition;
        this.startingRange = startingRange;
        this.endingRange = endingRange;
        this.unicodeRange = unicodeRange;
        
//        int index = 0;
//        while (randomCharacterBag.getCurrentSize() != 0) {
//            char key = randomCharacterBag.remove();
//            char value = characterArray[index];
//            if (key != value) {
//                this.add(key, value);
//            } else {
//                System.out.println("DOUBLON --- ERRRORORRR " + key + " "+ value);
//            }
//            index++;
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
        System.out.println(" size of the reflector " + this.size());
    }
}
