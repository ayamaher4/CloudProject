package com.example.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cloudproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.AboutViewHolder> {
    private List<About> mAbout;
    Context context;

      public AboutAdapter(Context context, ArrayList<String> list) {
          this.mAbout = new ArrayList<>();
          this.context=context;

}

        @NonNull
       @Override
     public AboutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_item, parent, false);
        return new AboutViewHolder(view);
        }

         @Override
        public void onBindViewHolder(@NonNull AboutViewHolder holder, int position) {
          holder.onBind(mAbout.get(position));
        }

     @Override
       public int getItemCount() {
        return mAbout.size();
        }


       public class AboutViewHolder extends RecyclerView.ViewHolder {
       TextView textTitle;
       TextView textDescription;
        ImageView imageView;

    public AboutViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.photo);
        textTitle = itemView.findViewById(R.id.text_title1);
        textDescription = itemView.findViewById(R.id.text_desc);


    }

    public void onBind(About item) {
        textTitle.setText(item.getTitle());
        textDescription.setText(item.getDescription());
        Picasso.get()
                .load(item.getUrlImage())
                .resize(50, 50)
                .centerCrop().error(R.drawable.home)
                .into(imageView);

    }
}}
