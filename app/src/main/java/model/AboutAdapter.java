package model;

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
    private List<Article> mArticles;
   // private List<String> mStrings;
    Context context;

public AboutAdapter(Context mArticles, ArrayList<String> context) {
         // this.mStrings = Strings;
        this.context = context;

         this.mArticles = new ArrayList<>();
        this.mArticles =mArticles;
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
       // holder.onBind(mStrings.get(position));
          holder.onBind(mArticles.get(position));
        }

@Override
public int getItemCount() {
        return mArticles.size();
        }


public class AboutViewHolder extends RecyclerView.ViewHolder {
    TextView textViewtitle;
    TextView textViewDescription;
    ImageView imageView;

    public AboutViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.photo);
        textViewtitle = itemView.findViewById(R.id.text_title1);
        textViewDescription = itemView.findViewById(R.id.text_desc);


    }

    public void onBind(Article item) {
        textViewtitle.setText(item.getTitle());
        textViewDescription.setText(item.getDescription());
         // public void onBind(String item) {
       // textViewtitle.setText(item.getTitle());
        Picasso.get()
                .load(item.getUrlToImage())
              //  .load(item)
                .resize(50, 50)
                .centerCrop().error(R.drawable.home)
                .into(imageView);

    }
}}
