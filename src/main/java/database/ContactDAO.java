package database;

import model.Contact;
import utils.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContactDAO extends AbsDao<Contact> {

    @Override
    public int insert(Contact contact) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "INSERT INTO Contacts (name, numberPhone, email, content) VALUES (?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, contact.getName());
            pst.setNString(2, contact.getNumberPhone());
            pst.setNString(3, contact.getEmail());
            pst.setNString(4, contact.getContent());
            result = pst.executeUpdate();
            if(result > 0){
                super.insert(contact);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<Contact> selectAll() {
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Contacts";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int contactID = rs.getInt("contactID");
                String name = rs.getNString("name");
                String numberPhone = rs.getNString("numberPhone");
                String email = rs.getNString("email");
                String content = rs.getNString("content");
                Contact contact = new Contact(contactID, name, numberPhone, email, content);
                contacts.add(contact);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }
    public ArrayList<Contact> selectByID(int id) {
        ArrayList<Contact> contacts = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "SELECT * FROM Contacts WHERE contactID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int contactID = rs.getInt("contactID");
                String name = rs.getNString("name");
                String numberPhone = rs.getNString("numberPhone");
                String email = rs.getNString("email");
                String content = rs.getNString("content");
                Contact contact = new Contact(contactID, name, numberPhone, email, content);
                contacts.add(contact);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    @Override
    public int update(Contact contact) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "UPDATE Contacts SET name = ?, numberPhone = ?, email = ?, content = ? WHERE contactID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setNString(1, contact.getName());
            pst.setNString(2, contact.getNumberPhone());
            pst.setNString(3, contact.getEmail());
            pst.setNString(4, contact.getContent());
            pst.setInt(5, contact.getContactId());
            result = pst.executeUpdate();
            if(result > 0){
                super.update(contact);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Contact contact) {
        int result = 0;
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "DELETE FROM Contacts WHERE contactID = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, contact.getContactId());
            result = pst.executeUpdate();
            if(result > 0){
                super.delete(contact);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        ContactDAO contactDao = new ContactDAO();

       // Contact contact = new Contact("Nguyen Van A", "0123456789", "a@gmail.com", "Noi dung lien he");

//       System.out.println(contactDao.selectByID(1));

        ArrayList<Contact> contacts = contactDao.selectAll();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
