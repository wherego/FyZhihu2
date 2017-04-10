package com.fengy.zhihu.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fengy.zhihu.R;
import com.fengy.zhihu.api.model.NewsList;
import com.fengy.zhihu.mvp.contract.NewsListContract;
import com.fengy.zhihu.mvp.presenter.NewsListPresenter;
import com.fengy.zhihu.util.LogUtil;

public class NewsActivity extends AppCompatActivity implements NewsListContract.View {

    private NewsListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        mPresenter = new NewsListPresenter(this);
        mPresenter.getLatestNews();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getLatestNews(NewsList newsList) {
        LogUtil.e(getClass().getName(), newsList.toString());
        mPresenter.getBeforeNews(newsList.getDate());
    }

    @Override
    public void getBeforeNews(NewsList newsList) {
        LogUtil.e(getClass().getName(), newsList.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
