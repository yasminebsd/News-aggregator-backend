package bd.feed.test.controller;

import bd.feed.test.model.Feed;
import bd.feed.test.model.FeedFlux;
import bd.feed.test.model.Subscriber;
import bd.feed.test.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:4200")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @PostMapping
    public List<Feed> feedRead(@RequestBody String filtre){
        System.out.println(filtre);
        return feedService.getFeedsByKeyword(filtre);  }

    @PostMapping("/jsoup")
    public List<Feed>  test(@RequestBody String searchTerm) throws IOException {
        return feedService.getFeedsByKeywordJsoup(searchTerm);
    }

    @PostMapping("/twitter")
    public  List<Feed> twitterDataExtraction(@RequestBody String searchTerm) throws TwitterException {
        return feedService.twitterExtract(searchTerm);
    }
}
