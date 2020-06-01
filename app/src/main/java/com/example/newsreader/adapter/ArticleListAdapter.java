package com.example.newsreader.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsreader.R;
import com.example.newsreader.model.Article;

import java.util.List;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.MyViewHolder> {

    private List<Article> list;
    private Context mContext;
    private ClickListner clickListner;

    public ArticleListAdapter(List<Article> list, Context mContext,ClickListner clickListner) {
        this.list = list;
        this.mContext = mContext;
        this.clickListner = clickListner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.article_list_item,null,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textView.setText(list.get(position).getTitle());

        // Trigger the download of the URL asynchronously into the image view.

        Glide.with(mContext)
                .load(list.get(position).getUrlToImage())
                .thumbnail(0.3f)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_image)
                .into(holder.imageView);

        holder.tVTime.setText(list.get(position).getPublishedAt());
        holder.tVAuthor.setText(list.get(position).getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListner.OnItemClick(position,list.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView tVTime;
        TextView tVAuthor;
         MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            tVTime = itemView.findViewById(R.id.tVTime);
             tVAuthor = itemView.findViewById(R.id.tVAuthor);
        }
    }

    public interface ClickListner{
        void OnItemClick(int position,Article data);
    }
}
