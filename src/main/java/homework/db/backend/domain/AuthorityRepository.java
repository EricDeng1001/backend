package homework.db.backend.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@PreAuthorize("hasRole('admin')")
public interface AuthorityRepository extends CrudRepository<Authority, String> {
}
