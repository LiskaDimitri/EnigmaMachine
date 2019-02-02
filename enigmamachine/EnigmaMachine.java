package enigmamachine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Dim
 */
public class EnigmaMachine {
    // public static DictionaryChain<Character, Character> plugBoard;
//    public static HashMap<Integer, Integer> hashmap;
    public static ArrayList<Character> arrayTest = new ArrayList<>();

    public static PlugBoard plugboard;
    public static Rotor rotorI;
    public static Rotor rotorII;
    public static Rotor rotorIII;
    public static Reflector reflector;

    public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    public static char[] characterBucket;
    public static ArrayList<Character> randomCharacterList;

    public static final int NUMBER_OF_PLUGBOARD_PAIRS = 10;
    public static final int STARTING_UNICODE_RANGE = 65; // Unicode for A = 65
    public static final int ENDING_UNICODE_RANGE = 90; // Unicode for Z = 90
    public static final int UNICODE_RANGE = ((ENDING_UNICODE_RANGE + 1) - STARTING_UNICODE_RANGE);

    public static final int RANDOM_SEED_PLUGBOARD = 1;
    public static final int RANDOM_SEED_ROTOR_I = 10000;
    public static final int RANDOM_SEED_ROTOR_II = 200;
    public static final int RANDOM_SEED_ROTOR_III = 300;
    public static final int RANDOM_SEED_REFLECTOR = 1000;

    public static int InitialPositionRotorI = 1;
    public static int InitialPositionRotorII = 1;
    public static int InitialPositionRotorIII = 1;

    public static void main(String[] args) {

        unicodeBucketMaker();

        characterBucketMaker();

//        randomCharListMaker(RANDOM_SEED_PLUGBOARD, unicodeBucket);
//        System.out.println("gdiahai");
        plugboard = new PlugBoard(randomCharListMaker(RANDOM_SEED_PLUGBOARD, unicodeBucketMaker()), characterBucket, NUMBER_OF_PLUGBOARD_PAIRS,
                STARTING_UNICODE_RANGE, ENDING_UNICODE_RANGE);

        rotorI = new Rotor(randomCharListMaker(RANDOM_SEED_ROTOR_I, unicodeBucketMaker()),
                characterBucket, InitialPositionRotorI, STARTING_UNICODE_RANGE,
                ENDING_UNICODE_RANGE, UNICODE_RANGE);

        rotorII = new Rotor(randomCharListMaker(RANDOM_SEED_ROTOR_II, unicodeBucketMaker()),
                characterBucket, InitialPositionRotorII, STARTING_UNICODE_RANGE,
                ENDING_UNICODE_RANGE, UNICODE_RANGE);

        rotorIII = new Rotor(randomCharListMaker(RANDOM_SEED_ROTOR_III, unicodeBucketMaker()),
                characterBucket, InitialPositionRotorIII, STARTING_UNICODE_RANGE,
                ENDING_UNICODE_RANGE, UNICODE_RANGE);

        reflector = new Reflector(randomCharListMaker(RANDOM_SEED_REFLECTOR, unicodeBucketMaker()),
                characterBucket, STARTING_UNICODE_RANGE,
                ENDING_UNICODE_RANGE, UNICODE_RANGE);

        System.out.println("-------");
        System.out.println(cypherchar('B'));
        
        System.out.println(cypherchar('Q'));
                
//        plugboard.add('f', 'z');
//        System.out.print(plugboard.get('f'));

        //test dictionaryChain
//        plugBoard = new DictionaryChain<>();
//        plugBoard.add('c', 'd');
//        System.out.println(plugBoard.get('c'));
        //test hashmap
//        hashmap = new HashMap<>();
//        hashmap.put(5, 2);
//        System.out.println(hashmap.get(5));
    }

    public static int[] unicodeBucketMaker() {
        int[] unicodeBucket = new int[UNICODE_RANGE];
        for (int index = 0; index < unicodeBucket.length; index++) {
            unicodeBucket[index] = index + STARTING_UNICODE_RANGE;
        }
        return unicodeBucket;
    }

    public static void characterBucketMaker() {
        int[] unicodeBucket = unicodeBucketMaker();
        characterBucket = new char[UNICODE_RANGE];
        for (int index = 0; index < characterBucket.length; index++) {
            characterBucket[index] = (char) (unicodeBucket[index]);
        }
    }

    public static ArrayList<Character> randomCharListMaker(int SeedForRandom, int[] unicodeBucket) {
        randomCharacterList = new ArrayList<>(UNICODE_RANGE);
        Random random = new Random(SeedForRandom);
        //    randomCharacterList = new ArrayList<>(UNICODE_RANGE);

//        int[] unicodeBucket = new int[UNICODE_RANGE];
//        for (int index = 0; index < unicodeBucket.length; index++) {
//            unicodeBucket[index] = index + STARTING_UNICODE_RANGE;
//        }
        while (randomCharacterList.size()< UNICODE_RANGE) {
            int randomUnicode = random.nextInt(unicodeBucket.length);
            while (unicodeBucket[randomUnicode] == 0) {
                randomUnicode = random.nextInt(unicodeBucket.length);
            }
            char randomChar = (char) (unicodeBucket[randomUnicode]);
            randomCharacterList.add(randomChar);
            unicodeBucket[randomUnicode] = 0;
        }
        return randomCharacterList;
    }

    public static char cypherchar(char charToCypher) {
        char charCyphered = plugboard.get(
                rotorIII.get(
                        rotorII.get(
                                rotorI.get(
                                        reflector.get(
                                                rotorI.get(
                                                        rotorII.get(
                                                                rotorIII.get(
                                                                        plugboard.get(charToCypher)
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        return charCyphered;
    }

}
