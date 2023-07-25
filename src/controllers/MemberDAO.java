package controllers;

import db.DbCon;
import models.Book;
import models.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    public void save(Member member) {

        try {
            Connection con =  DbCon.getConnection();
            String sql = "INSERT INTO member(nic,name,age,contact_number) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, member.getMemberID());
            ps.setString(2, member.getName());
            ps.setInt(3, member.getAge());
            ps.setInt(4, member.getContactNumber());
            ps.executeUpdate();

            System.out.println("\n***Order successful***");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("***Error in insertion***");
        }
    }

    public void update(Member member) {


        try {

            Connection con = DbCon.getConnection();
            String sql = "UPDATE member SET name=?,age=?,contact_number=? WHERE nic=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, member.getName());
            ps.setInt(2, member.getAge());
            ps.setInt(3, member.getContactNumber());
            ps.setString(5, member.getMemberID());
            ps.executeUpdate();



            System.out.println("***Updated successfully***");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("***Error in updating***");
        }

    }


    public void delete(Member member) {
        try {

            Connection con = DbCon.getConnection();
            String sql = "delete from member  WHERE nic=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, member.getMemberID());
            ps.executeUpdate();

            System.out.println("***Deleted successfully***");
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("***Error occurred while deleting***");
        }
    }


    public List<Member> list() {

        List<Member> list = new ArrayList<>();
        try {
            Connection con = DbCon.getConnection();
            String sql = "SELECT * FROM member";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();



            while(rs.next()){
                Member mem = new Member();
                mem.setMemberID(rs.getString("nic"));
                mem.setName(rs.getString("name"));
                mem.setAge(rs.getInt("age"));
                mem.setContactNumber(rs.getInt("contact_number"));

                list.add(mem);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;

    }

    public List<Book> list2(String memberId){
        List<Book> list = new ArrayList<>();
        try {
            Connection con = DbCon.getConnection();
            String sql = "SELECT * FROM ((book INNER JOIN issuance ON book.book_id=issuance.book_id) INNER JOIN member ON member.nic=issuance.member_id) where member.nic=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,memberId);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Book book = new Book();

                book.setBookId(rs.getInt("book_id"));
                book.setBookName(rs.getString("book_name"));
                list.add(book);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }
}
