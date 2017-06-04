package com.example.tsongling5.zhihudaily;

import android.os.AsyncTask;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by mac on 15-2-3.
 */
public class LoadNewsTask extends AsyncTask<Void, Void, List<ZhihuItem>> {
    private NewsAdapter adapter;
    private onFinishListener listener;
    private String url;

    public LoadNewsTask(NewsAdapter adapter) {
        super();
        this.adapter = adapter;
    }

    public LoadNewsTask(NewsAdapter adapter,String url) {
        super();
        this.adapter = adapter;
        this.url=url;
    }

    public LoadNewsTask(NewsAdapter adapter, String url,onFinishListener listener) {
        super();
        this.adapter = adapter;
        this.listener = listener;
        this.url=url;
    }

    @Override
    protected List<ZhihuItem> doInBackground(Void... params) {
        List<ZhihuItem> newsList = null;
        try {
            newsList = JsonHelper.parseJsonToList(Http.getNewsList(url));
        } catch (IOException | JSONException e) {

        } finally {
            return newsList;
        }
    }


//    @Override
//    protected List<ZhihuItem> doInBackground(Void... params) {
//        List<ZhihuItem> newsList = null;
//        try {
//            newsList = JsonHelper.parseJsonToList(Http.getLastNewsList());
//        } catch (IOException | JSONException e) {
//
//        } finally {
//            return newsList;
//        }
//    }

    @Override
    protected void onPostExecute(List<ZhihuItem> newsList) {
        adapter.refreshNewsList(newsList);
        if (listener != null) {
            listener.afterTaskFinish();
        }

    }

    public interface onFinishListener {
        public void afterTaskFinish();
        //////yes
    }
}
