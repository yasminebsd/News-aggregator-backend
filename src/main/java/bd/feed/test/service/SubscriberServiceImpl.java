package bd.feed.test.service;

import bd.feed.test.model.FeedFlux;
import bd.feed.test.model.Subscriber;
import bd.feed.test.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriberServiceImpl implements SubscriberService {

    @Autowired
    SubscriberRepository subscriberRepository;

    @Override
    public List<Subscriber> getAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    public void deleteSubscriber(int id) {
        subscriberRepository.deleteById(id);
    }

    @Override
    public Optional<Subscriber> getSubscriberByid(int id) {
        return subscriberRepository.findById(id);
    }

    @Override
    public void updateSubscriber(Subscriber subscriber) {
        subscriberRepository.save(subscriber);
    }

    @Override
    public Optional<Subscriber> findSubscriberByEmail(String email) {
        return subscriberRepository.findByEmail(email);
    }
}
