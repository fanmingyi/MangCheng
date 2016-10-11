package fmy.qf.com.mancheng.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.adpter.Home_Ranking;
import fmy.qf.com.mancheng.bean.Home_RandingBean;
import fmy.qf.com.mancheng.parse.Parse;
import fmy.qf.com.mancheng.util.HttpUtil;
import fmy.qf.com.mancheng.util.Url;

/**
 * Created by Administrator on 2016/10/11.
 */
public class TestFragment extends Fragment {

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
            myAdapter.notifyDataSetChanged();
                    break;
                case 1:

                    break;
                default:
            }
        }
    };
    private ArrayList<Home_RandingBean> data;
    private ListView viewById;
    private Home_Ranking myAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.test, container, false);
//
        data = new ArrayList<Home_RandingBean>();
        viewById = (ListView) view.findViewById(R.id.rl_contait);
         myAdapter = new Home_Ranking(getActivity(),data);

        viewById.setAdapter(myAdapter);

        downLoadData(0,20,true);
        return view;
    }

    public void downLoadData(int page, int limit, final boolean flag){
        final String url = Url.getSoftChangeview(page, limit);
        new Thread(){
            @Override
            public void run() {
                //判断有没有网络
                if (HttpUtil.isNetconn(getActivity())){
                    try {
                        String json = HttpUtil.getString(url);
                        try {
                            data.addAll(Parse.parseHomeRanking(json));
                            if (flag){
                                handler.sendEmptyMessage(0);
                                Log.e("fmy","下载完成");
                            }else {

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }


            }
        }.start();
    }
}
