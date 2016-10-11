package fmy.qf.com.mancheng.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import fmy.qf.com.mancheng.R;
import fmy.qf.com.mancheng.bean.Search_AllSeekBean;
import fmy.qf.com.mancheng.bean.Search_TagBean;
import fmy.qf.com.mancheng.parse.Parse;
import fmy.qf.com.mancheng.util.HttpUtil;
import fmy.qf.com.mancheng.util.Url;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {


    private View viewRoot;
    private EditText etSearch;
    private Button btnSubmit;
    private ImageView ivShow1;
    private ImageView ivShow2;
    private ImageView ivShow3;
    private TextView tvShow1;
    private TextView tvShow2;
    private TextView tvShow3;
    private TextView newImg;
    private List<Search_AllSeekBean> allSeeData;
    //完成下载
    private final int DOWN_COMPLEDTE = 0;
    //无网络
    private final int NET_NOT_ISCONN = DOWN_COMPLEDTE + 1;
    //解析json错误
    private final int PARSE_JSON_ERROR = NET_NOT_ISCONN + 1;
    //下载错误
    private final int DOWN_ERROR = PARSE_JSON_ERROR + 1;
    //下载的数据是标签
    private final int DOWN_COMPLEDTE_TAG = DOWN_ERROR + 1;

    //大家都在看的分页
    private static final int allBeanLimit = 3;
    //大家都在看的page
    private int allBeanPager = 0;
    //大家都在看的 文字集合
    private List<TextView> tvShowSet;
    //大家都在看的图片集合
    private List<ImageView> ivShowSet;
    //热门搜索标签
    private List<Search_TagBean> tagSet;


    Context myActivity;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //下载完成
                case DOWN_COMPLEDTE:
                    hadnlerComplete();
                    break;
                case DOWN_COMPLEDTE_TAG:
                    hadnlerCompleteTAG();
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
    //热门标签存放集合
    private GridView gvTab;
    //热门标签适配器
    private ArrayAdapter<String> hotTagAdapter;
    //热门标签所有文本集合
    private List<String> hotTagTextSet;

    private void hadnlerCompleteTAG() {

        if (tagSet != null) {
            if (hotTagTextSet == null) {
                hotTagTextSet = new ArrayList<String>();
            }
            for (int i = 0; i < tagSet.size(); i++) {
                Search_TagBean search_tagBean = tagSet.get(i);
                if (search_tagBean != null) {
                    hotTagTextSet.add(search_tagBean.getName());
                }
            }
            hotTagAdapter = new ArrayAdapter<String>(myActivity, R.layout.search_hottag_item, hotTagTextSet);
            gvTab.setAdapter(hotTagAdapter);
        }

    }


    private void hadnlerComplete() {
        for (int i = 0; i < ivShowSet.size(); i++) {
            Search_AllSeekBean bean = allSeeData.get(i);
            Picasso.with(myActivity).load(bean.getIcon()).into(ivShowSet.get(i));
            tvShowSet.get(i).setText(bean.getName());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_search, container, false);

        initFindView();
        //下载大家都在看数据
        if (allSeeData == null) {
            downLoadAllSeeData(Url.getSeekSearch(allBeanPager++, allBeanLimit), false);
        } else {
            hadnlerComplete();
        }
        //下载标签数据
        if (tagSet == null) {
            downLoadAllSeeData(Url.SOFT_GRIDVIEW, true);
        } else {
            hadnlerCompleteTAG();
        }

        return viewRoot;
    }

    /**
     * flag如果是true下载的热门标签搜索
     *
     * @param path
     * @param
     */
    private void downLoadAllSeeData(final String path, final boolean flag) {

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
                                if (flag) {
                                    tagSet = Parse.parseSearchHotTag(json);
                                    handler.sendEmptyMessage(DOWN_COMPLEDTE_TAG);
                                } else {
                                    allSeeData = Parse.parseSearchAllSeek(json);
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


    /**
     * 初始化找控件
     */
    private void initFindView() {
        myActivity = getActivity();
        gvTab = ((GridView) viewRoot.findViewById(R.id.gv_tab));
        etSearch = ((EditText) viewRoot.findViewById(R.id.et_search));
        btnSubmit = ((Button) viewRoot.findViewById(R.id.btn_submit));
        ivShow1 = ((ImageView) viewRoot.findViewById(R.id.iv_show1));
        ivShow2 = ((ImageView) viewRoot.findViewById(R.id.iv_show2));
        ivShow3 = ((ImageView) viewRoot.findViewById(R.id.iv_show3));
        tvShow1 = ((TextView) viewRoot.findViewById(R.id.tv_show1));
        tvShow2 = ((TextView) viewRoot.findViewById(R.id.tv_show2));
        tvShow3 = ((TextView) viewRoot.findViewById(R.id.tv_show3));
        newImg = ((TextView) viewRoot.findViewById(R.id.tv_newImg));
        ivShowSet = new ArrayList<ImageView>();
        ivShowSet.add(ivShow1);
        ivShowSet.add(ivShow2);
        ivShowSet.add(ivShow3);
        tvShowSet = new ArrayList<TextView>();
        tvShowSet.add(tvShow1);
        tvShowSet.add(tvShow2);
        tvShowSet.add(tvShow3);
    }

    /**
     * 初始化查找按钮
     */
    public void initBtnListener() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 初始化换一批图片按钮
     */
    public void InitNewImgListener() {
        newImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
