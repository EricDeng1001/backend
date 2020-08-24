package homework.db.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    @RestResource(path = "post", rel = "post")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Post post;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
