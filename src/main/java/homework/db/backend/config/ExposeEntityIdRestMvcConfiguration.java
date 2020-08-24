package homework.db.backend.config;

import homework.db.backend.domain.Authority;
import homework.db.backend.domain.Comment;
import homework.db.backend.domain.Post;
import homework.db.backend.domain.User;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

@Component
public class ExposeEntityIdRestMvcConfiguration extends RepositoryRestConfigurerAdapter {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(User.class, Post.class, Authority.class, Comment.class);
  }
}