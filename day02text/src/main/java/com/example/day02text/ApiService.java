package com.example.day02text;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by 雪碧 on 2019/11/19.
 */

public interface ApiService {
    String url ="http://news-at.zhihu.com/api/4/";
    @GET("news/hot")
    Observable<Bean> getData();
}
