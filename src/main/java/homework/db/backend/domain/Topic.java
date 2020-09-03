package homework.db.backend.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Topic {

    @Id
    private String title;

    @OneToOne
    private User owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String topic) {
        this.title = topic;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

}
