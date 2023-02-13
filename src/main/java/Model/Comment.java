package Model;

import java.time.LocalDateTime;

public class Comment {
    private  int id;
    private String content;

    public String getContent() {
        return content;
    }
    public LocalDateTime createAT;

    public void setContent(String content) {
        this.content = content;
    }

    private Blog blog;
    private Account account;
    private Comment comment;

    public Comment(int id, String content, LocalDateTime createAT, Blog blog, Account account, Comment comment) {
        this.id = id;
        this.content = content;
        this.createAT = createAT;
        this.blog = blog;
        this.account = account;
        this.comment = comment;
    }

    public Comment(String content, LocalDateTime createAT, Blog blog, Account account, Comment comment) {
        this.content = content;
        this.createAT = createAT;
        this.blog = blog;
        this.account = account;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreateAT() {
        return createAT;
    }

    public void setCreateAT(LocalDateTime createAT) {
        this.createAT = createAT;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}