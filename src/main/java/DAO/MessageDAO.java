package DAO;

import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MessageDAO {
    public Message addMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
        if(message.getMessage_text() != ""){
            if(message.getMessage_text().length() <= 255){
                if(message.getPosted_by() > 0){
                String sql = "INSERT INTO message (posted_by,message_text,time_posted_epoch) VALUES (?,?,?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,message.getPosted_by());
                ps.setString(2, message.getMessage_text());
                ps.setLong(3,message.getTime_posted_epoch());
                ps.executeUpdate();
                ResultSet pkeyResultSet = ps.getGeneratedKeys();
                if(pkeyResultSet.next()){
                    int generated_message_id = (int) pkeyResultSet.getLong(1);
                    return new Message(generated_message_id,message.getPosted_by(),message.getMessage_text(), message.getTime_posted_epoch());}
                }
            }
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
    }
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
    public Message getMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        Message message = null;
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,message_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                message = new Message(rs.getInt("message_id"),rs.getInt("posted_by"),rs.getString("message_text"),rs.getLong("time_posted_epoch"));
            }
            return message;
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void deleteMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "DELETE FROM message WHERE message_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,message_id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public Boolean updateMessageById(String message_text, int message_id){
        Connection connection = ConnectionUtil.getConnection();
        Boolean updated = false;

        try {
            if(message_text.length() <= 255){
                if(message_text != ""){
                     String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";
                     PreparedStatement ps = connection.prepareStatement(sql);
                     ps.setString(1,message_text);
                     ps.setInt(2,message_id);
                     ps.executeUpdate();
                     updated = true;
                     return updated;
                }}
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return updated;
    }

}
