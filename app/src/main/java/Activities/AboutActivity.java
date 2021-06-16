package Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

import model.AboutAdapter;

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




            getListItems();

        }

        private void getListItems() {
            progressBar.setVisibility(View.VISIBLE);
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("image")

                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    list.add(document.getData().get("m1").toString());


                                    Log.d("TAG", document.getId() + " => " + document.getData());
                                    Log.d("TAG", document.getId() + " =>sssss " + list.size());
                                    Log.d("TAG", document.getId() + " =>gggggggg " + document.getData().get("m2"));
                                }
                                adapter = new AboutAdapter(getApplicationContext(), list);
                                recyclerView.setHasFixedSize(true);
                                // StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                                //   recyclerView.setLayoutManager(staggeredGridLayoutManager);
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