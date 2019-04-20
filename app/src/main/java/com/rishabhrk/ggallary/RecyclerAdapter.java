package com.rishabhrk.ggallary;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.imageViewHolder> {
    private ArrayList<String> filepath;

    RecyclerAdapter(ArrayList<String> file)
    {
        this.filepath=file;
    }


    @NonNull
    @Override
    public RecyclerAdapter.imageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.album,viewGroup,false);
        return new imageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.imageViewHolder imageViewHolder, int i) {
        int s= filepath.indexOf(i);
        imageViewHolder.album.setImageResource(s);

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
            album=itemView.findViewById(R.id.img_album);

        }
    }

}
