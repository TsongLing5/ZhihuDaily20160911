package com.example.tsongling5.zhihudaily;

import java.io.Serializable;

/**
 * Created by TsongLing5 on 2016/9/21.
 */
public class ZhihuItem implements Serializable {
    private String title,imageView;
    private int id;

    public ZhihuItem(int id, String imageView,String title){
        this.id=id;
        this.title=title;
        this.imageView=imageView;

    }



    public void setId(int id){
        this.id=id;
    }


    public void setTitle(String title){
        this.title=title;
    }

    public void setImageView(String imageView){
        this.imageView=imageView;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getImageView(){
        return this.imageView;
    }


}
