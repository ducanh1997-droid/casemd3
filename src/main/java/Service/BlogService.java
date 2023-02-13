package Service;

import DAO.BlogDAO;
import Model.Account;
import Model.Blog;
import Model.Category;

import java.util.List;

public class BlogService {
    private final BlogDAO blogDAO;

    public BlogService(){
        blogDAO = new BlogDAO();
    }

    public List<Blog> finAll(){
        return blogDAO.findAll();
    }

    public List<Blog> findBlogsByAccount(Account account){
        return blogDAO.findBlogsByAccount(account);
    }

    public Blog findById(int id) {
        return blogDAO.findById(id);
    }

    public void createBlog(Blog blog) {
        blogDAO.create(blog);
    }

    public void updateBlog(Blog blog) {
        blogDAO.update(blog);
    }

    public void deleteBlog(int id){
        blogDAO.delete(id);
    }
}
