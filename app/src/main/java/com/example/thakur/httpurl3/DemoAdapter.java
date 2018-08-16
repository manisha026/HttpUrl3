package com.example.thakur.httpurl3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Thakur on 6/25/2018.
 */

public class DemoAdapter extends RecyclerView.Adapter{

    Context context;
    ArrayList<ModelClass>data;

    public DemoAdapter(Context context,ArrayList<ModelClass>data) {

        this.context=context;
        this.data=data;
        //super(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_items,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MyViewHolder myViewHolder=(MyViewHolder) holder;


        myViewHolder.uid.setText(data.get(position).getId());
        myViewHolder.name.setText(data.get(position).getName());
        myViewHolder.author.setText(data.get(position).getAuthor());
        myViewHolder.publishedAt.setText(data.get(position).getPublishedAt());
        String url=data.get(position).getUrlToImage();

        Picasso.with(context).load(url).into(myViewHolder.images);



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView uid,name,author,publishedAt;
        ImageView images;

        public MyViewHolder(View itemView) {
            super(itemView);
            uid=itemView.findViewById(R.id.uid);
            name=itemView.findViewById(R.id.name);
            author=itemView.findViewById(R.id.author);
            publishedAt=itemView.findViewById(R.id.publishedAt);
            images=itemView.findViewById(R.id.images);
        }
    }
}
