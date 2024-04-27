package soumyadeep.com.backend.Street_light.Area.Transformer.Street_light.Model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "street_light_details")
@Getter
@Setter
@NoArgsConstructor
public class Street_lightModel {

    public Street_lightModel(String streetLight_Name,String streetLightCode, Boolean street_light_status, String power_consumption, String electricity_consumption, String voltage) {
        this.streetLight_Name = streetLight_Name;
        this.streetLightCode = streetLightCode;
        this.street_light_status = street_light_status;
        this.power_consumption = power_consumption;
        Electricity_consumption = electricity_consumption;
        this.voltage = voltage;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String streetLight_Name;
    private String streetLightCode;
    private Boolean street_light_status;
    private String power_consumption;
    private String Electricity_consumption;
    private String voltage;

}
