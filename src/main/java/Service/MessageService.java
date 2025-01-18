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
}
