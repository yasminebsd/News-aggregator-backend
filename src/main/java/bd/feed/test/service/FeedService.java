package bd.feed.test.service;

import bd.feed.test.model.Feed;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.util.List;

public interface FeedService {

    public List<Feed> getFeedsByKeyword(String keyword);
    public List<Feed> getFeedsByKeywordJsoup(String searchTerm) throws IOException;
    public List<Feed> twitterExtract(String searchTerm) throws TwitterException;
}
