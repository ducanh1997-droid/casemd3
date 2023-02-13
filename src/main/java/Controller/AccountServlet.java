package Controller;

import Model.Account;
import Model.Blog;
import Model.Category;
import Service.AccountService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AccountServlet", value = "/AccountServlet")
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //        Account service test
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action=request.getParameter("action");
        if (action==null){
            action="";
        }
        Category category= new Category("sport","");
        ArrayList<Blog> blogs = new ArrayList<>();
//        blogs.add(new Blog(1,"thể thao","thể thao là gì?", LocalDate.of(2022,1,12),"",category,account,"",""));
//        blogs.add(new Blog(2,"tennis","tennis là một môn thể thao nhiều...", LocalDate.of(2022,1,12),"",category,account,"",""));
        switch (action) {
            case "logout":
                accountService.currentAccount = null;
                response.sendRedirect("/blogs");
                break;
            case "login":
                RequestDispatcher rd2 = request.getRequestDispatcher("WEB-LOGIN/login_register.jsp");
                rd2.forward(request, response);
                break;
            case "loadBlog":
                int blogId=Integer.parseInt(request.getParameter("blogId"));
                request.setAttribute("accountDisplay", "none");
                request.setAttribute("blogDisplay", "block");
                request.setAttribute("blogReq", blogs.get(blogId-1));
                request.setAttribute("blogs", blogs);
                RequestDispatcher rd = request.getRequestDispatcher("WEB-LOGIN/accountService.jsp");
                rd.forward(request, response);
                break;
            default:
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("accountDisplay", "block");
                request.setAttribute("blogs", blogs);
                Account currentAccount = accountService.findById(id);
                request.setAttribute("account", currentAccount);
                RequestDispatcher rd1 = request.getRequestDispatcher("WEB-LOGIN/accountService.jsp");
                rd1.forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action =request.getParameter("action");
        if(action==null) {
            action = "";
        }
        switch (action){
            case "updatePassword":
                updatePassword(request,response);
                break;
            case "update":
                updateAccount(request,response);
                break;
            case "login":
                accountService.login(request,response);
                break;
            case "register":
                accountService.createAccount(request,response);
                break;
        }

    }

    private void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("password");
        Integer id = Integer.parseInt(request.getParameter("id"));
        Account account = accountService.findById(id);
        accountService.updatePassword(new Account(account.getId(),account.getUserName(),newPassword,account.getFullName(), account.getPhoneNumber(),account.getEmail(),account.getAddress(),account.isRole(),account.isStatus()));
        request.setAttribute("id",id);
        request.setAttribute("status","success");
        doGet(request,response);
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Account account = accountService.findById(id);
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        accountService.updateAccount(new Account(id,account.getUserName(),account.getPassWord(),fullName,
        phoneNumber,account.getEmail(),address,account.isRole(),account.isStatus()));
        request.setAttribute("id",id);
        request.setAttribute("status","success");
        doGet(request,response);
//        RequestDispatcher rd1 = request.getRequestDispatcher("/AccountServlet");
//        rd1.forward(request, response);
    }
}