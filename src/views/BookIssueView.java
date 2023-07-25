package views;

import controllers.IssuanceDAO;

import java.util.Scanner;

public class BookIssueView {
    public static void run() {

        boolean stop = false;
            while(!stop) {
                Scanner sc1 = new Scanner(System.in);
                Scanner sc2 = new Scanner(System.in);
                System.out.println("**** Book Issuing Menu *** \n");

                System.out.println("Enter the book id of the book needed to issue : ");
                int bookId = sc1.nextInt();

                System.out.println("Enter the member id : ");
                String memberId = sc2.nextLine();

                IssuanceDAO dao = new IssuanceDAO();
                dao.save(bookId, memberId);

                System.out.println("Do you want to issue another book (y/n) : ");
                String choice = sc1.next();

                boolean stop1 = false;
                while(!stop1) {
                    if (choice.equalsIgnoreCase("y")) {
                        stop1 = true;
                    } else if (choice.equalsIgnoreCase("n")) {
                        stop = true;
                        stop1 = true;
                    } else {
                        System.out.println("\nWrong character entered !!");
                        System.out.println("\nDo you want to issue another book (y/n) : ");
                        choice = sc1.next();
                    }
                }
            }
        }
}

