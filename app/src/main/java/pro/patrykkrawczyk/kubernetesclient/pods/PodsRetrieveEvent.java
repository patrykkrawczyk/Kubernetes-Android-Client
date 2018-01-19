package pro.patrykkrawczyk.kubernetesclient.pods;

import io.fabric8.kubernetes.api.model.Pod;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PodsRetrieveEvent {

    private List<Pod> data;
}
