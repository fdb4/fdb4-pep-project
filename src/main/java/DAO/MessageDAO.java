package DAO;
import Model.Message;
import Util.ConnectionUtil;
import java.sql.*;
import java.util.*;
public class MessageDAO {
    public Message createMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try{
            String sql="insert into message (posted_by,message_text,time_posted_epoch) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            System.out.print("Success");

            if(resultSet.next()){
                int account_id = (int) resultSet.getInt(1);
                return new Message(account_id,message.getPosted_by(),message.getMessage_text(),message.getTime_posted_epoch());
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messageList=new ArrayList<>();

        try{
            String sql = "select * from message";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int message_id=resultSet.getInt(1);
                int posted_by=resultSet.getInt(2);
                String message_text=resultSet.getString(3);
                long time_posted_epoch=resultSet.getLong(4);
                messageList.add(new Message(message_id,posted_by,message_text,time_posted_epoch));
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return messageList; 
    }
}
