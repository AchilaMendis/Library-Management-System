package controllers;

import db.DbCon;
import models.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

public class IssuanceDAO {
    public void save(int bookId, String memberId) {

        try {
            Connection con =  DbCon.getConnection();
            String sql = "INSERT INTO issuance(member_id,book_id) VALUES (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, memberId);
            ps.setInt(2, bookId);
            ps.executeUpdate();

            System.out.println("***Book issued successfully***");
        }  catch (SQLIntegrityConstraintViolationException si){
            System.out.println("@@@@@@@@@@@@@@@@ Entered book id OR NIC number does not exist. Please enter valid ones !! @@@@@@@@@@@@@@@@@@ \n");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("***Error in issuing***");
        }
    }
}
