package com.fengy.zhihu.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by fengyun on 2016/10/28.
 * 自动上拉加载RecyclerView
 */

public class AutoLoadRecyclerView extends RecyclerView implements LoadFinishCallBack {
    //上拉加载回调接口
    private onLoadMoreListener loadMoreListener;
    //是否允许加载 false允许加载
    private boolean isLoadingMore;

    public AutoLoadRecyclerView(Context context) {
        this(context, null);
    }

    public AutoLoadRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLoadRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        isLoadingMore = false;
        //添加滑动监听
        addOnScrollListener(new AutoLoadScrollListener(true, true));
    }

    public void setLoadMoreListener(onLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    @Override
    public void loadFinish() {
        isLoadingMore = false;
    }

    /**
     * 滑动自动加载监听器
     */
    private class AutoLoadScrollListener extends OnScrollListener {

        private boolean pauseOnScroll;
        private boolean pauseOnFling;

        public AutoLoadScrollListener(boolean pauseOnScroll, boolean pauseOnFling) {
            super();
            this.pauseOnScroll = pauseOnScroll;
            this.pauseOnFling = pauseOnFling;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            //GridLayoutManager是LinearLayoutManager子类，同样适用。
            if (getLayoutManager() instanceof LinearLayoutManager) {
                //最前可见的Item
                int firstVisibleItem = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();
                //最后可见的Item
                int lastVisibleItem = ((LinearLayoutManager) getLayoutManager()).findLastVisibleItemPosition();
                //总的Item数
                int totalItemCount = AutoLoadRecyclerView.this.getAdapter().getItemCount();
                if (loadMoreListener != null && !isLoadingMore && lastVisibleItem >= totalItemCount -
                        2 && dy > 0) {
                    //加载更多
                    loadMoreListener.loadMore();
                    //停止加载
                    isLoadingMore = true;
                }
            }
        }
    }

    public interface onLoadMoreListener {
        void loadMore();
    }
}
