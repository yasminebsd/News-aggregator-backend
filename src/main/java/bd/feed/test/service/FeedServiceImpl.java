package bd.feed.test.service;

import bd.feed.test.model.Feed;
import bd.feed.test.model.FeedFlux;
import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    FeedFluxService feedFluxService;

    @Autowired
    LevenshteinDistance levenshteinDistance;

    @Override
    public List<Feed> getFeedsByKeyword(String keyword) {

        List<FeedFlux> feedFluxList = feedFluxService.getByType("rss");

        List<Feed> feedList = new ArrayList<>();
        for (int i = 0; i < feedFluxList.size(); i++) {
            try {
                URL feedSource = new URL(feedFluxList.get(i).getUrl_feed());
                SyndFeedInput input = new SyndFeedInput();
                SyndFeed feed = input.build(new XmlReader(feedSource));
                List entries = feed.getEntries();
                Iterator it = entries.iterator();
                while (it.hasNext()) {
                    SyndEntry entry = (SyndEntry) it.next();
                    String[] arr = entry.getTitle().split(" ");

                    for (int j = 0; j < arr.length; j++) {
                        System.out.print(arr[j]);
                        System.out.println(" " + levenshteinDistance.calculatePercentage(arr[j], keyword) + "%");
                        if (levenshteinDistance.calculatePercentage(arr[j], keyword).compareTo(new BigDecimal(50)) > 0) {
                            Feed f = new Feed();
                            f.setTitle(entry.getTitle());
                            System.out.println(entry.getTitle());
                            SyndContent description = entry.getDescription();
                            f.setDescription(description.getValue());
                            System.out.println(description.getValue());

                            Matcher matcher = Pattern.compile("<img src=\"([^\"]+)").matcher(entry.getContents().toString());
                            while (matcher.find()) {
                                System.out.println("img url: " + matcher.group(1));
                                f.setImage_src(matcher.group(1).toString());
                            }

                            if (f.getImage_src() == null) {
                                SyndEnclosure img = entry.getEnclosures().get(0);
                                f.setImage_src(img.getUrl());
                            }
                            f.setLink(entry.getLink());
                            feedList.add(f);
                            System.out.println();
                        }
                    }
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (FeedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                return new ArrayList<>();
            }
        }
        return feedList;
    }

    @Override
    public List<Feed> getFeedsByKeywordJsoup(String searchTerm) throws IOException {

        List<FeedFlux> feedFluxList = feedFluxService.getByType("jsoup");
        List<Feed> feedList = new ArrayList<>();

        for (int i = 0; i < feedFluxList.size(); i++) {
            try {
                org.jsoup.nodes.Document document = Jsoup.connect(feedFluxList.get(i).getUrl_feed()).get();

                Elements blocks = document.getElementsByClass("post-block");

                for (org.jsoup.nodes.Element block : blocks) {
                    org.jsoup.nodes.Element title = block.getElementsByClass("post-block__title__link").first();
                    String[] array = title.text().split(" ");

                    for (int j = 0; j < array.length; j++) {
                        System.out.print(array[j]);
                        System.out.println(" " + levenshteinDistance.calculatePercentage(array[j], searchTerm) + "%");
                        if (levenshteinDistance.calculatePercentage(array[j], searchTerm).compareTo(new BigDecimal(50)) > 0) {
                            Feed f = new Feed();
                            f.setTitle(title.text());
                            f.setDescription(block.getElementsByClass("post-block__content").first().text());
                            f.setLink(title.attr("href"));
                            org.jsoup.nodes.Element figure = block.getElementsByClass("post-block__media").first();
                            Element a = figure.getElementsByTag("img").first();
                            f.setImage_src(a.attr("src"));
                            feedList.add(f);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        return feedList;
    }

    @Override
    public List<Feed> twitterExtract(String searchTerm) throws TwitterException {

        List<Feed> feedList = new ArrayList<>();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("GEsWEKBYZVYjAeTDoca4RX6WZ")
                .setOAuthConsumerSecret("JFcvaRZ13oIDTVLSMrO9S0P0A8KoheK1xJevfWq8YSQsUK77UE")
                .setOAuthAccessToken("1017786110079094784-PbnnRpeWLPmLBnmk2nTyfJr5Cj7rzj")
                .setOAuthAccessTokenSecret("iFlIkw6LBrT0rrTIJpu7DG339bmi4uXiM7wdEWZHI4D7U");

        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter4j.Twitter twitter = tf.getInstance();

        /*List<Status> status = twitter.getHomeTimeline();*/

        Query query = new Query("#"+searchTerm);
        QueryResult result = twitter.search(query);

        /*for (Status st : status) */
        for(Status status: result.getTweets()){
                String language = status.getLang();

                if(language.compareTo("en") == 0){
                    Feed f = new Feed();
                    f.setTitle(status.getText());
                    f.setDescription(status.getLang());
                    f.setImage_src("http://www.imt-atlantique.fr/lexians/wp-content/uploads/2016/05/twitter.jpg");
                    f.setLink("https://twitter.com/" + status.getUser().getScreenName()
                            + "/status/" + status.getId());
                    System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                    feedList.add(f);

            /*String[] tab = st.getText().split(" ");

            for (int j = 0; j < tab.length; j++) {
                System.out.print(tab[j]);
                System.out.println(" " + levenshteinDistance.calculatePercentage(tab[j], searchTerm) + "%");
                if (levenshteinDistance.calculatePercentage(tab[j], searchTerm).compareTo(new BigDecimal(50)) > 0) {
                    Feed f = new Feed();
                    f.setTitle(st.getText());
                    f.setDescription(" ");
                    f.setLink("https://twitter.com/" + st.getUser().getScreenName()
                            + "/status/" + st.getId());
                    feedList.add(f);
                    System.out.println(st.getUser().getName() + "---------" + st.getText());
                    String url = "https://twitter.com/" + st.getUser().getScreenName()
                            + "/status/" + st.getId();
                    System.out.println(url);
                }
            }*/
                }
        }
        return feedList;
    }
}
