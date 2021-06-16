package Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.cloudproject.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import Activities.NewsDetailsActivity;
import Network.NetworkUtils;
import model.Article;
import model.News;
import model.NewsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private NetworkUtils networkUtils;
    private RecyclerView recyclerView;
    private Gson gson;
    private Call<News> recentApiCall;
    private String api_key = "a8f62f7bc7f84af7b64af921614be2ae";
    private String q = "%D8%A7%D9%84%D9%82%D8%AF%D8%B3";
    private NewsAdapter newsAdapter;
    private List<Article> articleList;
    private ProgressBar progressBarNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        articleList = new ArrayList<>();
        progressBarNews = root.findViewById(R.id.progress_news);

        recyclerView=(RecyclerView)root.findViewById(R.id.recyclerView_home);
        newsAdapter = new NewsAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);
        networkUtils = new NetworkUtils(getContext());


        gson = new Gson();
        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LoadingData();

    }

    public void LoadingData() {
        progressBarNews.setVisibility(View.VISIBLE);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println("Current time => " + formattedDate);

        recentApiCall = networkUtils.getApiInterface().getRecent(q, "2021-05-20", formattedDate,"popularity", api_key,"ar");
        recentApiCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<Article> article = response.body().getArticles();
                    articleList.addAll(article);

                    newsAdapter.setmRecentModels(articleList);
                    progressBarNews.setVisibility(View.INVISIBLE);

                    newsAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            Article a=articleList.get(position);

                            Intent intent=new Intent(getActivity(), NewsDetailsActivity.class);
                            intent.putExtra("author",a.getAuthor());
                            intent.putExtra("image",a.getUrlToImage());
                            intent.putExtra("title",a.getTitle());
                            intent.putExtra("desc",a.getDescription());
                            intent.putExtra("date",a.getPublishedAt());
                            startActivity(intent);

                        }
                    });


//                   newsAdapter.notifyDataSetChanged();
//                    Log.e("tag","success");
                    Log.e("tag",response.body().toString());
                    Log.e("taglist",articleList.toString());
                    //    Log.e("tag_title",articleList.get(0).getAuthor());

                    Log.e("tag2",response.toString());

                }else{
                    Log.e("tag","noooo success");

                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                // Snackbar.make(getView(), t.getMessage(), Snackbar.LENGTH_LONG).show();
                t.printStackTrace();
                progressBarNews.setVisibility(View.VISIBLE);

            }
        });


    }

}