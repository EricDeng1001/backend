package homework.db.backend.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;

public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
