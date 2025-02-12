package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;


public class AccountDAO {


    public Account loginAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        Account loginAccount = new Account();
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,account.getUsername());
            preparedStatement.setString(2,account.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                 loginAccount.setAccount_id(rs.getInt("account_id"));
                 loginAccount.setUsername(rs.getString("username"));
                 loginAccount.setPassword(rs.getString("password"));
            }
            return loginAccount;    
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Account registerUser(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try{
        if(account.getUsername() != ""){
            if(account.getPassword().length() >= 4){
                String sql = "INSERT INTO account (username,password) VALUES (?,?)";
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
