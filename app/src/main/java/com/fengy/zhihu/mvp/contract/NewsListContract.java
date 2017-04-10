package com.fengy.zhihu.mvp.contract;

import android.content.Context;

import com.fengy.zhihu.api.model.NewsList;

/**
 * Created by fengyun on 2017/4/9.
 * 提供view层 Presenter层接口
 */

public interface NewsListContract {

    interface View extends BaseView {

        Context getContext();

        void getLatestNews(NewsList newsList);

        void getBeforeNews(NewsList newsList);
    }

    interface Presenter extends BaseContract<View> {

        void getLatestNews();
        void getBeforeNews(String date);
    }
}