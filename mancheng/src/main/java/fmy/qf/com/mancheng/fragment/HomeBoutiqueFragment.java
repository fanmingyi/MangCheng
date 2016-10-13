package fmy.qf.com.mancheng.fragment;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.adpter.Home_Boutique_JP;
import fmy.qf.com.mancheng.adpter.Home_Boutique_JPBottom;
import fmy.qf.com.mancheng.bean.Home_BoutiqueBean;
import fmy.qf.com.mancheng.parse.Parse;
import fmy.qf.com.mancheng.util.HttpUtil;
import fmy.qf.com.mancheng.util.Url;


public class HomeBoutiqueFragment extends Fragment {
    //本页面的view
    private View mConverView;
    //所有的bean
    private Home_BoutiqueBean bean;
    //viewpager的适配器
    private MyViewPagerAdapter myViewPagerAdapter;
    //存放轮播图的bean
    private List<Home_BoutiqueBean.CarouselFigureBean> carouselSet;
    //存放所有轮播图片的集合
    private List<ImageView> carouseImgSet;
    //推荐界面bean
    private List<Home_BoutiqueBean.BoutiquesBean.BoutiqueBean> recommendDate;
    //推荐界面的适配器
    private Home_Boutique_JP recommendAdpter;
    //今日热点适配器
    private Home_Boutique_JP todayHotAdapter;
    //今日热点的数据源
    private List<Home_BoutiqueBean.RankingsBean.RankingBean> todayHotDate;
    //今日最新的适配器
    private Home_Boutique_JP todayNewsAdapter;
    //今天最新的数据源
    private List<Home_BoutiqueBean.NewsBean.NewBean> todayNewsDate;
    //记录当前是哪个Viewpager
    private int current;
    //记录所有的集合点
    private List<ImageView> dotSet;
    //存放所有dot的布局
    private LinearLayout dotContent;
    //正常状态dot图
    private Bitmap dot_normal;
    //选择状态dot图
    private Bitmap dot_enable;

    //完成下载
    private final int DOWN_COMPLEDTE = 0;
    //无网络
    private final int NET_NOT_ISCONN = DOWN_COMPLEDTE + 1;
    //解析json错误
    private final int PARSE_JSON_ERROR = NET_NOT_ISCONN + 1;
    //下载错误
    private final int DOWN_ERROR = PARSE_JSON_ERROR + 1;
    //自动轮播
    private final int CAROUSEL_AUTO = DOWN_ERROR + 1;

    //判断是否是因为滚动引起观察者
    boolean flagAnimation = false;
    //页面顶部的轮播器
    private ViewPager vpCarousel;
    //静态的上下文
    private static Activity myActivity;

    //根布局
    private LinearLayout llRoot;
    //展示图-推荐
    private LinearLayout includeRecommend;
    //展示图-今天热点
    private LinearLayout includeTodayHot;
    //展示图-今天最新
    private LinearLayout includeTodayNews;
    //推荐的GrideView
    private GridView recommendGridView;
    //今天最新的GrideView
    private GridView todayNewsGridView;
    //今天热点的GrideView
    private GridView todayHotGridView;
    //顶部viewpager灰色条中文字
    private TextView tvTip;
    //顶部viewpager灰色条
    private RelativeLayout rlTip;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //下载完成
                case DOWN_COMPLEDTE:
                    handleComledte();
                    break;
                //网络无连接
                case NET_NOT_ISCONN:
                    Toast.makeText(myActivity, "无网络", Toast.LENGTH_SHORT).show();
                    break;
                //解析错误
                case PARSE_JSON_ERROR:
                    Toast.makeText(myActivity, "暂无数据", Toast.LENGTH_SHORT).show();
                    break;
                //下载错误
                case DOWN_ERROR:
                    Toast.makeText(myActivity, "下载错误", Toast.LENGTH_SHORT).show();
                    break;
                //自动轮播
                case CAROUSEL_AUTO:
                    vpCarousel.setCurrentItem(vpCarousel.getCurrentItem() + 1);
                    handler.sendEmptyMessageDelayed(CAROUSEL_AUTO, 2000);
                    break;

