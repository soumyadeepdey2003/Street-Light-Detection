package soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Service.Street_lightService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/gov/original/area/transformer/street-light")
public class Street_lightController {

    @Autowired
    private Street_lightService street_lightService;

    @Async
    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<?>> Street_lightDetails(){
        return street_lightService.getAllStreet_lights().thenApply(success->{
            if(success != null)
                return ResponseEntity.ok(success);
            else
                return ResponseEntity.notFound().build();
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<?>> getStreet_lightById(String id){
        return street_lightService.getStreet_lightById(id).thenApply(success->{
            if(success != null)
                return ResponseEntity.ok(success);
            else
                return ResponseEntity.notFound().build();
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }


}
