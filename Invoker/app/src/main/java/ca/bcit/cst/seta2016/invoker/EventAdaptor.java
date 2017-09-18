package ca.bcit.cst.seta2016.invoker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class EventAdaptor extends RecyclerView.Adapter<EventAdaptor.ViewHolder> {
    private Context context;
    private List<EventCard> list;

    public EventAdaptor(Context context, List<EventCard> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_event, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.child.setText(list.get(position).getChild());
        holder.date.setText(list.get(position).getDate());
        holder.event.setText(list.get(position).getEvent());
        holder.desc.setText(list.get(position).getDesc());
        holder.rank.setText(list.get(position).getRank());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView child;
        public TextView date;
        public TextView event;
        public TextView desc;
        public TextView rank;


        public ViewHolder(View itemView) {
            super(itemView);
            child = itemView.findViewById(R.id.eventChild);
            date = itemView.findViewById(R.id.eventDate);
            event = itemView.findViewById(R.id.eventEvent);
            desc = itemView.findViewById(R.id.eventDesc);
            rank = itemView.findViewById(R.id.eventRank);
        }
    }
}
