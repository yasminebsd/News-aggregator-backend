package bd.feed.test.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "feedosubscriber")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private int active;

    @NotBlank @NotNull
    private String nom;

    @NotBlank @NotNull
    private String prenom;

    @NotBlank @NotNull
    @Email
    private String email;

    @NotNull
    @DateTimeFormat
    private java.sql.Date dateDeNaissance;

    @NotNull @NotBlank
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "abonne_role", joinColumns = @JoinColumn(name = "abonne_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public Subscriber() {
    }

    public Subscriber(Subscriber subscriber) {
       this.active = subscriber.getActive();
       this.dateDeNaissance= subscriber.getDateDeNaissance();
       this.email = subscriber.getEmail();
       this.nom = subscriber.getNom();
       this.prenom = subscriber.getPrenom();
       this.password= subscriber.getPassword();
       this.roles = subscriber.getRoles();
       this.id = subscriber.getId();
    }

    /*@ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "abonne_site",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "id_site")})
   private Set<FeedFlux> feedFluxSet = new HashSet<>();*/
}
