package com.fengy.zhihu.api;

import com.fengy.zhihu.api.model.NewsDetail;
import com.fengy.zhihu.api.model.NewsList;

import io.reactivex.Flowable;

/**
 * Created by fengyun on 2017/4/10.
 */

public class ZhihuApi extends BaseApi {

    public static ZhihuApi zhihuApi;
    private ZhihuInterface zhihuInterface;

    public static ZhihuApi getInstance() {
        synchronized (ZhihuApi.class) {
            if (zhihuApi == null) {
                zhihuApi = new ZhihuApi();
            }
        }
        return zhihuApi;
    }

    private ZhihuApi() {
        zhihuInterface = RetrofitManager.builder().newRetrofit().create(ZhihuInterface.class);
    }

    public Flowable<NewsList> getLatestNews() {
        return create(zhihuInterface.getLatestNews())
                .compose(BaseApi.<NewsList>background());
    }
    /*public Call<NewsList> getLatestNews() {
        return zhihuInterface.getLatestNews();
    }*/

    public Flowable<NewsList> getBeforeNews(String date) {
        return create(zhihuInterface.getBeforeNews(date))
                .compose(BaseApi.<NewsList>background());
    }
    /*public Call<NewsList> getBeforeNews(String date) {
        return zhihuInterface.getBeforeNews(date);
    }*/

    public Flowable<NewsDetail> getNewsDetail(int id) {
        return create(zhihuInterface.getNewsDetail(id))
                .compose(BaseApi.<NewsDetail>background());
    }
    /*public Call<NewsDetail> getNewsDetail(int id) {
        return zhihuInterface.getNewsDetail(id);
    }*/
}
