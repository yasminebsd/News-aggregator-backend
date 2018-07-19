package bd.feed.test.service;

import bd.feed.test.model.FeedFlux;
import bd.feed.test.model.Subscriber;

import java.util.List;
import java.util.Optional;


public interface FeedFluxService {

    public List<FeedFlux> getAll();
    public void addFeedFlux(FeedFlux feedFlux);
    public void deleteFeedFlux(int id);
    public Optional<FeedFlux> getFeedFluxByid(int id);
    public void updateFeedFlux(FeedFlux feedFlux);
    public List<FeedFlux> getByType(String type);

}
