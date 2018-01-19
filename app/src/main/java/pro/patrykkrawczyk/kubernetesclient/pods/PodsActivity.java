package pro.patrykkrawczyk.kubernetesclient.pods;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import pro.patrykkrawczyk.kubernetesclient.ManagedActivity;
import pro.patrykkrawczyk.kubernetesclient.NavigationButton;
import pro.patrykkrawczyk.kubernetesclient.R;
import pro.patrykkrawczyk.kubernetesclient.client.Kubernetes;
import pro.patrykkrawczyk.kubernetesclient.namespaces.NamespacesActivity;
import pro.patrykkrawczyk.kubernetesclient.nodes.NodesActivity;
import pro.patrykkrawczyk.kubernetesclient.services.ServicesActivity;

public final class PodsActivity extends ManagedActivity<PodsAdapter> {

    public PodsActivity() {
        adapter = new PodsAdapter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(PodsRetrieveEvent event) {
        adapter.setDataSet(event.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initializeAdapterData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Kubernetes.getInstance().getPods();
            }
        }).start();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_pods;
    }

    @Override
    protected NavigationButton[] getNavigationButtons() {
        NavigationButton[] buttons = new NavigationButton[3];

        buttons[0] = new NavigationButton("Nodes", NodesActivity.class);
        buttons[1] = new NavigationButton("Services", ServicesActivity.class);
        buttons[2] = new NavigationButton("Namespaces", NamespacesActivity.class);

        return buttons;
    }
}

