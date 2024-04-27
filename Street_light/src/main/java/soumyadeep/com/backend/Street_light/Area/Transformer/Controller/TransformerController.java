package soumyadeep.com.backend.Street_light.Area.Transformer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumyadeep.com.backend.Street_light.Area.Transformer.Service.TransformerService;
import soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Model.Street_lightModel;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/gov/original/area/transformer")
public class TransformerController {

    @Autowired
    private TransformerService transformerService;

    @Async
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<?>> getTransformerById(@PathVariable("id") String id){
        return transformerService.getTransformerById(id).thenApply(success->{
            if(success != null)
                return ResponseEntity.ok(success);
            else
                return ResponseEntity.notFound().build();
        }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }


    @Async
    @PutMapping("/update/{id}")
    public CompletableFuture<ResponseEntity<?>> updateTransformer(
            @PathVariable("id") String id,
            @RequestBody Street_lightModel Model
    ) {
        return CompletableFuture.completedFuture(transformerService.UpdateTransformer(Model,id))
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }


}
