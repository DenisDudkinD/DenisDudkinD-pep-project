package Service;

import Model.Account;
import DAO.AccountDAO;

public class AccountService {

    private AccountDAO accountDAO;

        public AccountService(){
            accountDAO = new AccountDAO();
        }

        public AccountService (AccountDAO accountDAO){
            this.accountDAO = accountDAO;
        }
        public Account addAccount(Account account){
            return accountDAO.registerUser(account);
        }
        public Account loginAttempt(Account account){
            return accountDAO.loginAccount(account);
        }
    }

