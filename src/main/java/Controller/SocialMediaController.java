package Controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;
import java.util.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController(){
        this.accountService=new AccountService();
        this.messageService=new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("register", this::registerHandler);
        app.post("login", this::loginHandler);
        app.post("messages",this::sendmessageHandler);
        app.get("messages",this::getallmessageHandler);
        return app;
    }

    /* creates a new account in accout table if
     *1. username is not blank
     *2. password is greater than 4 characters
     *3. there is no duplicate username
     */
    private void registerHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account=mapper.readValue(context.body(),Account.class);
        Account registeredAccount=  accountService.registerAccount(account);
        if (registeredAccount!=null)
            context.json(mapper.writeValueAsString(registeredAccount)).status(200);
        else{
            context.status(400);//error due to reasons above
        }
    }
    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void loginHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Account account=mapper.readValue(context.body(),Account.class);
        Account loginAccount=  accountService.loginAccount(account);
        if (loginAccount!=null)
            context.json(loginAccount).status(200);//Success
        else{
            context.status(401);//error due to reasons above
        }
    }
    private void sendmessageHandler(Context context) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message=mapper.readValue(context.body(), Message.class);
        Message createdMessage=messageService.createMessage(message);
        if(createdMessage!=null){
            context.json(createdMessage).status(200);
        }
        else{
            context.status(400);
        }
    }
    private void getallmessageHandler(Context context) throws JsonProcessingException{
        List<Message> createdMessage=messageService.getallMessage();
        context.json(createdMessage).status(200);
        
    }
    private void exampleHandler(Context context) {
        context.json("sample text");
    }


}