
package tool;

import java.util.Scanner;

public class Tools {
    //input a string
    public static String inputString(String mess){
        System.out.print(mess);
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine().trim();
        result = result.replaceAll("\\s+", " ");
        return result;
    }
    //input an integer
    public static int inputInt(String mess) {
    Scanner sc = new Scanner(System.in);
    int result = 0;
    boolean valid = false;
         do {
             System.out.print(mess);
             try {
                 String input = sc.nextLine().replaceAll("\\s","");
                 result = Integer.parseInt(input);
                 valid = true;
             } catch (NumberFormatException e) {
                 System.out.println("Invalid input, please enter an integer number");
             }
         } while (!valid);
         
    return result;
}
     //input a float
    public static float inputFloat(String mess) {
        Scanner sc = new Scanner(System.in);
        float result = 0;
        boolean valid = false;
        do {
            System.out.print(mess);
            try {
                String input = sc.nextLine().replaceAll("\\s", "");
                result = Float.parseFloat(input);
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a float number.");
            }
        } while (!valid);

        return result;
    }
    //exit back to menu or not
    public static boolean continueFunction(String s) {
    s = s.trim().toLowerCase();
    if (s.equals("y")) {
        return true;
    } else if (s.equals("n")) {
        return false;
    } else {
        System.out.println("Invalid input. Please enter 'y' or 'n'.");
        s = Tools.inputString("Enter your choice: "); 
        return continueFunction(s);
    }
}
     //check if year is valid
     public static boolean checkValidYear(int modelYear) {
        if(modelYear >= 1817 && modelYear <= 2024){
            return true;
        }
        else{
            System.out.print("Invalid model year.1800< year < 2024 \n");
            return false;
        }
    }
}
