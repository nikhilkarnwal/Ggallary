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
        imageViewHolder.album.setImageBitmap(decodeSampledBitmapFromResource(filepath.get(i), 100, 100));

    }

    private Bitmap decodeSampledBitmapFromResource(String filePath,
                                                   int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }
    private int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
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
