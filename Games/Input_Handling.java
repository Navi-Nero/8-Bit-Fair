package Games;
import java.util.Scanner;

public class Input_Handling {

    private final Scanner sc;

    //Open Scanner
    public Input_Handling() 
    {
        sc = new Scanner(System.in);
    }

    public int getInt(String dialogue) 
    {
        int getInt;

        while (true) 
        {

            System.out.print(dialogue);

            if (sc.hasNextInt()) 
            {

                getInt = sc.nextInt();
                sc.nextLine();
                break;

            } else {

                System.out.println("Invalid input! Please enter an integer.");
                sc.nextLine();

            }
        }

        return getInt;
        
    }

    public String getStr(String dialogue) 
    {

        System.out.print(dialogue);
        return sc.nextLine();

    }

    public int randomize(int n) 
    {
        return (int)(Math.random() * n);
    }

    public void close() 
    {
        sc.close();
    }
}