package com.example.tsongling5.zhihudaily;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by mac on 15-2-17.
 */
public class NewsDetailActivity extends Activity {
    private WebView mWebView;
    private boolean isFavourite = false;
    private ZhihuItem news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsdetails_layout);
		
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        mWebView = (WebView) findViewById(R.id.newsWebView);
        setWebView(mWebView);

        news = (ZhihuItem) getIntent().getSerializableExtra("news");
        new LoadNewsDetailTask(mWebView).execute(news.getId());
        imgReset();
//        isFavourite = DailyNewsDB.getInstance(this).isFavourite(news);
    }

    private void setWebView(WebView mWebView) {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setHorizontalScrollBarEnabled(false);
//        mWebView.getSettings().setBlockNetworkImage(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);

        mWebView.getSettings().setDefaultFontSize(48);
    }

    public static void startActivity(Context context, ZhihuItem news) {
        if (Utility.isConnected(context)) {
            Intent i = new Intent(context, NewsDetailActivity.class);
            i.putExtra("news", news);
            context.startActivity(i);
        } else {
            Utility.NoNetAlert(context);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        if (isFavourite) menu.findItem(R.id.action_favourite).setIcon(R.drawable.fav_active);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

//        if (id == R.id.action_favourite) {
//            if (!isFavourite) {
//                DailyNewsDB.getInstance(this).saveFavourite(news);
//                item.setIcon(R.drawable.fav_active);
//                isFavourite = true;
//            } else {
//                DailyNewsDB.getInstance(this).deleteFavourite(news);
//                item.setIcon(R.drawable.fav_normal);
//                isFavourite = false;
//            }
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    private void imgReset() {
        mWebView.loadUrl("javascript:(function(){"
                + "var objs = document.getElementsByTagName('img'); "
                + "for(var i=0;i<objs.length;i++)  " + "{"
                + "var img = objs[i];   "
                + "    img.style.width = '100%';   "
                + "    img.style.height = 'auto';   "
                + "}" + "})()");
    }
}
