package com.example.model;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cloudproject.OnItemClickListener;
import com.example.cloudproject.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import com.example.Network.NetworkUtils;
import retrofit2.Call;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.newsViewHolder> {

    private List<Article> mArticles;
    Context context;
    Call<Article> recentApiCall;
    NetworkUtils networkUtils = new NetworkUtils(context);
    OnItemClickListener onItemClickListener;



    public NewsAdapter(Context context) {
        this.mArticles = new ArrayList<>();
        this.context=context;

    }

    public void setmRecentModels(List<Article> mRecentModels) {
        this.mArticles.addAll(mRecentModels);
        notifyDataSetChanged();
    }


    public NewsAdapter(List<Article> mArticles,Context context) {
        this.mArticles = new ArrayList<>();
        this.mArticles =mArticles;
        this.context=context;
    }

    @NonNull
    @Override
    public newsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new newsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newsViewHolder holder, int position) {
        holder.onBind(mArticles.get(position));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }


    public class newsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewtitle;
        TextView textViewAuthor;
        TextView textViewDate;
        ImageView imageViewNews;
        ImageView imageViewShare;

        public newsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewtitle = itemView.findViewById(R.id.text_tittle);
            textViewAuthor = itemView.findViewById(R.id.text_author);
            textViewDate = itemView.findViewById(R.id.text_date_news);
            imageViewNews=itemView.findViewById(R.id.imageView_news);
            imageViewShare = itemView.findViewById(R.id.share_icon);
            Log.e("size", String.valueOf(mArticles.size()));

            if (onItemClickListener !=null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(v,getAdapterPosition());
                    }
                });
            }
        }

        public void onBind(Article item) {
            textViewtitle.setText(item.getTitle());
            textViewDate.setText(item.getPublishedAt());
            imageViewShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, item.getDescription());
                    sendIntent.setType("text/plain");
                    context.startActivity(sendIntent);
                }
            });
            Picasso.get()
                    .load(item.getUrlToImage())
                    .resize(50, 50)
                    .centerCrop().error(R.drawable.home)
                    .into(imageViewNews);


        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
