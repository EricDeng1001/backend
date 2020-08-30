package homework.db.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Topic topic;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime dateTime = LocalDateTime.now();

    @ManyToOne
    private User publisher;

    private AtomicLong viewCount = new AtomicLong(0);

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE}, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "post")
    private List<Vote> votes = new ArrayList<>();

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topics) {
        this.topic = topics;
    }

    @JsonIgnore
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getCommentCount() {
        return comments.size();
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

    public long getVotesCount() {
        return votes.size();
    }

    public long getRank() {
        return viewCount.get() / 2 + getCommentCount() * 2 + comments.stream().map(Comment::getVoteCount)
            .reduce(Long::sum).orElse(0L) + getVotesCount() * 2;
    }

}
