package Games.Wordle_Assets;
import Games.Input_Handling;

// Word bank for Wordle - stores all possible words to guess
// Randomly picks one when the player chooses to play with a random word
public class Words {
    
    private final Input_Handling input = new Input_Handling();

    private final String[] words = {"HONEY", "BROAD", "AISLE", "DEBIT", "DRAWN", "OWNER", "WIDEN", "STERN", "FAINT",
        "CAMEL", "MUSHY", "SPOUT", "FLUNK", "DELAY", "STORM", "BRACE", "ACORN", "MEDIC", "OUNCE", "LOWER", "SCALE",
        "SHOUT", "SUPER", "FERAL", "APPLE", "HELLO", "GHOST", "WORDS", "LEMON", "MELON", "GRAPE", "PAIRS", "PEERS",
        "PEARS", "SHIRT", "SHOES", "WATER", "FROGS", "HORSE", "TREES", "TRAIN", "DEEDS", "BRAIN", "FRAME", "BLAME",
        "SMILE", "TEETH", "PLEAD", "SEEDS", "QUITE", "FLYER", "PHONE", "SHORT", "SHAKE"};                            

    public String getword(){

        int i = input.randomize(words.length);

        String word = words[i];
        return word;
    }
}