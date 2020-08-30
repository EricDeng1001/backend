package homework.db.backend.config;

import homework.db.backend.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final AuthorityRepository authorityRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final TopicRepository topicRepository;

    @Autowired
    public DatabaseLoader(AuthorityRepository authorityRepository,
                          UserRepository userRepository, PostRepository postRepository,
                          TopicRepository topicRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public void run(String... strings) {
        final User user = new User("admin", passwordEncoder.encode("admin"), List.of(Authority.ADMIN));
        authorityRepository.save(Authority.USER);
        authorityRepository.save(Authority.ADMIN);
        userRepository.save(user);
        final Topic s = new Topic();
        s.setTitle("topic");
        s.setOwner(user);
        List<Post> posts = new ArrayList<>();
        topicRepository.save(s);
        for (int i = 0; i < 3; i++) {
            final Post post = new Post();
            post.setTitle("一篇好帖子");
            post.setContent("北邮是个傻逼学校");
            post.setPublisher(user);
            post.setTopic(s);
            Vote vote = new Vote();
            vote.setUser(user);
            post.setVotes(List.of(vote));
            postRepository.save(post);
            posts.add(post);
        }
        s.setPosts(posts);
        topicRepository.save(s);
    }

}