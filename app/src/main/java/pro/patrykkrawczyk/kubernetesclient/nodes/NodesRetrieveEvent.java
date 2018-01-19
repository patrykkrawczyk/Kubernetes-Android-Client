package pro.patrykkrawczyk.kubernetesclient.nodes;

import io.fabric8.kubernetes.api.model.Node;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NodesRetrieveEvent {

    private List<Node> data;
}
