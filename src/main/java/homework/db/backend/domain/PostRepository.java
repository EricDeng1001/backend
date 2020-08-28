package homework.db.backend.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}
