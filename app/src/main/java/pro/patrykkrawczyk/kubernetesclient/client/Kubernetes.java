package pro.patrykkrawczyk.kubernetesclient.client;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceList;
import io.fabric8.kubernetes.client.AutoAdaptableKubernetesClient;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.KubernetesClient;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import pro.patrykkrawczyk.kubernetesclient.namespaces.NamespacesRetrieveEvent;
import pro.patrykkrawczyk.kubernetesclient.nodes.NodesRetrieveEvent;
import pro.patrykkrawczyk.kubernetesclient.pods.PodsRetrieveEvent;
import pro.patrykkrawczyk.kubernetesclient.services.ServicesRetrieveEvent;

public final class Kubernetes implements KubernetesRetrieveApi {

    private static final String HOST = "https://35.190.220.34";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "VucH3ecEYytZ11T9";
    private final KubernetesClient client;

    private Kubernetes() {
        final Config config = new ConfigBuilder()
            .withMasterUrl(HOST)
            .withTrustCerts(true)
            .withUsername(USERNAME)
            .withPassword(PASSWORD)
            .build();

        this.client = new AutoAdaptableKubernetesClient(config);
    }

    public static Kubernetes getInstance() {
        return LazyHolder.INSTANCE;
    }

    @Override
    public PodList getPods() {
        PodList pods = client.pods().list();

        List<Pod> data = pods.getItems();
        PodsRetrieveEvent event = new PodsRetrieveEvent(data);
        EventBus.getDefault().post(event);

        return pods;
    }

    @Override
    public NamespaceList getNamespaces() {
        NamespaceList namespaces = client.namespaces().list();

        List<Namespace> data = namespaces.getItems();
        NamespacesRetrieveEvent event = new NamespacesRetrieveEvent(data);
        EventBus.getDefault().post(event);

        return namespaces;
    }

    @Override
    public ServiceList getServices() {
        ServiceList services = client.services().list();

        List<Service> data = services.getItems();
        ServicesRetrieveEvent event = new ServicesRetrieveEvent(data);
        EventBus.getDefault().post(event);

        return services;
    }

    @Override
    public NodeList getNodes() {
        NodeList nodes = client.nodes().list();

        List<Node> data = nodes.getItems();
        NodesRetrieveEvent event = new NodesRetrieveEvent(data);
        EventBus.getDefault().post(event);

        return nodes;
    }

    private static class LazyHolder {

        static final Kubernetes INSTANCE = new Kubernetes();
    }
}
