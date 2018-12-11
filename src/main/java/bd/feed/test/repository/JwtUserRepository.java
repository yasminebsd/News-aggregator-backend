package bd.feed.test.repository;

import bd.feed.test.model.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtUserRepository extends JpaRepository<JwtUser, Integer> {
    JwtUser getJwtUserByUsername(String username);
}
