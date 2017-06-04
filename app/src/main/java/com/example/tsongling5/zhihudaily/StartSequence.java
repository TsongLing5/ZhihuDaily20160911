package com.example.tsongling5.zhihudaily;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TsongLing5 on 2016/9/11.
 */
public class StartSequence extends AppCompatActivity {

    private final OkHttpClient client = new OkHttpClient();
    private ImageView startImage;
    private static String url="http://news-at.zhihu.com/api/4/start-image/1080*1776";

    @Override
    protected void onCreate(Bundle save){
        super.onCreate(save);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.start_layout);

        //创建默认的ImageLoader配置参数
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration
                .createDefault(this);

        //Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(configuration);


        startImage=(ImageView)findViewById(R.id.start_imageView);

//        Intent i=new Intent(StartSequence.this,MainActivity.class);
//        startActivity(i);
        LoadImage();
    }


    private void LoadImage(){

        File dir = getFilesDir();
        final File imgFile = new File(dir, "start.jpg");
        if (imgFile.exists()) {
            startImage.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
        } else {
            startImage.setImageResource(R.mipmap.start);
        }

        final ScaleAnimation scaleAnim = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {


                if(NetUtility.checkNetworkConnection(StartSequence.this)){

                    GetHTTP example=new GetHTTP();

                    try {



                        String response = example.run("http://news-at.zhihu.com/api/4/start-image/1080*1776");

                        JSONObject jsonObject = new JSONObject(response);
                        String url = jsonObject.getString("img");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }

//                    try {
//
//                       String response= getAddress.run("http://news-at.zhihu.com/api/4/start-image/1080*1776");
//                        JSONObject jsonObject = null;
//                        try {
//                            jsonObject = new JSONObject(response);
//                            String url = jsonObject.getString("img");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    JSONObject jsonObject = new JSONObject(response);
//                    String url = jsonObject.getString("img");


                    startActivity();
                } else{
                    NetUtility.noNetworkAlert(StartSequence.this);
                    startActivity();
                }
//                if (HttpUtils.isNetworkConnected(StartSequence.this)) {
//                    HttpUtils.get(Constant.START, new AsyncHttpResponseHandler() {
//                        @Override
//                        public void onSuccess(int i, PreferenceActivity.Header[] headers, byte[] bytes) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(new String(bytes));
//                                String url = jsonObject.getString("img");
//                                HttpUtils.getImage(url, new BinaryHttpResponseHandler() {
//                                    @Override
//                                    public void onSuccess(int i, PreferenceActivity.Header[] headers, byte[] bytes) {
//                                        saveImage(imgFile, bytes);
//                                        startActivity();
//                                    }
//
//                                    @Override
//                                    public void onFailure(int i, PreferenceActivity.Header[] headers, byte[] bytes, Throwable throwable) {
//                                        startActivity();
//                                    }
//                                });
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                            startActivity();
//                        }
//                    });
//                } else {
//                    Toast.makeText(StartSequence.this, "没有网络连接!", Toast.LENGTH_LONG).show();
//                    startActivity();
//                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startImage.startAnimation(scaleAnim);
//        startActivity();
    }

    private void startActivity() {
        Intent intent = new Intent(StartSequence.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }

    public void saveImage(File file, byte[] bytes) {
        try {
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public class GetHTTP {
        OkHttpClient client = new OkHttpClient();

        @TargetApi(Build.VERSION_CODES.KITKAT)
        String run(String url) throws IOException {
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        }
    }
}
