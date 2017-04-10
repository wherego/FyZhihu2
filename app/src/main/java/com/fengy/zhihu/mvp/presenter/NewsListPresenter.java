package com.fengy.zhihu.mvp.presenter;

import android.widget.Toast;

import com.fengy.zhihu.api.ZhihuApi;
import com.fengy.zhihu.api.model.NewsList;
import com.fengy.zhihu.mvp.contract.NewsListContract;
import com.fengy.zhihu.util.LogUtil;

import io.reactivex.functions.Consumer;


/**
 * Created by fengyun on 2017/4/9.
 * 中间件，关联view和Contract
 */
public class NewsListPresenter implements NewsListContract.Presenter {

    private NewsListContract.View mView;
    private ZhihuApi zhihuApi;

    public NewsListPresenter(NewsListContract.View view) {
        this.mView = view;
        zhihuApi = ZhihuApi.getInstance();
    }

    @Override
    public void getLatestNews() {
        zhihuApi.getLatestNews()
                .doOnNext(new Consumer<NewsList>() {
                    @Override
                    public void accept(NewsList newsList) throws Exception {
                        mView.getLatestNews(newsList);
                        Toast.makeText(mView.getContext(),newsList.getDate(),Toast.LENGTH_LONG).show();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(getClass().getName(), throwable.getMessage());
                    }
                })
                .subscribe();

    }

    @Override
    public void getBeforeNews(String date) {
        zhihuApi.getBeforeNews(date)
                .doOnNext(new Consumer<NewsList>() {
                    @Override
                    public void accept(NewsList newsList) throws Exception {
                        mView.getBeforeNews(newsList);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtil.e(getClass().getName(), throwable.getMessage());
                    }
                })
                .subscribe();
    }


}

