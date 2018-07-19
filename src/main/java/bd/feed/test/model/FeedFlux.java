package bd.feed.test.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "feedosites")
@Getter
@Setter
public class FeedFlux {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_site;

    @NotBlank
    private String nom_site;

    @NotBlank
    private String url_feed;

    @NotBlank
    private String type;

    /*@ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "feedFluxSet")
    private Set<Subscriber> abonnéSet = new HashSet<>();*/
}