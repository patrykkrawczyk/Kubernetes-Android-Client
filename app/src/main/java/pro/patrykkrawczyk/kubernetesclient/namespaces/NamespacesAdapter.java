package pro.patrykkrawczyk.kubernetesclient.namespaces;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric8.kubernetes.api.model.Namespace;
import java.util.ArrayList;
import java.util.List;
import pro.patrykkrawczyk.kubernetesclient.R;

public final class NamespacesAdapter extends RecyclerView.Adapter<NamespacesAdapter.ViewHolder> {

    private List<Namespace> dataSet;

    NamespacesAdapter() {
        this.dataSet = new ArrayList<>();
    }

    void setDataSet(List<Namespace> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public NamespacesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_namespace, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Namespace namespace = dataSet.get(position);

        holder.name.setText(namespace.getMetadata().getName());
        holder.version.setText(namespace.getMetadata().getResourceVersion());
        holder.created.setText(namespace.getMetadata().getCreationTimestamp());
        holder.status.setText(namespace.getStatus().getPhase());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.version)
        TextView version;

        @BindView(R.id.created)
        TextView created;

        @BindView(R.id.status)
        TextView status;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
