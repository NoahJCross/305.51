package com.example.a51youtube;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<URL> urls;
    private Context context;
    private clickListener listener;

    public RecyclerViewAdapter(List<URL> urls, Context context, clickListener listener){
        this.urls = urls;
        this.context = context;
        this.listener = listener;
    }

    // Interface for item click
    public interface clickListener{
        void onClick(int position);
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.url_item, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // Bind URL to the TextView
        holder.urlTextView.setText(urls.get(position).getURL());
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView urlTextView;
        private clickListener clickListener;

        public ViewHolder(@NonNull View itemView, clickListener listener) {
            super(itemView);
            // Initialize views
            urlTextView = itemView.findViewById(R.id.urlTextView);
            this.clickListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Pass clicked item's position to the listener
            int position = getAdapterPosition();
            clickListener.onClick(position);
        }
    }
}
