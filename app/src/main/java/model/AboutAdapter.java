package model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cloudproject.R;
import com.squareup.picasso.Picasso;
import java.util.List;


public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.photoViewHolder> {

private List<String> mStrings;
        Context context;
public AboutAdapter(Context context, List<String> Strings) {
        this.mStrings = Strings;
        this.context = context;

        }

     @NonNull
@Override
public photoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.about_item, parent, false);
        return new photoViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull photoViewHolder holder, int position) {
        holder.onBind(mStrings.get(position));
        }

@Override
public int getItemCount() {
        return mStrings.size();
        }


public class photoViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public photoViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.photo);


    }

    public void onBind(String item) {
        Picasso.get()
                .load(item)
                .resize(50, 50)
                .centerCrop().error(R.drawable.home)
                .into(imageView);

    }
}}
