package ca.bcit.cst.seta2016.invoker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private Context context;
    private List<CardData> list;

    public CustomAdapter(Context context, List<CardData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.description.setText(list.get(position).getTextDesc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView description;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.cardTextTitle);
            description = itemView.findViewById(R.id.cardTextDisc);
        }
    }
}
