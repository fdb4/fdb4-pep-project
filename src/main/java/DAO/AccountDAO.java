package DAO;
import Model.Account;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
public class AccountDAO {
    //Logic to determine if username and password is value is in service
    public Account registerAccount(Account user){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql="insert into account (username, password) values (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int account_id = (int) pkeyResultSet.getLong(1);
                return new Account(account_id, user.getUsername(),user.getPassword());
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account loginAccount(Account user){
        Connection connection = ConnectionUtil.getConnection();

        try{
            String sql="select * from account where username=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                int account_id = (int) resultSet.getInt(1);
                return new Account(account_id, user.getUsername(),user.getPassword());
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public boolean existsAccount(Message message){//Created for the purpose of locating an existing account
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql="select * from account where account_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,message.getPosted_by());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;//an error occured
    }
}
