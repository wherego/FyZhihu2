package com.fengy.zhihu.api;

import com.fengy.zhihu.api.model.NewsDetail;
import com.fengy.zhihu.api.model.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by fengyun on 2017/4/10.
 */

public interface ZhihuInterface {

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_SHORT)
    @GET("stories/latest")
    Call<NewsList> getLatestNews();

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("stories/before/{date}")
    Call<NewsList> getBeforeNews(@Path("date") String date);

    @Headers(RetrofitManager.CACHE_CONTROL_AGE + RetrofitManager.CACHE_STALE_LONG)
    @GET("story/{id}")
    Call<NewsDetail> getNewsDetail(@Path("id") int id);
}
