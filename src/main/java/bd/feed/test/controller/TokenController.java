package bd.feed.test.controller;

import bd.feed.test.Security.JwtGenerator;
import bd.feed.test.model.JwtUser;
import bd.feed.test.repository.JwtUserRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class TokenController {

    private JwtGenerator jwtGenerator;
    private JwtUserRepository jwtUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public TokenController(JwtGenerator jwtGenerator,
                           JwtUserRepository jwtUserRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtGenerator = jwtGenerator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtUserRepository = jwtUserRepository;
    }

    @PostMapping("/token")
    public String generate(@RequestBody final JwtUser jwtUser){
        return jwtGenerator.generate(jwtUser);
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody JwtUser jwtUser) {
        jwtUser.setPassword(bCryptPasswordEncoder.encode(jwtUser.getPassword()));
        jwtUserRepository.save(jwtUser);
    }

    @GetMapping
    public List<JwtUser> getList(){
        return jwtUserRepository.findAll();
    }
}
