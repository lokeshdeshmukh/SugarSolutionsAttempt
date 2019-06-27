package com.sugar.example.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sugar.example.R;
import com.sugar.example.Utils.Utils;
import com.sugar.example.activity.MainActivity;

import java.util.ArrayList;
import java.util.Map;

public class ShowProductAdapter extends RecyclerView.Adapter<ShowProductAdapter.MovieViewHolder> implements View.OnClickListener {

    private Map<String,ArrayList<String>> categoryData;
    private int rowLayout;
    private Context context;
    private MainActivity instance;
    private ArrayList<String> keys=new ArrayList<>();
    private Utils utilsObj=new Utils();

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        RecyclerView recyclerView;
        ImageView expand_arrow;
        TextView categoryTitle;


        public MovieViewHolder(View v) {
            super(v);
            moviesLayout =  v.findViewById(R.id.movies_layout);
            recyclerView =  v.findViewById(R.id.child_recycler_view);
            expand_arrow = v.findViewById(R.id.expand_arrow);
            categoryTitle=v.findViewById(R.id.categoryTitle);
        }
    }

    public ShowProductAdapter(Map<String, ArrayList<String>> categoryData, int rowLayout, Context context, MainActivity instance) {

        for (Map.Entry<String, ArrayList<String>> entry : categoryData.entrySet()) {
            keys.add(entry.getKey());
        }
        this.categoryData = categoryData;
        this.rowLayout = rowLayout;
        this.context = context;
        this.instance = instance;

    }

    @Override
    public ShowProductAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2);
        holder.recyclerView.setLayoutManager(layoutManager);
        ChildProductAdapter childProductObject=new ChildProductAdapter(keys.get(position),categoryData.get(keys.get(position)), R.layout.child_card_view, context, instance);
        holder.recyclerView.setAdapter(childProductObject);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childProductObject.visibleItemSpecified) {
                    childProductObject.visibleItemSpecified = false;
                    utilsObj.resizeWithAnimation(holder.itemView, 500, holder.itemView.getHeight()*2);
                    utilsObj.rorateClockwise(holder.expand_arrow);
                }
                else {
                    childProductObject.visibleItemSpecified = true;
                    utilsObj.resizeWithAnimation(holder.itemView, 500, holder.itemView.getHeight()/2);
                    utilsObj.rorateAntiClockwise(holder.expand_arrow);
                }


                    childProductObject.notifyDataSetChanged();


            }
        });
        holder.categoryTitle.setText(keys.get(position));


    }

    @Override
    public int getItemCount() {
        return categoryData.size();
    }

    @Override
    public void onClick(View v) {


    }

}