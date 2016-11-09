package com.mainli.suspension;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Mainli on 2016/11/9.
 */
public class RVHeadSuspensionListener<V extends View> extends RecyclerView.OnScrollListener {
    private V mHead;
    private int mSuspensionHeight = 0;
    private LinearLayoutManager mLinearLayoutManager;
    private ResetViewDataListener mResetViewDataListener;
    private int mHeadViewType;

    public interface ResetViewDataListener<V> {
        void onResetView(RecyclerView recyclerView, V v, int itemPosition);
    }

    public RVHeadSuspensionListener(V v, ResetViewDataListener<V> listener) {
        this(v, 0, listener);
    }

    public RVHeadSuspensionListener(V v, int headViewType, ResetViewDataListener<V> listener) {
        mHead = v;
        mResetViewDataListener = listener;
        mHeadViewType = headViewType;
    }


    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        mSuspensionHeight = mHead.getHeight();
        if (layoutManager instanceof LinearLayoutManager)
            mLinearLayoutManager = (LinearLayoutManager) layoutManager;
        else
            throw new IllegalArgumentException("RecyclerView LayoutManager must be LinearLayoutManager");
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (mLinearLayoutManager != null) {
            int itemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
            if (mHeadViewType == recyclerView.getAdapter().getItemViewType(itemPosition + 1)) {
                View view = mLinearLayoutManager.findViewByPosition(itemPosition + 1);
                if (view == null) return;
                if (view.getTop() <= mSuspensionHeight)
                    ViewCompat.setY(mHead, -(mSuspensionHeight - view.getTop()));
            } else ViewCompat.setY(mHead, 0);//归零
            if (mResetViewDataListener != null) {//TODO 减少更新头部频率
                mResetViewDataListener.onResetView(recyclerView, mHead, itemPosition);
            }
        }
    }
}
