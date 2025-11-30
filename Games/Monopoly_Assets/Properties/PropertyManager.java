package Games.Monopoly_Assets.Properties;

import Games.Monopoly_Assets.Players.PlayerData;
import java.util.ArrayList;
import java.util.List;

public class PropertyManager {

    public static final String RESET = "\u001B[0m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String LIGHT_BLUE = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    private final List<PropertyData> properties;

    public PropertyManager() 
    {

        properties = new ArrayList<>();

        // --- COLOR PROPERTIES ---
        addProperty(1,  colorize(BLACK, "Mediterranean Avenue"), "Black", 60,  new int[]{2, 10, 30, 90, 160, 250});
        addProperty(3,  colorize(BLACK, "Baltic Avenue"),        "Black", 60,  new int[]{4, 20, 60, 180, 320, 450});

        addProperty(6,  colorize(LIGHT_BLUE, "Oriental Avenue"),    "Light Blue", 100, new int[]{6, 30, 90, 270, 400, 550});
        addProperty(8,  colorize(LIGHT_BLUE, "Vermont Avenue"),     "Light Blue", 100, new int[]{6, 30, 90, 270, 400, 550});
        addProperty(9,  colorize(LIGHT_BLUE, "Connecticut Avenue"), "Light Blue", 120, new int[]{8, 40, 100, 300, 450, 600});

        addProperty(11, colorize(PURPLE, "St. Charles Place"),    "Pink", 140, new int[]{10, 50, 150, 450, 625, 750});
        addProperty(13, colorize(PURPLE, "States Avenue"),        "Pink", 140, new int[]{10, 50, 150, 450, 625, 750});
        addProperty(14, colorize(PURPLE, "Virginia Avenue"),      "Pink", 160, new int[]{12, 60, 180, 500, 700, 900});

        addProperty(16, colorize(WHITE, "St. James Place"),        "Orange", 180, new int[]{14, 70, 200, 550, 750, 950});
        addProperty(18, colorize(WHITE, "Tennessee Avenue"),       "Orange", 180, new int[]{14, 70, 200, 550, 750, 950});
        addProperty(19, colorize(WHITE, "New York Avenue"),        "Orange", 200, new int[]{16, 80, 220, 600, 800, 1000});

        addProperty(21, colorize(RED, "Kentucky Avenue"),         "Red", 220, new int[]{18, 90, 250, 700, 875, 1050});
        addProperty(23, colorize(RED, "Indiana Avenue"),          "Red", 220, new int[]{18, 90, 250, 700, 875, 1050});
        addProperty(24, colorize(RED, "Illinois Avenue"),         "Red", 240, new int[]{20, 100, 300, 750, 925, 1100});

        addProperty(26, colorize(YELLOW, "Atlantic Avenue"),      "Yellow", 260, new int[]{22, 110, 330, 800, 975, 1150});
        addProperty(27, colorize(YELLOW, "Ventnor Avenue"),       "Yellow", 260, new int[]{22, 110, 330, 800, 975, 1150});
        addProperty(29, colorize(YELLOW, "Marvin Gardens"),       "Yellow", 280, new int[]{24, 120, 360, 850, 1025, 1200});

        addProperty(31, colorize(GREEN, "Pacific Avenue"),        "Green", 300, new int[]{26, 130, 390, 900, 1100, 1275});
        addProperty(32, colorize(GREEN, "North Carolina Avenue"), "Green", 300, new int[]{26, 130, 390, 900, 1100, 1275});
        addProperty(34, colorize(GREEN, "Pennsylvania Avenue"),   "Green", 320, new int[]{28, 150, 450, 1000, 1200, 1400});

        addProperty(37, colorize(BLUE, "Park Place"),             "Dark Blue", 350, new int[]{35, 175, 500, 1100, 1300, 1500});
        addProperty(39, colorize(BLUE, "Boardwalk"),              "Dark Blue", 400, new int[]{50, 200, 600, 1400, 1700, 2000});

        // RAILROADS & UTILITIES (no color)
        addProperty(5,  "Reading Railroad",      "Railroad", 200, new int[]{25, 50, 100, 200});
        addProperty(15, "Pennsylvania Railroad", "Railroad", 200, new int[]{25, 50, 100, 200});
        addProperty(25, "B&O Railroad",          "Railroad", 200, new int[]{25, 50, 100, 200});
        addProperty(35, "Short Line",            "Railroad", 200, new int[]{25, 50, 100, 200});

        addProperty(12, "Electric Company",      "Utility", 150, new int[]{4, 10});
        addProperty(28, "Water Works",           "Utility", 150, new int[]{4, 10});
        
    }


        private String colorize(String colorCode, String name) {
        return colorCode + name + RESET;
    }
    // Helper to add properties
    private void addProperty(int index, String name, String type, int price, int[] rent) {
        properties.add(new PropertyData(index, name, type, price, rent, null));
    }

    // MAIN ACCESSOR: return property at board index
    public PropertyData getPropertyByIndex(int boardIndex) {
        for (PropertyData p : properties) {
            if (p.getBoardIndex() == boardIndex) {
                return p;
            }
        }
        return null;
    }

    public List<PropertyData> getAllProperties() {
        return properties;
    }

    // Count how many railroads a player owns
    public int countRailroadsOwned(PlayerData player) {
        int count = 0;
        for (PropertyData p : properties) {
            if (p.getPropertyType().equals("Railroad") && p.getPropertyOwner() == player) {
                count++;
            }
        }
        return count;
    }

    // Count utilities owned
    public int countUtilitiesOwned(PlayerData player) {
        int count = 0;
        for (PropertyData p : properties) {
            if (p.getPropertyType().equals("Utility") && p.getPropertyOwner() == player) {
                count++;
            }
        }
        return count;
    }
}
