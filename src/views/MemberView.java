package views;

import controllers.BookDAO;
import controllers.MemberDAO;
import models.Book;
import models.Member;

import java.util.List;
import java.util.Scanner;

public class MemberView {
    public static void run() {

        boolean stop = false;
        while(!stop) {
            System.out.println("\n*** Member Management ***\n");
            System.out.println("What do you want to do ? \nChoose one of the following : ");

            System.out.println("\t1. Add new Member");
            System.out.println("\t2. Update an existing member's details");
            System.out.println("\t3. Delete an existing member's details");
            System.out.println("\t4. View all member details");
            System.out.println("\t5. View all borrowed books of particular member");
            System.out.println("\t6. Return to the main menu");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addMember();
                    break;

                case 2:
                    updateMember();
                    break;

                case 3:
                    deleteMember();
                    break;

                case 4:
                    viewAllMembers();
                    break;

                case 5:
                    viewAllBorrowedBooks();
                    break;

                case 6:
                    stop = true;

                default:
                    System.out.println("\n!!! Wrong selection, please enter a valid number between 1 to 5 !!!\n");
            }
        }

    }


    public static void addMember() {
        System.out.println("*** Inserting a member ***");

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter the NIC Number");
        String id = sc1.next();
        System.out.println("Enter name of the member: ");
        String name = sc2.nextLine();
        System.out.println("Enter age : ");
        int age = sc1.nextInt();
        System.out.println("Enter contact number : ");
        int contactNum = sc1.nextInt();

        Member mem = new Member();

        mem.setMemberID(id);
        mem.setAge(age);
        mem.setName(name);
        mem.setContactNumber(contactNum);


        MemberDAO dao = new MemberDAO();

        dao.save(mem);
    }

    public static void updateMember() {
        System.out.println("*** Updating an member ***\n");
        System.out.println("Enter NIC number of the member needed to be updated");


        Scanner sc = new Scanner(System.in);
        String id = sc.next();

        System.out.println("Enter name of the member : ");
        String name = sc.next();
        System.out.println("Enter age : ");
        int age = sc.nextInt();
        System.out.println("Enter contact number : ");
        int contactNum = sc.nextInt();

        Member mem = new Member();

        mem.setMemberID(id);
        mem.setAge(age);
        mem.setName(name);
        mem.setContactNumber(contactNum);


        MemberDAO dao = new MemberDAO();

        dao.update(mem);
    }

    public static void deleteMember() {
        System.out.println("*** Deleting a member ***\n");
        System.out.println("Enter NIC number of the member needed to be deleted");

        Scanner sc = new Scanner(System.in);
        String id = sc.next();

        Member mem = new Member();

        mem.setMemberID(id);

        MemberDAO dao = new MemberDAO();

        dao.delete(mem);
    }


    public static void viewAllMembers()
    {
        System.out.println("*** Member List in the Library ***\n");
        MemberDAO dao = new MemberDAO();
        List<Member> list = dao.list();

        System.out.printf("%-20s%-20s%-5s%-20s\n","id","name","age","contact number");
        System.out.println("------------------------------------------------------------");

        for(Member mem : list)
        {
            String id = mem.getMemberID();
            String name = mem.getName();
            int age = mem.getAge();
            int contactNum = mem.getContactNumber();

            System.out.printf("%-20s%-20s%-5d%-20d\n", id, name, age, contactNum);
        }

    }

    public static void viewAllBorrowedBooks(){
        System.out.println("Enter the NIC Number of the member :");
        Scanner sc = new Scanner(System.in);
        String memberId = sc.nextLine();

        MemberDAO dao = new MemberDAO();
        List<Book> list = dao.list2(memberId);


        System.out.printf("%-20s%-20s\n","Book ID","Book Name");
        System.out.println("------------------------------------------------------------");

        for(Book book : list)
        {
            int id = book.getBookId();
            String name = book.getBookName();

            System.out.printf("%-20s%-20s\n", id, name);
        }

    }
}
