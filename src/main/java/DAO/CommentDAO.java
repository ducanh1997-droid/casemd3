package DAO;

import Model.Account;
import Model.Blog;
import Model.Category;
import Model.Comment;
import Service.AccountService;
import Service.BlogService;
import Service.CategoryService;
import Service.CommentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    private final Connection connection;

    private final AccountService accountService;

    private final BlogService blogService;

    private final CommentService commentService;
    private final String SELECT_COMMENT_BY_ID_BLOG = "select * from comment where blog_id=?";
    private final String SELECT_COMMENT_BY_ID = "select * from comment where id=?";
    private final String UPDATE_COMMENT_BY_ID = " update  comment set content = ? where id = ? ";
    private final String DELETE_COMMENT_BY_ID= "delete from comment where id = ? " ;

    private final String SET_FOREIGN_KEY= "SET FOREIGN_KEY_CHECKS = 0 " ;
    private final String INSERT_COMMENT = " insert into comment( content, createAt, blog_id,account_id,comment_id) value (?,?,?,?,?) ";


    public CommentDAO(CommentService commentService) {
        connection = MyConnection.getConnection();
        accountService = new AccountService();
        blogService = new BlogService();
        this.commentService = commentService;
    }

    public List<Comment> findAll(int id_Blog) {
        List<Comment> listComment = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT_BY_ID_BLOG)) {
            preparedStatement.setInt(1, id_Blog);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = blogService.findById(resultSet.getInt(4));
                Account account = accountService.findById(resultSet.getInt(5));
                Comment comment = commentService.findById(resultSet.getInt(6));
                listComment.add(new Comment(resultSet.getInt(1), resultSet.getString(2), resultSet.getTimestamp(3).toLocalDateTime(), blog,
                        account,comment));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listComment;
    }




    public Comment findById(int id) {
        Comment comment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COMMENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Blog blog = blogService.findById(resultSet.getInt(4));
                Account account = accountService.findById(resultSet.getInt(5));

                comment = new Comment(resultSet.getInt(1), resultSet.getString(2), resultSet.getTimestamp(3).toLocalDateTime(), blog,
                        account,comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comment;
    }
    public void updateComment(Comment comment) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT_BY_ID)) {
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setInt(2, comment.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteComment(int id){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SET_FOREIGN_KEY);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_COMMENT_BY_ID);
            preparedStatement1.setInt(1,id);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createComment(Comment comment){
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COMMENT)) {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, comment.getContent());
            preparedStatement.setTimestamp(2, Timestamp.valueOf( comment.getCreateAT()));
            preparedStatement.setInt(3,  comment.getBlog().getId());
            preparedStatement.setLong(4,  comment.getAccount().getId());
            preparedStatement.setLong(5,  comment.getComment().getId());
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
