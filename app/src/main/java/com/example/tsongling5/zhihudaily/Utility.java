package com.example.tsongling5.zhihudaily;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by TsongLing5 on 2016/9/21.
 */
public class Utility {
    public static boolean isConnected(Context context){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        return info!=null && info.isConnected();


    }
    public static void NoNetAlert(Context context){
        Toast.makeText(context,"Internet is invalid",Toast.LENGTH_SHORT).show();
    }
}
