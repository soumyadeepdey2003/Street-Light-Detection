package soumyadeep.com.backend.Street_light.Area.Transformer.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Model.Street_lightModel;

import java.util.List;

@Entity
@Table(name = "transformer_details")
@Getter
@Setter
@NoArgsConstructor
public class TransformerModel {

    public TransformerModel(String transformerName,String transformerCode, List<Street_lightModel> street_lightModelDetails) {
        this.transformerName = transformerName;
        this.transformerCode = transformerCode;
        this.street_lightModelDetails = street_lightModelDetails;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String transformerName;
    private String transformerCode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Street_lightModel> street_lightModelDetails;
}
