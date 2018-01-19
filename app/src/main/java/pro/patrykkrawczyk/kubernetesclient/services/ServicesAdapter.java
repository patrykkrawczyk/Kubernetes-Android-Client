package pro.patrykkrawczyk.kubernetesclient.services;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServicePort;
import java.util.ArrayList;
import java.util.List;
import pro.patrykkrawczyk.kubernetesclient.R;

public final class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    private List<Service> dataSet;

    ServicesAdapter() {
        this.dataSet = new ArrayList<>();
    }

    void setDataSet(List<Service> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_service, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Service service = dataSet.get(position);

        holder.name.setText(service.getMetadata().getName());
        holder.namespace.setText(service.getMetadata().getNamespace());
        holder.created.setText(service.getMetadata().getCreationTimestamp());
        holder.clusterIp.setText(service.getSpec().getClusterIP());
        holder.type.setText(service.getSpec().getType());

        List<ServicePort> ports = service.getSpec().getPorts();
        if (ports.size() > 0) {
            holder.port.setText(String.valueOf(ports.get(0).getPort()));
        } else {
            holder.port.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.namespace)
        TextView namespace;

        @BindView(R.id.created)
        TextView created;

        @BindView(R.id.port)
        TextView port;

        @BindView(R.id.clusterIp)
        TextView clusterIp;

        @BindView(R.id.type)
        TextView type;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
