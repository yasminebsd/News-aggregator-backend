package bd.feed.test.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user")
public class JwtUser {

    @Id
    private int id;
    private String username;
    private String password;
}
