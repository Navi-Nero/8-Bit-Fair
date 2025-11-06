package Games;

import java.util.Scanner;

public class Input_Handling {

    public int getInt (String dialouge) {
        Scanner sc =  new Scanner(System.in);
        int getInt;

        while (true) {
            System.out.print(dialouge);

            if (sc.hasNextInt()) {
                getInt = sc.nextInt();
                sc.nextLine();
                break;

            } else {
                System.out.println("Invalid input! Please enter an integer.");
                sc.nextLine();
            }
        }

        sc.close();
        return getInt;
    }

    public String getStr (String dialouge) {
        Scanner sc = new Scanner(System.in);

        System.out.print(dialouge);
        sc.close();
        return sc.nextLine();
    }

    public int randomize (int n) {
        int i = (int)(Math.random() * n);

        return i;
    }  
}
