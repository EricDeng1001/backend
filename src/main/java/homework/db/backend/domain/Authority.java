package homework.db.backend.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Authority {

    public static final Authority USER = new Authority("user");

    public static final Authority ADMIN = new Authority("admin");

    private String authority;

    public Authority() {
    }

    public Authority(String authority) {
        this.authority = authority;
    }

    @Id
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

}
