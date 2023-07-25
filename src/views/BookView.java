package views;

import controllers.BookDAO;
import models.Book;
import models.Member;

import java.util.List;
import java.util.Scanner;

public class BookView {
    public static void run() {

        boolean stop = false;
        while(!stop) {
            System.out.println("\n*** Book Management ***\n");
            System.out.println("What do you want to do ? \nChoose one of the following : ");

            System.out.println("\t1. Add new book");
            System.out.println("\t2. Update an existing book details");
            System.out.println("\t3. Delete an existing book details");
            System.out.println("\t4. View all books");
            System.out.println("\t5. View borrowed members of a particular book");
            System.out.println("\t6. Return to the main menu");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    updateDish();
                    break;

                case 3:
                    deleteBook();
                    break;

                case 4:
                    BookView.viewAllBooks();
                    break;

                case 5:
                    BookView.viewAllBorrowedMembers();
                    break;

                case 6:
                    stop = true;

                default:
                    System.out.println("\n!!! Wrong selection, please enter a valid number between 1 to 5 !!!\n");
            }
        }

    }


    public static void addBook() {
        System.out.println("*** Inserting a book ***");

        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.println("Enter the book ID");
        int id = sc1.nextInt();
        System.out.println("Enter name of the book: ");
        String name1 = sc2.nextLine();
        System.out.println("Enter quantity : ");
        int qty = sc1.nextInt();

        Book book = new Book();
        book.setBookId(id);
        book.setBookName(name1);
        book.setQuantity(qty);

        BookDAO dao = new BookDAO();

        dao.save(book);
    }

    public static void updateDish() {
        System.out.println("*** Updating an book ***\n");
        System.out.println("Enter book id of the book needed to be updated");


        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        System.out.println("Enter name of the book : ");
        String name = sc.next();
        System.out.println("Enter quantity : ");
        int qty = sc.nextInt();

        Book book  = new Book();

        book.setBookId(id);
        book.setBookName(name);
        book.setQuantity(qty);

        BookDAO dao = new BookDAO();

        dao.update(book);
    }

    public static void deleteBook() {
        System.out.println("*** Deleting a Book ***\n");
        System.out.println("Enter book id of the book needed to be deleted");

        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        Book book = new Book();

        book.setBookId(id);

        BookDAO dao = new BookDAO();

        dao.delete(book);
    }


    public static void viewAllBooks()
    {
        System.out.println("*** Book List in the Library ***\n");
        BookDAO dao = new BookDAO();
        List<Book> list = dao.list();

        System.out.printf("%-8s%-50s%-10s\n","id","name","quantity");
        System.out.println("---------------------------------------------------------------------");

        for(Book book: list)
        {
            int id = book.getBookId();
            String name = book.getBookName();
            int qty = book.getQuantity();

            System.out.printf("%-8d%-50s%-10d\n", id, name,qty);
        }

    }

    public static void viewAllBorrowedMembers(){
        System.out.println("Enter the book id : ");
        Scanner sc = new Scanner(System.in);
        int bookId = sc.nextInt();

        BookDAO dao = new BookDAO();
        List<Member> list = dao.getAllMembers(bookId);

        System.out.println("\n********** Members who borrowed the book having book ID " + bookId);

        System.out.printf("\n%-20s%-20s%-5s%-20s\n","id","name","age","contact number");
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

}