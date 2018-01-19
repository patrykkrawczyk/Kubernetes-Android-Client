package pro.patrykkrawczyk.kubernetesclient.namespaces;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import pro.patrykkrawczyk.kubernetesclient.ManagedActivity;
import pro.patrykkrawczyk.kubernetesclient.NavigationButton;
import pro.patrykkrawczyk.kubernetesclient.R;
import pro.patrykkrawczyk.kubernetesclient.client.Kubernetes;
import pro.patrykkrawczyk.kubernetesclient.nodes.NodesActivity;
import pro.patrykkrawczyk.kubernetesclient.pods.PodsActivity;
import pro.patrykkrawczyk.kubernetesclient.services.ServicesActivity;

public final class NamespacesActivity extends ManagedActivity<NamespacesAdapter> {

    public NamespacesActivity() {
        adapter = new NamespacesAdapter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NamespacesRetrieveEvent event) {
        adapter.setDataSet(event.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initializeAdapterData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Kubernetes.getInstance().getNamespaces();
            }
        }).start();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_namespaces;
    }

    @Override
    protected NavigationButton[] getNavigationButtons() {
        NavigationButton[] buttons = new NavigationButton[3];

        buttons[0] = new NavigationButton("Nodes", NodesActivity.class);
        buttons[1] = new NavigationButton("Pods", PodsActivity.class);
        buttons[2] = new NavigationButton("Services", ServicesActivity.class);

        return buttons;
    }
}
