package com.rishabhrk.ggallary;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> mFileList;
    RecyclerAdapter adapter;
   // private ArrayList<String> mNameList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler);
        readFiles();
        layoutManager=new GridLayoutManager(this,3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RecyclerAdapter(mFileList);
        recyclerView.setAdapter(adapter);
    }
    void readFiles(){
        mFileList = new ArrayList<>();
    //    mNameList=new ArrayList<>();
        ContentResolver cr = this.getApplicationContext().getContentResolver();
        Cursor cursor = cr.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{ MediaStore.Images.Media.DATA},
                null,
                null,
                MediaStore.Images.Media.DEFAULT_SORT_ORDER);

        if (cursor.moveToFirst()){
            do {
                String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
               // String fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                if (filePath != null){
                    mFileList.add(filePath);
                //    mNameList.add(fileName);
                }
            }while (cursor.moveToNext());
        }
        cursor.close();
    }


}
