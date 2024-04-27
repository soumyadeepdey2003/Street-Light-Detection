package soumyadeep.com.backend.Street_light.Area.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import soumyadeep.com.backend.Street_light.Area.Model.AreaModel;
import soumyadeep.com.backend.Street_light.Area.Repository.AreaRepository;
import soumyadeep.com.backend.Street_light.Area.Transformer.Model.TransformerModel;
import soumyadeep.com.backend.Street_light.Area.Transformer.Service.TransformerService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private TransformerService transformer;

    @Async
    public CompletableFuture<List<AreaModel>> getAllArea(){
        List<AreaModel> areas = areaRepository.findAll();
        if (areas.isEmpty()){
            throw new RuntimeException("No Area Found");
        }
        return CompletableFuture.completedFuture(areas);
    }

    @Async
    public CompletableFuture<AreaModel> getAreaById(String id){
        Optional<AreaModel> area = areaRepository.findByAreaCode(id);
        if (area.isEmpty()){
            throw new RuntimeException("No Area Found");
        }
        return CompletableFuture.completedFuture(area.get());
    }

    @Async
    public CompletableFuture<AreaModel> saveArea(AreaModel area){
        AreaModel areaModel = areaRepository.save(area);
        return CompletableFuture.completedFuture(areaModel);
    }

    @Async
    public CompletableFuture<AreaModel> updateArea(TransformerModel model, String id){
        Optional<AreaModel> areaModel = areaRepository.findByAreaCode(id);
        if (areaModel.isEmpty()){
            throw new RuntimeException("No Area Found");
        }
        List<TransformerModel> t = areaModel.get().getTransformerModelDetails();
        CompletableFuture<TransformerModel> m = transformer.saveTransformer(model);
        t.add(m.join());
        areaModel.get().setTransformerModelDetails(t);
        return CompletableFuture.completedFuture( areaRepository.save(areaModel.get()));
    }
}
