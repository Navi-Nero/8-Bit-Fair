package Games.Monopoly_Assets.Properties;

public class Properties_Constructor 
{

    // Initialized Parameters
    private int property_Index;
    private String property_Name;
    private String property_Type;
    private int property_Price;
    private int[] property_Rent;
    private String property_Owner;

    // Constructor 
    Properties_Constructor(int index, String name, String type, int price, int[] rent, String owner)
    {

        this.property_Index = index;
        this.property_Name = name;
        this.property_Type = type;
        this.property_Price = price;
        this.property_Rent = rent;
        this.property_Owner = owner;

    }

    public String get_Property_Name()
    {
        return property_Name;
    }

    public String get_Property_Type()
    {
        return property_Type;
    }

    public int get_Property_Price()
    {
        return property_Price;
    }

    public String get_Property_Owner()
    {

        if (property_Owner == null)
        {
            return null;
        }

        return property_Owner;

    }

    // Rent scales with number of Properties with the same color/type owned
    public int get_Rent(int houses) 
    {

        if (houses < 0 || houses >= property_Rent.length) 
        {
            throw new IllegalArgumentException("Invalid number of houses/hotel");
        }

        return property_Rent[houses];

    }

    public void property_New_Owner(String new_Owner)
    {
        this.property_Owner = new_Owner;
    }
}