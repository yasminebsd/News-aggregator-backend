package bd.feed.test.controller;

import bd.feed.test.model.Subscriber;
import bd.feed.test.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.access.prepost.PreAuthorize;*/
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Subscribers")
@CrossOrigin(origins = "http://localhost:4200")
public class SubscriberController {

    @Autowired
    SubscriberService subscriberService;

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping
    public List<Subscriber> getAll(){
        return subscriberService.getAll();
    }

    @PostMapping
    public void addSubscriber(@RequestBody Subscriber subscriber){
        subscriberService.addSubscriber(subscriber);
    }

    @PutMapping("/{id}")
    public void updateSubscriber(@PathVariable int id, @RequestBody Subscriber subscriber){
        subscriberService.updateSubscriber(subscriber);
    }

    @GetMapping("/{id}")
    public Optional<Subscriber> getSubscriberById(@PathVariable int id){
        return subscriberService.getSubscriberByid(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubscriber(@PathVariable int id){
        subscriberService.deleteSubscriber(id);
    }
}
