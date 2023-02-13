package Service;

import DAO.AccountDAO;
import Model.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    public static Account currentAccount;
    private AccountDAO accountDAO;
    public  AccountService () {
        accountDAO=new AccountDAO();
    }
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        Account account = accountDAO.checkLoginAccount(name, password);
        if (account == null) {
            request.setAttribute("condition", "login");
            request.setAttribute("status", "false");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-LOGIN/login_register.jsp");
            rd.forward(request, response);
        } else {
            currentAccount = account;
            request.setAttribute("account", currentAccount);
            RequestDispatcher rd = request.getRequestDispatcher("/blogs");
            rd.forward(request, response);
        }
    }
    public void createAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String useName = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        Account account = new Account(useName, password, fullName, phoneNumber, email, address, false, true);
        //accountDAO.create(account);
        if (accountDAO.create(account)) {
            request.setAttribute("status", "success");
        }else{
            request.setAttribute("status", "false");
            request.setAttribute("password",password);
            request.setAttribute("fullName",fullName);
            request.setAttribute("phoneNumber",phoneNumber);
            request.setAttribute("address",address);
        }
        request.setAttribute("condition","register");
        RequestDispatcher rd = request.getRequestDispatcher("WEB-LOGIN/login_register.jsp");
        rd.forward(request, response);
    }


    public Account findById(int id) {
        return accountDAO.findById(id);
    }

    public void updateAccount(Account account) {
        accountDAO.updateAccount(account);
    }

    public void updatePassword(Account account) {
        accountDAO.updatePassword(account);
    }
}