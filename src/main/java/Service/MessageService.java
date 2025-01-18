package Service;
import DAO.MessageDAO;
import DAO.AccountDAO;
import Model.Account;
import Model.Message;
import java.util.*;
public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;
    public MessageService(){
        messageDAO=new MessageDAO();
        accountDAO=new AccountDAO();
    }
    public Message createMessage(Message createdMessage){
        if(!createdMessage.getMessage_text().isBlank() && createdMessage.getMessage_text().length()<255 && accountDAO.existsAccount(createdMessage)){
            return messageDAO.createMessage(createdMessage);
        }
        return null;
    }
    public List<Message> getallMessage(){
        return messageDAO.getAllMessages();
    }
    public Message getoneMessage(String message_id){
        return messageDAO.getoneMessage(message_id);
    }
    public Message deleteMessage(String message_id){
        Message deletedMessage=getoneMessage(message_id);
        int delsuc=messageDAO.deleteMessage(message_id);
        if (delsuc!=0)
            return deletedMessage;
        else
            return null;
    }
    public Message updateMessage(String message_id,Message updatedMessage){
        Message updateMessage=getoneMessage(message_id);
        System.out.print(updatedMessage.getMessage_text().isBlank());
        if(updateMessage!=null && !updatedMessage.getMessage_text().isBlank() && updatedMessage.getMessage_text().length()<255){
            messageDAO.updateMessage(message_id,updatedMessage);
            Message newMessage=getoneMessage(message_id);//newmessage
            return newMessage;
        }
        return null;

    }
    public List<Message> getallmessageAccount(String account_id){
        return messageDAO.getAllMessageAccount(account_id);
    }
}
