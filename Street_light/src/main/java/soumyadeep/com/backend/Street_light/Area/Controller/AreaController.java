package soumyadeep.com.backend.Street_light.Area.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import soumyadeep.com.backend.Street_light.Area.Service.AreaService;
import soumyadeep.com.backend.Street_light.Area.Transformer.Model.TransformerModel;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/gov/original/area")
public class AreaController {

    @Autowired
    private AreaService areaService;

    @Async
    @GetMapping("/all")
    public CompletableFuture<ResponseEntity<?>> AreaDetails(){

        return CompletableFuture.completedFuture(areaService.getAllArea()).thenApply(success->{
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }

    @Async
    @PutMapping("/update/{id}")
    public CompletableFuture<ResponseEntity<?>> updateArea(
            @PathVariable("id") String id,
            @RequestBody TransformerModel transformer
    ) {
        return CompletableFuture.completedFuture(areaService.updateArea(transformer,id))
                .thenApply(success -> {
                    if(success != null)
                        return ResponseEntity.ok(success);
                    else
                        return ResponseEntity.notFound().build();
                }).exceptionally(ex -> ResponseEntity.badRequest().body(ex.getMessage()));
    }
}
