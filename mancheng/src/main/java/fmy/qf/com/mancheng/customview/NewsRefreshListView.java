package fmy.qf.com.mancheng.customview;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.bean.News_TT_Bean;

public class NewsRefreshListView extends ListView implements OnScrollListener {
    //此标志为判断是否计算过viewpager的高度
    boolean flag = false;

    private View headerView;//headerView
    private ImageView iv_arrow;
    private ProgressBar pb_rotate;
    private TextView tv_state, tv_time;
    private View footerView;
    private int footerViewHeight;

    private int headerViewHeight;//headerView高

    private int downY;//按下时y坐标

    private final int PULL_REFRESH = 0;//下拉刷新的状态
    private final int RELEASE_REFRESH = 1;//松开刷新的状态
    private final int REFRESHING = 2;//正在刷新的状态
    private int currentState = PULL_REFRESH;
    private RotateAnimation upAnimation, downAnimation;
    private boolean isLoadingMore = false;//当前是否正在处于加载更多
    private ViewPager vpContain;
    private int viewpagerHieght;

    //轮播器数据
    private List<News_TT_Bean> carouselData = new ArrayList<>();
    //轮播图片集合
    private List<ImageView> carouselImgs = new ArrayList<>();
    //Viewpager的适配器
    private Myadapter myadapter;

    private final  int INFINTE = 0;

    public Handler handler = new Handler(){

        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case INFINTE:
                    vpContain.setCurrentItem(1+vpContain.getCurrentItem());
                    handler.sendEmptyMessageDelayed(INFINTE,2000);
                    break;

                default:
            }
        }
    };

    //把数据放入轮播器
    public void setdata(List<News_TT_Bean> carouselData) {
        this.carouselData.addAll(carouselData);
        for (int i = 0; i < carouselData.size(); i++) {
            ImageView iv = new ImageView(getContext());
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Picasso.with(getContext()).load(carouselData.get(i).getL_picture()).into(iv);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(params);
            carouselImgs.add(iv);
        }
        if (myadapter != null) {
            myadapter.notifyDataSetChanged();
        }else{
            myadapter = new Myadapter();
            vpContain.setAdapter(myadapter);
            myadapter.notifyDataSetChanged();
            vpContain.setCurrentItem(10);
            //开启轮播
            handler.sendEmptyMessageDelayed(INFINTE,2000);
        }
    }



    public NewsRefreshListView(Context context) {
        super(context);
        init();
    }

    public NewsRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setOnScrollListener(this);
        initHeaderView();
        initRotateAnimation();
        initFooterView();
    }




    /**
     * 初始化headerView
     */
    private void initHeaderView() {
        headerView = View.inflate(getContext(), R.layout.layout_header_news, null);
        iv_arrow = (ImageView) headerView.findViewById(R.id.iv_arrow);
        pb_rotate = (ProgressBar) headerView.findViewById(R.id.pb_rotate);
        tv_state = (TextView) headerView.findViewById(R.id.tv_state);
        tv_time = (TextView) headerView.findViewById(R.id.tv_time);
        vpContain = ((ViewPager) headerView.findViewById(R.id.vp_carousel));

        vpContain.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (!flag) {
                    viewpagerHieght = vpContain.getHeight();
                    headerViewHeight -= viewpagerHieght;
                    headerView.setPadding(0, -headerViewHeight, 0, 0);
                }
                flag = true;

            }
        });
        headerView.measure(0, 0);//主动通知系统去测量该view;

        headerViewHeight = headerView.getMeasuredHeight();

        headerViewHeight -= viewpagerHieght;
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        addHeaderView(headerView);


        vpContain.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                if (isLoadingMore) {
                    return false;
                }
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY = (int) ev.getY();
                        headY = (int) headerView.getY();
//                Log.e("TAG","按下得到"+downY);
                        break;
                    case MotionEvent.ACTION_MOVE:

                        headY = (int) headerView.getY();
                        if (currentState == REFRESHING) {
                            break;
                        }

                        int deltaY = (int) (ev.getY() - downY);
