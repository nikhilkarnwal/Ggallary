package com.rishabhrk.ggallary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.imageViewHolder> {
    private ArrayList<String> filepath=new ArrayList<>();

    public RecyclerAdapter(ArrayList<String> file)
    {
        this.filepath=file;
    }


    @NonNull
    @Override
    public RecyclerAdapter.imageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.album,viewGroup,false);
        imageViewHolder imageViewHolder=new imageViewHolder(view);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.imageViewHolder imageViewHolder, int i) {
        String s= filepath.get(i);
        imageViewHolder.album.setImageResource(Integer.parseInt(s));

    }

    @Override
    public int getItemCount() {
        return filepath.size();
    }

    public static class imageViewHolder extends RecyclerView.ViewHolder
    {
        ImageView album;

        public imageViewHolder(@NonNull View itemView) {
            super(itemView);
            album=itemView.findViewById(R.id.album);

        }
    }

}
