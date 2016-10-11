package fmy.qf.com.mancheng.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.adpter.Home_Ranking;
import fmy.qf.com.mancheng.bean.Home_RandingBean;
import fmy.qf.com.mancheng.customview.RefreshListView;
import fmy.qf.com.mancheng.parse.Parse;
import fmy.qf.com.mancheng.util.HttpUtil;
import fmy.qf.com.mancheng.util.Url;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeRankingFragment extends Fragment {

    //根布局
    private View mConverView;
    //自定义一个具有上拉刷新和下拉加载
    private RefreshListView rlContait;
    //适配器所需的数据
    private List<Home_RandingBean> data;
    //完成下载
    private final int DOWN_COMPLEDTE = 0;
    //无网络
    private final int NET_NOT_ISCONN = DOWN_COMPLEDTE + 1;
    //解析json错误
    private final int PARSE_JSON_ERROR = NET_NOT_ISCONN + 1;
    //下载错误
    private final int DOWN_ERROR = PARSE_JSON_ERROR + 1;
    //分页下载完成
    private final int DOWN_COMPLEDTE_FLAG = DOWN_ERROR + 1;
    private  int page = 0;
    private int limit =10;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_COMPLEDTE_FLAG:
                    rlContait.completeRefresh();
                    Toast.makeText(myActivity, "刷新完成", Toast.LENGTH_SHORT).show();
                //下载完成
                case DOWN_COMPLEDTE:
                   myAdapter.notifyDataSetChanged();
                    break;
                //网络无连接
                case NET_NOT_ISCONN:
                    rlContait.completeRefresh();
                    Toast.makeText(myActivity, "无网络", Toast.LENGTH_SHORT).show();
                    break;
                //解析错误
                case PARSE_JSON_ERROR:
                    rlContait.completeRefresh();
                    Toast.makeText(myActivity, "暂无数据", Toast.LENGTH_SHORT).show();
                    break;
                //下载错误
                case DOWN_ERROR:
                    rlContait.completeRefresh();
                    Toast.makeText(myActivity, "下载错误", Toast.LENGTH_SHORT).show();
                    break;

                default:
            }
        }
    };

    private Activity myActivity;
    private Home_Ranking myAdapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mConverView = inflater.inflate(R.layout.fragment_home_ranking, container, false);
        myActivity = getActivity();
        rlContait = ((RefreshListView) mConverView.findViewById(R.id.rl_contait));

        data = new ArrayList<Home_RandingBean>();
        myAdapter = new Home_Ranking(myActivity,data);
        downLoadData(page++,limit,false);
        rlContait.setAdapter(myAdapter);
        //初始化回调
        initBackCall();
        return mConverView;
    }

    /**
     * 初始化下拉刷新回调
     */
    public void initBackCall(){

        rlContait.setOnRefreshListener(new RefreshListView.OnRefreshListener() {
            @Override
            public void onPullRefresh() {
                downLoadData(page++,limit,true);
            }

            @Override
            public void onLoadingMore() {
                downLoadData(page++,limit,true);
            }
        });
    }

    /**
     * 下载线程
     * limit 分页数
     * flag 是否是分页下载的
     */
    public void downLoadData(int page, int limit, final boolean flag){
        final String url = Url.getSoftChangeview(page, limit);
        new Thread(){
            @Override
            public void run() {
                //判断有没有网络
                if (HttpUtil.isNetconn(myActivity)){
                    try {
                        String json = HttpUtil.getString(url);
                        try {
                            data.addAll(Parse.parseHomeRanking(json));
                            if (flag){
                                handler.sendEmptyMessage(DOWN_COMPLEDTE_FLAG);
                            }else {
                                handler.sendEmptyMessage(DOWN_COMPLEDTE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            handler.sendEmptyMessage(PARSE_JSON_ERROR);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        handler.sendEmptyMessage(DOWN_ERROR);
                    }
                }else{
                    handler.sendEmptyMessage(NET_NOT_ISCONN);
                }


            }
        }.start();
    }
}
