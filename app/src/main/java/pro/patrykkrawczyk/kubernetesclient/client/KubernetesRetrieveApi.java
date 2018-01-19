package pro.patrykkrawczyk.kubernetesclient.client;

import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.ServiceList;

public interface KubernetesRetrieveApi {

    PodList getPods();

    NamespaceList getNamespaces();

    ServiceList getServices();

    NodeList getNodes();

}
