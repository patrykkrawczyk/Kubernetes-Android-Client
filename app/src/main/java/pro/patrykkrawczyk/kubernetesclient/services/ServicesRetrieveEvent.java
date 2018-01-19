package pro.patrykkrawczyk.kubernetesclient.services;

import io.fabric8.kubernetes.api.model.Service;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServicesRetrieveEvent {

    private List<Service> data;
}
