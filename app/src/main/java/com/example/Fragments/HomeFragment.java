package com.example.Fragments;

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

import com.example.cloudproject.OnItemClickListener;
import com.example.cloudproject.R;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.Activities.NewsDetailsActivity;
import com.example.Network.NetworkUtils;
import com.example.model.Article;
import com.example.model.News;
import com.example.model.NewsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private NetworkUtils networkUtils;
    private RecyclerView recyclerView;
    private Call<News> recentApiCall;
    private String api_key = "a8f62f7bc7f84af7b64af921614be2ae";
    private String q = "القدس";
    private NewsAdapter newsAdapter;
    private List<Article> articleList;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        articleList = new ArrayList<>();


        recyclerView=root.findViewById(R.id.recyclerView_home);
        newsAdapter = new NewsAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsAdapter);
        networkUtils = new NetworkUtils(getContext());

        return root;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LoadingData();

    }

    public void LoadingData() {


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault());
        String formattedDate = df.format(c);
        System.out.println("Current time => " + formattedDate);

        recentApiCall = networkUtils.getApiInterface().getRecent(q, "2021-05-30", formattedDate,"popularity", api_key,"ar");
        recentApiCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Article> article = response.body().getArticles();
                    articleList.addAll(article);

                    newsAdapter.setmRecentModels(articleList);

                    newsAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {
                            Article a=articleList.get(position);

                            Intent intent=new Intent(getActivity(), NewsDetailsActivity.class);
                            intent.putExtra("image",a.getUrlToImage());
                            intent.putExtra("title",a.getTitle());
                            intent.putExtra("desc",a.getDescription());
                            intent.putExtra("date",a.getPublishedAt());
                            startActivity(intent);

                        }
                    });



                }

            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
                t.printStackTrace();


            }
        });



    }

}