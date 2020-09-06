package homework.db.backend.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Topic {

    @Id
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String topic) {
        this.title = topic;
    }


}
