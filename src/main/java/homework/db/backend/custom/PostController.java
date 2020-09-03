package homework.db.backend.custom;

import homework.db.backend.domain.Post;
import homework.db.backend.domain.PostRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@RequestMapping("posts")
@RepositoryRestController
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {this.postRepository = postRepository;}

    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<?> viewPost(@PathVariable Long id) {
        final Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            byId.get().getViewCount().incrementAndGet();
            byId.get().getRank();
            postRepository.save(byId.get());
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }

}
