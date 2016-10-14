package fmy.qf.com.mancheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.adpter.ShowBean;
import fmy.qf.com.mancheng.parse.Parse;
import fmy.qf.com.mancheng.util.HttpUtil;
import fmy.qf.com.mancheng.util.Url;

public class ShowActivity extends AppCompatActivity {

    private ViewPager vpShow;
    private String id;
    private String number;
    //完成下载
    private final int DOWN_COMPLEDTE = 0;
    //无网络
    private final int NET_NOT_ISCONN = DOWN_COMPLEDTE + 1;
    //解析json错误
    private final int PARSE_JSON_ERROR = NET_NOT_ISCONN + 1;
    //下载错误
    private final int DOWN_ERROR = PARSE_JSON_ERROR + 1;

    List<ShowBean> dataSet;
    private Myadapter myadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initData();

    }

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
                    Toast.makeText(ShowActivity.this, "无网络", Toast.LENGTH_SHORT).show();
                    break;
                //解析错误
                case PARSE_JSON_ERROR:
                    Toast.makeText(ShowActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    break;
                //下载错误
                case DOWN_ERROR:
                    Toast.makeText(ShowActivity.this, "下载错误", Toast.LENGTH_SHORT).show();
                    break;
                default:
            }
        }
    };

    private void handleComledte() {
        myadapter.notifyDataSetChanged();
    }

    public void downData() {
        new Thread() {
            @Override
            public void run() {
                //判断有没有网络
                if (HttpUtil.isNetconn(getApplicationContext())) {
                    //有网络开始下载
                    try {
                        String json = HttpUtil.getString(Url.getChapterData(id, number));
                        if (!TextUtils.isEmpty(json)) {
                            try {
                                dataSet = Parse.parseShowBean(json);
                                handler.sendEmptyMessage(DOWN_COMPLEDTE);
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

    private void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        number = intent.getStringExtra("number");
        myadapter = new Myadapter();
        vpShow.setAdapter(myadapter);
        downData();
    }

    private void initView() {
        vpShow = ((ViewPager) findViewById(R.id.vp_show));

    }

    class Myadapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(ShowActivity.this);
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT);
            ShowBean showBean = dataSet.get(position);
            String icon = showBean.getIcon();
            if (!TextUtils.isEmpty(icon)){
                Picasso.with(ShowActivity.this).load(icon).into(iv, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Log.e("TAG","下载失败");
                    }
                });
            }
            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return dataSet == null ? 0 : dataSet.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }
}
