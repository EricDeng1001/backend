package homework.db.backend.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comment")
    private List<Vote> votes;

    @ManyToOne
    private Post post;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
