package Controller;

import Model.Account;
import Model.Blog;
import Model.Comment;
import Service.AccountService;
import Service.BlogService;
import Service.CategoryService;
import Service.CommentService;
import jdk.jfr.Category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "BlogsServlet", value = "/blogs")
public class BlogServlet extends HttpServlet {

    private final CategoryService categoryService;

    private final BlogService blogService;

    private final CommentService commentService;

    private final AccountService accountService;

    public BlogServlet(){
        categoryService = new CategoryService();
        blogService = new BlogService();
        commentService = new CommentService();
        accountService = new AccountService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null){
            action = "";
        }

        switch (action){
            case "postManager":
                managerAllBlogs(request,response);
                break;
            case "create":
                createBlogForm(request,response);
                break;
            case "detail":
                detailBlogs(request,response);
                break;
            case "mylist":
                displayMyBlogs(request,response);
                break;
            case "updateMyBlog":
                updateMyBlogsForm(request,response);
                break;
            case "deleteMyBlog":
            case "deleteBlog":
                deleteBlog(request,response);
                break;
            default:
                displayBlogs(request,response);
        }
    }

    private void managerAllBlogs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("blog/blogManager.jsp");
        request.setAttribute("account",accountService.currentAccount);
        request.setAttribute("categories",categoryService.findAll());
        request.setAttribute("blogs",blogService.finAll());
        rd.forward(request,response);
    }

    private void deleteBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        blogService.deleteBlog(id);
        response.sendRedirect("/blogs");
    }


    private void updateMyBlogsForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher rd = request.getRequestDispatcher("blog/edit.jsp");
        request.setAttribute("blog", blogService.findById(id));
        request.setAttribute("categories", categoryService.findAll());
        rd.forward(request, response);

    }


    private void createBlogForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("blog/create.jsp");
        request.setAttribute("categories", categoryService.findAll());
        rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action == null){
            action = "";
        }

        switch (action){
            case "create":
                createBlog(request,response);
                break;
            case "detail":
                detailBlogs(request,response);
                break;
            case "updateMyBlog":
                updateMyBlog(request,response);
                break;
            default:
                displayBlogs(request,response);
        }
    }

    private void updateMyBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("blog_id"));
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String image = request.getParameter("image");
        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        Account account = accountService.currentAccount;
        blogService.updateBlog(new Blog(id,title, content, LocalDateTime.now(),account.getFullName(),
                categoryService.findById(categoryId),account,true,image));
        response.sendRedirect("/blogs");
    }

    private void createBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String image = request.getParameter("image");
        Integer categoryId = Integer.parseInt(request.getParameter("category"));
        Account account = accountService.currentAccount;
        blogService.createBlog(new Blog(title, content, LocalDateTime.now(),account.getFullName(),
                categoryService.findById(categoryId),account,true,image));
        //xử lý lỗi duplicate dữ liệu trong khi tạo hoặc sửa: điều hướng với Servlet tương ứng
        response.sendRedirect("/blogs");
    }

    private void displayBlogs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("blog/list.jsp");
        request.setAttribute("account",accountService.currentAccount);
        request.setAttribute("categories",categoryService.findAll());
        request.setAttribute("blogs",blogService.finAll());
        rd.forward(request,response);
    }
    private void displayMyBlogs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("blog/mylist.jsp");
        Account account = accountService.findById(1);
        request.setAttribute("blogs",blogService.findBlogsByAccount(account));
        rd.forward(request,response);
    }
    private void detailBlogs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer account_comment_id = Integer.parseInt(request.getParameter("account_comment_id"));
        RequestDispatcher rd = request.getRequestDispatcher("blog/detail.jsp");
      //  request.setAttribute("account",commentService.findById(id));
        request.setAttribute("account", accountService.findById(account_comment_id));
        request.setAttribute("comments",commentService.findAll(id));
        request.setAttribute("blog", blogService.findById(id));
        rd.forward(request, response);
    }



}
