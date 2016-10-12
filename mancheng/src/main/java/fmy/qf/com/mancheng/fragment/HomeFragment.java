package fmy.qf.com.mancheng.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import fmy.qf.com.mancheng.R;


public class HomeFragment extends Fragment {


    private View rootView;
    private PagerTabStrip ptsTitle;
    private ViewPager vpContain;
    private ArrayList<Fragment> allFragment = new ArrayList<Fragment>();
    private HomeBoutiqueFragment boutiqueFragment;
    private HomeRankingFragment rankingFragment;
    private TextView tvJP;
    private TextView tvPH;
    private View vUnderLine;
    private int currentFragment = JPFRAGMENT;
    private final static int JPFRAGMENT = 0;
    private final static int PHFRAGMENT = JPFRAGMENT + 1;
    public HomeFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boutiqueFragment = new HomeBoutiqueFragment();
        rankingFragment = new HomeRankingFragment();
        allFragment.add(boutiqueFragment);
        allFragment.add(rankingFragment);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        //初始化找到所有View组建
        initFindView();
        //初始化ViewPager
        initViewPager();
        return rootView;
    }



    private void initFindView() {
        vpContain = ((ViewPager) rootView.findViewById(R.id.vp_contain));
        tvJP = ((TextView) rootView.findViewById(R.id.tv_jp));
        tvPH = ((TextView) rootView.findViewById(R.id.tv_ph));
        vUnderLine = rootView.findViewById(R.id.v_underLine);
        initTitleClick();
    }

    /**
     * 初始化两个标题栏的点击事件
     */
    private void initTitleClick() {

        tvJP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragmentChange(JPFRAGMENT);
            }
        });
        tvPH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChange(PHFRAGMENT);
            }
        });
    }

    /**
     * 切换fragment
     */
    private void fragmentChange(int curretState) {
        if (curretState == JPFRAGMENT) {
            vpContain.setCurrentItem(JPFRAGMENT);

            currentFragment = JPFRAGMENT;
        } else {
            vpContain.setCurrentItem(PHFRAGMENT);
            currentFragment = PHFRAGMENT;
        }
    }

    private void initViewPager() {
        vpContain.setAdapter(new MyViewPagerAdapter(getChildFragmentManager()));
        vpContain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //positionOffsetPixels在从左往右一直增加的时候到变化当前显示fragment的时候回变成0
                //防止底线回弹到0处
                if (currentFragment == PHFRAGMENT && positionOffsetPixels == 0) {
                    return;
                }
                //重现改写底线位置
                vUnderLine.setX(positionOffsetPixels / 2);

            }

            @Override
            public void onPageSelected(int position) {
                tvPH.setTextColor(Color.BLACK);
                tvJP.setTextColor(Color.BLACK);
                if (position == JPFRAGMENT) {
                    currentFragment = JPFRAGMENT;
                    tvJP.setTextColor(Color.GREEN);
                } else {
                    currentFragment = PHFRAGMENT;
                    tvPH.setTextColor(Color.GREEN);
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        public MyViewPagerAdapter(FragmentManager fm) {
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


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
