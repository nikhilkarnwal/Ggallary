package com.rishabhrk.ggallary;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<String> mFileList;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWritePermission();
    }

    void readFiles() {
        mFileList = new ArrayList<>();
        //    mNameList=new ArrayList<>();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        ContentResolver cr = this.getContentResolver();
        Cursor cursor = cr.query(
                uri,
                new String[]{MediaStore.Images.Media.DATA},
                null,
                null,
                MediaStore.Images.Media.DEFAULT_SORT_ORDER);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                // String fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
                if (filePath != null) {
                    mFileList.add(filePath);
                    //    mNameList.add(fileName);
                }
            }
            cursor.close();
        }

    }

    public boolean getWritePermission() {
        int currPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (currPermission != PackageManager.PERMISSION_GRANTED) {
            makeRequest();
            return false;
        } else {
            return true;
        }

    }

    private void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1022);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (!(grantResults.length == 0
                || grantResults[0] !=
                PackageManager.PERMISSION_GRANTED)) {
            recyclerView = findViewById(R.id.recycler);
            readFiles();
            // Integer[] paths={R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic6,R.drawable.pic7,R.drawable.pic5,R.drawable.pic6,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic6,R.drawable.pic7,R.drawable.pic5,R.drawable.pic6,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic6,R.drawable.pic7,R.drawable.pic5,R.drawable.pic6,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7,R.drawable.pic6,R.drawable.pic7,R.drawable.pic5,R.drawable.pic6};
            layoutManager = new GridLayoutManager(this, 2);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerAdapter(mFileList);
            recyclerView.setAdapter(adapter);
            Toast toast = Toast.makeText(getApplicationContext(), mFileList.get(0), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
