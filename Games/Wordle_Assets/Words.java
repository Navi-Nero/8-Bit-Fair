package Games.Wordle_Assets;

import Games.Input_Handling;

public class Words {
    
    private final Input_Handling input = new Input_Handling();

    private final String[] words = {"HONEY", "BROAD", "AISLE", "DEBIT", "DRAWN", "OWNER", "WIDEN", "STERN", "FAINT",       //9
        "CAMEL", "MUSHY", "SPOUT", "FLUNK", "DELAY", "STORM", "BRACE", "ACORN", "MEDIC", "OUNCE", "LOWER", "SCALE",  //12
        "SHOUT", "SUPER", "FERAL", "APPLE", "HELLO", "GHOST", "WORDS", "LEMON", "MELON", "GRAPE", "PAIRS", "PEERS",  //12
        "PEARS", "SHIRT", "SHOES", "WATER", "FROGS", "HORSE", "TREES", "TRAIN", "DEEDS", "BRAIN", "FRAME", "BLAME",  //12
        "SMILE", "TEETH", "PLEAD", "SEEDS", "QUITE", "FLYER", "PHONE", "SHORT", "SHAKE"};                            //9

    public String getword(){

        int i = input.randomize(54);

        String word = words[i];
        return word;
    }
}
