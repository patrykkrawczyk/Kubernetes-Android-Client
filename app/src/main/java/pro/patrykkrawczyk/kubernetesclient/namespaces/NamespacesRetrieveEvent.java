package pro.patrykkrawczyk.kubernetesclient.namespaces;

import io.fabric8.kubernetes.api.model.Namespace;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NamespacesRetrieveEvent {

    private List<Namespace> data;
}
