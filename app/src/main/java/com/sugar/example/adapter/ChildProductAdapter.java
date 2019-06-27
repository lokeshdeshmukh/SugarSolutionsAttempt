package com.sugar.example.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sugar.example.Fragment.ShowMoreDetails;
import com.sugar.example.R;
import com.sugar.example.activity.MainActivity;
import com.sugar.example.model.pojo.ProductDetails;
import com.sugar.example.model.pojo.Products;
import com.sugar.example.rest.ApiClient;
import com.sugar.example.rest.ApiInterface;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildProductAdapter extends RecyclerView.Adapter<ChildProductAdapter.MovieViewHolder> implements View.OnClickListener {

    private String type;
    private ArrayList<String> id;
    private int rowLayout;
    private Context context;
    private MainActivity instance;
    public Boolean visibleItemSpecified=true;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView productName;
        ImageView imageTile;
        ProductDetails value;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout =  v.findViewById(R.id.movies_layout);
            imageTile = v.findViewById(R.id.imageTile);
            productName = v.findViewById(R.id.productName);
            value=new ProductDetails();
        }
    }

    public ChildProductAdapter(String type,ArrayList<String> id, int rowLayout, Context context, MainActivity instance) {
        this.type=type;
        this.id = id;
        this.rowLayout = rowLayout;
        this.context = context;
        this.instance = instance;

    }

    @Override
    public ChildProductAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                  int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

                if(holder.value.getTitle()==null) {
                    Call<Products> call = apiService.getSpecificCategoryDetails(type, id.get(position));
                    call.enqueue(new Callback<Products>() {
                        @Override
                        public void onResponse(Call<Products> call, Response<Products> response) {


                            Picasso.get().load("https://cdn.shopify.com/s/files/1/0906/2558/products/BTWGW02_01-compressor.png?v=1543578611").into(holder.imageTile);
                            holder.productName.setText(response.body().getProducts().get(0).getTitle());
                            holder.value = response.body().getProducts().get(0);
                        }

                        @Override
                        public void onFailure(Call<Products> call, Throwable t) {

                        }
                    });
                }
                holder.imageTile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppCompatActivity activity = (AppCompatActivity) view.getContext();
                        ShowMoreDetails myFragment = new ShowMoreDetails();
                        Bundle value=new Bundle();
                        value.putParcelable("data",holder.value);
                        myFragment.setArguments(value);
                        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, myFragment).addToBackStack(null).commit();

                    }
                });
    }

    @Override
    public int getItemCount() {
        return visibleItemSpecified?2:id.size();
    }

    @Override
    public void onClick(View v) {


    }


}