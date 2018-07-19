package bd.feed.test.service;

import bd.feed.test.model.FeedFlux;
import bd.feed.test.model.Subscriber;
import bd.feed.test.repository.FeedFluxRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeedFluxServiceImpl implements FeedFluxService {

    private FeedFluxRepository feedFluxRepository;

    public FeedFluxServiceImpl(FeedFluxRepository feedFluxRepository) {
        this.feedFluxRepository = feedFluxRepository;
    }

    @Override
    public List<FeedFlux> getAll() {

      List<FeedFlux> feedFluxList = new ArrayList<>();
       feedFluxRepository.findAll().forEach(feedFluxList::add);
       return feedFluxList;
    }

    @Override
    public void addFeedFlux(FeedFlux feedFlux) {
        feedFluxRepository.save(feedFlux);
    }

    @Override
    public void deleteFeedFlux(int id) {
        feedFluxRepository.deleteById(id);
    }

    @Override
    public Optional<FeedFlux> getFeedFluxByid(int id) {
        return feedFluxRepository.findById(id);
    }

    @Override
    public void updateFeedFlux(FeedFlux feedFlux) {
        feedFluxRepository.save(feedFlux);
    }

    @Override
    public List<FeedFlux> getByType(String type){
        return feedFluxRepository.findFeedFluxByType(type);
    }

}
