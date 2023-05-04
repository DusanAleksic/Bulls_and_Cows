package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static String generateRandomCode(int numberLength,int possibleSymbols) {
        Random random = new Random();

        int algorithmChoice = -1;
        String code = new String("");
        int randomNumber = -1;

        int j = 2;
        if (possibleSymbols == 0)
            j = 1;

       while( code.length()< numberLength) {
            algorithmChoice = random.nextInt(j);

            if (algorithmChoice == 0) {
                randomNumber = random.nextInt(10) + 48;
                if (code.contains(String.valueOf((char) randomNumber))) {
                    continue;
                }
               code= code.concat(String.valueOf((char) randomNumber));

            } else if (algorithmChoice == 1) {
                randomNumber = random.nextInt(possibleSymbols) + 97;
                if (code.contains(String.valueOf((char) randomNumber))) {
                    continue;
                }
                  code=  code.concat(String.valueOf((char) randomNumber));
                }
            }
        return code;
        }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        String numberLength1= "";
        int numberLength =0;

        numberLength1 = scanner.nextLine();
        for(int i=0;i<numberLength1.length();i++){
            if(!Character.isDigit(numberLength1.charAt(i))){
                System.out.println("Error: "+numberLength1+" isn't a valid number.");
                System.exit(0);
            }

        }
        numberLength = Integer.parseInt(numberLength1);

        if(numberLength>36){
            System.out.println("Error: can't generate a secret number with a length more then 36.");
            System.exit(0);
        }
        if(numberLength<1){
            System.out.println("Error: can't generate a secret number with a length less then 1.");
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code:");
        int possibleSymbols = scanner.nextInt();
        if(possibleSymbols<numberLength){
            System.out.println("Error: it's not possible to generate a code with a length of "+numberLength+" with " +
                    +possibleSymbols+" unique symbols.");
            System.exit(0);
        }
        if(possibleSymbols>36){
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }

        possibleSymbols = possibleSymbols - 10;
        System.out.print("The secret is prepared: ");
        for(int i=0;i<numberLength;i++){
            System.out.print("*");
        }
        char c= ' ';
        if(possibleSymbols==0){
            System.out.println(" (0-9).");
        }
        else {
            c= (char) (possibleSymbols+96);
            System.out.printf(" (0-9) ,(a-%c).",c);
        }
        System.out.println();
        scanner.nextLine();
        String secretCode = generateRandomCode(numberLength,possibleSymbols);

        System.out.println("Okay, let's start a game!");

        int bulls=0,cows=0,turn=1;

        while(bulls!=numberLength){

            System.out.println("\nTurn "+(turn++)+":");
            bulls=0; cows=0;

            String guessString = scanner.nextLine();

            for(int i=0;i<secretCode.length();i++) {
                for(int j=0;j<guessString.length();j++){
                    if(secretCode.charAt(i)==guessString.charAt(j) && i!=j){
                        cows++;
                    }
                    else if(secretCode.charAt(i)==guessString.charAt(j) && i==j){
                        bulls++;
                    }
                }
            }

            if(bulls>0 || cows>0)
                System.out.printf("Grade : %d bull(s) and %d cow(s).",bulls,cows);
            else
                System.out.printf("Grade: None.");
            if(bulls==numberLength)
                System.out.println("Congratulations! You guessed the secret code." );
        }
    }
}
