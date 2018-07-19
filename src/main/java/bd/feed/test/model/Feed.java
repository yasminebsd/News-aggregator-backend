package bd.feed.test.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Feed {

    @Id
    int id;
    String title;
    String description;
    String link;
    String image_src;

    @Override
    public String toString() {
        return "FeedMessage [title=" + title + ", description=" + description
                + ", link=" + link + "]";
    }
}
