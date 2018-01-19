package pro.patrykkrawczyk.kubernetesclient.pods;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.OwnerReference;
import io.fabric8.kubernetes.api.model.Pod;
import java.util.ArrayList;
import java.util.List;
import pro.patrykkrawczyk.kubernetesclient.R;

public final class PodsAdapter extends RecyclerView.Adapter<PodsAdapter.ViewHolder> {

    private List<Pod> dataSet;

    PodsAdapter() {
        this.dataSet = new ArrayList<>();
    }

    void setDataSet(List<Pod> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public PodsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_pod, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pod pod = dataSet.get(position);

        holder.name.setText(pod.getMetadata().getName());
        holder.status.setText(pod.getStatus().getPhase());
        holder.namespace.setText(pod.getMetadata().getNamespace());
        holder.restartPolicy.setText(pod.getSpec().getRestartPolicy());
        holder.hostIp.setText(pod.getStatus().getHostIP());
        holder.podIp.setText(pod.getStatus().getPodIP());
        holder.startTime.setText(pod.getStatus().getStartTime());
        holder.node.setText(pod.getSpec().getNodeName());

        List<Container> containers = pod.getSpec().getContainers();
        if (containers.size() > 0) {
            holder.owner.setText(containers.get(0).getName());
        } else {
            holder.owner.setText("");
        }

        List<OwnerReference> ownerReferences = pod.getMetadata().getOwnerReferences();
        if (ownerReferences.size() > 0) {
            holder.owner.setText(ownerReferences.get(0).getKind());
        } else {
            holder.owner.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.status)
        TextView status;

        @BindView(R.id.namespace)
        TextView namespace;

        @BindView(R.id.owner)
        TextView owner;

        @BindView(R.id.restartPolicy)
        TextView restartPolicy;

        @BindView(R.id.container)
        TextView container;

        @BindView(R.id.hostIp)
        TextView hostIp;

        @BindView(R.id.podIp)
        TextView podIp;

        @BindView(R.id.startTime)
        TextView startTime;

        @BindView(R.id.node)
        TextView node;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
