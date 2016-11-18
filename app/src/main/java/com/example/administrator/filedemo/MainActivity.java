package com.example.administrator.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private File fPhonedirecotry;
    private File fExternalStoragePublicDirectory;
    private File fExternalStorageDirectory;
    private File fDataStorage;
    private File fDownloadCacheDirectory;
    private File fRootDirectory;
    private String name,path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1= (TextView) findViewById(R.id.result);
        //获取各种存储位置
        fPhonedirecotry=this.getFilesDir();
        fExternalStoragePublicDirectory= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fExternalStorageDirectory=Environment.getExternalStorageDirectory();
        fDataStorage=Environment.getDataDirectory();
        fDownloadCacheDirectory=Environment.getDownloadCacheDirectory();
        fRootDirectory=Environment.getRootDirectory();
        
        //没有存储卡时，外部存储按钮无效
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_REMOVED)){
            Button btn= (Button) findViewById(R.id.externalStorageDirectory);
            btn.setEnabled(false);
        }
    }
    public void phoneDirectory(View v){
        path=fPhonedirecotry.getPath();
        try {
            FileOutputStream fos=openFileOutput("test.txt",MODE_PRIVATE);
            fos.write("hello".getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        listFies(path);
    }
    public void externalStorageDirectory(View v){
        path=fExternalStorageDirectory.getAbsolutePath();
        listFies(path);
    }
    public void dataStorage(View v){
        path=fDataStorage.getAbsolutePath();
        listFies(path);
    }
    public void downloadCacheDirectory(View v){
        path=fDownloadCacheDirectory.getAbsolutePath();
        listFies(path);
    }
    public void rootDirectory(View v){
        path=fRootDirectory.getAbsolutePath();
        listFies(path);
    }
    public void externalStoragePublicDirectory(View v){
        path=fExternalStoragePublicDirectory.getAbsolutePath();
        listFies(path);
    }

    private boolean listFies(String path) {
        name="路径"+path+"\n文件清单:\n";
        File file=new File(path);
        if(file.listFiles()!=null&&file.listFiles().length>0){
            for(File f:file.listFiles()){
                path=f.getAbsolutePath();
                name=name+f.getName()+"\n";
            }
        }
        tv1.setText(name);
        return true;
    }
}
