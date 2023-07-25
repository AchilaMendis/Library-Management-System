package controllers;

import models.Book;
import db.DbCon;
import models.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public void save(Book book) {

        try {
            Connection con =  DbCon.getConnection();
            String sql = "INSERT INTO book(book_id,book_name,quantity) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, book.getBookId());
            ps.setString(2, book.getBookName());
            ps.setInt(3, book.getQuantity());
            ps.executeUpdate();


            System.out.println("***Inserted successfully***");
        }  catch (SQLIntegrityConstraintViolationException se) {
            System.out.println("Entered dish ID already exists, please enter new one");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("***Error in insertion***");
        }
    }

    public void update(Book book) {


        try {

            Connection con = DbCon.getConnection();
            String sql = "UPDATE book SET book_name=?,quantity=? WHERE book_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, book.getBookName());
            ps.setInt(2, book.getQuantity());
            ps.setInt(3, book.getBookId());
            ps.executeUpdate();

            System.out.println("***Updated successfully***");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("***Error in updating***");
        }

    }


    public void delete(Book book) {
        try {

            Connection con = DbCon.getConnection();
            String sql = "delete from book  WHERE book_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, book.getBookId());
            ps.executeUpdate();

            System.out.println("***Deleted successfully***");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("***Error occurred while deleting***");
        }
    }

    public List<Book> list() {

        List<Book> list = new ArrayList<>();
        try {
            Connection con = DbCon.getConnection();
            String sql = "SELECT * FROM book";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Book book = new Book();

                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setQuantity(rs.getInt("quantity"));
                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;

    }

    public List<Member> getAllMembers(int bookId){
        List<Member> list = new ArrayList<>();
        try {
            Connection con = DbCon.getConnection();
            String sql = "SELECT * FROM ((book INNER JOIN issuance ON book.book_id=issuance.book_id) INNER JOIN member ON member.nic=issuance.member_id) where book.book_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,bookId);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Member mem = new Member();
                mem.setMemberID(rs.getString("nic"));
                mem.setName(rs.getString("name"));
                mem.setAge(rs.getInt("age"));
                mem.setContactNumber(rs.getInt("contact_number"));

                list.add(mem);;

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }
}
