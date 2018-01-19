package pro.patrykkrawczyk.kubernetesclient.nodes;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import pro.patrykkrawczyk.kubernetesclient.ManagedActivity;
import pro.patrykkrawczyk.kubernetesclient.NavigationButton;
import pro.patrykkrawczyk.kubernetesclient.R;
import pro.patrykkrawczyk.kubernetesclient.client.Kubernetes;
import pro.patrykkrawczyk.kubernetesclient.namespaces.NamespacesActivity;
import pro.patrykkrawczyk.kubernetesclient.pods.PodsActivity;
import pro.patrykkrawczyk.kubernetesclient.services.ServicesActivity;

public class NodesActivity extends ManagedActivity<NodesAdapter> {

    public NodesActivity() {
        adapter = new NodesAdapter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(NodesRetrieveEvent event) {
        adapter.setDataSet(event.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initializeAdapterData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Kubernetes.getInstance().getNodes();
            }
        }).start();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_nodes;
    }

    @Override
    protected NavigationButton[] getNavigationButtons() {
        NavigationButton[] buttons = new NavigationButton[3];

        buttons[0] = new NavigationButton("Pods", PodsActivity.class);
        buttons[1] = new NavigationButton("Services", ServicesActivity.class);
        buttons[2] = new NavigationButton("Namespaces", NamespacesActivity.class);

        return buttons;
    }
}