//                Log.e("TAG","移动得到"+ev.getY());
                        int paddingTop = -headerViewHeight + deltaY;
                        if (paddingTop > -headerViewHeight && getFirstVisiblePosition() == 0) {
                            headerView.setPadding(0, paddingTop, 0, 0);
//				Log.e("RefreshListView", "paddingTop: "+paddingTop);

                            if (paddingTop >= 0 && currentState == PULL_REFRESH) {
                                //从下拉刷新进入松开刷新状态
                                currentState = RELEASE_REFRESH;
                                refreshHeaderView();
                            } else if (paddingTop < 0 && currentState == RELEASE_REFRESH) {
                                //进入下拉刷新状态
                                currentState = PULL_REFRESH;
                                refreshHeaderView();
                            }
                            return true;//拦截TouchMove，不让listview处理该次move事件,会造成listview无法滑动
                        }


                        break;
                    case MotionEvent.ACTION_UP:
                        if (currentState == PULL_REFRESH) {
                            //隐藏headerView
                            headerView.setPadding(0, -headerViewHeight, 0, 0);
                        } else if (currentState == RELEASE_REFRESH) {
                            headerView.setPadding(0, 0, 0, 0);
                            currentState = REFRESHING;
                            refreshHeaderView();

                            if (listener != null) {
                                listener.onPullRefresh();
                            }
                        }
                        break;
                }

                return false;
            }
        });

    }

    /**
     * 初始化旋转动画
     */
    private void initRotateAnimation() {
        upAnimation = new RotateAnimation(0, -180,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        upAnimation.setDuration(300);
        upAnimation.setFillAfter(true);
        downAnimation = new RotateAnimation(-180, -360,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        downAnimation.setDuration(300);
        downAnimation.setFillAfter(true);
    }

    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.layout_footer, null);
        footerView.measure(0, 0);//主动通知系统去测量该view;
        footerViewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0, -footerViewHeight, 0, 0);
        addFooterView(footerView);

    }
    int headY;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {


        if (isLoadingMore) {
            return super.onTouchEvent(ev);
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downY = (int) ev.getY();
             headY= (int)headerView.getY();

                break;
            case MotionEvent.ACTION_MOVE:
                if (currentState == REFRESHING) {
                    break;
                }

                int deltaY = (int) (ev.getY() - downY);
//                Log.e("TAG","移动得到"+ev.getY());
                int paddingTop = -headerViewHeight + deltaY;
                if (paddingTop > -headerViewHeight && getFirstVisiblePosition() == 0&&headY==0) {

                    headerView.setPadding(0, paddingTop, 0, 0);
//				Log.e("RefreshListView", "paddingTop: "+paddingTop);

                    if (paddingTop >= 0 && currentState == PULL_REFRESH) {
                        //从下拉刷新进入松开刷新状态
                        currentState = RELEASE_REFRESH;
                        refreshHeaderView();
                    } else if (paddingTop < 0 && currentState == RELEASE_REFRESH) {
                        //进入下拉刷新状态
                        currentState = PULL_REFRESH;
                        refreshHeaderView();
                    }
                    return true;//拦截TouchMove，不让listview处理该次move事件,会造成listview无法滑动
                }


                break;
            case MotionEvent.ACTION_UP:
                if (currentState == PULL_REFRESH) {
                    //隐藏headerView
                    headerView.setPadding(0, -headerViewHeight, 0, 0);
                } else if (currentState == RELEASE_REFRESH) {
                    headerView.setPadding(0, 0, 0, 0);
                    currentState = REFRESHING;
                    refreshHeaderView();
                    if (listener != null) {
                        listener.onPullRefresh();
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 根据currentState来更新headerView
     */
    private void refreshHeaderView() {
        switch (currentState) {
            case PULL_REFRESH:
                tv_state.setText("下拉刷新");
                iv_arrow.startAnimation(downAnimation);
                break;
            case RELEASE_REFRESH:
                tv_state.setText("松开刷新");
                iv_arrow.startAnimation(upAnimation);
                break;
            case REFRESHING:
                iv_arrow.clearAnimation();//因为向上的旋转动画有可能没有执行完
                iv_arrow.setVisibility(View.INVISIBLE);
                pb_rotate.setVisibility(View.VISIBLE);
                tv_state.setText("正在刷新...");
                break;
        }
    }

    /**
     * 完成刷新操作，重置状态,在你获取完数据并更新完adater之后，去在UI线程中调用该方法
     */
    public void completeRefresh() {
        if (isLoadingMore) {
            //重置footerView状态
            footerView.setPadding(0, -footerViewHeight, 0, 0);
            isLoadingMore = false;
        } else {
            //重置headerView状态
            headerView.setPadding(0, -headerViewHeight, 0, 0);
            currentState = PULL_REFRESH;
            pb_rotate.setVisibility(View.INVISIBLE);
            iv_arrow.setVisibility(View.VISIBLE);
            tv_state.setText("下拉刷新");
            tv_time.setText("最后刷新：" + getCurrentTime());
        }
    }

    /**
     * 获取当前系统时间，并格式化
     *
     * @return
     */
    private String getCurrentTime() {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    private OnRefreshListener listener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    public interface OnRefreshListener {
        void onPullRefresh();

        void onLoadingMore();
    }

    /**
     * SCROLL_STATE_IDLE:闲置状态，就是手指松开
     * SCROLL_STATE_TOUCH_SCROLL：手指触摸滑动，就是按着来滑动
     * SCROLL_STATE_FLING：快速滑动后松开
     */
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                && getLastVisiblePosition() == (getCount() - 1) && !isLoadingMore) {
            if (currentState == REFRESHING) {
                return;
            }
            isLoadingMore = true;

            footerView.setPadding(0, 0, 0, 0);//显示出footerView
            setSelection(getCount());//让listview最后一条显示出来

            if (listener != null) {
                listener.onLoadingMore();

            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

//
    }

    class Myadapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (carouselImgs==null||carouselImgs.size()==0){
                return 0;
            }else {
                return Integer.MAX_VALUE;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           ImageView iv= carouselImgs.get(position%carouselImgs.size());
            container.addView(iv);
            return carouselImgs.get(position%carouselImgs.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

}
