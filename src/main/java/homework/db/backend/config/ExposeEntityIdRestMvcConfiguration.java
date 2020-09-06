package homework.db.backend.config;

import homework.db.backend.domain.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ExposeEntityIdRestMvcConfiguration implements RepositoryRestConfigurer {

  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
    config.exposeIdsFor(User.class, Post.class, Authority.class, Comment.class, Topic.class);
  }

}
