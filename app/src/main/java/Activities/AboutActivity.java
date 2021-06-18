package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cloudproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import com.example.model.AboutAdapter;

public class AboutActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private ArrayList<String> list;
    private AboutAdapter adapter;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        list=new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView_photo);
        progressBar = findViewById(R.id.progress);
        FirebaseFirestore db = FirebaseFirestore.getInstance();




        getjerusalem();
        getAlAqsaMosque();

    }

    private void getjerusalem() {
        progressBar.setVisibility(View.VISIBLE);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("jerusalem")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                list.add(document.getData().get("title1").toString());
                                list.add(document.getData().get("title2").toString());
                                list.add(document.getData().get("title3").toString());
                                list.add(document.getData().get("title4").toString());
                                list.add(document.getData().get("title5").toString());
                                list.add(document.getData().get("title6").toString());
                                list.add(document.getData().get("description1").toString());
                                list.add(document.getData().get("description2").toString());
                                list.add(document.getData().get("description3").toString());
                                list.add(document.getData().get("description4").toString());
                                list.add(document.getData().get("description5").toString());
                                list.add(document.getData().get("description6").toString());
                                list.add(document.getData().get("photo1").toString());
                                list.add(document.getData().get("photo2").toString());
                                list.add(document.getData().get("photo3").toString());
                                list.add(document.getData().get("photo4").toString());
                                list.add(document.getData().get("photo5").toString());
                                list.add(document.getData().get("photo6").toString());

                                Log.d("TAG", document.getId() + " Id " + document.getData());
                                Log.d("TAG", document.getId() + " Size " + list.size());
                            }
                            adapter = new AboutAdapter(getApplicationContext(), list);
                            recyclerView.setHasFixedSize(true);
                            recyclerView.setLayoutManager(new LinearLayoutManager(AboutActivity.this));
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.INVISIBLE);

                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                            progressBar.setVisibility(View.VISIBLE);

                        }
                    }
                });
    }



        private void getAlAqsaMosque() {
            progressBar.setVisibility(View.VISIBLE);
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("AlAqsaMosque")

                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    list.add(document.getData().get("titlea1").toString());
                                    list.add(document.getData().get("title2a").toString());
                                    list.add(document.getData().get("titlea3").toString());
                                    list.add(document.getData().get("titlea4").toString());
                                    list.add(document.getData().get("descriptiona1").toString());
                                    list.add(document.getData().get("descriptiona2").toString());
                                    list.add(document.getData().get("descriptiona3").toString());
                                    list.add(document.getData().get("descriptiona4").toString());
                                    list.add(document.getData().get("photoa1").toString());
                                    list.add(document.getData().get("photoa2").toString());
                                    list.add(document.getData().get("photoa3").toString());
                                    list.add(document.getData().get("photoa4").toString());


                                    Log.d("TAG", document.getId() + " Id " + document.getData());
                                    Log.d("TAG", document.getId() + " Size " + list.size());
                                }
                                adapter = new AboutAdapter(getApplicationContext(), list);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(AboutActivity.this));
                                recyclerView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                progressBar.setVisibility(View.INVISIBLE);

                            } else {
                                Log.d("TAG", "Error getting documents: ", task.getException());
                                progressBar.setVisibility(View.VISIBLE);

                            }
                        }
                    });


        }
  }