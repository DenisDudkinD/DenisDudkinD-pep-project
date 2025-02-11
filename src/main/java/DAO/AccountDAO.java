package DAO;

import Model.Account;
import Util.ConnectionUtil;
import java.sql.*;


public class AccountDAO {


    public Account getAccountByUser(String username){
        Connection connection = ConnectionUtil.getConnection();
        Account account = new Account();
        try {
            String sql = "SELECT * FROM account WHERE username = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet rs = preparedStatement.executeQuery();
            account.setAccount_id(rs.getInt("account_id"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("password"));    
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return account;
    }
    public Account registerUser(Account account){
        Connection connection = ConnectionUtil.getConnection();
        System.out.println(account.getUsername());
        System.out.println(account.getPassword());
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
