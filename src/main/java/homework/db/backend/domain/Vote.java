package homework.db.backend.domain;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private User user;

    @ManyToOne
    private Comment comment;

    @ManyToOne
    private Post post;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
