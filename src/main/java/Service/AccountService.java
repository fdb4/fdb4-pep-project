package Service;
import DAO.AccountDAO;
import Model.Account;
public class AccountService {
    private AccountDAO accountDAO;
    public AccountService(){
        accountDAO= new AccountDAO();
    }
    public Account registerAccount(Account user){
        /*Account database already ensure username is unique
        *if that is not ok i can simply create method to compare usernames
        *in the table with this user. Test cases say it is ok though.
        */
        if (!(user.getUsername().isBlank()) && user.getPassword().length()>3){
          
            return accountDAO.registerAccount(user);
        }
        return null;// this will return null
    }
    public Account loginAccount(Account user){
        /*LoginAccount returns account if user exists
        *returns null if user does not exist
        */

        
        return accountDAO.loginAccount(user);// this will return null
    }
}
