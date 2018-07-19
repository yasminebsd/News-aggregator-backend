package bd.feed.test.controller;

import bd.feed.test.model.FeedFlux;
import bd.feed.test.service.FeedFluxService;
import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.access.prepost.PreAuthorize;*/
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/FeedSites")
@CrossOrigin(origins = "http://localhost:4200")
public class FeedFluxController {

    @Autowired
    FeedFluxService feedFluxService;

    @GetMapping
    public List<FeedFlux> getAllSites(){
        return feedFluxService.getAll();
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public void addSite(@RequestBody FeedFlux feedFlux){
        feedFluxService.addFeedFlux(feedFlux);
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/{id}")
    public void updateSite(@PathVariable int id, @RequestBody FeedFlux feedFlux){
        feedFluxService.updateFeedFlux(feedFlux);
    }

    @GetMapping("/{id}")
    public Optional<FeedFlux> getSiteById(@PathVariable int id){
        return feedFluxService.getFeedFluxByid(id);
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteSite(@PathVariable int id){
        feedFluxService.deleteFeedFlux(id);
    }


}
