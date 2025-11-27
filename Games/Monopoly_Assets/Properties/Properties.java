package Games.Monopoly_Assets.Properties;

import java.util.ArrayList;
import java.util.List;

public class Properties {

    private List<Properties_Constructor> property;

    // Creating Properties
    public Properties()
    {
        property = new ArrayList<>();

        // Brown
        property.add(new Properties_Constructor(1, "Mediterranean Avenue", "Brown", 60, new int[] {2, 10, 30, 90, 160, 250}, null));
        property.add(new Properties_Constructor(3, "Baltic Avenue", "Brown", 60, new int[] {4, 20, 60, 180, 320, 450}, null));

        // Light Blue
        property.add(new Properties_Constructor(6, "Oriental Avenue", "Light Blue", 100, new int[] {6, 30, 90, 270, 400, 550}, null));
        property.add(new Properties_Constructor(8, "Vermont Avenue", "Light Blue", 100, new int[] {6, 30, 90, 270, 400, 550}, null));
        property.add(new Properties_Constructor(9, "Connecticut Avenue", "Light Blue", 120, new int[] {8, 40, 100, 300, 450, 600}, null));

        // Pink
        property.add(new Properties_Constructor(11, "St. Charles Place", "Pink", 140, new int[] {10, 50, 150, 450, 625, 750}, null));
        property.add(new Properties_Constructor(13, "States Avenue", "Pink", 140, new int[] {10, 50, 150, 450, 625, 750}, null));
        property.add(new Properties_Constructor(14, "Virginia Avenue", "Pink", 160, new int[] {12, 60, 180, 500, 700, 900}, null));

        // Orange
        property.add(new Properties_Constructor(16, "St. James Place", "Orange", 180, new int[] {14, 70, 200, 550, 750, 950}, null));
        property.add(new Properties_Constructor(18, "Tennessee Avenue", "Orange", 180, new int[] {14, 70, 200, 550, 750, 950}, null));
        property.add(new Properties_Constructor(19, "New York Avenue", "Orange", 200, new int[] {16, 80, 220, 600, 800, 1000}, null));

        // Red
        property.add(new Properties_Constructor(21, "Kentucky Avenue", "Red", 220, new int[] {18, 90, 250, 700, 875, 1050}, null));
        property.add(new Properties_Constructor(23, "Indiana Avenue", "Red", 220, new int[] {18, 90, 250, 700, 875, 1050}, null));
        property.add(new Properties_Constructor(24, "Illinois Avenue", "Red", 240, new int[] {20, 100, 300, 750, 925, 1100}, null));

        // Yellow
        property.add(new Properties_Constructor(26, "Atlantic Avenue", "Yellow", 260, new int[] {22, 110, 330, 800, 975, 1150}, null));
        property.add(new Properties_Constructor(27, "Ventnor Avenue", "Yellow", 260, new int[] {22, 110, 330, 800, 975, 1150}, null));
        property.add(new Properties_Constructor(29, "Marvin Gardens", "Yellow", 280, new int[] {24, 120, 360, 850, 1025, 1200}, null));

        // Green
        property.add(new Properties_Constructor(31, "Pacific Avenue", "Green", 300, new int[] {26, 130, 390, 900, 1100, 1275}, null));
        property.add(new Properties_Constructor(32, "North Carolina Avenue", "Green", 300, new int[] {26, 130, 390, 900, 1100, 1275}, null));
        property.add(new Properties_Constructor(34, "Pennsylvania Avenue", "Green", 320, new int[] {28, 150, 450, 1000, 1200, 1400}, null));

        // Dark Blue
        property.add(new Properties_Constructor(37, "Park Place", "Dark Blue", 350, new int[] {35, 175, 500, 1100, 1300, 1500}, null));
        property.add(new Properties_Constructor(39, "Boardwalk", "Dark Blue", 400, new int[] {50, 200, 600, 1400, 1700, 2000}, null));

        // Railroads 
        property.add(new Properties_Constructor(5, "Reading Railroad", "Railroad", 200, new int[] {25, 50, 100, 200}, null));
        property.add(new Properties_Constructor(15, "Pennsylvania Railroad", "Railroad", 200, new int[] {25, 50, 100, 200}, null));
        property.add(new Properties_Constructor(25, "B & O Railroad", "Railroad", 200, new int[] {25, 50, 100, 200}, null));
        property.add(new Properties_Constructor(35, "Short Line", "Railroad", 200, new int[] {25, 50, 100, 200}, null));

        // Utilities (rent is multiplier × dice roll)
        property.add(new Properties_Constructor(12, "Electric Company", "Utility", 150, new int[] {4, 10}, null));
        property.add(new Properties_Constructor(28, "Water Works", "Utility", 150, new int[] {4, 10}, null));

    }

    public List<Properties_Constructor> getProperties() 
    {
        return property;
    }
}
