package DAO;

import Model.Account;
import Model.Blog;
import Model.Category;
import Service.AccountService;
import Service.BlogService;
import Service.CategoryService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {

    private final Connection connection;

    private final CategoryService categoryService;

    private final AccountService accountService;

    public BlogDAO(){
        connection = MyConnection.getConnection();
        categoryService = new CategoryService();
        accountService = new AccountService();
    }

    private final String SELECT_ALL_BLOG = "Select * from blogs where status = true";

    private final String SELECT_BLOG_BY_ID = "Select * from blogs where id=?";

    private final String INSERT_BLOG = "insert into blogs(title,content,createAt,author,category_id,account_id,status,image) values (?,?,?,?,?,?,?,?)";

    private final String UPDATE_BLOG = "update blogs set title=?, content=?,category_id=?,image=? where id=?";

    private final String DELETE_BLOG = "delete from blogs where id = ?";
    private final String SET_FOREIGN_KEY= "SET FOREIGN_KEY_CHECKS = 0 ";

    private final String SELECT_BLOGS_BY_ACCOUNT = "Select * from blogs where status = true and account_id=?";



    public List<Blog> findAll(){
        List<Blog> blogs = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_BLOG);){
            while (resultSet.next()){
                Category category = categoryService.findById(resultSet.getInt(6));
                Account account = accountService.findById(resultSet.getInt(7));
                blogs.add(new Blog(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getTimestamp(4).toLocalDateTime(),resultSet.getString(5),
                        category,account,resultSet.getBoolean(8),resultSet.getString(9)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blogs;
    }


    public Blog findById(int id){
        Blog blog = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BLOG_BY_ID)){
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category category = categoryService.findById(resultSet.getInt(6));
                Account account = accountService.findById(resultSet.getInt(7));
                blog = new Blog(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getTimestamp(4).toLocalDateTime(),resultSet.getString(5),
                        category,account,resultSet.getBoolean(8),resultSet.getString(9));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blog;
    }

    public void create(Blog blog){
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BLOG)){
            connection.setAutoCommit(false);
            preparedStatement.setString(1,blog.getTitle());
            preparedStatement.setString(2,blog.getContent());
            preparedStatement.setTimestamp(3,Timestamp.valueOf(blog.getCreateAt()));
            preparedStatement.setString(4,blog.getAuthor());
            preparedStatement.setInt(5,blog.getCategory().getId());
            preparedStatement.setInt(6,blog.getAccount().getId());
            preparedStatement.setBoolean(7,blog.getStatus());
            preparedStatement.setString(8,blog.getImage());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Blog blog){
        try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BLOG)){
            connection.setAutoCommit(false);
            preparedStatement.setString(1,blog.getTitle());
            preparedStatement.setString(2,blog.getContent());
            preparedStatement.setInt(3,blog.getCategory().getId());
            preparedStatement.setString(4,blog.getImage());
            preparedStatement.setInt(5,blog.getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int id){
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(SET_FOREIGN_KEY);
        preparedStatement.executeUpdate();
        PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_BLOG);
        preparedStatement1.setInt(1,id);
        preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Blog> findBlogsByAccount(Account account){
        List<Blog> blogs = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BLOGS_BY_ACCOUNT)){
            preparedStatement.setInt(1,account.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category category = categoryService.findById(resultSet.getInt(6));
                blogs.add(new Blog(resultSet.getInt(1),resultSet.getString(2),
                        resultSet.getString(3),resultSet.getTimestamp(4).toLocalDateTime(),resultSet.getString(5),
                        category,account,resultSet.getBoolean(8),resultSet.getString(9)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return blogs;
    }

}
