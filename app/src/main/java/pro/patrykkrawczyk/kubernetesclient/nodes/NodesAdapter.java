package pro.patrykkrawczyk.kubernetesclient.nodes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeAddress;
import java.util.ArrayList;
import java.util.List;
import pro.patrykkrawczyk.kubernetesclient.R;

public final class NodesAdapter extends RecyclerView.Adapter<NodesAdapter.ViewHolder> {

    private List<Node> dataSet;

    NodesAdapter() {
        this.dataSet = new ArrayList<>();
    }

    void setDataSet(List<Node> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public NodesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_node, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Node node = dataSet.get(position);

        holder.name.setText(node.getMetadata().getName());
        holder.kernel.setText(node.getStatus().getNodeInfo().getKernelVersion());
        holder.created.setText(node.getMetadata().getCreationTimestamp());
        holder.version.setText(node.getApiVersion());

        List<NodeAddress> addresses = node.getStatus().getAddresses();
        if (addresses.size() > 0) {
            holder.internalIp.setText(addresses.get(0).getAddress());
        } else {
            holder.internalIp.setText("");
        }

        if (addresses.size() > 1) {
            holder.externalIp.setText(addresses.get(1).getAddress());
        } else {
            holder.externalIp.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.internalIp)
        TextView internalIp;

        @BindView(R.id.externalIp)
        TextView externalIp;

        @BindView(R.id.kernel)
        TextView kernel;

        @BindView(R.id.created)
        TextView created;

        @BindView(R.id.version)
        TextView version;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
