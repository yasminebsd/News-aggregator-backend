package bd.feed.test.repository;

import bd.feed.test.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
public interface SubscriberRepository extends JpaRepository<Subscriber,Integer> {

    Optional<Subscriber> findByEmail(String email);
    Optional<Subscriber> findByNom(String nom);
}
