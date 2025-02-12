package DAO;

import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;



public class MessageDAO {
    public Message addMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
        if(message.getMessage_text() != ""){
            if(message.getMessage_text().length() <= 255){
                String sql = "INSERT INTO message (posted_by,message_text) VALUES (?,?)";
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,account.getUsername());
                ps.setString(2, account.getPassword());

                ps.executeUpdate();
                ResultSet pkeyResultSet = ps.getGeneratedKeys();
                if(pkeyResultSet.next()){
                    int generated_account_id = (int) pkeyResultSet.getLong(1);
                    return new Account(generated_account_id, account.getUsername(),account.getPassword());}
            }
        }
    }catch(SQLException e){
        System.out.println(e.getMessage());
    }
    return null;
    }
}
