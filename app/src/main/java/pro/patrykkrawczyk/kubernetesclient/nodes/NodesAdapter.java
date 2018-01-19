package pro.patrykkrawczyk.kubernetesclient.nodes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import io.fabric8.kubernetes.api.model.Node;
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
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_node, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}
