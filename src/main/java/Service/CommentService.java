package Service;

import DAO.CommentDAO;
import Model.Category;
import Model.Comment;

import java.util.List;

public class CommentService {

    public CommentDAO commentDAO;
    public CommentService() {
        commentDAO=new CommentDAO(this);
    }
    public List<Comment> findAll(int id_Blog){
        return commentDAO.findAll(id_Blog);
    }

    public Comment findById(int id) {
        return commentDAO.findById(id);
    }
    public void updateComment(Comment comment){
        commentDAO.updateComment(comment);
    }
    public void deleteComment(int id){
        commentDAO.deleteComment(id);
    }
    public void createComment(Comment comment){
        commentDAO.createComment(comment);
    }



}
