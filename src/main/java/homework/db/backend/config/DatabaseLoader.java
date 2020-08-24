package homework.db.backend.config;

import homework.db.backend.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseLoader implements CommandLineRunner {
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final AuthorityRepository authorityRepository;

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    @Autowired
    public DatabaseLoader(AuthorityRepository authorityRepository,
                          UserRepository userRepository, PostRepository postRepository) {
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        final User user = new User("admin", passwordEncoder.encode("admin"), List.of(Authority.ADMIN));
        final Post post = new Post();
        post.setContent("北邮是个傻逼学校");
        post.setPublisher(user);
		authorityRepository.save(Authority.USER);
		authorityRepository.save(Authority.ADMIN);
		userRepository.save(user);
		postRepository.save(post);
    }

}