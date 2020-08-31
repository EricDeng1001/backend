package homework.db.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import homework.db.backend.config.BCryptPasswordDeserializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User {

    @Id
    private String username;

    @JsonDeserialize(using = BCryptPasswordDeserializer.class)
    private String password;

    private Boolean enabled = true;

    @OneToMany(mappedBy = "publisher", cascade = {CascadeType.REMOVE})
    private List<Post> posts = new ArrayList<>();

    @ManyToMany
    private List<Authority> authorities = new ArrayList<>();

    public User(String username, String password, List<Authority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        User user = (User) o;
        return Objects.equals(username, user.username) &&
            Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

}
