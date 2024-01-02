package controller;

import java.sql.SQLException;

import common.exception.SignupFailedException;
import entity.user.Account;

public class AccountController extends BaseController {
    private Account acc;

    public void login(String username, String password) throws SQLException {
        Account loginAccount = new Account(username, password);
        acc = loginAccount.login();
    }

    public Account getLoggedInAccount() {
        return acc;
    }

    public void signup(String username, String password, String confirmPassword, String name, String birthDate, String phoneNumber) throws SQLException {
        if (password.compareTo(confirmPassword) == 0) {
            Account loginAccount = new Account(username, password, name, birthDate, phoneNumber);
            acc = loginAccount.signup();
        } else {
            throw new SignupFailedException("Mật khẩu và xác nhận mật khẩu không trùng nhau");
        }
    }
    
}
