package com.example.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.news.adapter.ItemAdapter;
import com.example.news.api.Client;
import com.example.news.api.Service;
import com.example.news.model.Item;
import com.example.news.model.ItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private TextView disconnected;
    private ItemAdapter itemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemAdapter = new ItemAdapter(MainActivity.this);

        swipeRefreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemAdapter);
        recyclerView.smoothScrollToPosition(0);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews();
                Toast.makeText(MainActivity.this,"News Refreshing", Toast.LENGTH_SHORT).show();
            }
        });


        justView();

    }

    private void justView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("News is Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();

        loadNews();


    }

    private void loadNews() {
        disconnected = findViewById(R.id.disconnected);

        try {

            Client client = new Client();

            Service apiService = client.getClient().create(Service.class);
            Call<ItemResponse> call = apiService.getItem();

            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {

                    List<Item> items = response.body().getArticles();
                    itemAdapter.addData(items);
                    swipeRefreshLayout.setRefreshing(false);
                    progressDialog.hide();


                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(MainActivity.this, "Check Internet Connection!", Toast.LENGTH_SHORT).show();
                    disconnected.setVisibility(View.VISIBLE);
                    progressDialog.hide();

                }
            });
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
