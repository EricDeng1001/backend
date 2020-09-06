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
        Topic s = new Topic();
        s.setTitle("求职");
        s.setOwner(user);
        List<Post> posts = new ArrayList<>();
        topicRepository.save(s);
        for (int i = 0; i < 3; i++) {
            final Post post = new Post();
            post.setTitle("一篇好帖子" + i);
            post.setMarkdown("北邮是个好学校" + i);
            post.setContent("北邮是个好学校" + i);
            post.setPublisher(user);
            post.setTopic(s);
            Vote vote = new Vote();
            vote.setUser(user);
            post.setVotes(List.of(vote));
            postRepository.save(post);
            posts.add(post);
        }
        topicRepository.save(s);
        s = new Topic();
        s.setTitle("校园生活");
        topicRepository.save(s);
        s = new Topic();
        s.setTitle("情感天地");
        topicRepository.save(s);
    }

}