                default:
            }
        }
    };
    private GridView gvBottom;
    private List<Home_BoutiqueBean.ClassificationBean> classificationBean;
    private Home_Boutique_JPBottom gridBottomAdapter;
    private ScrollView scrollView;

    /**
     * 处理下载完成后处理轮播器的逻辑代码
     */
    private void handleComledte() {
        if (bean != null) {
            //拆解数据bean
            resolve();

            if (myViewPagerAdapter == null) {
                myViewPagerAdapter = new MyViewPagerAdapter();
            }
            if (vpCarousel != null) {

                vpCarousel.setAdapter(myViewPagerAdapter);
                int midlle = Integer.MAX_VALUE / 2;
                int size = carouseImgSet.size();

                vpCarousel.setCurrentItem(1000 - 1000 % size, false);
                handler.sendEmptyMessageDelayed(CAROUSEL_AUTO, 2000);
                dotSet = new ArrayList<ImageView>(size);
                dot_normal = BitmapFactory.decodeResource(getResources(), R.mipmap.dot_normal);
                dot_enable = BitmapFactory.decodeResource(getResources(), R.mipmap.dot_enable);

                // 初始化轮播器dot
                for (int i = 0; i < size; i++) {
                    ImageView dot = new ImageView(myActivity);

                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    dot.setImageBitmap(dot_normal);
                    dot.setLayoutParams(params);
                    dotContent.addView(dot);
                    dotSet.add(dot);

                }


                vpCarousel.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                        flagAnimation = true;
                    }

                    @Override
                    public void onPageSelected(int position) {
                        //设置因为viewpager滑动回调观察者
                        flagAnimation = true;

                        //顶部灰色dot 和文字设定
                        int index = position % carouselSet.size();
                        if (dotSet != null && dotSet.size() > 0) {
                            dotSet.get(current).setImageBitmap(dot_normal);
                            current = index;
                            dotSet.get(current).setImageBitmap(dot_enable);

                        }
                        current = index;
                        String introduction = carouselSet.get(index).getName();
                        if (!TextUtils.isEmpty(introduction)) {
                            tvTip.setText(introduction);
                        } else {
                            tvTip.setText("商业推广...");
                        }

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
            }

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    boolean first = false;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mConverView = inflater.inflate(R.layout.fragment_home_boutique, container, false);


        if (myActivity == null) {
            myActivity = getActivity();
        }
        llRoot = ((LinearLayout) mConverView.findViewById(R.id.ll_root));
        vpCarousel = ((ViewPager) mConverView.findViewById(R.id.vp_carousel));

        //三个展示图
        includeRecommend = ((LinearLayout) mConverView.findViewById(R.id.include_recommend));
        recommendGridView = ((GridView) mConverView.findViewById(R.id.gv_show));

        includeTodayHot = ((LinearLayout) mConverView.findViewById(R.id.include_todayHot));
        ((TextView) includeTodayHot.findViewById(R.id.tv_recommend)).setText("今日热点");
        todayHotGridView = ((GridView) includeTodayHot.findViewById(R.id.gv_show));

        includeTodayNews  = ((LinearLayout) mConverView.findViewById(R.id.include_todayNews));
        todayNewsGridView = ((GridView) includeTodayNews.findViewById(R.id.gv_show));
        ((TextView) includeTodayNews.findViewById(R.id.tv_recommend)).setText("今日最新");


        //如果bean是空的需要下载
        if (bean == null) {
            downData();
        }

        //顶部提示条的文字
        tvTip = ((TextView) mConverView.findViewById(R.id.tv_tip));
        //顶部的相对布局
        rlTip = ((RelativeLayout) mConverView.findViewById(R.id.rl_tip));
        dotContent = ((LinearLayout) mConverView.findViewById(R.id.ll_dotContent));

        //底部的
        gvBottom = ((GridView) mConverView.findViewById(R.id.gv_bottom));

        scrollView = ((ScrollView) mConverView.findViewById(R.id.scrollView));



        return mConverView;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * 下载数据
     */
    public void downData() {
        new Thread() {
            @Override
            public void run() {
                //判断有没有网络
                if (HttpUtil.isNetconn(getActivity())) {
                    //有网络开始下载
                    try {
                        String json = HttpUtil.getString(Url.BOUTIQUE_URL);
                        if (!TextUtils.isEmpty(json)) {
                            Home_BoutiqueBean home_boutiqueBean = null;
                            try {
                                home_boutiqueBean = Parse.parseHomeBoutique(json);
                            } catch (Exception e) {
                                e.printStackTrace();
                                handler.sendEmptyMessage(PARSE_JSON_ERROR);
                            }
                            if (home_boutiqueBean != null) {
                                bean = home_boutiqueBean;
                                handler.sendEmptyMessage(DOWN_COMPLEDTE);
                            }
                        }
                    } catch (IOException e) {
                        handler.sendEmptyMessage(DOWN_ERROR);
                        e.printStackTrace();
                    }
                } else {
                    handler.sendEmptyMessage(NET_NOT_ISCONN);
                }
            }
        }.start();
    }

    class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = carouseImgSet.get(position % carouseImgSet.size());
            container.addView(iv);
            return iv;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


    }

    /**
     * 把原始的bean取出其中内部bean 如轮播图bean 并填充数据
     */
    public void resolve() {
        carouselSet = bean.getCarousel_figure();
        carouseImgSet = new ArrayList<ImageView>();
        for (int i = 0; i < carouselSet.size(); i++) {
            ImageView imageView = new ImageView(myActivity);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.anzai));
            Picasso.with(myActivity).load(carouselSet.get(i).getCover()).into(imageView);
            carouseImgSet.add(imageView);
        }
        //中部图片展示
        recommendDate = bean.getBoutiques().getBoutique();
        recommendAdpter = new Home_Boutique_JP(myActivity, recommendDate);
        recommendGridView.setAdapter(recommendAdpter);

        todayHotDate = bean.getRankings().getRanking();
        todayHotAdapter = new Home_Boutique_JP(myActivity, todayHotDate);
        todayHotGridView.setAdapter(todayHotAdapter);

        todayNewsDate = bean.getNews().getNewX();
        todayNewsAdapter = new Home_Boutique_JP(myActivity, todayNewsDate);
        todayNewsGridView.setAdapter(todayNewsAdapter);

        //底部图片数据设置
        classificationBean = bean.getClassification();
        gridBottomAdapter = new Home_Boutique_JPBottom(myActivity, classificationBean);
        gvBottom.setAdapter(gridBottomAdapter);

        //重新测量
     /*   setViewHeightBasedOnChildren(recommendGridView);
        setViewHeightBasedOnChildren(todayHotGridView);
        setViewHeightBasedOnChildren(todayNewsGridView);
//        //底部重新测量高度
        setViewHeightBasedOnChildren(gvBottom);*/
//---
//        setZero();

    }


    /**
     * @param listView 你需要测量的view
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void setViewHeightBasedOnChildren(GridView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        int lineMaxHeight = 0;
        int num = listView.getNumColumns();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            lineMaxHeight = Math.max(listItem.getMeasuredHeight(), lineMaxHeight);

        }
        int row = (int) Math.ceil((double) listAdapter.getCount() / num);
        int spacing = listView.getVerticalSpacing() * row;
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = lineMaxHeight * row + spacing + listView.getPaddingTop() + listView.getPaddingBottom();
        listView.setLayoutParams(params);

    }


    public void setZero() {

        scrollView.smoothScrollTo(0, 0);

    }
}
