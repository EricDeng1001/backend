package homework.db.backend.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {

    @Id
    private String title;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "topic")
    private List<Post> posts = new ArrayList<>();

    @OneToOne
    private User owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String topic) {
        this.title = topic;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
