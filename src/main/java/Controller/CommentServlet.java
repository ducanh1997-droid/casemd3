package Controller;

import Model.Comment;
import Service.AccountService;
import Service.BlogService;
import Service.CommentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "ServletComment", value = "/comment")
public class CommentServlet extends HttpServlet {
    private final CommentService commentService;

    private final AccountService accountService;
    private final BlogService blogService;

    public CommentServlet(){
        commentService = new CommentService();
        blogService = new BlogService();
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
            case "delete":
                deleteComment(request,response);
                break;
            default:

        }
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
                createComment(request,response);
                break;
            case "edit":
                editComment(request,response);
                break;
            default:

        }
    }

    private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer comment_id = Integer.parseInt(request.getParameter("comment_id"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer account_comment_id = Integer.parseInt(request.getParameter("account_comment_id"));
        commentService.deleteComment(comment_id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("blog/detail.jsp");
        request.setAttribute("account", accountService.findById(account_comment_id));
        request.setAttribute("comments",commentService.findAll(id));
        request.setAttribute("blog", blogService.findById(id));
        requestDispatcher.forward(request,response);
    }

    private void editComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer comment_id = Integer.parseInt(request.getParameter("comment_id"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer account_comment_id = Integer.parseInt(request.getParameter("account_comment_id"));
        String content = request.getParameter("message");
        Comment comment = null;
        comment = new Comment(comment_id,content, LocalDateTime.now(),blogService.findById(id),accountService.findById(1),commentService.findById(1));
        commentService.updateComment(comment);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("blog/detail.jsp");
        request.setAttribute("account", accountService.findById(account_comment_id));
        request.setAttribute("comments",commentService.findAll(id));
        request.setAttribute("blog", blogService.findById(id));
        requestDispatcher.forward(request,response);
    }

    private void createComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Integer account_comment_id = Integer.parseInt(request.getParameter("account_comment_id"));
        String content = request.getParameter("message");
        Comment comment = null;
        comment = new Comment(content, LocalDateTime.now(),blogService.findById(id),accountService.findById(1),commentService.findById(1));
        commentService.createComment(comment);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("blog/detail.jsp");
        request.setAttribute("account", accountService.findById(account_comment_id));
        request.setAttribute("comments",commentService.findAll(id));
        request.setAttribute("blog", blogService.findById(id));
        requestDispatcher.forward(request,response);
    }
}
