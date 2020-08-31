package homework.db.backend.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private LocalDate dateTime = LocalDate.now();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comment")
    private List<Vote> votes = new ArrayList<>();

    @ManyToOne
    private Post post;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public long getVoteCount() {
        return votes.size();
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
