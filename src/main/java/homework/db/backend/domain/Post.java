package homework.db.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private LocalDateTime dateTime = LocalDateTime.now();

    @OneToOne
    private User publisher;

    private AtomicLong viewCount = new AtomicLong(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE}, mappedBy = "post")
    private List<Comment> comments;

    private Integer commentCount = 0;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @JsonIgnore
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
        this.commentCount = comments.size();
    }

    public Integer getCommentCount() {
        commentCount = comments.size();
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
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

    public AtomicLong getViewCount() {
        return viewCount;
    }

    public void setViewCount(AtomicLong viewCount) {
        this.viewCount = viewCount;
    }

    public User getPublisher() {
        return publisher;
    }

    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
