package fmy.qf.com.mancheng.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fmy.qf.com.mancheng.R;


public class NewsFragment extends Fragment {

    //顶层容器view
    private View rootView;
    //一个切换fragment的viewpager
    private ViewPager vpContain;
    //存放所有fragment的集合类
    private List<Fragment> allFragment;
    //第一个头条fragment
    private News_zx_Fragment newsZxFragment;
    //第二个动漫fragment
    private News_dm_Fragment newsDmFragment;
    //第三个图片fragment
    private News_tp_Fragment newsTpFragment;
    //第四个段子fragment
    private News_dz_ragment newsDzRagment;
    //viewpager的适配器
    private Myadapter adapter;
    //四个标签页卡
    private TextView tvZX;
    private TextView tvDM;
    private TextView tvTP;
    private TextView tvDZ;
    //存放所有页卡标题的结合
    private List<TextView> allTitle;
    //页卡底线
    private View underLine;

    //记录当前是哪个fragment
    private  final int  CURRENT_ZX = 0;
    private  final int  CURRENT_DM = CURRENT_ZX+1;
    private  final int  CURRENT_TP = CURRENT_DM+1;
    private  final int  CURRENT_DZ = CURRENT_TP+1;

    //记录位置的标记
    private  int flagCurrent = CURRENT_ZX;
    //此flag为的防止页卡标题底线回滚问题
    int flag = flagCurrent;
    //每个页卡宽度
    private int pagerWidth;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allFragment = new ArrayList<>();
        newsZxFragment = new News_zx_Fragment();
        newsDmFragment = new News_dm_Fragment();
        newsTpFragment = new News_tp_Fragment();
        newsDzRagment = new News_dz_ragment();
        allFragment.add(newsZxFragment);
        allFragment.add(newsDmFragment);
        allFragment.add(newsTpFragment);
        allFragment.add(newsDzRagment);
        adapter = new Myadapter(getChildFragmentManager());
        allTitle = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news, container,false);
       //找出轮播控件
        vpContain = ((ViewPager) rootView.findViewById(R.id.vp_contain));
        //四个页卡标题
        tvZX = ((TextView) rootView.findViewById(R.id.tv_zx));
        tvDM = ((TextView) rootView.findViewById(R.id.tv_dm));
        tvTP = ((TextView) rootView.findViewById(R.id.tv_tp));
        tvDZ = ((TextView) rootView.findViewById(R.id.tv_dz));
        tvZX.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                pagerWidth = tvZX.getWidth();
            }
        });
        //添加到集合中
        allTitle.add(tvZX);
        allTitle.add(tvDM);
        allTitle.add(tvTP);
        allTitle.add(tvDZ);
        //底线
        underLine = rootView.findViewById(R.id.v_underLine);
        //初始化viewpager
        initViewPager();


        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    class Myadapter extends FragmentPagerAdapter {

        public Myadapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return allFragment.get(position);
        }

        @Override
        public int getCount() {
            return allFragment.size();
        }
    }

    /**
     * 初始化viewpager的方法
     */
     private void initViewPager() {
        vpContain.setAdapter(adapter);
        vpContain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //positionOffsetPixels在从左往右一直增加的时候到变化当前显示fragment的时候回变成0
                //防止底线回弹到0处
               if (flag!=position && positionOffsetPixels == 0) {
                   flag = position;
                    return;
                }

                underLine.setX(pagerWidth*position+positionOffsetPixels / allTitle.size());

            }

            @Override
            public void onPageSelected(int position) {

               //得到上一个页卡然后颜色变成黑色
                allTitle.get(flagCurrent).setTextColor(Color.BLACK);
                //切换当前状态
                flagCurrent = position;

                //得到当前页卡切换颜色
                allTitle.get(flagCurrent).setTextColor(Color.GREEN);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
