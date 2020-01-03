package com.example.diamond_miner;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class adapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<player> players;
    private OnItemClickListener mItemClickListener;

    public adapter(Context context, ArrayList<player> players) {
        this.context = context;
        this.players = players;
    }

    public void updateList(ArrayList<player> players) {
        this.players = players;
        notifyDataSetChanged();

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.top_scores, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final player p = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

            //    genericViewHolder.note_LBL_title.setText(p.getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    private player getItem(int position) {
        return players.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView note_LBL_title;

        public ViewHolder(final View itemView) {
            super(itemView);
            // this.note_LBL_title = itemView.findViewById(R.id.note_LBL_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), getItem(getAdapterPosition()));
                }
            });
        }
    }

    public void removeAt(int position) {
        players.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, players.size());
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position, player p);
    }
}
