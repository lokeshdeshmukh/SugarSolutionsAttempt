package com.sugar.example.activity;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sugar.example.R;
import com.sugar.example.adapter.ShowProductAdapter;
import com.sugar.example.databinding.ActivityMainBinding;
import com.sugar.example.model.ViewModel.MainActivityViewModel;
import com.sugar.example.rest.ApiClient;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = MainActivity.class.getSimpleName();
    MainActivityViewModel model;
    RecyclerView recyclerView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ApiClient.context=this;
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setActivity(this);
        binding.setLifecycleOwner(this);
        initProgressBar();

        recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        model = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        model.getMainResponse().observe(this, fruitlist -> {

            recyclerView.setAdapter(new ShowProductAdapter(fruitlist.get(0), R.layout.product_parent_item_view, getApplicationContext(), MainActivity.this));
            progressDialog.dismiss();
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    void initProgressBar(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..."); // Setting Message
        progressDialog.setTitle("ProgressDialog"); // Setting Title
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
    }
}
