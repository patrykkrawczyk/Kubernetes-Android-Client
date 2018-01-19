package pro.patrykkrawczyk.kubernetesclient.services;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import pro.patrykkrawczyk.kubernetesclient.ManagedActivity;
import pro.patrykkrawczyk.kubernetesclient.NavigationButton;
import pro.patrykkrawczyk.kubernetesclient.R;
import pro.patrykkrawczyk.kubernetesclient.client.Kubernetes;
import pro.patrykkrawczyk.kubernetesclient.namespaces.NamespacesActivity;
import pro.patrykkrawczyk.kubernetesclient.nodes.NodesActivity;
import pro.patrykkrawczyk.kubernetesclient.pods.PodsActivity;

public final class ServicesActivity extends ManagedActivity<ServicesAdapter> {

    public ServicesActivity() {
        adapter = new ServicesAdapter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ServicesRetrieveEvent event) {
        adapter.setDataSet(event.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void initializeAdapterData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Kubernetes.getInstance().getServices();
            }
        }).start();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_services;
    }

    @Override
    protected NavigationButton[] getNavigationButtons() {
        NavigationButton[] buttons = new NavigationButton[3];

        buttons[0] = new NavigationButton("Nodes", NodesActivity.class);
        buttons[1] = new NavigationButton("Pods", PodsActivity.class);
        buttons[2] = new NavigationButton("Namespaces", NamespacesActivity.class);

        return buttons;
    }
}
