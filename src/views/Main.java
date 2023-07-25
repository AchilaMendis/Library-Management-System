package views;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean stop = false;
        while (!stop) {
            System.out.println("\n*** LIBRARY MANAGEMENT SYSTEM ***\n");
            System.out.println("Choose one of the following");
            System.out.println("\t1. Book Management");
            System.out.println("\t2. Member Management");
            System.out.println("\t3. Issue a book");
            System.out.println("\t4. Exit");

            try {
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        BookView.run();
                        break;

                    case 2:
                        MemberView.run();
                        break;

                    case 3:
                        BookIssueView.run();
                        break;

                    case 4:
                        //System.exit(0);
                        stop = true;
                        break;

                    default:
                        System.out.println("!!! Wrong selection, please enter a valid number between 1 to 6 !!!\n");
                }
            } catch (InputMismatchException ime) {
                //ime.printStackTrace();
                System.out.println("Entered INAPPROPRIATE value !! Please input only appropriate values\n");
            }
        }

    }
}

