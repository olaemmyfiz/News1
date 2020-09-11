package com.example.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.DetailActivity;
import com.example.news.R;
import com.example.news.model.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> items;
    private Context context;

    public ItemAdapter(Context context) {
        this.items = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_resource,parent,false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Picasso.get().load(items.get(position).getUrlToImage()).into(holder.urlToImage);
        holder.title.setText(items.get(position).getTitle());
        holder.description.setText(items.get(position).getDescription());
        holder.url.setText(items.get(position).getUrl());
        holder.published.setText(items.get(position).getPublishedAt());
        holder.author.setText(items.get(position).getAuthor());


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView urlToImage;
        TextView title;
        TextView description;
        TextView url;
        TextView published;
        TextView author;

        public ItemViewHolder(@NonNull final View itemView) {
            super(itemView);

            urlToImage = itemView.findViewById(R.id.urlToImage);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.Description);
            url = itemView.findViewById(R.id.url);
            published = itemView.findViewById(R.id.publishedAt);
            author = itemView.findViewById(R.id.author);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();

                    if(pos != RecyclerView.NO_POSITION){
                       // Item clickable = items.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("image", items.get(pos).getUrlToImage());
                        intent.putExtra("description", items.get(pos).getDescription());
                        intent.putExtra("url", items.get(pos).getUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });


        }
    }

    public void addData(List<Item> data){
        items.addAll(data);
        notifyDataSetChanged();
    }
}
