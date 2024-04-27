package soumyadeep.com.backend.Street_light.Area.Transformer.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumyadeep.com.backend.Street_light.Area.Transformer.Model.TransformerModel;
import soumyadeep.com.backend.Street_light.Area.Transformer.Repository.TransformerRepository;
import soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Model.Street_lightModel;
import soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Service.Street_lightService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class TransformerService {

    @Autowired
    private TransformerRepository transformerRepository;

    @Autowired
    private Street_lightService streetLight;

    @Async
    public CompletableFuture<TransformerModel> getTransformerById(String id){
        Optional<TransformerModel> model = transformerRepository.findByTransformerCode(id);
        if (model.isPresent()){
            return CompletableFuture.completedFuture(model.get());
        } else
            throw new RuntimeException("Transformer not found");
    }

    @Async
    public CompletableFuture<TransformerModel> saveTransformer(TransformerModel transformerModel){
        TransformerModel model = transformerRepository.save(transformerModel);
        return CompletableFuture.completedFuture(model);
    }


    @Async
    public CompletableFuture<TransformerModel> UpdateTransformer(Street_lightModel streetLightModel, String id){
        Optional<TransformerModel> model = transformerRepository.findByTransformerCode(id);
        if (model.isEmpty()){
            throw new RuntimeException("Transformer not found");
            }
        CompletableFuture<Street_lightModel> m = streetLight.saveStreet_light(streetLightModel);
        List<Street_lightModel> t = model.get().getStreet_lightModelDetails();
        t.add(m.join());
        model.get().setStreet_lightModelDetails(t);
        return CompletableFuture.completedFuture(transformerRepository.save(model.get()));
    }

}
