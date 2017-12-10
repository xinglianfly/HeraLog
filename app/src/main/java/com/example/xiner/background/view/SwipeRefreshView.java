package com.example.xiner.background.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xiner.background.R;

/**
 * Created by seald on 2017/12/6.
 */

public class SwipeRefreshView extends SwipeRefreshLayout {
    private final View footView;
    private final int mScaledTouchSlop;

    private float mDownY, mUpY;
    private ListView listView;
    private OnLoadMoreListener mListener;
    Context context;
    boolean isBottom = false;//看看list是不是到最底部了
    boolean isLoadingMore = false;//看看现在是不是正在加载状态


    public SwipeRefreshView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        footView = View.inflate(context,R.layout.view_footer,null);
        // 表示控件移动的最小距离，手移动的距离大于这个距离才能拖动控件
        mScaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private boolean canLoadMore(){
        float mDistance = mDownY - mUpY;
        boolean upPull = false;
        if (mDistance>=mScaledTouchSlop){
            upPull = true;
        }
        boolean isNotLoading = !isLoadingMore;


        return upPull && isBottom && isNotLoading;
    }

    public void setLoading(boolean loading){
        isLoadingMore = loading;
        if(loading){
            listView.addFooterView(footView);
        }else {
            listView.removeFooterView(footView);
            // 重置滑动的坐标
            mDownY = 0;
            mUpY = 0;
        }
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(listView == null){
            if (getChildCount()>0){
                if(getChildAt(0) instanceof ListView){
                    listView = (ListView) getChildAt(0);
                    listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                        @Override
                        public void onScrollStateChanged(AbsListView absListView, int i) {
                            if(canLoadMore()){

                                if (mListener != null) {
                                    // 设置加载状态，让布局显示出来


                                    setLoading(true);
                                    mListener.loadMore();
                                }

                            }
                        }

                        @Override
                        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                            if (firstVisibleItem == 0) {//滚动到顶部


                            } else if ((firstVisibleItem + visibleItemCount) == totalItemCount) {//滚动到底部

                                isBottom = true;

                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            //移动的起点
            case MotionEvent.ACTION_DOWN:
                mDownY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
                //移动的终点
            case MotionEvent.ACTION_UP:
                mUpY = ev.getY();
                break;
        }
            return super.dispatchTouchEvent(ev);
    }

    public void setLoadMoreListener(OnLoadMoreListener listener){
        this.mListener = listener;
    }


}
