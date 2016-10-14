package fmy.qf.com.mancheng.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.adpter.Charpter;
import fmy.qf.com.mancheng.bean.CharpterBean;
import fmy.qf.com.mancheng.parse.Parse;
import fmy.qf.com.mancheng.util.HttpUtil;
import fmy.qf.com.mancheng.util.Url;

public class CharpterActivity extends AppCompatActivity {

    private int id;
    //完成下载
    private final int DOWN_COMPLEDTE = 0;
    //无网络
    private final int NET_NOT_ISCONN = DOWN_COMPLEDTE + 1;
    //解析json错误
    private final int PARSE_JSON_ERROR = NET_NOT_ISCONN + 1;
    //下载错误
    private final int DOWN_ERROR = PARSE_JSON_ERROR + 1;

    //数据bean集合
    CharpterBean bean;

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
                    Toast.makeText(CharpterActivity.this, "无网络", Toast.LENGTH_SHORT).show();
                    break;
                //解析错误
                case PARSE_JSON_ERROR:
                    Toast.makeText(CharpterActivity.this, "暂无数据", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                //下载错误
                case DOWN_ERROR:
                    Toast.makeText(CharpterActivity.this, "下载错误", Toast.LENGTH_SHORT).show();
                    break;


                default:
            }
        }
    };
    private ImageView ivShow;
    private TextView tvTitle;
    private TextView tvAuthor;
    private TextView tvState;
    private TextView tvSummary;
    private ListView lvCatalogue;
    //listView适配器 目录列表适配器
    private Charpter charpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charpter);
        //初始化上个界面传来的数据
        initData();
        initView();
    }

    private void initView() {
        ivShow = ((ImageView) findViewById(R.id.iv_show));
        tvTitle = ((TextView) findViewById(R.id.tv_title));
        tvAuthor = ((TextView) findViewById(R.id.tv_author));
        tvState = ((TextView) findViewById(R.id.tv_state));
        tvSummary = ((TextView) findViewById(R.id.tv_suammary));
        lvCatalogue = ((ListView) findViewById(R.id.lv_catalogue));
        charpter = new Charpter(this, bean);
        lvCatalogue.setAdapter(charpter);
        lvCatalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CharpterActivity.this, ShowActivity.class);
                intent.putExtra("id",bean.getId()+"");
                intent.putExtra("number",bean.getChapter().get(position).getNumber()+"");
                CharpterActivity.this.startActivity(intent);
            }
        });
    }

    private void initData() {
        Intent intent = getIntent();
        //获取漫画id
        id = intent.getIntExtra("id", 0);
        downData();
    }


    private void handleComledte() {
        Picasso.with(this).load(bean.getIcon()).into(ivShow, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                Toast.makeText(CharpterActivity.this, "抱歉图片下载失败", Toast.LENGTH_SHORT).show();
            }
        });

        tvTitle.setText(bean.getName());
        tvAuthor.setText(bean.getAuthor());
        tvState.setText(bean.getState());
        tvSummary.setText(bean.getIntroduction());
        charpter.setdata(bean);
        charpter.notifyDataSetChanged();
    }


    /**
     * 下载数据
     */
    public void downData() {
        new Thread() {
            @Override
            public void run() {
                //判断有没有网络
                if (HttpUtil.isNetconn(CharpterActivity.this)) {
                    //有网络开始下载
                    try {
                        String json = HttpUtil.getString(Url.getChapter(id));
                        if (!TextUtils.isEmpty(json)) {
                            try {
                                bean = Parse.parseChapterBean(json);
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

    public void startRead(View view) {
        Intent intent = new Intent(CharpterActivity.this, ShowActivity.class);
        intent.putExtra("id",bean.getId()+"");
        intent.putExtra("number",bean.getChapter().get(0).getNumber()+"");
        CharpterActivity.this.startActivity(intent);
    }
}
