package Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cloudproject.R;

public class NewsDetailsActivity extends AppCompatActivity {
    TextView textViewAuthor;
    TextView textViewTitle;
    TextView textViewDate;
    TextView textViewDesc;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_details);
        textViewAuthor =findViewById(R.id.details_author);
        textViewTitle =findViewById(R.id.details_title);
        textViewDesc =findViewById(R.id.details_desc);
        textViewDate =findViewById(R.id.details_date);
        imageView =findViewById(R.id.details_image);

        String author = getIntent().getStringExtra("author");
        String title = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        String date = getIntent().getStringExtra("date");
        String image = getIntent().getStringExtra("image");

        textViewTitle.setText(title);
        textViewAuthor.setText(author);
        textViewDesc.setText(desc);
        textViewDate.setText(date);

        readUserPreferences();

        Glide.with(imageView).load(image).error(R.drawable.ic_launcher_background).into(imageView);
    }

    private void readUserPreferences(){
        SharedPreferences sharedPreferences=   PreferenceManager.getDefaultSharedPreferences(this);
        String fontsize=sharedPreferences.getString(getString(R.string.key_font_size), String.valueOf(20));
        textViewDesc.setTextSize(Float.parseFloat(fontsize));

        boolean isNight=sharedPreferences.getBoolean(getString(R.string.key_night_mode),false);
        if (isNight){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


    }
