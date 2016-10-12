package fmy.qf.com.mancheng.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.adpter.News_TT;
import fmy.qf.com.mancheng.bean.News_TT_Bean;
import fmy.qf.com.mancheng.customview.NewsRefreshListView;
import fmy.qf.com.mancheng.parse.Parse;
import fmy.qf.com.mancheng.util.HttpUtil;
import fmy.qf.com.mancheng.util.Url;


public class News_zx_Fragment extends Fragment {

    //顶层容器
    private View rootView;
    //存放所有的数据
    private List<News_TT_Bean> allData;
    //轮播器数据
    private List<News_TT_Bean> carouselData;

    //完成下载
    private final int DOWN_COMPLEDTE = 0;
    //无网络
    private final int NET_NOT_ISCONN = DOWN_COMPLEDTE + 1;
    //解析json错误
    private final int PARSE_JSON_ERROR = NET_NOT_ISCONN + 1;
    //下载错误
    private final int DOWN_ERROR = PARSE_JSON_ERROR + 1;
    //分页下载
    private final int DOWN_COMPLEDTE_REFRESH = DOWN_ERROR + 1;


    //listview
    private NewsRefreshListView myListView;
    //头部轮播数
    int carouselNum = 4;
    //ListView的适配器
    private News_TT myAdapter;
    //网页分页数
    int index = 1;
    //上下文
    Activity myActivity;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //下载完成
                case DOWN_COMPLEDTE_REFRESH:
                    myListView.completeRefresh();
                    Toast.makeText(myActivity, "刷新成功", Toast.LENGTH_SHORT).show();
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

                default:
            }
        }
    };


    /**
     * 如果为true为分页下载
     * @param path
     * @param flag
     */
    public void downData(final String path, final boolean flag) {
        new Thread() {
            @Override
            public void run() {
                //判断有没有网络
                if (HttpUtil.isNetconn(getActivity())) {
                    //有网络开始下载
                    try {
                        String json = HttpUtil.getString(path);
                        if (!TextUtils.isEmpty(json)) {
                            try {
                                allData.addAll(Parse.parseNewsTT(json));
                                if (flag){
                                    handler.sendEmptyMessage(DOWN_COMPLEDTE_REFRESH);
                                }else{
                                    handler.sendEmptyMessage(DOWN_COMPLEDTE);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                handler.sendEmptyMessage(PARSE_JSON_ERROR);
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

    private void handleComledte() {

        //把数据赋值给轮播器数据存放容器
        if (carouselData != null && carouselData.size() == 0 && allData.size() > carouselNum) {
            for (int i = 0; i < carouselNum; i++) {
                carouselData.add(allData.get(i));
            }
            myListView.setdata(carouselData);
        }
        myListView.setdata(carouselData);
        myAdapter.notifyDataSetChanged();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_news_zx, container, false);
        myListView = ((NewsRefreshListView) rootView.findViewById(R.id.listView));

        ListAdapter adapter = myListView.getAdapter();
        if (adapter == null) {
            myListView.setAdapter(myAdapter);
            //初始化回调
            initListViewListener();
        }
        handleComledte();
        return rootView;
    }

    private void initListViewListener() {
        myListView.setOnRefreshListener(new NewsRefreshListView.OnRefreshListener() {
            @Override
            public void onPullRefresh() {

                downData( Url.getHeadPagingPath(index++),true);
            }

            @Override
            public void onLoadingMore() {
                downData( Url.getHeadPagingPath(index++),true);
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allData = new ArrayList<>();
        carouselData = new ArrayList<>();
        myActivity = getActivity();
        downData(Url.game_headline_path,false);
        myAdapter = new News_TT(getContext(),allData);

    }


}
