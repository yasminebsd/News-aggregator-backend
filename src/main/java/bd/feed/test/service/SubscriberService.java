package bd.feed.test.service;


import bd.feed.test.model.FeedFlux;
import bd.feed.test.model.Subscriber;

import java.util.List;
import java.util.Optional;

public interface SubscriberService {

    public List<Subscriber> getAll();
    public void addSubscriber(Subscriber subscriber);
    public void deleteSubscriber(int id);
    public Optional<Subscriber> getSubscriberByid(int id);
    public void updateSubscriber(Subscriber subscriber);
    public Optional<Subscriber> findSubscriberByEmail(String email);

}
