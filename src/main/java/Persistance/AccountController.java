package Persistance;

import Model.Account;
import Persistance.Generic.GenericController;
import Persistance.Generic.GenericDao;

public class AccountController extends GenericDao<Account> implements GenericController<Account> {

    public AccountController(){
        super();
    }

}