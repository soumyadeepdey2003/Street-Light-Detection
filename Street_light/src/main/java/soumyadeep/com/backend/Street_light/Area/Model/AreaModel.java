package soumyadeep.com.backend.Street_light.Area.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soumyadeep.com.backend.Street_light.Area.Transformer.Model.TransformerModel;

import java.util.List;

@Entity
@Table(name = "area_details")
@Getter
@Setter
@NoArgsConstructor
public class AreaModel {

    public AreaModel(String areaName, List<TransformerModel> transformerModelDetails, String areaCode) {
        this.areaName = areaName;
        this.areaCode = areaCode;
        this.transformerModelDetails = transformerModelDetails;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String areaName;
    private String areaCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransformerModel> transformerModelDetails;
}
