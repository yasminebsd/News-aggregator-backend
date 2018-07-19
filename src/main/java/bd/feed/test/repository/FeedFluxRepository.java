package bd.feed.test.repository;

import bd.feed.test.model.Feed;
import bd.feed.test.model.FeedFlux;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface FeedFluxRepository extends CrudRepository<FeedFlux, Integer> {

    List<FeedFlux> findFeedFluxByType(String type);
}
