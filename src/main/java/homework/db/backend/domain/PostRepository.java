package homework.db.backend.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

    @RestResource(path = "topic")
    Page<Post> findAllByTopic(@Param("topic") Topic topic, Pageable pageable);
}
