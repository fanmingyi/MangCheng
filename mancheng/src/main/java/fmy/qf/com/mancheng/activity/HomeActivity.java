package fmy.qf.com.mancheng.activity;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.animation.SwitchAnimationUtil;
import fmy.qf.com.mancheng.customview.MygifView;
import fmy.qf.com.mancheng.fragment.HomeFragment;
import fmy.qf.com.mancheng.fragment.ManagerFragment;
import fmy.qf.com.mancheng.fragment.NewsFragment;
import fmy.qf.com.mancheng.fragment.SearchFragment;
import pl.droidsonroids.gif.GifDrawable;

public class HomeActivity extends AppCompatActivity {

    //用于记录拖拽机器人偏移点
    float rowX;
    float rowY;
    int windowWidth;//屏幕宽
    int windowHeight;//屏幕高

    //所有的View控件
    private RadioGroup rg;
    private RadioButton rbHome;
    private RadioButton rbManager;
    private RadioButton rbSearch;
    private RadioButton rbNews;
    private FrameLayout flContain;//填充fragment用

    //指代当前是哪个fragment
    private final static int CURRENT_HOME = 0;
    private final static int CURRENT_SERARCH = CURRENT_HOME + 1;
    private final static int CURRENT_NEWS = CURRENT_SERARCH + 1;
    private final static int CURRENT_MANAGER = CURRENT_NEWS + 1;
    int currentFragment = CURRENT_HOME;

    /**
     * 创建四个Fragment 分别为主页 搜索 资讯 管理
     */
    private FragmentManager supportFragmentManager;
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private NewsFragment newsFragment;
    private ManagerFragment managerFragment;
    private List<Fragment> allFragment;

    //判断用户是否按下返回键 当为true时再按下就退出
    boolean blackFlag = false;
    private MygifView gvAnZai;
    private GifDrawable anzhaiPress;
    private GifDrawable anzhaiNomo;
    private SwitchAnimationUtil mSwitchAnimationUtil;
    private TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        initRadioGroup();
        //初始化所有存放集合
        allFragment = new ArrayList<Fragment>(4);
        //初始化第一个fragment 即homefragment
        initFragment();


    }

    //初始化第一个fragment 即homefragment
    private void initFragment() {
        homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_contain, homeFragment).commit();
        //添加到集合中
        allFragment.add(CURRENT_HOME, homeFragment);
    }

    private void initRadioGroup() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                        }
                        chageFragment(homeFragment, CURRENT_HOME);
                        tvTitle.setText("首页");
                        break;
                    case R.id.rb_search:
                        if (searchFragment == null) {
                            searchFragment = new SearchFragment();
                            allFragment.add(CURRENT_SERARCH, searchFragment);
                        }
                        chageFragment(searchFragment, CURRENT_SERARCH);
                        tvTitle.setText("搜索");
                        break;
                    case R.id.rb_news:
                        if (newsFragment == null) {
                            newsFragment = new NewsFragment();
                            allFragment.add(CURRENT_NEWS, newsFragment);
                        }
                        chageFragment(newsFragment, CURRENT_NEWS);
                        tvTitle.setText("资讯");
                        break;
                    case R.id.rb_manager:
                        if (managerFragment == null) {
                            managerFragment = new ManagerFragment();
                            allFragment.add(CURRENT_MANAGER, newsFragment);
                        }
                        chageFragment(managerFragment, CURRENT_MANAGER);
                        tvTitle.setText("管理");
                        break;

                    default:
                }
            }
        });
    }

    /**
     * 需要切换的fragment
     */
    public void chageFragment(Fragment fragment, int currentSate) {


        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        Fragment fragment2 = allFragment.get(currentFragment);
        fragmentTransaction.hide(fragment2);
        if (fragment.isAdded()) {
            Fragment fragment1 = allFragment.get(currentSate);
            fragmentTransaction.show(fragment1);
        } else {

            fragmentTransaction.add(R.id.fl_contain, fragment);
        }
        currentFragment = currentSate;
        fragmentTransaction.commit();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setContentView(R.layout.activity_home);

        rg = ((RadioGroup) findViewById(R.id.rg));
        rbHome = ((RadioButton) findViewById(R.id.rb_home));
        rbManager = ((RadioButton) findViewById(R.id.rb_manager));
        rbSearch = ((RadioButton) findViewById(R.id.rb_search));
        rbNews = ((RadioButton) findViewById(R.id.rb_news));
        supportFragmentManager = getSupportFragmentManager();
        flContain = ((FrameLayout) findViewById(R.id.fl_contain));
        gvAnZai = ((MygifView) findViewById(R.id.gv_anzai));
        tvTitle = ((TextView) findViewById(R.id.tv_title));
        initGvAnZai();
        initWindowData();

        try {
            anzhaiPress = new GifDrawable(getResources(), R.mipmap.anzhuo2);
            anzhaiNomo = new GifDrawable(getResources(), R.mipmap.anzai);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /**
     * 初始化获得屏幕高度和宽度
     */
    private void initWindowData() {
        windowWidth = getResources().getDisplayMetrics().widthPixels;
        windowHeight = getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * 初始化拖拽安卓机器人
     */
    public void initGvAnZai() {

        gvAnZai.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        rowX = event.getRawX();
                        gvAnZai.setImageDrawable(anzhaiPress.getCurrent());
                        rowY = event.getRawY();

                        return true;

                    case MotionEvent.ACTION_MOVE:

                        float dx = event.getRawX() - rowX;

                        float dy = event.getRawY() - rowY;
                        int left = (int) (v.getLeft() + dx);
                        int right = (int) (v.getRight() + dx);
                        int top = (int) (v.getTop() + dy);
                        int bottom = (int) (v.getBottom() + dy);
                        if (left < 0) {
                            left = 0;
                            right = v.getWidth();
                        }
                        if (right > windowWidth) {
                            left = windowWidth - v.getWidth();
                            right = windowWidth;
                        }
                        if (top < 0) {
                            top = 0;
                            bottom = v.getBottom();
                        }
                        if (bottom + getStatusBarHeight() > windowHeight) {
                            bottom = windowHeight - getStatusBarHeight();
                            top = bottom - v.getHeight();
                        }
//                        v.layout(left, top, right, bottom);
                        gvAnZai.moveTiger(left, top, right, bottom);
                        rowX = event.getRawX();
                        rowY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:

                        gvAnZai.setImageDrawable(anzhaiNomo.getCurrent());

                        break;
                    default:
                }
                return true;
            }
        });
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourdid = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourdid > 0) {
            result = getResources().getDimensionPixelSize(resourdid);
        }
        return result;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!blackFlag) {
                blackFlag = true;
                Toast.makeText(HomeActivity.this, "再此按下退出程序", Toast.LENGTH_SHORT).show();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        blackFlag = false;
                    }
                }, 2000);
                return true;
            }
        }

        return super.onKeyDown(keyCode, event);
    }


}
