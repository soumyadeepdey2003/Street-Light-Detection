package soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Model.Street_lightModel;
import soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Repository.Street_lightRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class Street_lightService {

    @Autowired
    private Street_lightRepository street_lightRepository;

    @Async
    public CompletableFuture<List<Street_lightModel>> getAllStreet_lights(){
        List<Street_lightModel> model = street_lightRepository.findAll();
        return CompletableFuture.completedFuture(model);
    }

    @Async
    public CompletableFuture<Street_lightModel>  getStreet_lightById(String id){
        Optional<Street_lightModel> model = street_lightRepository.findByStreetLightCode(id);

        if (model.isPresent()){
            return CompletableFuture.completedFuture(model.get());
        } else
            throw new RuntimeException("Street_light not found");
    }

    @Async
    public CompletableFuture<Street_lightModel> saveStreet_light(Street_lightModel street_lightModel){
        Street_lightModel model = street_lightRepository.save(street_lightModel);
        return CompletableFuture.completedFuture(model);
    }
}
