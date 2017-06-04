package com.example.tsongling5.zhihudaily;

import android.annotation.TargetApi;
import android.os.Build;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by TsongLing5 on 2016/9/11.
 */
public class HttpUtility {

    public static String NEWSLIST_LATEST = "http://news-at.zhihu.com/api/4/news/latest";
    public static String STORY_VIEW = "http://daily.zhihu.com/story/";
    public static String NEWSDETAIL = "http://news-at.zhihu.com/api/4/news/";

    private OkHttpClient mClient;

//    private OkHttpClient client = new OkHttpClient();
    private static OkHttpClient okHttpClient = new OkHttpClient();


    public Response GetHTTPResponse(String url) throws IOException {


//        Request request = new Request.Builder().url(url).build();
//        Response response = null;
//        try {
//            //同步请求返回的是response对象
//            response = mClient.newCall(request).execute();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return response;


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response=null;
        try {
              response = client.newCall(request).execute();
               String s=response.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String message=response.body().string();
        return response;
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
