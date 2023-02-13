package Model;

import java.time.LocalDateTime;

public class Blog {
    private int id;
    private String title;

    private String content;

    private LocalDateTime createAt;

    private String author;

    private Category category;

    private Account account;

    private String image;

    private Boolean status;


    public Blog(int id, String title, String content, LocalDateTime createAt, String author, Category category, Account account, Boolean status,String image) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.author = author;
        this.category = category;
        this.account = account;
        this.image = image;
        this.status = status;
    }

    public Blog(String title, String content, LocalDateTime createAt, String author, Category category, Account account, Boolean status, String image) {
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.author = author;
        this.category = category;
        this.account = account;
        this.image = image;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
