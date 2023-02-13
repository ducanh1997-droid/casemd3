package Controller;

import Service.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CategoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    private final CategoryService categoryService;

    public CategoryServlet() {
        categoryService = new CategoryService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null) {
//            action = "";
//        }
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        displayCategoryList(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        displayCategoryList(request, response);
    }

    private void displayCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("category/list.jsp");
        request.setAttribute("categories", categoryService.findAll());
        rd.forward(request, response);
    }
}
